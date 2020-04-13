package com.demo.servicepractice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * @author 29003
 * @description
 * @date 2020/4/2
 */
public class ForegroundService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("执行了onCreate()");

        //添加下列代码将后台Service变成前台Service
        String CHANNEL_ID = "com.example.recyclerviewtest.N1";
        String CHANNEL_NAME = "TEST";
        NotificationChannel notificationChannel ;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //构建"点击通知后打开MainActivity"的Intent对象
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);

        //新建notification对象,用于设置显示内容
        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)//请记：这个id和前面notificationChannel的id要一致,不然程序崩溃
                .setContentTitle("前台服务通知的标题")//设置通知的标题
                .setContentText("前台服务通知的内容")//设置通知的内容
                .setSmallIcon(R.mipmap.ic_launcher)//设置通知的图标
                .setWhen(System.currentTimeMillis())
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent).build();//设置点击通知后的操作

        startForeground(1,notification);//让Service变成前台Service,并在系统的状态栏显示出来
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("执行了onStartCommand()");
        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("执行了onDestory()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
