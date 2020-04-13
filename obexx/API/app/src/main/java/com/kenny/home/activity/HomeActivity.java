package com.kenny.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kenny.api.R;
import com.kenny.home.adapter.HomeAdapter;
import com.kenny.home.model.ArticleBean;
import com.kenny.home.model.ImageBannerBean;
import com.kenny.base.BaseActivity;
import com.kenny.base.WebViewActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
/**
 * 玩安卓api接入练手
 */


public class HomeActivity extends BaseActivity implements OnBannerListener {
    private static final String TAG = HomeActivity.class.getSimpleName();
    Banner image_banner;                     //轮播图
    ImageBannerBean imageBannerBean;
    OkHttpClient imageBannerclient;          //轮播图OkHttpClient
    ArticleBean articles;
    OkHttpClient articleClient;              //文章OkHttpClient
    int articlePage = 0;                     //文章页码
    HomeAdapter homeAdapter;                 //RecyclerView适配器
    RecyclerView homesRecyclerView;
    List<ArticleBean.DataBean.DatasBean> articleBeans = new ArrayList<>();
    ImageButton search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        image_banner = findViewById(R.id.image_banner);
        homesRecyclerView = findViewById(R.id.homeItem);

        search = findViewById(R.id.home_search);
        initlist();
        getImage_banner();
        getArticleJson(articlePage);

        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String articleUrl;
                articleUrl = articleBeans.get(position).getLink();
                actionStart(HomeActivity.this,articleUrl);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,HomeSearchActivity.class);
                startActivity(intent);
            }
        });

        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getArticleJson(0);
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                articlePage = ++articlePage;
                getArticleJson(articlePage);
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    public void initlist(){
        LinearLayoutManager homeslayoutManager = new LinearLayoutManager(HomeActivity.this);
//        headerView = LayoutInflater.from(this).inflate(R.layout.activity_home_banner,null,false);
//
//        image_banner = headerView.findViewById(R.id.image_banner);
        homesRecyclerView.setLayoutManager(homeslayoutManager);

        homesRecyclerView.addItemDecoration(new DividerItemDecoration(HomeActivity.this,DividerItemDecoration.VERTICAL));//分割线
        homeAdapter = new HomeAdapter(articleBeans);
        homesRecyclerView.setAdapter(homeAdapter);
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
        Log.e(TAG+"parseArticleJson",articles.getData().toString());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                articleBeans.addAll(articles.getData().getDatas());
                homeAdapter.notifyDataSetChanged();
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
        Log.e(TAG,"你点击了第"+position+"张banner");
        String bannerUrl;
        bannerUrl= imageBannerBean.getData().get(position).getUrl();
        actionStart(HomeActivity.this,bannerUrl);
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
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
}
