package com.demo.tablayout;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author 29003
 * @description
 * @date 2019/12/23
 */
public class FmPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    public FmPagerAdapter(List<Fragment> fragmentList,FragmentManager fm){
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public int getCount() {
        return fragmentList != null && !fragmentList.isEmpty() ? fragmentList.size() : 0;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
}
