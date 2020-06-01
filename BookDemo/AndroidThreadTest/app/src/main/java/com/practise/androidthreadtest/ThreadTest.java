package com.practise.androidthreadtest;

import android.os.Bundle;
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
                MyThread1 myThread1 = new MyThread1("窗口1");
                MyThread2 myThread2 = new MyThread2("窗口2");
                myThread1.start();
                myThread2.start();
            }
        });
    }

    //第一个Thread子类实现一个窗口卖票速度是1s/张
    private class MyThread1 extends Thread{

        private int ticket = 100;//一个窗口有100张票
        private String name; //窗口名, 也即是线程的名字

        public MyThread1(String name){
            this.name=name;
        }

        //在run方法里复写需要进行的操作:卖票速度是1s/张
        @Override
        public void run(){
            while (ticket>0){
                ticket--;
                System.out.println(name + "卖掉了1张票，剩余票数为:"+ticket);
                //sleep要在while里，不然票先卖完了，再进入sleep
                try {
                    Thread.sleep(1000);//卖票速度是1s一张
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    //第二个Thread子类实现一个窗口卖票速度是3s/张
    private class MyThread2 extends Thread{

        private int ticket = 100;//一个窗口有100张票
        private String name; //窗口名, 也即是线程的名字

        public MyThread2(String name){
            this.name=name;
        }

        //在run方法里复写需要进行的操作:卖票速度是3s/张
        @Override
        public void run(){
            while (ticket>0){
                ticket--;
                System.out.println(name + "卖掉了1张票，剩余票数为:"+ticket);
                try {
                    Thread.sleep(3000);//卖票速度是1s一张
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


    }

}