package com.demo.customview.paintexplain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
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

    @SuppressLint("DrawAllocation")
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
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(1);
//        canvas.drawCircle(150, 125, 100, paint);
//        paint.setStrokeWidth(5);
//        canvas.drawCircle(400, 125, 100, paint);
//        paint.setStrokeWidth(40);
//        canvas.drawCircle(650, 125, 100, paint);

        /**
         * 设置线条线头形状
         */
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(20);
//        paint.setStrokeCap(Paint.Cap.BUTT);
//        canvas.drawLine(30,30,500,30,paint);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawLine(30,80,500,80,paint);
//        paint.setStrokeCap(Paint.Cap.SQUARE);
//        canvas.drawLine(30,130,500,130,paint);

        /**
         * 设置线条拐角形状
         */
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setStrokeJoin(Paint.Join.MITER);//默认为该值，还有bevel,round可选
        Path path = new Path();
        path.rLineTo(200,100);
        path.rLineTo(-160,120);
        canvas.translate(100,100);//画布起始点编译
        canvas.drawPath(path,paint);

        /**
         * 色彩优化
         */
        /**
         * 设置图像的抖动
         * setDither(boolean dither)
         */
//        paint.setDither(true);//开启抖动

        /**
         * 设置是否使用双线性过滤来绘制 Bitmap 。
         */
//        paint.setFilterBitmap(true);//使用双线性过滤绘制bitmap


        /**
         * 使用PathEffect给图形的轮廓设置效果
         * 50为单线长度,1为线与线之间宽度或者叫距离，10未知
         */
//        PathEffect pathEffect = new DashPathEffect(new float[]{15,5},10);
//        paint.setPathEffect(pathEffect);
//        paint.setStrokeWidth(10);
//        canvas.drawLine(30,80,800,80,paint);
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(500,500,250,paint);

        /**
         * 把所有拐角变成圆角
         */
//        PathEffect pathEffect = new CornerPathEffect(80);
//        paint.setPathEffect(pathEffect);
//        paint.setStrokeWidth(20);
//        paint.setStyle(Paint.Style.STROKE);
//        Path path = new Path();
//        path.lineTo(300,300);
//        path.lineTo(500,100);
//        canvas.drawPath(path,paint);

        /**
         * 将线条进行随机偏离
         */
//        PathEffect pathEffect = new DiscretePathEffect(20,5);
//        paint.setPathEffect(pathEffect);
//        paint.setStrokeWidth(20);
//        paint.setStyle(Paint.Style.STROKE);
//        Path path = new Path();
//        path.lineTo(300,300);
//        path.lineTo(500,100);
//        canvas.drawPath(path,paint);

        /**
         * 使用虚线绘制线条
         * 代码中的 20, 5, 10, 5 就表示虚线是按照「画 20 像素、空 5 像素、画 10 像素、空 5 像素」的模式来绘制；第二个参数 phase 是虚线的偏移量。
         */
//        PathEffect pathEffect = new DashPathEffect(new float[]{20,10,5,10},0);
//        paint.setPathEffect(pathEffect);
//        paint.setStyle(Paint.Style.STROKE);
//        Path path = new Path();
//        path.lineTo(300,300);
//        path.lineTo(500,100);
//        canvas.drawPath(path,paint);

        /**
         * PathDashPathEffect
         * 使用Path绘制虚线
         * 构造方法 PathDashPathEffect(Path shape, float advance,
         * float phase, PathDashPathEffect.Style style) 中，
         * shape 参数是用来绘制的 Path ； advance 是两个相邻的 shape 段之间的间隔，
         * 不过注意，这个间隔是两个 shape 段的起点的间隔，而不是前一个的终点和后一个的起点的距离；
         * phase 和 DashPathEffect 中一样，是虚线的偏移；
         * 最后一个参数 style，是用来指定拐弯改变的时候 shape 的转换方式。有三个值：TRANSLATE 位移
         * ROTATE 旋转， MORPH 变体
         */
//        Path path = new Path();
//        path.lineTo(300,300);
//        path.lineTo(500,100);
//        Path dashPath = new Path();
//        dashPath.moveTo(90, 340);
//        dashPath.lineTo(150, 340);
//        dashPath.lineTo(120, 290);
//        dashPath.close();
//        PathEffect pathEffect = new PathDashPathEffect(
//                dashPath,40,0, PathDashPathEffect.Style.TRANSLATE);
//        paint.setPathEffect(pathEffect);
//        canvas.drawPath(path,paint);

        /**
         * 按照两种PathEffect分别对目标进行绘制
         * SumPathEffect
         */
