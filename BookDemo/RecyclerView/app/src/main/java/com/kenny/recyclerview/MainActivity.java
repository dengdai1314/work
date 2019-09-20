package com.kenny.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity implements OnRefreshListener, OnBannerListener {
    Banner mBanner;
    MyImageLoader mMyImageLoader;
    ArrayList<Integer> imagePath;
    private List<Fruit> fruitList = new ArrayList<>();
    FruitAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();

        initData();
        initView();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RefreshLayout refreshLayout = findViewById(R.id.smartLayout);
        //创建实例，第一个参数用于指定布局的列数，第二个参数指定布局的排列方向
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//调整布局的排列方向（横向）
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //刷新两秒
                refreshlayout.finishRefresh(2000);
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                for (int i=0;i<30;i++){
                    fruitList.add(new Fruit(getRandomLengthName("tedt"),R.drawable.ic_launcher_background));
                }
                adapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore(2000);
            }
        });
    }
    public void initData(){
        imagePath = new ArrayList<>();
        imagePath.add(R.mipmap.ic_launcher);
        imagePath.add(R.mipmap.ic_launcher_round);
    }

    private void initView() {
        mMyImageLoader = new MyImageLoader();
//        mBanner = findViewById(R.id.banner);
        mBanner = (Banner) LayoutInflater.from(this).inflate(R.layout.adapter_main_header,null,false);
        //设置图片加载器
        mBanner.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        mBanner.setImages(imagePath)
                //轮播图的监听
                .setOnBannerListener(this)
                //开始调用的方法，启动轮播图。
                .start();
    }

    /**
     * 轮播图的监听
     *
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this, "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

    private void  initFruits(){
        for(int i=0;i<15;i++) {
            Fruit fruit1 = new Fruit(getRandomLengthName("fruit1"),R.mipmap.ic_launcher);
            fruitList.add(fruit1);
            Fruit fruit2 = new Fruit(getRandomLengthName("fruit2"),R.mipmap.ic_launcher);
            fruitList.add(fruit2);
            Fruit fruit3 = new Fruit(getRandomLengthName("fruit3"),R.mipmap.ic_launcher);
            fruitList.add(fruit3);
        }
    }

    //创造一个1到20间的随机数，然后将参数中传入的字符串重复随机遍
    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }
}
