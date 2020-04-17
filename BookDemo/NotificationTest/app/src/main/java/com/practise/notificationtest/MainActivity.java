package com.practise.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import static com.practise.notificationtest.R.drawable.ic_launcher_foreground;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/16 11:42
 * @description 8.2使用通知
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private NotificationManager mManager;
    NotificationCompat.Builder builder;
    Notification notification;
    public static final String sID = "channel_1";
    public static final String sName = "channel_name_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button normalNotice = findViewById(R.id.normal_notice);
        Button intentNotice = findViewById(R.id.intent_notice);
        Button longtextNotice = findViewById(R.id.longtext_notice);
        Button bigpictureNotice = findViewById(R.id.bigpicture_notice);
        normalNotice.setOnClickListener(this);
        intentNotice.setOnClickListener(this);
        longtextNotice.setOnClickListener(this);
        bigpictureNotice.setOnClickListener(this);
        initNotice();
    }

    public void initNotice() {
        if (mManager == null) {
            //创建通知管理器NotificationManager
            mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        //Android O以后，需要为Notification设置channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(sID, sName, NotificationManager.IMPORTANCE_HIGH);
            mManager.createNotificationChannel(channel);
        }
//        mManager.cancel(3);//当点击这个通知的时候，通知会自动取消，id为创建通知时指定的id
        builder = new NotificationCompat.Builder(getApplicationContext(),sID);
    }

    public void showNormalNotice(){
        //使用Builder构造器创建Notification对象
        //NotificationCompat.Builder中channelId要与NotificationChannel中的id要一致，不然出错
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置通知的小图标，只能使用纯alpha图层的图片进行设置
        builder.setContentTitle("test");//指定通知的标题内容
        builder.setContentText("This is a test");//指定通知的标题内容
        builder.setWhen(System.currentTimeMillis());//指定通知被创建的时间，以毫秒为单位
        builder.setAutoCancel(true);//设置自动取消
//        builder.setSound(Uri.fromFile(new File("/system/media/audio/rigtones/ANDROMEDA.ogg")));//通知发出的时候播放一段音频
//        builder.setVibrate(new long[]{0,1000,1000,1000});//需要声明权限VIBRATE//用于设置手机静止和振动的时长，以毫秒为单位，下标为0的值表示手机静止的时长， 下标为1的值表示手机振动的时长，下标为2的值又表示手机静止的时 长，以此类推
//        builder.setLights(Color.GREEN,1000,1000);//设置led闪烁第一个参数用于指定LED灯的颜色，第二个参数用 于指定LED灯亮起的时长，以毫秒为单位，第三个参数用于指定LED灯 暗去的时长，也是以毫秒为单位。
//        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        notification = builder.build();
        //显示通知，第一个参数为id，要保证为每个通知所指定的id都是不同的

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.normal_notice:
                showNormalNotice();
                mManager.notify(1,notification);
                break;
            case R.id.intent_notice:
                Intent intent = new Intent(this,MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
                builder.setContentIntent(pi);
                showNormalNotice();
                mManager.notify(2,notification);
                break;
            case R.id.longtext_notice:
                //显示长文本
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build " +
                                "notifications, send and sync data, and use voice actions. Get the official " +
                        "Android IDE and developer tools to build apps for Android."));
                showNormalNotice();
                mManager.notify(3,notification);
                break;
            case R.id.bigpicture_notice:
                //设置通知重要程度
                //好像高Android版本没啥用
                builder.setPriority(NotificationCompat.PRIORITY_MAX);
                //显示大图
                builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), ic_launcher_foreground)));
                showNormalNotice();
                mManager.notify(4,notification);
                default:break;
        }
    }
}
