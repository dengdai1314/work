package com.kenny.handler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {
    String TAG = MainActivity.class.getSimpleName();
    LinearLayout lcdHome;
    LinearLayout lcdTest;
    LinearLayout lcdResult;
    ImageView pollImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lcdHome = findViewById(R.id.lcd_home);
        lcdTest = findViewById(R.id.lcd_test);
        lcdResult = findViewById(R.id.lcd_result);
        pollImage = findViewById(R.id.poll_image);

        Message msgred = Message.obtain();
        msgred.what = 1;
        msgred.obj = "RED";
        Log.e(TAG,"信息发送：1，切换红色");
        mHandler.sendMessageDelayed(msgred,2000);

        Message msgblue = Message.obtain();
        msgblue.what = 2;
        msgblue.obj = "BLUE";
        Log.e(TAG,"信息发送：2，切换蓝色");
        mHandler.sendMessageDelayed(msgblue,5000);

        Message msggreen = Message.obtain();
        msggreen.what = 3;
        msggreen.obj = "GREEN";
        Log.e(TAG,"信息发送：3，切换绿色");
        mHandler.sendMessageDelayed(msggreen,8000);

        Message msgwhite = Message.obtain();
        msgwhite.what = 4;
        msgwhite.obj = "WHITE";
        Log.e(TAG,"信息发送：4，切换白色");
        mHandler.sendMessageDelayed(msgwhite,11000);

        Message msgresult = Message.obtain();
        msgresult.what = 5;
        msgresult.obj = "RESULT";
        Log.e(TAG,"信息发送：5，切换结果");
        mHandler.sendMessageDelayed(msgresult,13000);

    }

    @SuppressLint("HandlerLeak")
    Handler  mHandler = new Handler(){
        @SuppressLint("ResourceAsColor")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    lcdHome.setVisibility(View.GONE);
                    lcdTest.setVisibility(View.VISIBLE);
                    pollImage.setBackgroundResource(R.color.colorRed);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    break;
                case 2:
                    pollImage.setBackgroundResource(R.color.colorBlue);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    break;
                case 3:
                    pollImage.setBackgroundResource(R.color.colorGreen);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    break;
                case 4:
                    pollImage.setBackgroundResource(R.color.colorwhite);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    break;
                case 5:
                    lcdTest.setVisibility(View.GONE);
                    lcdResult.setVisibility(View.VISIBLE);
                    Log.e(TAG,"msg.obj=="+(String) msg.obj);
                    break;
            }
        }
    };
}
