package com.kenny.http;
/*
 *
 * File: MainActivity.java
 * Author: 29003
 * Create: 2019/8/28 14:19
 * Changes (from 2019/8/28)
 * -----------------------------------------------------------------
 * 2019/8/28 : Create MainActivity.java (29003);
 * -----------------------------------------------------------------
 * description:HttpUrlConnection
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    Button send_request;
    TextView response_data;
    public String[] NEEDED_PERMISSIONS = {Manifest.permission.INTERNET};
    boolean mHasPermission = true;                                      //是否请求权限
    boolean hasAllPermission = true;                                    //是否已申请全部权限
    private int PERMISSION_REQUEST_CODE=0;                              //请求权限码
    //    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_request = findViewById(R.id.send_request);
        send_request.setOnClickListener(this);
        response_data = findViewById(R.id.response_data);
        initPermission();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_request:{
                sendRequestWithHttpUrlConnecttion();
                break;
            }
            default:
                break;
        }
    }

    public void sendRequestWithHttpUrlConnecttion(){//开启子线程，在子线程中发出一条http请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url;
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    url = new URL("https://www.baidu.com");//传入目标网络地址
                    connection = (HttpURLConnection) url.openConnection();//访问链接
                    connection.setRequestMethod("GET");//HTTP请求方法
//                    connection.setRequestMethod("post");//post数据
//                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//                    out.writeBytes("username=admin&&password=123456");
                    connection.setConnectTimeout(8000);//设置连接超时的毫秒数
                    connection.setReadTimeout(8000);    //设置读取超时的毫秒数
                    InputStream in = connection.getInputStream();//获取服务器返回的输入流
                    reader = new BufferedReader(new InputStreamReader(in));//对服务器返回的流进行读取
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){//遍历数据
                        response.append(line);//拼接字符串
                    }
                    showResponse(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(reader != null){
                        try{
                            reader.close();//代码从上而下执行
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(connection != null){
                        connection.disconnect();//关闭HTTP连接
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {//Android不允许在子线程中进行UI操作，通过该方法切换到主线程，在主线程更新UI元素
            @Override
            public void run() {
                response_data.setText(response);
            }
        });
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
        for(String permission : NEEDED_PERMISSIONS){                             //遍历权限组，查看当前权限是否已赋予
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

    /**
     * 请求权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE){
            for (int x:grantResults){                       //grantResults 请求权限结果
                if (x!=PackageManager.PERMISSION_GRANTED){  //PERMISSION_GRANTED=0,代表该权限已申请
                    hasAllPermission = false;
                }
            }
            if(hasAllPermission){
                mHasPermission = true;
            }else {
                mHasPermission = false;
                Log.e(MainActivity.class.getSimpleName(),"拒绝权限，将影响功能实现");
            }
        }
    }
}
