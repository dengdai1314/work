package com.demo.servicepractice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/14 14:38
 * @description 服务
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_startlocalservice;
    private Button btn_stoplocalservice;
    private Button btn_bindService;
    private Button btn_unbindService;
    private Button btn_foregroundService;
    private CommunicationService.MyBinder myBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_startlocalservice = findViewById(R.id.startLocalService);
        btn_stoplocalservice = findViewById(R.id.stopLocalService);
        btn_bindService = findViewById(R.id.bindService);
        btn_unbindService = findViewById(R.id.unbindService);
        btn_foregroundService = findViewById(R.id.foregroundService);
        btn_startlocalservice.setOnClickListener(this);
        btn_stoplocalservice.setOnClickListener(this);
        btn_bindService.setOnClickListener(this);
        btn_unbindService.setOnClickListener(this);
        btn_foregroundService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startLocalService:
                //构建启动服务的Intent对象
                Intent startLocal = new Intent(this,LocalService.class);
                //调用startService()方法-传入Intent对象,以此启动服务
                startService(startLocal);
                break;
            case R.id.stopLocalService:
                //构建停止服务的Intent对象
                Intent stopLocal = new Intent(this,LocalService.class);
                //调用stopService()方法-传入Intent对象,以此停止服务
                stopService(stopLocal);
                break;
            case R.id.bindService:
                //构建绑定服务的Intent对象
                Intent bindIntent = new Intent(this,CommunicationService.class);
                //调用bindService()方法,以此绑定服务
                //参数说明
                //第一个参数:Intent对象
                //第二个参数:上面创建的Serviceconnection实例
                //第三个参数:标志位
                //这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service
                //这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.unbindService:
                //调用unbindService()解绑服务
                //参数是上面创建的Serviceconnection实例
                unbindService(connection);
                break;
            case R.id.foregroundService:
                Intent foregroundIntent = new Intent(this,ForegroundService.class);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    startForegroundService(foregroundIntent);
                } else {
                    startService(foregroundIntent);
                }
                default:break;
        }
    }

    //创建ServiceConnection的匿名类
    private ServiceConnection connection = new ServiceConnection() {

        //在Activity与Service解除关联的时候调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //实例化Service的内部类myBinder
            //通过向下转型得到了MyBinder的实例
            myBinder = (CommunicationService.MyBinder) service;
            //在Activity调用Service类的方法
            myBinder.service_connect_Activity();
        }

        //重写onServiceConnected()方法和onServiceDisconnected()方法
        //在Activity与Service建立关联和解除关联的时候调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
