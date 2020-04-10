package com.demo.customview.drawingauxiliary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.demo.customview.R;

import androidx.annotation.Nullable;

/**
 * @author 29003
 * @description
 * @date 2020/3/3
 */
public class Drawingauxiliaryview extends View {
    Paint paint;
    Bitmap bitmap;
    Point point1;
    Point point2;
    Camera camera;
    public Drawingauxiliaryview(Context context) { super(context); init();}
    public Drawingauxiliaryview(Context context, @Nullable AttributeSet attrs){super(context,attrs);init();}
    public Drawingauxiliaryview(Context context, @Nullable AttributeSet attrs,int defStyleAttr){super(context,attrs,defStyleAttr);init();}

    public void init(){
        paint = new Paint();
        camera = new Camera();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        point1 = new Point(200, 200);
        point2 = new Point(600, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * clipRect()范围裁切
         */
//        int left = (getWidth() - bitmap.getWidth()) / 2;
//        int top = (getHeight() - bitmap.getHeight()) / 2;
//        canvas.save();
//        canvas.clipRect(left + 50, top + 50, left + 300, top + 200);
//        canvas.drawBitmap(bitmap, left, top, paint);
//        canvas.restore();

        /**
         * clipPath()范围裁切
         */
//        Path path1 = new Path();
//        Path path2 = new Path();
//
//        path1.addCircle(point1.x + 200, point1.y + 200, 150, Path.Direction.CW);
//        path2.setFillType(Path.FillType.INVERSE_WINDING);
//        path2.addCircle(point2.x + 200, point2.y + 200, 150, Path.Direction.CW);
//
//        canvas.save();
//        canvas.clipPath(path1);
//        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.clipPath(path2);
//        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
//        canvas.restore();

        /**
         *Canvas.translate(float dx, float dy) 平移
         * 参数里的 dx 和 dy 表示横向和纵向的位移
         */

//        canvas.save();
//        canvas.translate(-100, -100);
//        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(200, 0);
//        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
//        canvas.restore();

        /**
         * Canvas.rotate(float degrees, float px, float py) 旋转
         * 参数里的 degrees 是旋转角度，单位是度（也就是一周有 360° 的那个单位），方向是顺时针为正向； px 和 py 是轴心的位置。
         */
//        canvas.save();
//        canvas.translate(-100, -100);
//        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(200, 0);
//        canvas.rotate(45,400,200);
//        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
//        canvas.restore();

        /**
         * Canvas.scale(float sx, float sy, float px, float py) 放缩
         */
//        int bitmapWidth = bitmap.getWidth();
//        int bitmapHeight = bitmap.getHeight();
//        canvas.save();
//        canvas.drawBitmap(bitmap,0,0,paint);
//        canvas.scale(1.3f, 1.3f, bitmapWidth + bitmapWidth / 2, bitmapHeight + bitmapHeight / 2);
//        canvas.drawBitmap(bitmap, 700, 200, paint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.scale(0.5f,1.0f);
//        canvas.drawBitmap(bitmap,3000,0,paint);
//        canvas.restore();f

        /**
         *  skew(float sx, float sy) 错切
         *  参数里的 sx 和 sy 是 x 方向和 y 方向的错切系数。
         */
//        canvas.save();
//        canvas.drawBitmap(bitmap,0,0,paint);
//        canvas.skew(0.5f, 0);
//        canvas.drawBitmap(bitmap, 700, 0, paint);
//        canvas.restore();


        /**
         * 使用Matrix对象
         */
        Matrix matrix = new Matrix();
//        matrix.reset();
//        matrix.postTranslate(300,0);
//        canvas.drawBitmap(bitmap,0,0,paint);
//        canvas.save();
//        canvas.concat(matrix);
//        canvas.drawBitmap(bitmap,0,0,paint);
//        canvas.restore();

        /**
         * Matrix.setPolyToPoly(float[] src, int srcIndex,
         * float[] dst, int dstIndex, int pointCount) 用点对点映射的方式设置变换
         *
         * poly 就是「多」的意思。setPolyToPoly() 的作用是通过多点的映射的方式来直接设置变换。
         * 「多点映射」的意思就是把指定的点移动到给出的位置，从而发生形变。
         * 例如：(0, 0) -> (100, 100) 表示把 (0, 0) 位置的像素移动到 (100, 100) 的位置，这个是单点的映射，
         * 单点映射可以实现平移。而多点的映射，就可以让绘制内容任意地扭曲。
         */
//        float[] pointsSrc = {50f,30f,70f,80f,20f,100f,60f,40f};
//        float[] pointsDst = {40f,80f,20f,150f,40f,80f,70f,60f};
//        canvas.drawBitmap(bitmap,0,0,paint);
//        matrix.reset();
//        matrix.setPolyToPoly(pointsSrc,0,pointsDst,0,4);
//        canvas.save();
//        canvas.concat(matrix);
//        canvas.drawBitmap(bitmap,500,0,paint);
//        canvas.restore();

        /**
         * Camera.rotate*() 三维旋转
         * Camera.rotate*() 一共有四个方法： rotateX(deg) rotateY(deg) rotateZ(deg) rotate(x, y, z)
         */
//        canvas.drawBitmap(bitmap,0,0,paint);
//        canvas.save();
//        Camera camera = new Camera();
//        camera.save();
//        camera.rotateX(30);
//        camera.applyToCanvas(canvas);
//        camera.restore();
//        canvas.drawBitmap(bitmap,point1.x,point1.y,paint);
//        canvas.restore();

        //左右对称旋转
        canvas.save();

        camera.save(); // 保存 Camera 的状态
        camera.rotateX(30); // 旋转 Camera 的三维空间
        canvas.translate(point1.x, point1.y); // 旋转之后把投影移动回来
        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        canvas.translate(-point1.x, -point1.y); // 旋转之前把绘制内容移动到轴心（原点）
        camera.restore(); // 恢复 Camera 的状态

        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();
    }
}
