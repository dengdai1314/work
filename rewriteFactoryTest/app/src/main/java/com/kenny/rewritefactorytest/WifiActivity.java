package com.kenny.rewritefactorytest;
/*
 *
 * File: WifiActivity.java
 * Author: 29003
 * Create: 2019/8/20 11:06
 * Changes (from 2019/8/20)
 * -----------------------------------------------------------------
 * 2019/8/20 : Create WifiActivity.java (29003);
 * -----------------------------------------------------------------
 * description:wifi测试
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class WifiActivity extends BaseActivity implements View.OnClickListener {
    private WifiManager wifiManager;
    List<ScanResult> wifilist;                          //存储wifi扫描结果
    Button wifiup;
    Button wifidown;
    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        wifiup = findViewById(R.id.key_up);
        wifidown = findViewById(R.id.key_down);
        wifiup.setOnClickListener(WifiActivity.this);
        wifidown.setOnClickListener(WifiActivity.this);
        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);    //获取系统服务
        initPermission();                                                           //调用BaseActivity初始化权限
        openWifi();                                                                 //打开WiFi
    }

    /**
     *  打开WIFI
     */
    private void openWifi(){
        if(!wifiManager.isWifiEnabled()){                                           //判断wifi当前是否打开
            wifiManager.setWifiEnabled(true);                                       //打开WiFi，改变wifi状态
        }else{
            wifiScan();
        }
    }

    /**
     * wifi扫描，获取wifi列表，设置适配器
     */
    private void  wifiScan(){
        wifilist = wifiManager.getScanResults();                                    //扫描到的周围热点信息
        ListView WifilistView = (ListView) findViewById(R.id.wifilist);
        if (wifilist == null) {
            Toast.makeText(this, "未扫描到WiFi！", Toast.LENGTH_LONG).show();
        }else {
            saveJson("wifi","测试成功");
            WifilistView.setAdapter(new WifiAdapter(this,wifilist));        //设置适配器
        }
    }

    /**
     * 设置按钮点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.key_up:
                saveJson("Wifi测试","成功");
                skip(WifiActivity.this, TrumpetActivity.class);
                break;
            case R.id.key_down:
                saveJson("Wifi测试","失败");
                skip(WifiActivity.this, TrumpetActivity.class);
                break;
        }
    }
}
