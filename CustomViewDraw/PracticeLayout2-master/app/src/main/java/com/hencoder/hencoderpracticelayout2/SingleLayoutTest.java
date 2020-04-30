package com.hencoder.hencoderpracticelayout2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2916:53
 * @description 重写onMeasure和onLayout https://juejin.im/entry/585df51061ff4b0063f5a0c4
 */
public class SingleLayoutTest extends ViewGroup {
    public SingleLayoutTest(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(getChildCount()>0){
            View childAt = getChildAt(0);
            measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        if(getChildCount()>0){
            View childAt = getChildAt(0);
            childAt.layout(0,0,childAt.getMeasuredWidth(),childAt.getMeasuredHeight());

        }
    }
}
