package com.practise.androidthreadtest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/1217:56
 * @description
 */
public class ThreadTest extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button saleTicket;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_test);
        saleTicket = findViewById(R.id.sale_ticket);
        saleTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"卖掉了1张，剩余：");
                MyThread myThread1 = new MyThread("窗口1");
                MyThread myThread2 = new MyThread("窗口2");
                myThread1.start();
                myThread2.start();
            }
        });
    }

    private class MyThread extends Thread{
        private int ticket = 100;
        private String name;
        public MyThread(){}
        public MyThread(String name){
            this.name = name;
        }

        @Override
        public void run() {
            super.run();
            while (ticket > 0) {
                ticket --;
                Log.d(TAG,name+"卖掉了1张，剩余："+ticket);
            }
            try{
                MyThread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}