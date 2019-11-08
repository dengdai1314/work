package com.demo.filedownload;


import com.apkfuns.logutils.LogUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

import java.io.File;

/**
 * @author 29003
 * @description
 * @date 2019/11/4
 */
public class SingleFileDownLoad {
    BaseDownloadTask singleTask ;
    public int singleTaskId = 0;
    String apkUrl = "http://cdn.llsapp.com/android/LLS-v4.0-595-20160908-143200.apk";
    String singleFileSaveName = "liulishuo.apk";
    public String mSinglePath = FileDownloadUtils.getDefaultSaveRootPath()+File.separator+"feifei_save"
            +File.separator+singleFileSaveName;
    public String mSaveFolder = FileDownloadUtils.getDefaultSaveRootPath()+File.separator+"feifei_save";

    public void start_single(){

        String url = apkUrl;
        singleTask = FileDownloader.getImpl().create(url)
                //                .setPath(mSinglePath,false)
                .setPath(mSinglePath,true)//设置下载路径
                .setCallbackProgressTimes(300)//设置回调进度时间
                .setMinIntervalUpdateSpeed(400)//设置最小更新速度间隔
                //.setTag()
                .setListener(new FileDownloadSampleListener(){//文件下载监听
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        LogUtils.d("feifei","pending taskId:"+task.getId()+",soFarBytes:"+soFarBytes+",totalBytes:"+totalBytes+",percent:"+soFarBytes*1.0/totalBytes);

                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        LogUtils.d("feifei","progress taskId:"+task.getId()+",soFarBytes:"+soFarBytes+",totalBytes:"+totalBytes+",percent:"+soFarBytes*1.0/totalBytes+",speed:"+task.getSpeed());
                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
                        LogUtils.d("feifei","blockComplete taskId:"+task.getId()+",filePath:"+task.getPath()+",fileName:"+task.getFilename()+",speed:"+task.getSpeed()+",isReuse:"+task.reuse());
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        LogUtils.d("feifei","completed taskId:"+task.getId()+",isReuse:"+task.reuse());
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        LogUtils.d("feifei","paused taskId:"+task.getId()+",soFarBytes:"+soFarBytes+",totalBytes:"+totalBytes+",percent:"+soFarBytes*1.0/totalBytes);
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        LogUtils.d("feifei","error taskId:"+task.getId()+",e:"+e.getLocalizedMessage());
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        LogUtils.d("feifei","warn taskId:"+task.getId());
                    }
                });

        singleTaskId =  singleTask.start();//开始下载

    }


    public void pause_single(){//暂停单个任务

        LogUtils.d("feifei","pause_single task:"+singleTaskId);
        FileDownloader.getImpl().pause(singleTaskId);
    }

    public void delete_single(){//删除单个任务

        //删除单个任务的database记录
        boolean deleteData =  FileDownloader.getImpl().clear(singleTaskId,mSaveFolder);
        File targetFile = new File(mSinglePath);
        boolean delate = false;
        if(targetFile.exists()){
            delate = targetFile.delete();
        }

        LogUtils.d("feifei","delete_single file,deleteDataBase:"+deleteData+",mSinglePath:"+mSinglePath+",delate:"+delate);

        new File(FileDownloadUtils.getTempPath(mSinglePath)).delete();
    }
}
