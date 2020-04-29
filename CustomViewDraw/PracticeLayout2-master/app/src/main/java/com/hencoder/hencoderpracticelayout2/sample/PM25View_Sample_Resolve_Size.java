package com.hencoder.hencoderpracticelayout2.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.hencoder.hencoderpracticelayout2.PM25View;

import static android.view.View.MeasureSpec.EXACTLY;

/**
 * Created by wliu on 25/06/2018.
 */

public class PM25View_Sample_Resolve_Size extends PM25View {
    private static final String TAG = "HenCoder ResolveSize";

    private int count = 0;

    public PM25View_Sample_Resolve_Size(Context context) {
        super(context);
    }

    public PM25View_Sample_Resolve_Size(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PM25View_Sample_Resolve_Size(Context context, AttributeSet attrs, int defStyleAttr) {
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
                Log.w(TAG, "width mode == exactly");
                // w = getMeasuredWidth();
                //
                // 不管设定的是 100dp 还是多少dp 我都给他设定成 1080 像素
                // 这样就会出现扔物线视频当中提到的： 会发生什么？ bug！
                // 但resolveSize可以帮助符合这个用户设定的限制
                w = 1080;
                break;
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.AT_MOST:
                break;
            case EXACTLY:
                Log.w(TAG, "height mode == exactly");
                h = getMeasuredHeight();
                break;
        }

        //
        // 打开resolveSize 则符合用户设定
        w = resolveSize(w, widthMeasureSpec);
        h = resolveSize(h, widthMeasureSpec);

        setMeasuredDimension(w, h);
        // 这句代码调用了
        // 并设定了一些PM25View的参数
        setSizes(w, h);
    }
}
