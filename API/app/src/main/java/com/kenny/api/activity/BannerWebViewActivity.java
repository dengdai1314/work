package com.kenny.api.activity;
/*
 *
 * File: BannerWebViewActivity.java
 * Author: luohuojin
 * Create: 2019/9/12 10:35
 * Changes (from 2019/9/12)
 * -----------------------------------------------------------------
 * 2019/9/12 : Create BannerWebViewActivity.java (29003);
 * -----------------------------------------------------------------
 * description:点击轮播图后跳转网页
 */
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.kenny.api.R;

import androidx.appcompat.app.AppCompatActivity;

public class BannerWebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_web_view);
        Intent intent = getIntent();
        String urls = intent.getStringExtra("url");
        skipurl(urls);
    }

    /**
     * 跳转网页
     * @param url
     */
    public void skipurl(String url){
        LinearLayout webview = findViewById(R.id.mbannerwv);
        AgentWeb magentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) webview, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);
    }
}
