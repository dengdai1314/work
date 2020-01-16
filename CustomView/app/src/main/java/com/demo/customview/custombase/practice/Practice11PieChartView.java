package com.demo.customview.custombase.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice11PieChartView extends View {

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        Paint mPaint = new Paint();
        canvas.drawArc(90, 90, 630, 590, 180, 120, true, mPaint); // 绘制扇形
        canvas.drawArc(100, 100, 650, 600, 300, 50, true, mPaint);
        canvas.drawArc(100, 100, 650, 600, 10, 50, true, mPaint);
        canvas.drawArc(100, 100, 650, 600, 70, 30, true, mPaint);
        canvas.drawArc(90, 90, 640, 610, 110, 70, true, mPaint);
    }
}