//        paint.setStyle(Paint.Style.STROKE);
//        Path path = new Path();
//        path.lineTo(300,300);
//        path.lineTo(500,100);
//        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
//        PathEffect discreteEffect = new DiscretePathEffect(20, 5);
//        SumPathEffect pathEffect = new SumPathEffect(dashEffect, discreteEffect);
//        paint.setPathEffect(pathEffect);
//        canvas.drawPath(path,paint);

        /**
         * 按照两种PathEffect分别对目标进行绘制
         * ComposePathEffect
         * 构造方法 ComposePathEffect(PathEffect outerpe, PathEffect innerpe)
         * 中的两个 PathEffect 参数， innerpe 是先应用的， outerpe 是后应用的。
         * 所以上面的代码就是「先偏离，再变虚线」。而如果把两个参数调换，
         * 就成了「先变虚线，再偏离」。
         */
//        paint.setStyle(Paint.Style.STROKE);
//        Path path = new Path();
//        path.lineTo(300,300);
//        path.lineTo(500,100);
//        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
//        PathEffect discreteEffect = new DiscretePathEffect(20, 5);
//        ComposePathEffect pathEffect = new ComposePathEffect(dashEffect, discreteEffect);
//        paint.setPathEffect(pathEffect);
//        canvas.drawPath(path,paint);

        /**
         * setShadowLayer(float radius, float dx, float dy, int shadowColor)
         * 在之后的绘制内容下面加一层阴影
         * 方法的参数里， radius 是阴影的模糊范围； dx dy 是阴影的偏移量； shadowColor 是阴影的颜色。
         * 清除阴影层，使用 clearShadowLayer()
         */
//        String text = "text";
//        paint.setTextSize(80);
//        paint.setShadowLayer(10,0,0,Color.RED);
//        canvas.drawText(text,80,300,paint);
//        paint.clearShadowLayer();
//        canvas.drawText(text,100,500,paint);

        /**
         * setMaskFilter(MaskFilter maskfilter)
         * 在绘制层上方添加附加效果
         */
        /**
         * BlurMaskFilter
         * 模糊效果
         * 它的构造方法 BlurMaskFilter(float radius, BlurMaskFilter.Blur style) 中， radius 参数是模糊的范围， style 是模糊的类型。一共有四种：
         * NORMAL: 内外都模糊绘制
         * SOLID: 内部正常绘制，外部模糊
         * INNER: 内部模糊，外部不绘制
         * OUTER: 内部不绘制，外部模糊（什么鬼？）
         */
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
//        bitmap = imageScale(bitmap,400,400);
//        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
//        canvas.drawBitmap(bitmap,100,100,paint);

        /**
         * EmbossMaskFilter
         * 浮雕效果的 MaskFilter。
         * 它的构造方法 EmbossMaskFilter(float[] direction, float ambient, float specular,
         * float blurRadius) 的参数里， direction 是一个 3 个元素的数组，指定了光源的方向；
         * ambient 是环境光的强度，数值范围是 0 到 1； specular 是炫光的系数；
         * blurRadius 是应用光线的范围。
         */
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
//        bitmap = imageScale(bitmap,400,400);
//        paint.setMaskFilter(new EmbossMaskFilter(new float[]{0, 1, 1}, 0.2f, 8, 10));
//        canvas.drawBitmap(bitmap,100,100,paint);

        /**
         * 获取绘制的Path
         * 根据 paint 的设置，计算出绘制 Path 或文字时的实际 Path。
         */
        /**
         * getFillPath(Path src,Path dst)
         * 实际 Path ，指的就是 drawPath() 的绘制内容的轮廓，要算上线条宽度和设置的 PathEffect。
         * 默认情况下（线条宽度为 0、没有 PathEffect），原 Path 和实际 Path 是一样的；而在线条宽度不为 0 （并且模式为 STROKE 模式或 FLL_AND_STROKE ），
         * 或者设置了 PathEffect 的时候，实际 Path 就和原 Path 不一样了：
         * 通过 getFillPath(src, dst) 方法就能获取这个实际 Path。方法的参数里，src 是原 Path ，而 dst 就是实际 Path 的保存位置。
         * getFillPath(src, dst) 会计算出实际 Path，然后把结果保存在 dst 里。
         */
        /**
         * getTextPath(String text, int start, int end, float x, float y, Path path)
         * / getTextPath(char[] text, int index, int count, float x, float y, Path path)
         */


        /**
         * 初始化类
         */
        /**
         * reset
         * 重置Paint的所有属性为默认值
         */
        /**
         * set(Paint src)
         * 把src 的所有属性全部复制过来。
         * 相当于调用 src 所有的 get 方法，然后调用这个 Paint 的对应的 set 方法来设置它们。
         */
        /**
         * setFlags(int flags)
         * 批量设置 flags。相当于依次调用它们的 set 方法
         * paint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
         * 这行代码，和下面这两行是等价的：
         *
         * paint.setAntiAlias(true);
         * paint.setDither(true);
         * setFlags(flags) 对应的 get 方法是 int getFlags()。
         */
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
