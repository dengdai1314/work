package com.practice.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/11 10:28
 * @description 新建匿名Handler内部类完成handler调用
 */
public class AnonymousInnerActivity extends AppCompatActivity {

    private TextView mTextView;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner);

        mTextView = findViewById(R.id.show);

        // 步骤1：在主线程中 通过匿名内部类 创建Handler类对象
        mHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        mTextView.setText("执行了线程1的UI操作");
                        break;
                    case 2:
                        mTextView.setText("执行了线程2的UI操作");
                        break;
                }
            }
        };

        // 采用继承Thread类实现多线程演示
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 步骤3：创建所需的消息对象
                Message msg = Message.obtain();
                msg.what = 1; // 消息标识
                msg.obj = "A"; // 消息内存存放

                // 步骤4：在工作线程中 通过Handler发送消息到消息队列中
                mHandler.sendMessage(msg);
            }
        }.start();
        // 步骤5：开启工作线程（同时启动了Handler）

        // 此处用2个工作线程展示
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 通过sendMessage（）发送
                // a. 定义要发送的消息
                Message msg = Message.obtain();
                msg.what = 2; //消息的标识
                msg.obj = "B"; // 消息的存放
                // b. 通过Handler发送消息到其绑定的消息队列
                mHandler.sendMessage(msg);
            }
        }.start();
    }
}
