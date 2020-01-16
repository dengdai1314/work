package com.demo.customview.custombase.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice10HistogramView extends View {

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        Paint mPaint = new Paint();
        Path mPath = new Path();
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(3);
        canvas.drawLine(5,50,0,500,mPaint);
        canvas.drawLine(5,500,1200,500,mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(30,495,150,500,mPaint);
        canvas.drawRect(180,475,300,500,mPaint);
        canvas.drawRect(330,475,450,500,mPaint);
        canvas.drawRect(480,320,610,500,mPaint);
        canvas.drawRect(640,225,760,500,mPaint);
        canvas.drawRect(790,150,920,500,mPaint);
        canvas.drawRect(950,300,1050,500,mPaint);
        mPaint.setTextSize(35);
        mPaint.setColor(Color.WHITE);
        canvas.drawText("Froyo",35,540,mPaint);
    }
}
