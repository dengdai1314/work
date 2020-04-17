package com.practise.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/1614:44
 * @description
 */
public class NotificationUtil extends ContextWrapper {

    private NotificationManager mManager;
    public static final String sID = "channel_1";
    public static final String sName = "channel_name_1";

    public NotificationUtil(Context context) {
        super(context);
    }

    public void sendNotification(String title,String content,int smallIcon){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel();
        }
        Notification notification = getNotification(title,content,smallIcon).build();
        getmManager().notify(1,notification);
    }

    private NotificationManager getmManager(){
        if (mManager == null){
            mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(sID, sName, NotificationManager.IMPORTANCE_HIGH);
        getmManager().createNotificationChannel(channel);
    }

    public NotificationCompat.Builder getNotification(String title, String content,int smallIcon) {
        return new NotificationCompat.Builder(getApplicationContext(),sID)
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true);
    }
}
