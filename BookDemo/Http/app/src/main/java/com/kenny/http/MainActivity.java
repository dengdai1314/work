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

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.RequiresApi;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    Button post_request;
    Button get_request;
    TextView response_data;

    //    WebView webView;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        post_request = findViewById(R.id.main_post_request);
        post_request.setOnClickListener(this);
        get_request =findViewById(R.id.get_request);
        get_request.setOnClickListener(this);
        response_data = findViewById(R.id.response_data);
        initPermission();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_post_request:{
                postRequestWithHttpUrlConnecttion();
                break;
            }
            case R.id.get_request:{
                getRequestWithHttpUrlConnecttion();
                break;
            }
            default:
                break;
        }
    }

    public void getRequestWithHttpUrlConnecttion(){//开启子线程，在子线程中发出一条http请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url;
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    url = new URL("https://raw.github.com/square/okhttp/master/README.md");//传入目标网络地址
                    connection = (HttpURLConnection) url.openConnection();//访问链接
                    connection.setRequestMethod("GET");//HTTP请求方法
                    connection.setConnectTimeout(8000);//设置连接超时的毫秒数
                    connection.setReadTimeout(8000);    //设置读取超时的毫秒数
                    InputStream in = connection.getInputStream();//获取服务器返回的输入流
                    reader = new BufferedReader(new InputStreamReader(in));//对服务器返回的流进行读取
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){//遍历数据
                        response.append(line);//拼接字符串
                    }
                    Log.e("MainActivity","GET:   "+response);
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

    public void postRequestWithHttpUrlConnecttion(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url;
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    url = new URL("https://www.baidu.com");//传入目标网络地址
                    connection = (HttpURLConnection) url.openConnection();//访问链接
                    connection.setRequestMethod("POST");//post数据
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes("username=admin&&password=123456");
                    connection.setConnectTimeout(8000);//设置连接超时的毫秒数
                    connection.setReadTimeout(8000);    //设置读取超时的毫秒数
                    InputStream post = connection.getInputStream();//获取服务器返回的输入流
                    reader = new BufferedReader(new InputStreamReader(post));//对服务器返回的流进行读取
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){//遍历数据
                        response.append(line);//拼接字符串
                    }
                    Log.e("MainActivity","POST:  "+response);
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

}
