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
        setContentView(R.layout.activity_post);
        mTextView = findViewById(R.id.show);

        mHandler = new Handler();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("执行了线程1的UI操作");
                    }
                });
            }
        }.start();

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
