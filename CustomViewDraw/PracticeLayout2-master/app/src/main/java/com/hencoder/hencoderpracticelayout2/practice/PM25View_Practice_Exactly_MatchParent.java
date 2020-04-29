package com.hencoder.hencoderpracticelayout2.practice;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.hencoder.hencoderpracticelayout2.PM25View;

import static android.view.View.MeasureSpec.EXACTLY;

/**
 * MeasureSpec.EXACTLY 场景
 *
 * layout_width = "MATCH_PARENT"
 * 设定为 match_parent 的宽度 或 高度，那么 getMeasuredWidth() 就返回了应有的固定宽度
 *
 *  可以关注下面注释中带有 ---- 的部分
 */

public class PM25View_Practice_Exactly_MatchParent extends PM25View {
    public PM25View_Practice_Exactly_MatchParent(Context context) {
        super(context);
    }

    public PM25View_Practice_Exactly_MatchParent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PM25View_Practice_Exactly_MatchParent(Context context, AttributeSet attrs, int defStyleAttr) {
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
                break;
            case MeasureSpec.AT_MOST:
                break;
            case EXACTLY:
                // ----
                // 指定 MATCH_PARENT，MeasureSpec.getMode 会返回 EXACTLY
                Log.w(TAG, "width mode == exactly");
                w = getMeasuredWidth();
                break;
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.AT_MOST:
                break;
            case EXACTLY:
                // ----
                // 指定 MATCH_PARENT，MeasureSpec.getMode 会返回 EXACTLY
                Log.w(TAG, "height mode == exactly");
                h = getMeasuredHeight();
                break;
        }

        // 强制宽高相等
        if (h > w) {
            h = w;
        }
        if (w > h) {
            w = h;
        }
        // 保存宽高计算结果
        setMeasuredDimension(w, h);

        // 这句代码调用了
        // 并设定了一些PM25View的参数
        setSizes(w, h);
    }
}
