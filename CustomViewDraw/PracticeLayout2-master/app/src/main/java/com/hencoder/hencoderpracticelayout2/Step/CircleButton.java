package com.hencoder.hencoderpracticelayout2.Step;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/3017:27
 * @description
 */
public class CircleButton extends AppCompatButton {
    //画圆
    private Paint paint = new Paint();
    //画字
    private Paint textPaint = new Paint();

    private int color;

    public CircleButton(Context context) {
        super(context);
        init();
    }
    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public CircleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(0x303F9F);
        color=0xbbd4e7;
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centre = getWidth() / 2;
        int radius = centre;

        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        //绘制圆形（圆心的x坐标，圆心的y坐标，圆的半径，绘制时所使用的画笔）
        canvas.drawCircle(centre, centre, radius, paint);

        textPaint.setTextSize(getTextSize());
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(0xffffffff);

        float length = textPaint.measureText(getText().toString());
        //绘制文字
        canvas.drawText(getText().toString(), centre - length / 2, centre + getTextSize() / 3, textPaint);
    }
    //改变文字颜色
    public void setPaintColor(int color) {
        this.color = color;
    }
}
