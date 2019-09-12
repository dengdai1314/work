package com.kenny.api.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kenny.api.model.ArticleBean;
import com.kenny.api.model.ImageBannerBean;
import com.kenny.api.LoadMoreWrapper;
import com.kenny.api.R;
import com.kenny.api.adapter.HomeAdapter;
import com.kenny.base.BaseActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
/*
 *
 * File: HomeActivity.java
 * Author: luohuojin
 * Create: 2019/9/12 10:36
 * Changes (from 2019/9/12)
 * -----------------------------------------------------------------
 * 2019/9/12 : Create HomeActivity.java (29003);
 * -----------------------------------------------------------------
 * description:首页
 */
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends BaseActivity implements OnBannerListener {
    private final String TAG = HomeActivity.class.getSimpleName();
    Banner image_banner;
    OkHttpClient imageBannerclient;
    OkHttpClient articleClient;
    ImageBannerBean imageBannerBean;
    ArticleBean articles;
    int articlePage = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        image_banner = findViewById(R.id.image_banner);
        getImage_banner();
        getArticleJson(articlePage);

    }

    /**
     * 获取首页文章json数据
     */
    private void getArticleJson(int page){
        articleClient = new OkHttpClient();
        final Request article_request = new Request.Builder()
                .url("https://www.wanandroid.com/article/list/"+page+"/json")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = articleClient.newCall(article_request).execute();
                    String responseData = response.body().string();
                    parseArticleJson(responseData);
                    Log.e(TAG,responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 解析文章json数据
     * @param jsonData
     */
    private void parseArticleJson(String jsonData){
        articles = gson.fromJson(jsonData, ArticleBean.class);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG+"parseArticleJson",articles.getData().toString());
                List<ArticleBean> articlelist = new ArrayList<>();
                for (int i=0;i<articles.getData().getSize();i++){
                    articlelist.add(articles);
                }
                RecyclerView homesRecyclerView = findViewById(R.id.rc_home);


                LinearLayoutManager homeslayoutManager = new LinearLayoutManager(HomeActivity.this);
                HomeAdapter homeAdapter = new HomeAdapter(articlelist);
                LoadMoreWrapper loadMoreWrapper = new LoadMoreWrapper(homeAdapter);
                homesRecyclerView.setLayoutManager(homeslayoutManager);
                homesRecyclerView.addItemDecoration(new DividerItemDecoration(HomeActivity.this,DividerItemDecoration.VERTICAL));//分割线
                homesRecyclerView.setAdapter(homeAdapter);
            }
        });
    }

    /**
     * 获取首页banner
     */
    private void getImage_banner(){
        imageBannerclient = new OkHttpClient();
        final Request image_request = new Request.Builder()
                .url("https://www.wanandroid.com/banner/json")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = imageBannerclient.newCall(image_request).execute();
                    String responseData = response.body().string();
                    parseImageJsom(responseData);
                    Log.e(TAG,responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 解析轮播图照片数据
     * @param jsonData
     */
    private void parseImageJsom(String jsonData){
        imageBannerBean = gson.fromJson(jsonData, ImageBannerBean.class);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                image_banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
                image_banner.setImageLoader(new MyLoader());
                image_banner.setBannerAnimation(Transformer.Default);
                image_banner.setDelayTime(3000);
                image_banner.isAutoPlay(true);
                List<String> imagebanners = new ArrayList<>();
                for (int i = 0; i< imageBannerBean.getData().size(); i++){
                    imagebanners.add(imageBannerBean.getData().get(i).getImagePath());
                }
                image_banner.setImages(imagebanners)
                        .setOnBannerListener(HomeActivity.this)
                        .start();
            }
        });
    }

    /**
     * 轮播图点击事件
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this, "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
        String urls;
        urls= imageBannerBean.getData().get(position).getUrl();
        actionStart(HomeActivity.this,urls);
    }

    /**
     * 网络加载图片
     * 使用了Glide图片加载框架
     */
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path)
                    .into(imageView);
        }
    }

    /**
     * 点击图片跳转对应网页
     * @param context
     * @param url
     */
    public void actionStart(Context context, String url){
        Intent intent = new Intent(context, BannerWebViewActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
}
