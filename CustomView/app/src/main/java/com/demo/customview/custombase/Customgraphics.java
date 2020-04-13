package com.demo.customview.custombase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author 29003
 * @description
 * @date 2019/12/16
 */
public class Customgraphics extends View {
    Paint mpaint;
    Path path;
    public Customgraphics(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mpaint = new Paint();
        mpaint.setColor(Color.RED);
//        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeWidth(10);//设置画线的宽度
        path = new Path();
        // 使用 path 对心型进行描述（这段描述代码不必看懂）
//        path.addArc(200, 200, 400, 400, -225, 225);
//        path.arcTo(400, 200, 600, 400, -180, 225, false);
//        path.lineTo(400, 542);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
//        path.addCircle(800,800,200,Path.Direction.CW);
//        canvas.drawPath(path,mpaint);
//        addOval(float left, float top, float right, float bottom, Direction dir) / addOval(RectF oval, Direction dir) 添加椭圆
//        addRect(float left, float top, float right, float bottom, Direction dir) / addRect(RectF rect, Direction dir) 添加矩形
//        addRoundRect(RectF rect, float rx, float ry, Direction dir) / addRoundRect(float left, float top, float right, float bottom, float rx, float ry, Direction dir) / addRoundRect(RectF rect, float[] radii, Direction dir) / addRoundRect(float left, float top, float right, float bottom, float[] radii, Direction dir) 添加圆角矩形
//        addPath(Path path) 添加另一个 Path
//        canvas.drawPath(path, mpaint); // 绘制出 path 描述的图形（心形），大功告成
        //画直线
//        path.lineTo(100,100);
//        path.rLineTo(100,0);
//        canvas.drawPath(path,mpaint);
        //画贝塞尔曲线
//        path.quadTo(150,140,150,160);
//        path.rQuadTo(370,380,50,700);

        //移动到目标位置moveTo
//        path.lineTo(100, 100); // 画斜线
//        path.moveTo(200, 100); // 我移~~
//        path.lineTo(200, 0); // 画竖线

//        arcTo(RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)
//        arcTo(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo)
//        arcTo(RectF oval, float startAngle, float sweepAngle) 画弧形

//        addArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle) / addArc(RectF oval, float startAngle, float sweepAngle)
//        又是一个弧形的方法。一个叫 arcTo ，一个叫 addArc()，都是弧形，区别在哪里？其实很简单： addArc() 只是一个直接使用了 forceMoveTo = true 的简化版 arcTo() 。

//        path.lineTo(100, 100);
//        path.arcTo(100, 100, 300, 300, -90, 90, true); // 强制移动到弧形起点（无痕迹）
//        path.lineTo(100, 100);
//        path.arcTo(100, 100, 300, 300, -90, 90, false); // 直接连线连到弧形起点（有痕迹）
//        path.lineTo(100, 100);
//        path.addArc(100, 100, 300, 300, -90, 90);

//        path.moveTo(100, 100);
//        path.lineTo(200, 100);
//        path.lineTo(150, 150);
//        path.close(); // 使用 close() 封闭子图形。等价于 path.lineTo(100, 100)

//        mpaint.setStyle(Paint.Style.FILL);
//        path.moveTo(100, 100);
//        path.lineTo(200, 100);
//        path.lineTo(150, 150);
//        // 这里只绘制了两条边，但由于 Style 是 FILL ，所以绘制时会自动封口
//        path.addCircle(200,200,100, Path.Direction.CW);
//        path.addCircle(350,200,100, Path.Direction.CW);

//        canvas.drawPath(path,mpaint);
        mpaint.setTextSize(150);
        canvas.drawText("nihao",800,1000,mpaint);
    }
}
