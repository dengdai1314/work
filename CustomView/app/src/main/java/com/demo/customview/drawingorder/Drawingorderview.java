package com.demo.customview.drawingorder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.demo.customview.R;

import androidx.annotation.Nullable;

/**
 * @author 29003
 * @description
 * @date 2020/3/11
 */
public class Drawingorderview extends View {
    public Drawingorderview(Context context) { super(context); init();}
    public Drawingorderview(Context context, @Nullable AttributeSet attrs) { super(context, attrs); init();}
    public Drawingorderview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr);init(); }
    Paint paint;
    Bitmap bitmap;

    public void init(){
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        canvas.drawBitmap(bitmap,100,100,paint);
        String text = " test";
        paint.setColor(Color.RED);
        paint.setTextSize(50);
        canvas.drawText(text,120,120,paint);
    }
}
