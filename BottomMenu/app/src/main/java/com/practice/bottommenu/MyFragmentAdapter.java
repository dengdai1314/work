package com.practice.bottommenu;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/2914:16
 * @description
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> list;

    public MyFragmentAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list=list;
    }//写构造方法，方便赋值调用

    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }//根据Item的位置返回对应位置的Fragment，绑定item和Fragment

    @Override
    public int getCount() {
        return list.size();
    }//设置Item的数量
}
