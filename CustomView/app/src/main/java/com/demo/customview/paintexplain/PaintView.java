package com.demo.customview.paintexplain;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author 29003
 * @description
 * @date 2019/12/24
 */
public class PaintView extends View {
    Paint paint;
    public PaintView(Context context){super(context);init();}
    public PaintView(Context context, @Nullable AttributeSet attrs) { super(context, attrs);init(); }
    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr);init(); }

    public void init(){
        paint = new Paint();
        paint.setColor(Color.parseColor("#009688"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * setColor
         */
//        paint.setColor(Color.parseColor("#009688"));
//        canvas.drawRect(30,30,230,180,paint);
//
//        paint.setColor(Color.parseColor("#FF9800"));
//        canvas.drawLine(300, 30, 450, 180, paint);
//
//        paint.setColor(Color.parseColor("#E91E63"));
//        canvas.drawText("HenCoder", 500, 130, paint);

        /**
         * setARGB
         */
//        paint.setARGB(180,255,0,0);//a：设置透明度
//        canvas.drawRect(0,0,200,200,paint);

        /**
         * LinearGradient 线性渐变
         *
         * 在设置了 Shader 的情况下， Paint.setColor/ARGB() 所设置的颜色就不再起作用
         *
         * 构造方法：
         * LinearGradient(float x0, float y0, float x1, float y1, int color0, int color1, Shader.TileMode tile) 。
         * 参数：
         * x0 y0 x1 y1：渐变的两个端点的位置
         * color0 color1 是端点的颜色
         * tile：端点范围之外的着色规则，类型是 TileMode。TileMode 一共有 3 个值可选： CLAMP, MIRROR 和 REPEAT。CLAMP 会在端点之外延续端点处的颜色；MIRROR 是镜像模式；REPEAT 是重复模式。
         */
//        Shader shader = new LinearGradient(0, 600, 400, 0, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
//        paint.setShader(shader);
//        canvas.drawRect(0,0,600,400,paint);
//
//        Shader shader2 = new LinearGradient(600, 100, 500, 500, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);
//        paint.setShader(shader2);
//        canvas.drawRect(0,500,600,900,paint);
//
//        Shader shader3 = new LinearGradient(30, 560, 500, 500, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);
//        paint.setShader(shader3);
//        canvas.drawRect(0,1000,600,1400,paint);

        /**
         * RadialGradient 辐射渐变
         *
         * 构造方法：
         * RadialGradient(float centerX, float centerY, float radius, int centerColor, int edgeColor, TileMode tileMode)。
         *
         * 参数：
         * centerX centerY：辐射中心的坐标
         * radius：辐射半径
         * centerColor：辐射中心的颜色
         * edgeColor：辐射边缘的颜色
         * tileMode：辐射范围之外的着色模式。
         */
//        Shader shader = new RadialGradient(300, 300, 200, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
//        paint.setShader(shader);
//        canvas.drawCircle(300, 300, 200, paint);
//
//        Shader shader2 = new RadialGradient(300, 300, 200, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);
//        paint.setShader(shader2);
//        canvas.drawCircle(300, 700, 200, paint);
//
//        Shader shader3 = new RadialGradient(300, 300, 200, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);
//        paint.setShader(shader3);
//        canvas.drawCircle(300, 1100, 200, paint);

        /**
         * SweepGradient 扫描渐变
         * 构造方法：
         * SweepGradient(float cx, float cy, int color0, int color1)
         * 参数：
         * cx cy ：扫描的中心
         * color0：扫描的起始颜色
         * color1：扫描的终止颜色
         */
//        Shader shader = new SweepGradient(300, 300, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"));
//        paint.setShader(shader);
//        canvas.drawCircle(300, 300, 200, paint);

        /**
         * BitmapShader
         */
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
//        bitmap = imageScale(bitmap,400,400);
//        Shader shader = new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
//        paint.setShader(shader);
//        canvas.drawCircle(200,200,200,paint);

        /**
         * 构造方法：
         * BitmapShader(Bitmap bitmap, Shader.TileMode tileX, Shader.TileMode tileY)
         *
         * 参数：
         * bitmap：用来做模板的 Bitmap 对象
         * tileX：横向的 TileMode
         * tileY：纵向的 TileMode。
         */
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
//        bitmap = imageScale(bitmap,400,400);
//        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        Shader shader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
//        Shader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
//        paint.setShader(shader);
//        canvas.drawRect(0,0,1200,1500,paint);

        /**
         * ComposeShader 混合着色器
         * 构造方法：ComposeShader(Shader shaderA, Shader shaderB, PorterDuff.Mode mode)
         * 参数：
         * shaderA, shaderB：两个相继使用的 Shader
         * mode: 两个 Shader 的叠加模式，即 shaderA 和 shaderB 应该怎样共同绘制。它的类型是 PorterDuff.Mode 。
         */
//        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.batman);
//        bitmap1 = imageScale(bitmap1,400,400);
//        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.human);
//        bitmap2 = imageScale(bitmap2,300,300);
//        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        Shader shader = new ComposeShader(shader1,shader2, PorterDuff.Mode.SRC_OVER);
//        Shader shader = new ComposeShader(shader1,shader2, PorterDuff.Mode.DST_OUT);
//        Shader shader = new ComposeShader(shader1,shader2, PorterDuff.Mode.DST_IN);
//        paint.setShader(shader);
//        canvas.drawCircle(200,200,200,paint);

        /**
         * setColorFilter(ColorFilter colorFilter)
         * 为绘制设置颜色过滤。颜色过滤的意思，就是为绘制的内容设置一个统一的过滤策略，
         * 然后 Canvas.drawXXX() 方法会对每个像素都进行过滤后再绘制出来
         */
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
//        bitmap = imageScale(bitmap,400,400);
//        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        /**
         * LightingColorFilter：模拟简单的光照效果
         */
//        ColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff,0x000000);
//        paint.setColorFilter(lightingColorFilter);

        /**
         * PorterDuffColorFilter
         */
//        ColorFilter porterDuffColorFilter = new PorterDuffColorFilter(0x000000, PorterDuff.Mode.SRC_OVER);
//        paint.setColorFilter(porterDuffColorFilter);

//        paint.setShader(shader);
//        canvas.drawCircle(200,200,200,paint);

        /**
         *  开启抗锯齿
         */
        paint.setAntiAlias(true);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        /**
         * 图形为线条/填充
         */
//        paint.setStyle(Paint.Style.FILL);//填充
//        canvas.drawRect(200,200,200,200,paint);
//        paint.setStyle(Paint.Style.STROKE);//线条
//        canvas.drawCircle(300,300,200,paint);
//        paint.setStyle(Paint.Style.FILL_AND_STROKE); // FILL_AND_STROKE 模式，填充 + 画线
//        canvas.drawCircle(300, 300, 200, paint);

        /**
         * 线条形状
         */
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        canvas.drawCircle(150, 125, 100, paint);
        paint.setStrokeWidth(5);
        canvas.drawCircle(400, 125, 100, paint);
        paint.setStrokeWidth(40);
        canvas.drawCircle(650, 125, 100, paint);

    }

    /**
     * 通过bitmap修改图片显示大小
     * @param bitmap
     * @param dst_w  显示长度
     * @param dst_h  显示宽度
     * @return
     */
    public static Bitmap imageScale(Bitmap bitmap, int dst_w , int dst_h){
        int src_w = bitmap.getWidth();
        int src_h = bitmap.getHeight();
        float scale_w = ((float) dst_w) / src_w;
        float scale_h = ((float) dst_h) / src_h;
        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix,
                true);
        return dstbmp;
    }
}
