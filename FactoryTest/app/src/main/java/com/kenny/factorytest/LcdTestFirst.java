package com.kenny.factorytest;
/*
 *
 * File: LcdTestFirst.java
 * Author: 29003
 * Create: 2019/8/3 11:52
 * Changes (from 2019/8/3)
 * -----------------------------------------------------------------
 * 2019/8/3 : Create LcdTestFirst.java (29003);
 * -----------------------------------------------------------------
 * description:LCD测试
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

public class LcdTestFirst extends BaseActivity implements View.OnClickListener{

    String TAG = LcdTestFirst.class.getSimpleName();
    LinearLayout lcdHome;
    LinearLayout lcdTest;
    LinearLayout lcdResult;
    ImageView background_image;
    Button lcdup;
    Button lcddown;
    Message msgred;
    Message msgblue;
    Message msggreen;
    Message msgwhite;
    Message msgresult;

    private static final int HANDLE_RED =1;
    private static final int HANDLE_BLUE =2;
    private static final int HANDLE_GREEN =3;
    private static final int HANDLE_WHITE =4;
    private static final int HANDLE_RESULT =5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lcd_first);

        lcdHome = findViewById(R.id.lcd_home);
        lcdTest = findViewById(R.id.lcd_test);
        lcdResult = findViewById(R.id.lcd_result);
        background_image = findViewById(R.id.background_image);
        lcdup = findViewById(R.id.key_volume_up);
        lcddown = findViewById(R.id.key_volume_down);
        lcdup.setOnClickListener(this);
        lcddown.setOnClickListener(this);
        initmessage();
    }

    /**
     * 初始化信息发送
     */
    public void initmessage(){
        //通过obtain方法获取Message对象使得Message到了重复的利用，
        // 减少了每次获取Message时去申请空间的时间。
        msgred = Message.obtain();
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
        Log.e(TAG,"信息发送：5，切换结果");
    }

    @SuppressLint("HandlerLeak")
    //实例化Handler
    Handler  mHandler = new Handler(){
        @SuppressLint("ResourceAsColor")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //获取信息中的what,根据what执行对应操作
            switch (msg.what){
                case HANDLE_RED:
                    lcdHome.setVisibility(View.GONE);
                    lcdTest.setVisibility(View.VISIBLE);
                    background_image.setBackgroundResource(R.color.colorRed);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    mHandler.sendMessageDelayed(msgblue,3000);
                    break;
                case 2:
                    background_image.setBackgroundResource(R.color.colorBlue);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    mHandler.sendMessageDelayed(msggreen,3000);
                    break;
                case 3:
                    background_image.setBackgroundResource(R.color.colorGreen);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    mHandler.sendMessageDelayed(msgwhite,3000);
                    break;
                case 4:
                    background_image.setBackgroundResource(R.color.colorwhite);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    mHandler.sendMessageDelayed(msgresult,3000);
                    break;
                case 5:
                    saveJson("Lcd","测试成功");
                    lcdTest.setVisibility(View.GONE);
                    lcdResult.setVisibility(View.VISIBLE);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    break;
            }
        }
    };

    @Override
    //按钮点击操作
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.key_volume_up:
                saveJson("LCD测试","成功");
                skip(LcdTestFirst.this,QRCode.class);
                break;
            case R.id.key_volume_down:
                saveJson("LCD测试","失败");
                skip(LcdTestFirst.this,QRCode.class);
                break;
        }
    }
}
