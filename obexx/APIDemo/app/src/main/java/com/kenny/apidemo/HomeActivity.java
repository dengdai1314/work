package com.kenny.apidemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 玩安卓api接入练手
 */

public class HomeActivity extends BaseActivity implements OnBannerListener {
    private final String TAG = HomeActivity.class.getSimpleName();
    HomeBanner homeBanner;
    OkHttpClient imageclient;
    Banner image_banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        image_banner = findViewById(R.id.image_banner);
        getBanner_image();
    }

    public void getBanner_image() {
        imageclient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://www.wanandroid.com/banner/json")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Response response = imageclient.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.e(TAG,responseData);
                    parseJson(responseData);
                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJson(String jsonData){
        homeBanner = gson.fromJson(jsonData,HomeBanner.class);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                image_banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
                image_banner.setImageLoader(new MyLoader());
                image_banner.setBannerAnimation(Transformer.Default);
                image_banner.setDelayTime(3000);
                image_banner.isAutoPlay(true);
                List<String> imagebanners = new ArrayList<>();
                for (int i=0;i<homeBanner.getData().size();i++){
                    imagebanners.add(homeBanner.getData().get(i).getImagePath());
                }
                image_banner.setImages(imagebanners)
                        .setOnBannerListener(HomeActivity.this)
                        .start();
            }
        });
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this, "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
        String urls;
        urls=homeBanner.getData().get(position).getUrl();
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

    public void actionStart(Context context, String url){
        Intent intent = new Intent(context,BannerWebView.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
}
