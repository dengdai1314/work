package com.kenny.rewritefactorytest;
/*
 *
 * File: LcdActivity.java
 * Author: 29003
 * Create: 2019/8/20 10:14
 * Changes (from 2019/8/20)
 * -----------------------------------------------------------------
 * 2019/8/20 : Create LcdActivity.java (29003);
 * -----------------------------------------------------------------
 * description:Lcd测试
 */
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public class LcdActivity extends BaseActivity implements View.OnClickListener {
    private static String TAG = LcdActivity.class.getSimpleName();
    private static final int HANDLE_RED =1;
    private static final int HANDLE_BLUE =2;
    private static final int HANDLE_GREEN =3;
    private static final int HANDLE_WHITE =4;
    private static final int HANDLE_RESULT =5;
    ImageView background_image;
    Button LcdUp;
    Button LcdDown;
    LinearLayout LcdHome;
    LinearLayout LcdTest;
    LinearLayout LcdResult;
    Message msgred;
    Message msgblue;
    Message msggreen;
    Message msgwhite;
    Message msgresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lcd);
        background_image = findViewById(R.id.background_image);
        LcdUp = findViewById(R.id.key_up);
        LcdDown = findViewById(R.id.key_down);
        LcdHome = findViewById(R.id.lcd_home);
        LcdTest = findViewById(R.id.lcd_test);
        LcdResult = findViewById(R.id.lcd_result);
        LcdUp.setOnClickListener(this);
        LcdDown.setOnClickListener(this);
        initPermission();        //调用BaseActivity初始化权限
        initmessage();
    }

    /**
     * 初始化信息发送
     */
    private void initmessage(){
        //通过obtain方法获取Message对象使得Message到了重复的利用，
        //减少了每次获取Message时去申请空间的时间。
        msgred = Message.obtain();
        //设置信息发送内容
        msgred.what = HANDLE_RED;
        msgred.obj = "RED";
        Log.e(TAG,"信息发送：1，切换红色");
        //延迟发送信息
        mHandler.sendMessageDelayed(msgred,2000);

        msgblue = Message.obtain();
        msgblue.what = HANDLE_BLUE;
        msgblue.obj = "BLUE";
        Log.e(TAG,"信息发送：2，切换蓝色");

        msggreen = Message.obtain();
        msggreen.what = HANDLE_GREEN;
        msggreen.obj = "GREEN";
        Log.e(TAG,"信息发送：3，切换绿色");

        msgwhite = Message.obtain();
        msgwhite.what = HANDLE_WHITE;
        msgwhite.obj = "WHITE";
        Log.e(TAG,"信息发送：4，切换白色");

        msgresult = Message.obtain();
        msgresult.what = HANDLE_RESULT;
        msgresult.obj = "RESULT";
        Log.e(TAG,"信息发送：5，切换结果页面");
    }

    /**
     * 消息处理操作
     */
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //获取信息中的what，根据what执行对应操作
            switch (msg.what){
                case HANDLE_RED:
                    LcdHome.setVisibility(View.GONE);
                    LcdTest.setVisibility(View.VISIBLE);
                    background_image.setBackgroundResource(R.color.colorRed);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    mHandler.sendMessageDelayed(msgblue,3000);
                    break;
                case HANDLE_BLUE:
                    background_image.setBackgroundResource(R.color.colorBlue);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    mHandler.sendMessageDelayed(msggreen,3000);
                    break;
                case HANDLE_GREEN:
                    background_image.setBackgroundResource(R.color.colorGreen);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    mHandler.sendMessageDelayed(msgwhite,3000);
                    break;
                case HANDLE_WHITE:
                    background_image.setBackgroundResource(R.color.colorwhite);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    mHandler.sendMessageDelayed(msgresult,3000);
                    break;
                case HANDLE_RESULT:
                    saveJson("Lcd","测试成功");
                    LcdTest.setVisibility(View.GONE);
                    LcdResult.setVisibility(View.VISIBLE);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    break;
            }
        }
    };

    /**
     * 按钮点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.key_up:
                saveJson("LCD测试","成功");
                skip(LcdActivity.this,WifiActivity.class);
                break;
            case R.id.key_down:
                saveJson("LCD测试","失败");
                skip(LcdActivity.this,WifiActivity.class);
                break;
        }
    }
}
