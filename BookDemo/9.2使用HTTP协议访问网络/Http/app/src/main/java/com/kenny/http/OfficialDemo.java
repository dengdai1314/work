package com.kenny.http;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OfficialDemo extends AppCompatActivity implements View.OnClickListener {
    Button get;
    Button post;
    OkHttpClient okHttpClient;
    Response response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official_demo);
        get = findViewById(R.id.get);
        post = findViewById(R.id.post);
        get.setOnClickListener(this);
        post.setOnClickListener(this);
        okHttpClient = new OkHttpClient();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.get:
                getUrl();
                break;
            case R.id.post:
                postUrl();
                break;
                default:break;
        }
    }
    public void getUrl(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Request request = new Request.Builder()
                            .url("https://raw.github.com/square/okhttp/master/README.md")
                            .build();
                    response = okHttpClient.newCall(request).execute();
                    Log.e("GET:  ",response.body().toString());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void postUrl(){}
}
