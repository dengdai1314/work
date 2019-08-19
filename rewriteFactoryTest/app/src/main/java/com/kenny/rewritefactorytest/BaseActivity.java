package com.kenny.rewritefactorytest;
/*
 *
 * File: BaseActivity.java
 * Author: 29003
 * Create: 2019/8/19 14:45
 * Changes (from 2019/8/19)
 * -----------------------------------------------------------------
 * 2019/8/19 : Create BaseActivity.java (29003);
 * -----------------------------------------------------------------
 * description:基类活动，用于其他活动公用方法或数据
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * @author 29003
 * @description
 * @date 2019/8/19
 */
public class BaseActivity extends AppCompatActivity {
    public String[] NEEDED_PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,   //允许程序写入外部存储
            Manifest.permission.RECORD_AUDIO,             //允许程序录制声音通过手机或耳机的麦克
            Manifest.permission.CAMERA,                   //允许程序访问摄像头进行拍照
            Manifest.permission.ACCESS_COARSE_LOCATION,   //允许程序通过WiFi或移动基站的方式获取用户错略的经纬度信息
            Manifest.permission.ACCESS_FINE_LOCATION,     //允许程序通过GPS芯片接收卫星的定位信息
            Manifest.permission.ACCESS_WIFI_STATE,        //允许程序获取当前WiFi接入的状态以及WLAN热点的信息
            Manifest.permission.CHANGE_WIFI_STATE,        //允许程序改变WiFi状态
    };
    Boolean mHasPermission = false;                                     //是否请求权限
    private int PERMISSION_REQUEST_CODE=0;                              //请求权限码
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    /**
     * 初始化权限
     */
    public void initPermission(){
        mHasPermission = checkPermission();
        if(mHasPermission){

        }else {
            requestPermission();
        }
    }

    /**
     * 检查权限
     * @return
     */
    public boolean checkPermission(){
        for(String permission : NEEDED_PERMISSIONS){
            if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 请求权限
     */
    public void requestPermission(){
        ActivityCompat.requestPermissions(this,NEEDED_PERMISSIONS,PERMISSION_REQUEST_CODE);
    }
}
