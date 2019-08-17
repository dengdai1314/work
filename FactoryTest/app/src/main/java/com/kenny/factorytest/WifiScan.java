package com.kenny.factorytest;
/*
 *
 * File: WifiScan.java
 * Author: 29003
 * Create: 2019/8/9 11:38
 * Changes (from 2019/8/9)
 * -----------------------------------------------------------------
 * 2019/8/9 : Create WifiScan.java (29003);
 * -----------------------------------------------------------------
 * description:wifi测试
 */
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class WifiScan extends BaseActivity implements View.OnClickListener {

    private WifiManager wifiManager;
    List<ScanResult> list;
    Button wifiup;
    Button wifidown;

    //权限请求码
    private int PERMISSION_REQUEST_CODE = 0;
    //权限组
    //Android6.0后需要动态权限申请，wifi扫描需要获取定位权限
    private String[] NEEDED_PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE};
    boolean mHasPermission = false;

    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_wifi_scan);

        wifiup = findViewById(R.id.key_volume_up);
        wifidown = findViewById(R.id.key_volume_down);
        wifiup.setOnClickListener(this);
        wifidown.setOnClickListener(this);
        //获取系统服务
        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        //检查WiFi权限是否允许
        startCheck();
    }

    /**
     * 检查权限是否打开
     * @return
     */
    private boolean checkPermission() {
        //遍历权限组
        for(String permission:NEEDED_PERMISSIONS){
            if (ContextCompat.checkSelfPermission(WifiScan.this,permission) != PackageManager.PERMISSION_GRANTED) {
                //权限没获取
                return false;
            }
        }
        openWifi();
        return true;
    }

    /**
     * 请求权限
     */
    public void requestPermission(){
        ActivityCompat.requestPermissions(WifiScan.this, NEEDED_PERMISSIONS, PERMISSION_REQUEST_CODE);
    }

    /**
     * 启动检查wifi
     */
    private void startCheck() {
        //检查权限
        mHasPermission = checkPermission();
        //直接初始化数据
        if (mHasPermission) {
            wifiscan();//已获取权限，开始获取wifi信息
        } else {
            requestPermission();//请求权限
        }
    }

    /**
     * wifi扫描，获取wifi列表，设置适配器
     */
    public void  wifiscan(){
        wifiManager = (WifiManager) WifiScan.this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            list = wifiManager.getScanResults();
            ListView listView = (ListView) findViewById(R.id.listView);
            if (list == null) {
                Toast.makeText(this, "wifi未打开！", Toast.LENGTH_LONG).show();
            }else {
                saveJson("wifi","测试成功");
                listView.setAdapter(new WifiAdapter(this,list));
            }
        }
    }

    /**
     *  打开WIFI
     */
    private void openWifi() {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);//设置WiFi状态为打开
            mHasPermission = true;
        }
    }

    /**
     * 设置按钮点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.key_volume_up:
                saveJson("Wifi测试","成功");
                skip(WifiScan.this, Trumpet.class);
                break;
            case R.id.key_volume_down:
                saveJson("Wifi测试","失败");
                skip(WifiScan.this, Trumpet.class);
                break;
        }
    }

    public class WifiAdapter extends BaseAdapter {
        LayoutInflater inflater;
        List<ScanResult> list;

        public WifiAdapter(Context context, List<ScanResult> list) {
            // TODO Auto-generated constructor stub
            this.inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        /**
         * 设置列表内容显示
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view = null;
            view = inflater.inflate(R.layout.item_wifi_list, null);
            ScanResult scanResult = list.get(position);
            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(scanResult.SSID);
            TextView signalStrenth = (TextView) view.findViewById(R.id.signal_strenth);
            signalStrenth.setText("信号强度:   -"+String.valueOf(Math.abs(scanResult.level)));
            return view;
        }
    }

    /**
     * 请求权限回调结果处理
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        boolean hasAllPermission = true;
        if (requestCode == PERMISSION_REQUEST_CODE){
            //遍历回调结果，权限获取成功为0，不成功为-1
            for (int x :grantResults){
                if (x!=PackageManager.PERMISSION_GRANTED){
                    hasAllPermission = false;
                    break;
                }
            }
            //如果同意权限
            if (hasAllPermission) {
                mHasPermission = true;
                if (mHasPermission && wifiManager.isWifiEnabled() == true) {  //如果wifi开关是开 并且 已经获取权限
                    wifiscan();
                }
            } else {  //用户不同意权限
                mHasPermission = false;
                Toast.makeText(WifiScan.this,"wifi权限没提供",Toast.LENGTH_SHORT).show();
            }
        }
    }
}

