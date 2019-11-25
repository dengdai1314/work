package com.kenny.base;
/*
 *
 * File: WebViewActivity.java
 * Author: luohuojin
 * Create: 2019/9/12 10:35
 * Changes (from 2019/9/12)
 * -----------------------------------------------------------------
 * 2019/9/12 : Create WebViewActivity.java (29003);
 * -----------------------------------------------------------------
 * description:点击轮播图后跳转网页
 */

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.kenny.api.R;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {
    AgentWeb mAgentWeb;
    LinearLayout webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webview = findViewById(R.id.ll_wv);
        Intent intent = getIntent();
        String urls = intent.getStringExtra("url");
        skipurl(urls);
    }

    /**
     * 跳转网页
     * @param url
     */
    public void skipurl(String url){
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) webview, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mAgentWeb.getWebCreator().getWebView().canGoBack()) {
            mAgentWeb.back();
        }
    }
}
