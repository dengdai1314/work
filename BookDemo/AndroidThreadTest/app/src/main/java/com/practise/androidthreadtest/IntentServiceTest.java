package com.practise.androidthreadtest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IntentServiceTest extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_service_test);

        context = getApplicationContext();

        Intent i = new Intent();
        i.setAction("com.practise.test");
        i.setPackage("com.practise.androidthreadtest.myIntentService");
        Bundle bundle = new Bundle();
        bundle.putString("taskName","task1");
        i.putExtras(bundle);
        context.startService(i);

        Intent i2 = new Intent();
        i2.setAction("com.practise.test");
        i2.setPackage("com.practise.androidthreadtest.myIntentService");
        Bundle bundle1 = new Bundle();
        bundle1.putString("taskName","task2");
        i2.putExtras(bundle1);
        context.startService(i2);

        context.startService(i);
    }
}
class myIntentService extends IntentService{
    //在构造函数中传入线程名字
    public myIntentService() {
        //调用父类的构造函数
        //参数 = 工作线程的名字
        super("myIntentService");
    }

    /**
     * 复写onHandleIntent()方法
     * 根据 Intent实现 耗时任务 操作
     **/
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // 根据 Intent的不同，进行不同的事务处理
        String taskName = intent.getExtras().getString("taskName");
        switch (taskName) {
            case "task1":
                Log.i("myIntentService", "do task1");
                break;
            case "task2":
                Log.i("myIntentService", "do task2");
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("myIntentSercvice","onCreate");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i("myIntentSercvice","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("myIntentService","onDestroy");
    }
}