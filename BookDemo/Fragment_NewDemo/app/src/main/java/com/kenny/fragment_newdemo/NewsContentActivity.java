package com.kenny.fragment_newdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NewsContentActivity extends AppCompatActivity {

    public static void actionStart(Context context, String newsTitle, String newsContent){
        Intent intent = new Intent(context,NewsContentActivity.class);
        //存储数据
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");
        //获取碎片实例并刷新碎片
        NewsContentFragment newsContentFragment = (NewsContentFragment)
                getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle,newsContent);
    }
}
