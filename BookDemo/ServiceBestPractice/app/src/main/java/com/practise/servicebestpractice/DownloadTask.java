package com.practise.servicebestpractice;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 29003
 * @description
 * @date 2020/4/13
 */
public class DownloadTask extends AsyncTask<String,Integer,Integer> {

    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;

    private DownloadListener listener;
    private int lastProgress;
    private boolean isCanceled = false;
    private boolean isPaused = false;

    //通过这个参数将下载的状态回调
    public DownloadTask(DownloadListener listener){
        this.listener = listener;
    }

    /**
     * 在后台执行具体的下载逻辑
     * @param params
     * @return
     */
    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try {
            long downloadedLength = 0;
            String downloadUrl = params[0];//获取下载的URL地址
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));//根据URL地址解析出下载的文件名
            //获取sd卡下载目录
            String directory = Environment.getExternalStoragePublicDirectory("DownLoad").getPath();///storage/emulated/0/DownLoad
//            String directory = Environment.getDownloadCacheDirectory().getPath();//cache
//            String directory = Environment.getExternalStorageDirectory().getPath();///storage/emulated/0
//            String directory = Environment.getExternalStorageState();//mounted
//            String directory = Environment.getRootDirectory().getPath();///system
//            String directory = Environment.getDataDirectory().getPath();//data
            Log.d("directory下载目录",directory);
            file = new File(directory+fileName);
            //判断要下载文件是否存在，存在的话就读取已下载的字节数，以便后续用断点续传
            if(file.exists()){
                downloadedLength = file.length();
            }
            long contentLength = getContentLength(downloadUrl); //获取待下载文件总长度
            Log.d("下载文件长度",contentLength+"");
            //如果待下载文件长度为0，说明该文件有问题
            if(contentLength == 0){
                return TYPE_FAILED;
            }else if(contentLength == downloadedLength){
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            //创建网络请求（在请求添加header,用于告诉服务器我们想要从哪个字节开始下载，因为已下载过的内容不需要重新下载）
            Request request = new Request.Builder()
                    .addHeader("RANGE","bytes="+downloadedLength+"-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();//发送请求并读取服务器返回的数据
            if(response!=null){
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file,"rw");
                savedFile.seek(downloadedLength);//跳过已下载的字节
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1){
                    //没有暂停的判断
                    if(isCancelled()){
                        return TYPE_CANCELED;
                    }else {
                        total += len;
                        savedFile.write(b,0,len);
                        int progress = (int) ((total + downloadedLength) * contentLength);
                        publishProgress(progress);//调用方法通知当前下载进度
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(is != null){
                    is.close();
                }
                if(savedFile != null){
                    savedFile.close();
                }
                if(isCancelled() && file != null){
                    file.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    private long getContentLength(String downloadUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response != null && response.isSuccessful()){
                long contentLength = response.body().contentLength();
                response.body().close();
                return contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 在界面上更新当前的下载进度
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int progress = values[0];//获取当前下载进度
        //和上一次的下载进度进行比对，如果变化则调用DownloadListenen的onProgress()方法通知下载进度更新
        if(progress > lastProgress){
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    /**
     * 通知最终的下载结果
     * 根据参数中传入的下载状态来进行回调
     * @param integer
     */
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        switch (integer){
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                default:break;
        }
    }

    public void pauseDownload(){
        isPaused = true;
    }

    public void cancelDownload(){
        isCanceled = true;
    }
}
