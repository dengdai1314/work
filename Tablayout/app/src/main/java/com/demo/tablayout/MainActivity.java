package com.demo.tablayout;

import android.os.Bundle;
import com.demo.tablayout.Fragment.PageFragment;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    ArrayList<Fragment> fragments = new ArrayList<>();
    String[] titles = new  String[]{"最新","热门","我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){

        mTabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        for(int i=0;i<titles.length;i++){
            fragments.add(new PageFragment());
            mTabLayout.addTab(mTabLayout.newTab());
        }

        mTabLayout.setupWithViewPager(viewPager,false);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(pagerAdapter);

        for(int i=0;i<titles.length;i++){
            mTabLayout.getTabAt(i).setText(titles[i]);
        }
    }

}
