package com.hencoder.hencoderpracticelayout2.practice;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.hencoder.hencoderpracticelayout2.PM25View;

import static android.view.View.MeasureSpec.EXACTLY;

/**
 * MeasureSpec.EXACTLY 场景
 *
 * layout_width = "200dp"
 * 固定设定为 200dp 的宽度 或 高度，那么 getMeasuredWidth() 就返回了应有的固定宽度
 *
 * 可以关注下面注释中带有 ---- 的部分
 */

public class PM25View_Practice_Exactly_200dp extends PM25View {
    private int count = 0;

    public PM25View_Practice_Exactly_200dp(Context context) {
        super(context);
    }

    public PM25View_Practice_Exactly_200dp(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PM25View_Practice_Exactly_200dp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = 0;
        int h = 0;

        //获取宽度测量模式（Mode）
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //获取宽度测量大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        //通过Mode 和 Size 生成新的SpecMode
        int measureSpec = MeasureSpec.makeMeasureSpec(widthSize,widthMode);

        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.AT_MOST:
                break;
            case EXACTLY:
                // ----
                // 指定 200dp，MeasureSpec.getMode 会返回 EXACTLY
                Log.w(TAG, "width mode == exactly");
                w = getMeasuredWidth();//获取子view的width
                break;
            default:break;
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.AT_MOST:
                break;
            case EXACTLY:
                // ----
                // 指定 200dp，MeasureSpec.getMode 会返回 EXACTLY
                Log.w(TAG, "height mode == exactly");
                h = getMeasuredHeight();
                break;
            default:break;
        }

        //
        // onMeasure 会执行多次，我的测试中他执行了12次
        // 打印log
        Log.w(TAG, ++count + " >>> " + "w =  " + w + ", h == " + h);

        //
        // ----
        // 如果没有 resolveSize，这12次onMeasure过程当中有时候得到的宽度或高度为0
        // 加上 resolveSize 则不会出现这个情况，从第一次到最后一次都能得到固定值
        w = resolveSize(w, widthMeasureSpec);//得到的结果就是符合父view限制的修正之后的尺寸
        h = resolveSize(h, widthMeasureSpec);

        // 强制宽高相等
//        if (h > w) {
//            h = w;
//        }
//        if (w > h) {
//            w = h;
//        }


        // 保存宽高计算结果
        setMeasuredDimension(w, h);
//        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec),getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec));


        //
        // 这句代码调用了
        // 并设定了一些PM25View的参数
        setSizes(w, h);
    }
}
