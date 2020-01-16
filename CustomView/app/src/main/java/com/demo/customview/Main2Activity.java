package com.demo.customview;

import android.os.Bundle;

import com.demo.customview.custombase.PageFragment;
;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * 自定义View 绘制基础作业
 */
public class Main2Activity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;
    List<PageModel> pageModels= new ArrayList<>();

    {
        pageModels.add(new PageModel(R.layout.sample_color, "drawColor", R.layout.practice_color));
        pageModels.add(new PageModel(R.layout.sample_circle, "drawCircle", R.layout.practice_circle));
        pageModels.add(new PageModel(R.layout.sample_rect, "drawRect", R.layout.practice_rect));
        pageModels.add(new PageModel(R.layout.sample_point, "drawPoint", R.layout.practice_point));
        pageModels.add(new PageModel(R.layout.sample_oval, "drawOval", R.layout.practice_oval));
        pageModels.add(new PageModel(R.layout.sample_line, "drawLine", R.layout.practice_line));
        pageModels.add(new PageModel(R.layout.sample_round_rect, "drawRoundRect", R.layout.practice_round_rect));
        pageModels.add(new PageModel(R.layout.sample_arc, "drawArc", R.layout.practice_arc));
        pageModels.add(new PageModel(R.layout.sample_path, "drawPath", R.layout.practice_path));
        pageModels.add(new PageModel(R.layout.sample_histogram, "直方图", R.layout.practice_histogram));
        pageModels.add(new PageModel(R.layout.sample_pie_chart,"饼图", R.layout.practice_pie_chart));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                PageModel pageModel = pageModels.get(position);
                return PageFragment.newInstance(pageModel.sampleLayoutRes,pageModel.practiceLayoutRes);
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return pageModels.get(position).titleRes;
            }
        });

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
    }

    private class PageModel {
        @LayoutRes
        int sampleLayoutRes;
        @StringRes
        String titleRes;
        @LayoutRes int practiceLayoutRes;

        PageModel(@LayoutRes int sampleLayoutRes, @StringRes String titleRes, @LayoutRes int practiceLayoutRes) {
            this.sampleLayoutRes = sampleLayoutRes;
            this.titleRes = titleRes;
            this.practiceLayoutRes = practiceLayoutRes;
        }
    }

}
