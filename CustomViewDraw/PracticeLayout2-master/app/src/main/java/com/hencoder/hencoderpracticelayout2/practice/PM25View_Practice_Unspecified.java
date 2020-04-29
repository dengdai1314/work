package com.hencoder.hencoderpracticelayout2.practice;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.hencoder.hencoderpracticelayout2.PM25View;

import static android.view.View.MeasureSpec.EXACTLY;

/**
 * MeasureSpec.UNSPECIFIED 场景
 *
 * 典型应用场景为
 * ScrollView 的高度是根据其子View的高度来计算出来的
 * 那么自定义的View的高度 （或者 HorizontalScrollView 来说就是宽度）就是不固定的
 * 就需要自己计算获得，没有限制
 *
 * 可以任意设定，用resolveSize方法不会影响结果
 *
 * 可以关注下面注释中带有 ---- 的部分
 */

public class PM25View_Practice_Unspecified extends PM25View {
    protected static final String TAG = "HenCoder Unspecified";
    private int count = 0;

    public PM25View_Practice_Unspecified(Context context) {
        super(context);
    }

    public PM25View_Practice_Unspecified(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PM25View_Practice_Unspecified(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = 0;
        int h = 0;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED:
                Log.w(TAG, "width mode == unspecified");
                // ----
                // 横向的ScrollView 自定义控件
                // 不限定宽度，可以任意指定值
//                 w = 200;
                 w = 3000;
                break;
            case MeasureSpec.AT_MOST:
                break;
            case EXACTLY:
                break;
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.AT_MOST:
                Log.w(TAG, "height mode == at most");
                h = getMeasuredHeight();
                break;
            case EXACTLY:
                break;
        }

        ++count;

        // ----
        // resolveSize 不会改变上面的计算结果
        w = resolveSize(w, widthMeasureSpec);
        h = resolveSize(h, heightMeasureSpec);

        Log.w(TAG, count + " >>> " + "w =  " + w + ", h == " + h);

        setMeasuredDimension(w, h);

        // 这句代码调用了
        // 并设定了一些PM25View的参数, 比如强制设定 宽高相等
        setSizes(w, h);
    }
}
