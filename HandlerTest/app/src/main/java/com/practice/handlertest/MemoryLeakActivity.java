package com.practice.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/1117:56
 * @description 寻找内存泄漏原因
 */
//public class MemoryLeakActivity extends AppCompatActivity {
//    public static final String TAG = "carson：";
//    private Handler showhandler;
//
//    // 主线程创建时便自动创建Looper & 对应的MessageQueue
//    // 之后执行Loop()进入消息循环
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //1. 实例化自定义的Handler类对象->>分析1
//        //注：此处并无指定Looper，故自动绑定当前线程(主线程)的Looper、MessageQueue
//        showhandler = new FHandler();
//
//        // 2. 启动子线程1
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                // a. 定义要发送的消息
//                Message msg = Message.obtain();
//                msg.what = 1;// 消息标识
//                msg.obj = "AA";// 消息存放
//                // b. 传入主线程的Handler & 向其MessageQueue发送消息
//                showhandler.sendMessage(msg);
//            }
//        }.start();
//
//        // 3. 启动子线程2
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                // a. 定义要发送的消息
//                Message msg = Message.obtain();
//                msg.what = 2;// 消息标识
//                msg.obj = "BB";// 消息存放
//                // b. 传入主线程的Handler & 向其MessageQueue发送消息
//                showhandler.sendMessage(msg);
//            }
//        }.start();
//
//    }
//
//    // 分析1：自定义Handler子类
//    class FHandler extends Handler {
//
//        // 通过复写handlerMessage() 从而确定更新UI的操作
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    Log.d(TAG, "收到线程1的消息");
//                    break;
//                case 2:
//                    Log.d(TAG, " 收到线程2的消息");
//                    break;
//
//
//            }
//        }
//    }
//}

/**
 * 方式2：匿名Handler内部类
 */
public class MemoryLeakActivity extends AppCompatActivity {

    public static final String TAG = "carson：";
    private Handler showhandler;

    // 主线程创建时便自动创建Looper & 对应的MessageQueue
    // 之后执行Loop()进入消息循环
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 通过匿名内部类实例化的Handler类对象
        //注：此处并无指定Looper，故自动绑定当前线程(主线程)的Looper、MessageQueue
        showhandler = new  Handler(){
            // 通过复写handlerMessage()从而确定更新UI的操作
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        Log.d(TAG, "收到线程1的消息");
                        break;
                    case 2:
                        Log.d(TAG, " 收到线程2的消息");
                        break;
                    default:break;
                }
            }
        };

        // 2. 启动子线程1
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // a. 定义要发送的消息
                Message msg = Message.obtain();
                msg.what = 1;// 消息标识
                msg.obj = "AA";// 消息存放
                // b. 传入主线程的Handler & 向其MessageQueue发送消息
                showhandler.sendMessage(msg);
            }
        }.start();

        // 3. 启动子线程2
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // a. 定义要发送的消息
                Message msg = Message.obtain();
                msg.what = 2;// 消息标识
                msg.obj = "BB";// 消息存放
                // b. 传入主线程的Handler & 向其MessageQueue发送消息
                showhandler.sendMessage(msg);
            }
        }.start();

    }

}
