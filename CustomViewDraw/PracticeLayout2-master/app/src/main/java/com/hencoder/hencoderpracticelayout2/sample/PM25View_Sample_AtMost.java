package com.hencoder.hencoderpracticelayout2.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.hencoder.hencoderpracticelayout2.PM25View;

import static android.view.View.MeasureSpec.EXACTLY;

/**
 * Created by wliu on 25/06/2018.
 */

public class PM25View_Sample_AtMost extends PM25View {
    protected static final String TAG = "HenCoder AtMost";
    private int count = 0;

    public PM25View_Sample_AtMost(Context context) {
        super(context);
    }

    public PM25View_Sample_AtMost(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PM25View_Sample_AtMost(Context context, AttributeSet attrs, int defStyleAttr) {
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
                Log.w(TAG, "width mode == at most");
                w = getMeasuredWidth();
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

        Log.w(TAG, count + " >>> " + "w =  " + w + ", h == " + h);

        w = w > 300 ? w - 200 : w;
        h = h > 300 ? h - 200 : h;

        w = resolveSize(w, widthMeasureSpec);
        h = resolveSize(h, heightMeasureSpec);

        Log.w(TAG, count + " >>> " + "w =  " + w + ", h == " + h);

        if (h > w) {
            h = w;
        }
        if (w > h) {
            w = h;
        }
        setMeasuredDimension(w, h);
        // 这句代码调用了
        // 并设定了一些PM25View的参数, 比如强制设定 宽高相等
        setSizes(w, h);
    }
}
