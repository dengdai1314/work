package com.test.baserecycleradapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
/*
github搜索BaseRecyclerViewAdapterHelper
优化适配器代码（减少百分之70％代码）
添加点击item点击，长按事件，以及item子控件的点击事件
添加加载动画（一行代码轻松切换5种替代动画）
添加头部，尾部，拖动刷新，上拉加载（感觉又回到ListView时代）
设置自定义的加载更多布局
添加分组（随心定义分组头部）
自定义不同的项目类型（简单配置，无需重新补充方法）
设置空布局（比Listview的setEmptyView还好用！）
添加拖拽item
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
