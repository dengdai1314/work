package com.practice.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PostActivity extends AppCompatActivity {
    private TextView mTextView;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner);
        mTextView = findViewById(R.id.show);

        // 步骤1：在主线程中创建Handler实例
        mHandler = new Handler();

        // 步骤2：在工作线程中 发送消息到消息队列中 & 指定操作UI内容
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                // 通过psot（）发送，需传入1个Runnable对象
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("执行了线程1的UI操作");
                    }
                });
            }
        }.start();
        // 步骤3：开启工作线程（同时启动了Handler）
        // 此处用2个工作线程展示
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("执行了线程2的UI操作");
                    }
                });
            }
        }.start();
    }
}
