package com.demo.customview.textrendering;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author 29003
 * @description
 * @date 2020/2/27
 */
public class Textrenderingview extends View {
    Paint paint;
    String text = "hello world";
    public Textrenderingview(Context context) {super(context);}
    public Textrenderingview(Context context, @Nullable AttributeSet attrs) { super(context, attrs);init(); }
    public Textrenderingview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }

    public void init(){
        paint= new Paint();
        paint.setTextSize(80);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * drawText(String text,float x,float y,Paint paint)
         */
//        String text = "hello world";
//        paint.setTextSize(300);
//        paint.setColor(Color.RED);
//        //x,y指的是与文字左下角比较接近的位置；x,y不能为0，否则文字出现在view的上方
//        canvas.drawText(text,100,100,paint);

        /**
         * drawTextOnPath
         * 沿着一条Path 绘制文字
         */
//        Path path = new Path();
//        paint.setStrokeWidth(10);
//        paint.setStyle(Paint.Style.STROKE);
//        path.moveTo(100, 200);
//        path.rLineTo(30, 100);
//        path.rLineTo(80, -150);
//        path.rLineTo(100, 100);
//        path.rLineTo(70, -120);
//        path.rLineTo(150, 80);
//        paint.setStrokeJoin(Paint.Join.ROUND);
//        canvas.drawPath(path,paint);
//        canvas.drawTextOnPath("hello world",path,0,0,paint);

        /**
         * StaticLayout 文字到viewv边缘自动换行
         * width 是文字区域的宽度，文字到达这个宽度后就会自动换行；
         * align 是文字的对齐方向；
         * spacingmult 是行间距的倍数，通常情况下填 1 就好；
         * spacingadd 是行间距的额外增加值，通常情况下填 0 就好；
         * includepad 是指是否在文字上下添加额外的空间，来避免某些过高的字符的绘制出现越界。
         */
//        Paint textpaint = new TextPaint();
//        textpaint.setTextSize(50);
//        String text1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
//        StaticLayout staticLayout1 = new StaticLayout(text1, (TextPaint) textpaint, 600,
//                Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
//        String text2 = "a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz";
//        StaticLayout staticLayout2 = new StaticLayout(text2, (TextPaint) textpaint, 600,
//                Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
//        canvas.save();
//        canvas.translate(50, 100);
//        staticLayout1.draw(canvas);
//        canvas.translate(0, 200);
//        staticLayout2.draw(canvas);
//        canvas.restore();

        /**
         * setTextSize(flat textSize)设置文字大小
         */
//        paint.setTextSize(18);
//        canvas.drawText(text, 100, 25, paint);
//        paint.setTextSize(36);
//        canvas.drawText(text, 100, 70, paint);
//        paint.setTextSize(60);
//        canvas.drawText(text, 100, 145, paint);
//        paint.setTextSize(84);
//        canvas.drawText(text, 100, 240, paint);
        /**
         * setTypeface(Typeface typeface)设置字体
         */
//        paint.setTypeface(Typeface.DEFAULT);
//        canvas.drawText(text, 100, 150, paint);
//        paint.setTypeface(Typeface.SERIF);
//        canvas.drawText(text, 100, 300, paint);

        /**
         *setFakeBoldText(boolean fakeBoldText)是否使用伪粗体。
         */
//        paint.setFakeBoldText(false);
//        canvas.drawText(text, 100, 150, paint);
//        paint.setFakeBoldText(true);
//        canvas.drawText(text, 100, 230, paint);

        /**
         * setStrikeThruText(boolean strikeThruText)是否加删除线。
         */
//        paint.setStrikeThruText(true);
//        canvas.drawText(text,100,150,paint);

        /**
         * setUnderlineText(boolean underlineText)
         */
//        paint.setUnderlineText(true);
//        canvas.drawText(text, 100, 150, paint);

        /**
         * setTextSkewX(float skewX) 设置文字横向错切角度（文字倾斜度）
         */
//        paint.setTextSkewX(-0.5f);
//        canvas.drawText(text, 100, 150, paint);

        /**
         * setTextScaleX(float scaleX)设置文字横向放缩也就是文字变胖变瘦。默认值为1
         */
//        paint.setTextScaleX(1);
//        canvas.drawText(text, 100, 150, paint);
//        paint.setTextScaleX(0.8f);
//        canvas.drawText(text, 100, 230, paint);
//        paint.setTextScaleX(1.2f);
//        canvas.drawText(text, 100, 310, paint);

        /**
         * setLetterSpacing(float letterSpacing)设置字符间距，默认值为0
         */
//        paint.setLetterSpacing(0.2f);
//        canvas.drawText(text, 100, 150, paint);

        /**
         * setFontFeatureSettings(String settings)用 CSS 的 font-feature-settings 的方式来设置文字。
         */
//        paint.setFontFeatureSettings("smcp"); // 设置 "small caps"
//        canvas.drawText("Hello HenCoder", 100, 150, paint);

        /**
         * setTextAlign(Paint.Align align)设置文字的对齐方式。一共有三个值：LEFT CETNER 和 RIGHT。默认值为 LEFT
         */
//        paint.setTextAlign(Paint.Align.LEFT);
//        canvas.drawText(text, 500, 150, paint);
//        paint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText(text, 500, 300 , paint);
//        paint.setTextAlign(Paint.Align.RIGHT);
//        canvas.drawText(text, 500, 450, paint);

        /**
         * setTextLocale(Locale locale) / setTextLocales(LocaleList locales)
         * 设置绘制所使用的 Locale(地域)
         */
//        text = "雨骨底条今直沿微写";
//        paint.setTextLocale(Locale.CHINA); // 简体中文
//        canvas.drawText(text, 150, 150, paint);
//        paint.setTextLocale(Locale.TAIWAN); // 繁体中文
//        canvas.drawText(text, 150, 300 , paint);
//        paint.setTextLocale(Locale.JAPAN); // 日语
//        canvas.drawText(text, 150, 450 , paint);

        /**
         * float getFontSpacing()获取推荐的行距。
         * 即推荐的两行文字的 baseline 的距离。
         * 这个值是系统根据文字的字体和字号自动计算的。
         * 它的作用是当你要手动绘制多行文字（而不是使用 StaticLayout）的时候，
         * 可以在换行的时候给 y 坐标加上这个值来下移文字。
         */
//        String[] texts = {"Hello world,AajJ跑步鞋"};
//        canvas.drawText(texts, 100, 150, paint);
//        canvas.drawText(texts, 100, 150 + paint.getFontSpacing(), paint);

        /**
         * getTextBounds()(String text, int start, int end, Rect bounds)
         * 测量的是文字的显示范围
         * 参数里，text 是要测量的文字，start 和 end 分别是文字的起始和结束位置，
         * bounds 是存储文字显示范围的对象，方法在测算完成之后会把结果写进 bounds。
         */
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawText(text,100,150,paint);
//        Rect bounds  = new Rect();
//        paint.getTextBounds(text,0,text.length(),bounds);
//        bounds.left += 100;
//        bounds.top += 150;
//        bounds.right += 100;
//        bounds.bottom += 150;
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawRect(bounds,paint);

        /**
         * float measureText(String text)测量的是文字绘制时所占用的宽度。
         */
//        canvas.drawText(text,100,150,paint);
//        float textWidth = paint.measureText(text);
//        canvas.drawLine(100,150,100+textWidth,150,paint);

        /**
         * getTextWidths(String text, float[] widths)获取字符串中每个字符的宽度，并将结果填入参数widths
         */
//        float[] measuredWidth = {1};
//        canvas.drawText(text,100,150,paint);
//        float textWidth = paint.getTextWidths(text,measuredWidth);
//        canvas.drawLine(100,150,100+textWidth,150,paint);

        /**
         * int breakText(String text, boolean measureForwards, float maxWidth, float[] measuredWidth)
         * 测量文字宽度的。但和 measureText() 的区别是， breakText() 是在给出宽度上限的前提下测量文字的宽度。如果文字的宽度超出了上限，那么在临近超限的位置截断文字。
         */
        text = "Hello HenCoder";
//        int measuredCount;
//        float[] measuredWidth ={0};
//        // 宽度上限 300 （不够用，截断）
//        measuredCount = paint.breakText(text, 0, text.length(), true, 300, measuredWidth);
//        canvas.drawText(text, 0, measuredCount, 150, 150, paint);
//
//        // 宽度上限 400 （不够用，截断）
//        measuredCount = paint.breakText(text, 0, text.length(), true, 400, measuredWidth);
//        canvas.drawText(text, 0, measuredCount, 150, 200, paint);
//
//        // 宽度上限 500 （够用）
//        measuredCount = paint.breakText(text, 0, text.length(), true, 500, measuredWidth);
//        canvas.drawText(text, 0, measuredCount, 150, 200 * 2, paint);
//
//        // 宽度上限 600 （够用）
//        measuredCount = paint.breakText(text, 0, text.length(), true, 600, measuredWidth);
//        canvas.drawText(text, 0, measuredCount, 150, 200 * 3, paint);

        /**
         * getRunAdvance(CharSequence text, int start, int end, int contextStart, int contextEnd, boolean isRtl, int offset)
         * start end 是文字的起始和结束坐标；contextStart contextEnd 是上下文的起始和结束坐标；isRtl 是文字的方向；offset 是字数的偏移(第几个字)
         *光标当然不能出现在符号中间
         */
        int length = text.length();
        float advance = paint.getRunAdvance(text,0,length,0,length,false,1);
        canvas.drawText(text,100,150,paint);
        canvas.drawLine(100+advance,150-50,100+advance,150+10,paint);

        text = "Hello HenCoder \uD83C\uDDE8\uD83C\uDDF3";// "Hello HenCoder 🇨🇳"
        float advance1 = paint.getRunAdvance(text,0,length,0,length,false,length);
        float advance2 = paint.getRunAdvance(text, 0, length, 0, length, false, length - 1);
        float advance3 = paint.getRunAdvance(text, 0, length, 0, length, false, length - 2);
        float advance4 = paint.getRunAdvance(text, 0, length, 0, length, false, length - 3);
        float advance5 = paint.getRunAdvance(text, 0, length, 0, length, false, length - 4);
        float advance6 = paint.getRunAdvance(text, 0, length, 0, length, false, length - 5);
        canvas.drawText(text,100,150,paint);
        canvas.drawLine(100+advance,150-50,100+advance1,150+10,paint);
        canvas.drawLine(100+advance,150-50,100+advance2,150+10,paint);
        canvas.drawLine(100+advance,150-50,100+advance3,150+10,paint);
        canvas.drawLine(100+advance,150-50,100+advance4,150+10,paint);
        canvas.drawLine(100+advance,150-50,100+advance5,150+10,paint);
        canvas.drawLine(100+advance,150-50,100+advance6,150+10,paint);

        /**
         * getOffsetForAdvance(CharSequence text, int start, int end, int contextStart, int contextEnd, boolean isRtl, float advance)
         * 给出一个位置的像素值，计算出文字中最接近这个位置的字符偏移量（即第几个字符最接近这个坐标）
         * text 是要测量的文字；start end 是文字的起始和结束坐标；
         * contextStart contextEnd 是上下文的起始和结束坐标；
         * isRtl 是文字方向；advance 是给出的位置的像素值。
         * 填入参数，对应的字符偏移量将作为返回值返回。
         * getOffsetForAdvance() 配合上 getRunAdvance() 一起使用，就可以实现「获取用户点击处的文字坐标」的需求。
         */

        /**
         * hasGlyph(String string)
         * 检查指定的字符串中是否是一个单独的字形 (glyph）。最简单的情况是，string 只有一个字母（比如 a）。
         */
    }

}
