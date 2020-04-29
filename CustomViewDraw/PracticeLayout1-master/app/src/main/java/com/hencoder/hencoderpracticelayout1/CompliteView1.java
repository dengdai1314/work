package com.hencoder.hencoderpracticelayout1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2817:00
 * @description
 */
public class CompliteView1 extends View {
    private static final int DEFAULT_WIDTH = 150;
    private static final int DEFAULT_HEIGHT = 80;
    public CompliteView1(Context context) {
        super(context);
    }

    /**
     * @param widthMeasureSpec 父容器给定的限制
     * @param heightMeasureSpec 父容器给定的限制
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth,measuredHeight;

        /**
         1.DEFAULT_WIDTH 是自身计算的大小，我这里直接设定了一个，和父容器给定的限制一起来决定 控件的大小
         2.当父容器的限定是精确模式时，自身的大小没什么价值了
         3.自身计算的大小一般在 WRAP_CONTENT (父容器给定的限定模式为 AT_MOST) 时有用
         如果不重写 onMeasure 方法，就会出现 wrap_content 失效，采用 View.java 的默认 onMeasure 方法，不能满足实际要求
         上面这三步的处理都是在 resolveSize()函数中来处理的！
         */
        measuredWidth = resolveSize(DEFAULT_WIDTH,widthMeasureSpec);
        measuredHeight = resolveSize(DEFAULT_HEIGHT,heightMeasureSpec);
        Log.d("MainActivity","MeasuredWidth | MeasuredHeight : "+measuredWidth+","+measuredHeight);
        setMeasuredDimension(measuredWidth,measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.LTGRAY);
    }
}
