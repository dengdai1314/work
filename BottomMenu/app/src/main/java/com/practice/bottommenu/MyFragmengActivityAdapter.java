package com.practice.bottommenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/2917:23
 * @description
 */
public class MyFragmengActivityAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"首页", "发现", "进货单","我的"};

    public MyFragmengActivityAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 1){
            return new FragmentActivity2();
        }else if(position == 2){
            return new FragmentActivity3();
        }else if(position == 3){
            return new FragmentActivity4();
        }
        return new FragmentActivity1();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
