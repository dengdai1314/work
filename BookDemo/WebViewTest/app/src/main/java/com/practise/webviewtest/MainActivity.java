package com.practise.webviewtest;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/15 17:00
 * @description 9.1Webview的用法
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.web_view);
        //getSettings 设置浏览器属性，setJavaScriptEnabled 让webview支持Javascript属性
        webView.getSettings().setJavaScriptEnabled(true);
        //当需要从一个网页跳转到另一个网页时，我们希望网页仍然在当前页面显示，而不是打开浏览器
        webView.setWebViewClient(new WebViewClient());
        //传入网址，展示相应网页内容
        webView.loadUrl("http://www.baidu.com");
    }
}
