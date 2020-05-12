package com.practice.handlertest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/1110:33
 * @description 内存泄漏解决方案：静态内部类+弱引用
 */
public class staticInnerActivity extends AppCompatActivity {
    private static TextView mTextView;
    private Handler mHandler;

    //分析1：自定义Handler子类
    //设置为静态内部类
    private static class Mhandler extends Handler{

        //定义弱引用实例
        private WeakReference<Activity> reference;

        //在构造方法中传入需持有的Activity实例
        Mhandler(Activity activity){
            //使用WeakReference弱引用持有Activity实例
            reference = new WeakReference<Activity>(activity);
        }

        // 通过复写handlerMessage() 从而确定更新UI的操作
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    mTextView.setText("test1");
                    break;
                case 2:
                    mTextView.setText("text2");
                    break;
                default:break;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner);

        mTextView = findViewById(R.id.show);
        mHandler = new Mhandler(this);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = "test";
                mHandler.sendMessage(msg);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = Message.obtain();
                msg.what = 2;
                msg.obj = "test";
                mHandler.sendMessage(msg);
            }
        }.start();
    }
}
