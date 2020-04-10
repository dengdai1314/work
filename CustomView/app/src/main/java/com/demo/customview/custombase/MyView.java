package com.demo.customview.custombase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * @author 29003
 * @description customView 绘制基础练习
 * @date 2019/12/14
 */
public class MyView extends View {
    Paint mPaint;
    Path mPath;
    float mRadius = 200;
    float pointX = 200f;
    float pointY = 200f;

    private  boolean moveable;
    //自定义View要在布局中使用，必须要创建一个构造器
    public MyView(Context context, @Nullable AttributeSet attrs ) {
        super(context, attrs );
        mPaint = new Paint();
//        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//打开抗锯齿效果
        //设置画笔的信息
        mPaint.setColor(Color.BLUE);//设置绘制内容的颜色
        mPaint.setStyle(Paint.Style.STROKE);//设置画笔风格，这是画线，如果不设置，默认为填充模式即FILL
        mPaint.setStrokeWidth(10);//设置画线的宽度
        mPaint.setAntiAlias(true);//打开抗锯齿效果，可以设置抗锯齿强度
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mPaint.setStrokeWidth(20);//设置画线宽度。如果这里设置了，将用这个画线宽度
        //画圆，mPaint为画笔，提供基本信息之外的所有风格信息，例如颜色，线条粗细，阴影等。
//        canvas.drawCircle(pointX,pointY,mRadius,mPaint);
        mPath = new Path();
        mPath.addCircle(pointX,pointY,mRadius, Path.Direction.CW);
        canvas.drawPath(mPath,mPaint);


        //画矩形
//        canvas.drawRect(500,100,1100,500,mPaint);

        //画点
        mPaint.setStrokeCap(Paint.Cap.BUTT);//设置点的形状 默认为方形
        canvas.drawPoint(900,900,mPaint);//只一个点
        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
        // 绘制四个点：(50, 50) (50, 100) (100, 50) (100, 100)
        canvas.drawPoints(points, 2 /* 跳过两个数，即前两个 0 */,
                8 /* 一共绘制 8 个数（4 个点）*/, mPaint);

        //画椭圆
//        canvas.drawOval(50,50,350,200,mPaint);

        //画线
//        canvas.drawLine(200, 200, 800, 500, mPaint);
//        float[] lines = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120, 150, 20, 250, 20, 150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};//批量画线
//        canvas.drawLines(lines, mPaint);

        //画圆角矩形
//        left, top, right, bottom 是四条边的坐标，rx 和 ry 是圆角的横向半径和纵向半径。
//        canvas.drawRoundRect(100, 100, 500, 300, 50, 50, mPaint);

        //画扇形和弧形
//        mPaint.setStyle(Paint.Style.FILL); // 填充模式
//        canvas.drawArc(200, 100, 800, 500, -110, 100, true, mPaint); // 绘制扇形
//        canvas.drawArc(200, 100, 800, 500, 20, 140, false, mPaint); // 绘制弧形
//        mPaint.setStyle(Paint.Style.STROKE); // 画线模式
//        canvas.drawArc(200, 100, 800, 500, 180, 60, false, mPaint); // 绘制不封口的弧形

//        这类颜色填充方法一般用于在绘制之前设置底色，或者在绘制之后为界面设置半透明蒙版
//        canvas.drawColor(Color.BLACK);//覆盖纯黑
//        canvas.drawColor(Color.parseColor("#88880000"));//半透明红色
//        canvas.drawRGB(30,80,100);
//        canvas.drawARGB(80,80,80,100);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Float touchX ;
        Float touchY;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                if(touchX>pointX - mRadius&&touchX<pointX+mRadius&&touchY>pointY-mRadius&&touchY<pointY+mRadius){
                    moveable = true;
                    Toast.makeText(getContext(),"我按下了",Toast.LENGTH_LONG).show();
                }else {
                    moveable = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(moveable){
                    pointX = event.getX();
                    pointY = event.getY();
                    invalidate();
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
