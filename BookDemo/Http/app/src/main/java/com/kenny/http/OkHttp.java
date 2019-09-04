package com.kenny.http;
/*
 *
 * File: OkHttp.java
 * Author: 29003
 * Create: 2019/8/28 14:14
 * Changes (from 2019/8/28)
 * -----------------------------------------------------------------
 * 2019/8/28 : Create OkHttp.java (29003);
 * -----------------------------------------------------------------
 * description:okhttp
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp extends BaseActivity implements View.OnClickListener {
    private static final String TAG = OkHttp.class.getSimpleName();
    Button get_request;
    Button post_request;
    Button asynchronous_request;
    Button synchronization_request;
    TextView response_data;
    String url;
    OkHttpClient client;
    Request getconnect;
    Request postconnect;
    Call call;
    public String[] NEEDED_PERMISSIONS = {Manifest.permission.INTERNET};
    boolean mHasPermission = true;                                      //是否请求权限
    boolean hasAllPermission = true;                                    //是否已申请全部权限
    private int PERMISSION_REQUEST_CODE=0;                              //请求权限码
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http
        );
        get_request = findViewById(R.id.send_getrequest);
        post_request = findViewById(R.id.send_postrequest);
        asynchronous_request = findViewById(R.id.asynchronous_request);
        synchronization_request = findViewById(R.id.synchronization_request);
        get_request.setOnClickListener(this);
        post_request.setOnClickListener(this);
        asynchronous_request.setOnClickListener(this);
        synchronization_request.setOnClickListener(this);
        response_data = findViewById(R.id.response_data);
        initPermission();
        url= "https://www.baidu.com";
        client = new OkHttpClient();//创建实例
        getconnect = new Request.Builder().url(url).get().build();//get请求
        //post请求
        RequestBody formBody = new FormBody.Builder()
                .add("username","123")
                .add("password","123")
                .build();
//        RequestBody.create(MediaType contenType, File file);
        postconnect = new Request.Builder().url("https://www.baidu.com").post(formBody).build();//发送数据请求
    }

    /**
     * 按钮点击事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_getrequest:{
                getrequest();
                break;
            }
            case R.id.send_postrequest:{
                postrequest();
                break;
            }
            case R.id.asynchronous_request:{
                asynchronousrequest();
                break;
            }
            case R.id.synchronization_request:{
                getrequest();
                break;
            }
            default:
                break;
        }
    }

    /**
     * 同步get请求与get请求一致
     * call.execute()提交同步请求，但这种方式会阻塞调用线程，所以放在子线程中执行，否则可能引起ANR异常
     * Android3.0后不允许在主线程访问网络
     */
    public void getrequest(){
        //http协议是一个无状态，一次性连接的协议，每次连接只处理一个请求，服务器返回本次请求的应答后便立即关闭连接，下次请求在重新建立连接。
        //所以使用http协议，如果需要多次发出请求，要重新构建连接
        call = client.newCall(getconnect);
        //同步GET请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();//发送请求并获取服务器返回的数据
                    String responseData = response.body().string();
                    showResponse(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /**
     * 异步get请求
     * 异步发起的请求会被加入到Dispatcher中的runningAsyncCalls双端队列通过线程池执行
     */
    public void asynchronousrequest(){
        call = client.newCall(getconnect);
        //call.enqueue :提交异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                showResponse("onFailure");
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                showResponse(response.body().string());
            }
        });
    }

    /**
     * post请求
     */
    public void postrequest(){
        call = client.newCall(postconnect);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 显示返回数据
     * @param response
     */
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
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
                if (x!= PackageManager.PERMISSION_GRANTED){  //PERMISSION_GRANTED=0,代表该权限已申请
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
