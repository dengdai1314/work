package com.practise.androidthreadtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RunnableTest extends AppCompatActivity {
    Button saleTicket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runnable_test);
        saleTicket = findViewById(R.id.sale_ticket);
        saleTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MyThread1 myThread1 = new MyThread1();
//                Thread td1 = new Thread(myThread1,"窗口1");
//                td1.start();
//                MyThread2 myThread2 = new MyThread2();
//                Thread td2 = new Thread(myThread2,"窗口2");
//                td2.start();
                MyThread3 myThread3 = new MyThread3();
                Thread td3 = new Thread(myThread3,"窗口3");
                td3.start();
                Thread td4 = new Thread(myThread3,"窗口4");
                td4.start();
            }
        });
    }
}
class MyThread1 implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        while (ticket>0){
            ticket--;
            System.out.println(Thread.currentThread().getName() + "卖掉了1张票，剩余票数为:"+ticket);

            try {
                Thread.sleep(1000);//卖票速度是1s一张
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class MyThread2 implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        while (ticket>0){
            ticket--;
            System.out.println(Thread.currentThread().getName() + "卖掉了1张票，剩余票数为:"+ticket);

            try {
                Thread.sleep(3000);//卖票速度是1s一张
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class MyThread3 implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        while (ticket>0){
            ticket--;
            System.out.println(Thread.currentThread().getName() + "卖掉了1张票，剩余票数为:"+ticket);

            try {
                Thread.sleep(1000);//卖票速度是1s一张
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
