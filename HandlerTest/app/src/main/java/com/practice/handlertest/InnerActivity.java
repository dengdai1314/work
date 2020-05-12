package com.practice.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/815:54
 * @description 通过新建Handler子类（内部类）完成Handler使用
 */
public class InnerActivity extends AppCompatActivity {
    private TextView mTextView;
    private Handler mHandler;

    // 步骤1：自定义Handler子类（继承Handler类） & 复写handleMessage（）方法
    class Mhandler extends Handler{

        // 通过复写handlerMessage() 从而确定更新UI的操作
        @Override
        public void handleMessage(@NonNull Message msg) {
            // 需执行的UI操作
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.d("test3","******");
                    mTextView.setText("执行了线程1的UI操作");
                    break;
                case 2:
                    mTextView.setText("执行了线程2的UI操作");
                    break;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner);
        Log.d("test","******");

        mTextView = findViewById(R.id.show);

        // 步骤2：在主线程中创建Handler实例
        mHandler = new Mhandler();

        new Thread(){
            @Override
            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                // 步骤3：创建所需的消息对象
                Message msg = Message.obtain();
                msg.what = 1; // 消息标识
                msg.obj = "A"; // 消息内存存放

                // 步骤4：在工作线程中 通过Handler发送消息到消息队列中
                // 可通过sendMessage（） / post（）
                // 多线程可采用AsyncTask、继承Thread类、实现Runnable

                mHandler.sendMessageDelayed(msg,3000);//指定多少毫秒(开机到当前的毫秒数+3000毫秒)后发送消息(经验证，该时间为活动onResume时到handleMessage之间的时间)
                Log.d("test2","******");
            }
        }.start();

        new Thread(){
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

    /**
     * 内存泄漏解决方案：当外部类结束生命周期时，清空Handler内消息队列
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //外部类Activity生命周期结束时，同时清空消息队列&结束Handler的生命周期
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test4","******");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test5","******");
    }
}
