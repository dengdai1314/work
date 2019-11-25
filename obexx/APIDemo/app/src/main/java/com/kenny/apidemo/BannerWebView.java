package com.kenny.apidemo;
/*
 *
 * File: BannerWebView.java
 * Author: luohuojin
 * Create: 2019/9/10 15:45
 * Changes (from 2019/9/10)
 * -----------------------------------------------------------------
 * 2019/9/10 : Create BannerWebView.java (29003);
 * -----------------------------------------------------------------
 * description:首页轮播图
 */
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;

import androidx.appcompat.app.AppCompatActivity;

public class BannerWebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_web_view);
        Intent intent = getIntent();
        String urls = intent.getStringExtra("url");
        skipurl(urls);
    }

    public void skipurl(String url){
        LinearLayout view = findViewById(R.id.view);
        AgentWeb magentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) view, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);
    }
}
