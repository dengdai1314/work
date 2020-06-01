package com.practice.bottommenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;
import androidx.viewpager.widget.ViewPager;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/29 16:49
 * @description 底部Tab菜单栏（FragmentTabHost+ViewPager+Fragment）
 */
public class MainActivity extends FragmentActivity implements
        ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    private ViewPager vp;
    public LayoutInflater layoutInflater;
    private FragmentTabHost mTabHost;
    private String textViewArray[] = {"首页","分类"};
    private Class fragmentArray[] = {Fragment1.class,Fragment2.class};
    private List<Fragment> list = new ArrayList<Fragment>();
    private int imageViewArray[] = {R.drawable.tab_home_btn,R.drawable.tab_view_btn};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPage();
    }

    //控件初始化控件
    private void initView(){
        vp = findViewById(R.id.pager);

        /*实现OnPageChangeListener接口,目的是监听Tab选项卡的变化，然后通知ViewPager适配器切换界面*/
        /*简单来说,是为了让ViewPager滑动的时候能够带着底部菜单联动*/
        vp.addOnPageChangeListener(this);//设置页面切换时的监听器
        layoutInflater = LayoutInflater.from(this);//加载布局管理器

        /*实例化FragmentTabHost对象并进行绑定*/
        mTabHost = findViewById(android.R.id.tabhost);//绑定tabhost
        mTabHost.setup(this,getSupportFragmentManager(),R.id.pager);//绑定viewPager

        /*实现setOnTabChangedListener接口,目的是为监听界面切换），然后实现TabHost里面图片文字的选中状态切换*/
        /*简单来说,是为了当点击下面菜单时,上面的ViewPager能滑动到对应的Fragment*/
        mTabHost.setOnTabChangedListener(this);

        int count = textViewArray.length;

        /*新建Tabspec选项卡并设置Tab菜单栏的内容和绑定对应的Fragment*/
        for (int i=0;i<count;i++){
            //给每个Tab按钮设置标签，图标和文字
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(textViewArray[i])
                    .setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中，并绑定Fragment
            mTabHost.addTab(tabSpec,fragmentArray[i],null);
            mTabHost.setTag(i);
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.selector_tab_background);//设置Tab被选中的时候颜色改变
        }
    }

    private void initPage(){
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();

        list.add(fragment1);
        list.add(fragment2);

        //绑定Fragment适配器
        vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
        mTabHost.getTabWidget().setDividerDrawable(null);
    }

    private View getTabItemView(int i){
        //将xml布局转换为view对象
        View view = layoutInflater.inflate(R.layout.tab_content, null);
        //利用view对象，找到布局中的组件,并设置内容，然后返回视图
        ImageView mImageView = (ImageView) view
                .findViewById(R.id.tab_imageview);
        TextView mTextView = (TextView) view.findViewById(R.id.tab_textview);
        mImageView.setBackgroundResource(imageViewArray[i]);
        mTextView.setText(textViewArray[i]);
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {//Tab改变的时候调用
        int position = mTabHost.getCurrentTab();
        vp.setCurrentItem(position);//把选中的Tab的位置赋给适配器，让它控制页面切换
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }//表示在前一个页面滑动到后一个页面的时候，在前一个页面滑动前调用的方法

    @Override
    public void onPageSelected(int position) {//arg0是表示你当前选中的页面位置Postion，这事件是在你页面跳转完毕的时候调用的。
        TabWidget widget = mTabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);//设置View覆盖子类控件而直接获得焦点
        mTabHost.setCurrentTab(position);//根据位置Postion设置当前的Tab
        widget.setDescendantFocusability(oldFocusability);//设置取消分割线
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }//arg0=0表示什么都没做，arg0=1表示正在滑动，arg0=2表示滑动完毕
}
