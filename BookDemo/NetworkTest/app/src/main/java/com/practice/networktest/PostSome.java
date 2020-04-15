package com.practice.networktest;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

import androidx.annotation.RequiresApi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class PostSome extends BaseActivity implements View.OnClickListener{
    private static final String TAG = PostSome.class.getSimpleName();
    Button string;
    Button file;
    Button flow;
    Button form;
    Button request;
    TextView response_data;
    private static final String IMGUR_CLIENT_ID = "...";
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_some);
        initControls();
    }

    /**
     * 初始化控件
     */
    private void initControls(){
        string = findViewById(R.id.post_string);
        file = findViewById(R.id.post_file);
        flow = findViewById(R.id.post_flow);
        form = findViewById(R.id.post_form);
        request = findViewById(R.id.post_request);
        response_data = findViewById(R.id.response_data);
        string.setOnClickListener(this);
        flow.setOnClickListener(this);
        file.setOnClickListener(this);
        form.setOnClickListener(this);
        request.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.post_string:
                poststring();
                break;
            case R.id.post_file:
                postfile();
                break;
            case R.id.post_flow:
                postflow();
                break;
            case R.id.post_form:
                postform();
                break;
            case R.id.post_request:
                postrequest();
                default:
                    break;
        }
    }

    /**
     * 提交string
     */
//    RequestBody requestBody = new RequestBody() {
//        @Nullable
//        @Override
//        public MediaType contentType() {
//            return MediaType.parse("text/x-makedown;charset = utf-8");
//        }
//
//        @Override
//        public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
//            bufferedSink.writeUtf8("I am IronMan");
//        }
//    };
    private void poststring(){
        MediaType mediaType = MediaType.parse("text/x-makedown;charset = utf-8");//返回媒体类型
        String requestBody = "I am IronMan";//构造RequestBody对象，来携带我们提交的数据
        Request request = new Request.Builder() //建立请求（链接等）
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(mediaType,requestBody))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        //newcall发出请求，服务器返回数据后，对数据进行处理
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG,"onFailure"+e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG,response.protocol()+""+response.code()+" "+response.message());
                Headers headers = response.headers();//请求头
                for (int i = 0; i<headers.size();i++){
                    Log.d(TAG,"onHeaders"+headers.name(i)+":"+headers.value(i));
                }
//                showResponse(response.body().string());//使用okhttp时，不能接连调用两次Response的string()方法
                Log.d(TAG,"onResponse"+response.body().string());
            }
        });
    }

    /**
     * 提交文件
     */
    private void postfile(){
        MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        File file = new File("MainActivity.java");
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(mediaType, file))
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });

    }

    /**
     * 提交流
     */
    //与上述写法一致
    RequestBody requestBody = new RequestBody() {
        @Nullable
        @Override
        public MediaType contentType() {
            return MediaType.parse("text/x-markdown; charset=utf-8");
        }

        @Override
        public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
            bufferedSink.writeUtf8("I an IronMan");
        }
    };
    private void postflow(){
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG,"onFailure"+e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG,response.protocol()+""+response.code()+" "+response.message());
                Headers headers = response.headers();
                for (int i = 0; i<headers.size();i++){
                    Log.d(TAG,"onHeaders"+headers.name(i)+":"+headers.value(i));
                }
//                showResponse(response.body().string());//使用okhttp时，不能接连调用两次Response的string()方法
                Log.d(TAG,"onResponse"+response.body().string());
            }
        });

    }

    /**
     * 提交表单
     */
    private void postform(){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("city", "深圳")
                .build();
        Request request = new Request.Builder()
                .url("http://www.weather.com.cn/data/sk/101010100.html" + "")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }
                Log.d(TAG, "onResponse: " + response.body().string());

            }
        });
    }

    /**
     * 提交分块请求
     */
    private void postrequest(){
        OkHttpClient client = new OkHttpClient();
        // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
        MultipartBody body = new MultipartBody.Builder("AaB03x")
                .setType(MultipartBody.FORM)
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"title\""),
                        RequestBody.create(null, "Square Logo"))
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"image\""),
                        RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url("https://api.imgur.com/3/image")
                .post(body)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
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
}
