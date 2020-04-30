package com.hencoder.hencoderpracticelayout2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/3015:13
 * @description 自定义View
 */
public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = 0;
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        switch (widthmode){
            case MeasureSpec.UNSPECIFIED://如果没有指定大小，就设置为默认大小
                width = getMeasuredWidth();
                break;
            case MeasureSpec.AT_MOST://如果测量模式是最大取值为size,我们将大小取最大值,你也可以取其他值
                break;
            case MeasureSpec.EXACTLY://如果是固定的大小，那就不要去改变它
                break;
            default:break;
        }
        switch (heightmode){
            case MeasureSpec.UNSPECIFIED:
                height = getMeasuredHeight();
                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                break;
            default:break;
        }
        width = resolveSize(width,widthMeasureSpec);
        height = resolveSize(height,heightMeasureSpec);
        if(width>height){
            width = height;
        }else {
            height = width;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int r = getMeasuredWidth() / 2;//也可以是getMeasuredHeight()/2,本例中我们已经将宽高设置相等了
        //圆心的横坐标为当前的View的左边起始位置+半径
        int centerX = getLeft() + r;
        //圆心的纵坐标为当前的View的顶部起始位置+半径
        int centerY = getTop() + r;

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        //开始绘制
        canvas.drawCircle(centerX, centerY, r, paint);
    }
}
