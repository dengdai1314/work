package com.practice.slideview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2711:28
 * @description 通过 View 的 ScrollBy 和 ScrollTo 方法实现滑动
 * 使用scrollBy()和scrollTo()方法能方便的滑动效果并且不影响内部元素的点击事件，但是只能滑动View的内容，而不能滑动View的本身。
 * 所以调用scrollBy()和scrollTo()方法时，是用Layout去调用的，这一点需要尤其理解清楚。
 */
public class ScrollSlide extends AppCompatActivity {
    LinearLayout mLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollslide);
        mLayout = findViewById(R.id.layout);
        Button scrollTo = findViewById(R.id.btn_scrollto);
        Button scrollBy = findViewById(R.id.btn_sctollby);
        scrollTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test", "点击前getScrollX值：" + mLayout.getScrollX());
                Log.e("test", "点击前getScrollY值：" + mLayout.getScrollY());
                //基于当前位置的相对滑动
                mLayout.scrollTo(-50, -50);
                Log.e("test", "点击后getScrollX值：" + mLayout.getScrollX());
                Log.e("test", "点击后getScrollY值：" + mLayout.getScrollY());
            }
        });
        scrollBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //基于所传递的参数的绝对滑动
                mLayout.scrollBy(-50, -50);
            }
        });
    }
}
