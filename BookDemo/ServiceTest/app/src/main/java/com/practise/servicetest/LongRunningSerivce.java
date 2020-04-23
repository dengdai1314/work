package com.practise.servicetest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.annotation.Nullable;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2211:42
 * @description 长时间在后台定时运行的任务
 */
public class LongRunningSerivce extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //在这里执行具体的逻辑操作
            }
        }).start();
        //获取AlarmManager实例
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int anHour = 60*60*1000;//这是一小时的毫秒数
        //定义任务的触发时间
        long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
        Intent i = new Intent(this,LongRunningSerivce.class);
        //指定定时任务的服务为LongRunningService0
        PendingIntent pi = PendingIntent.getService(this,0,i,0);
        //完成设定
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        //安卓4.4以后触发事件变得不准确，如果需要准确，请setExact
        manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent, flags, startId);

        //使用
        //Intent intent = new Intent(context,LongRunningSerivce.class);
        //context.startService(intent);
    }
}
