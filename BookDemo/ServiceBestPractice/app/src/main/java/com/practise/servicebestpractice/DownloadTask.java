package com.practise.servicebestpractice;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 29003
 * @description通过继承AsyncTask，在子线程中执行耗时任务（doInBackground）
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
     * 在后台任务开始执行之前被调用，用于进行一些界面上的初始化操作
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 在后台执行具体的下载逻辑
     * doInBackground方法中的所有代码都会在子线程中运行，
     * 应该在这里处理所有耗时任务（不可以进行ui操作，如果需要，调用publishProgress(Progress)方法）
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
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));//根据URL地址解析出下载的文件名（获取最后一个/后面的字符串）
            //获取sd卡下载目录
            String directory = Environment.getExternalStoragePublicDirectory("DownLoad").getPath();///storage/emulated/0/DownLoad
//            String directory = Environment.getDownloadCacheDirectory().getPath();//cache
//            String directory = Environment.getExternalStorageDirectory().getPath();///storage/emulated/0
//            String directory = Environment.getExternalStorageState();//mounted
//            String directory = Environment.getRootDirectory().getPath();///system
//            String directory = Environment.getDataDirectory().getPath();//data
            file = new File(directory+fileName);//拼凑文件名并创建文件对象
            //判断要下载文件是否存在
            if(file.exists()){
                downloadedLength = file.length();//存在的话就读取已下载的字节数，以便后续用断点续传
            }
            long contentLength = getContentLength(downloadUrl); //获取待下载文件总长度
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
                //拿到文件字节流
                is = response.body().byteStream();
                //RandonAccessFile：基于字节访问，能够任意访问文件的任意位置
                savedFile = new RandomAccessFile(file,"rw");
                savedFile.seek(downloadedLength);//跳到已下载字节后，设置文字指针的位置，设置后ras会从当前指针的下一位读取到或写入到文件
                byte[] b = new byte[1024];
                long total = 0;
                int len;
                while ((len = is.read(b)) != -1){
                    if(isCanceled){
                        return TYPE_CANCELED;
                    }else if(isPaused){
                        return TYPE_PAUSED;
                    }else {
                        total += len;
                        savedFile.write(b,0,len);//写入文件
                        //int除以int还是int
                        float progress = ((float)(total+downloadedLength)/contentLength)*100;
                        publishProgress((int)progress);//调用方法通知当前下载进度
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
                if(isCanceled && file != null){
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
     * 后台任务调用publishProgress(Progress)方法后，该方法就会很快被调用，在该方法中可以对UI进行操作。
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
     *
     * 后台任务完成后并通过return语句进行返回时，这个方法就会很快被调用。
     * 返回的数据会作为参数传递到此地方中，可以利用返回的数据来进行一些UI操作
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
