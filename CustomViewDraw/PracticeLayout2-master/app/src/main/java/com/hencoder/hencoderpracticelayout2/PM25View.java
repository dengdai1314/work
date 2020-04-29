package com.hencoder.hencoderpracticelayout2;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import static android.view.View.MeasureSpec.EXACTLY;

/**
 * Created by wliu on 05/06/2018.
 */

public class PM25View extends View {

    protected static final String TAG = "HenCoder";

    private static int mcount = 0;

    private static int STROKE_WIDTH = 30;
    private static int SWEEP_COLOR_START = Color.CYAN;
    private static int SWEEP_COLOR_END = Color.BLUE;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaint5 = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int mWidth, mHeight, mPadding, mCX, mCY, mEndTextSize, mPolutionTextSize, mAirStatusTextSize;

    private float pollution, pollutionValue = 121;
    private String airDesc = "轻度污染";

    private int mStartAngle = 135;
    private int mSwipeAngle = 360 - (mStartAngle - 90) * 2;

    public PM25View(Context context) {
        this(context, null);
    }

    public PM25View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PM25View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPollution(float pollution) {
        this.pollution = pollution;
        invalidate();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        ++mcount;
//        Log.w("xx", "mcount == " + mcount);
//
//
//        int w = getMeasuredWidth();
//        int h = getMeasuredHeight();
//
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        switch (widthMode) {
//            case MeasureSpec.UNSPECIFIED:
//                Log.w("xx", "width mode == unspecified");
//                break;
//            case MeasureSpec.AT_MOST:
//                Log.w("xx", "width mode == at most");
//                break;
//            case EXACTLY:
//                Log.w("xx", "width mode == exactly");
//                break;
//        }
//
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        switch (heightMode) {
//            case MeasureSpec.UNSPECIFIED:
//                Log.w("xx", "height mode == unspecified");
//                break;
//            case MeasureSpec.AT_MOST:
//                Log.w("xx", "height mode == at most");
//                break;
//            case EXACTLY:
//                Log.w("xx", "height mode == exactly");
//                break;
//        }
//
//
//        setSizes(w, h);
//    }


    /**
     *
     */
    protected void setSizes(int w, int h) {
        initNumbers(w, h);
        initPaints();
    }

    /**
     *
     * @param width
     * @param height
     */
    private void initNumbers(int width, int height) {
        mWidth = width;
        mHeight = height;
        mCX = mWidth / 2;
        mCY = mHeight / 2;

        STROKE_WIDTH = mWidth / 20;
        mEndTextSize = mWidth / 15;
        mPolutionTextSize = mWidth / 8;
        mAirStatusTextSize = mPolutionTextSize / 2;

        mPadding = getPaddingTop();
        // Sets the sweep gradient
        int[] colors = {Color.BLACK, Color.RED, Color.CYAN, Color.BLUE, Color.BLACK};
        float[] positions = {0f, .1f, 0.5f, 0.75f, 1f};
        mPaint2.setShader(new SweepGradient(mCX, mCY, colors, positions));
    }

    private void initPaints() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStrokeWidth(STROKE_WIDTH);

        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setStrokeCap(Paint.Cap.ROUND);
        mPaint2.setStrokeWidth(STROKE_WIDTH);
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "pollution", 0, pollutionValue);
        animator.setDuration(1000);
        animator.start();

        mPaint3.setStrokeWidth(1);
        mPaint3.setTextSize(mEndTextSize);
        mPaint3.setColor(Color.WHITE);


        mPaint4.setStrokeWidth(1);
        mPaint4.setTextSize(mPolutionTextSize);
        mPaint4.setColor(Color.WHITE);

        mPaint5.setStrokeWidth(1);
        mPaint5.setTextSize(mAirStatusTextSize);
        mPaint5.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawPoint(mCX, mCY, mPaint2);

//        canvas.drawLine(mCX, 0, mCX, 500, mPaint5);

        Rect rect = new Rect();
        mPaint3.getTextBounds("0", 0, 1, rect);
        int height = rect.height();

        int left = STROKE_WIDTH + mPadding + height / 2;
        int top = STROKE_WIDTH + mPadding + height / 2;
        int right = mWidth - STROKE_WIDTH - mPadding - height / 2;
        int bottom = mHeight - STROKE_WIDTH - mPadding - height / 2;
        RectF rectF = new RectF(left, top, right, bottom);
        canvas.drawArc(rectF, mStartAngle, mSwipeAngle, false, mPaint);


        float endAngle = this.pollution / 500 * mSwipeAngle;
        canvas.drawArc(rectF, mStartAngle, endAngle, false, mPaint2);


        int r = (right - left) / 2;
        double a0 = (180 - (double)mStartAngle) / 180 * Math.PI;
        addEndText(canvas, "0", r, a0);

        double a500 = ((double)mStartAngle) / 180 * Math.PI;
        addEndText(canvas, "500", r, a500);

        String pollution = String.valueOf((int) pollutionValue);
        mPaint4.getTextBounds(pollution, 0, pollution.length(), rect);
        int pollutionWidth = rect.width();
        int pollutionHeight = rect.height();
        int pollutionX = mCX - pollutionWidth / 2;
        int pollutionY = mCY + pollutionHeight / 2 ;
        canvas.drawText(pollution, pollutionX, pollutionY, mPaint4);


        mPaint5.getTextBounds(airDesc, 0, airDesc.length(), rect);
        int adWidth = rect.width();
        int adHeight = rect.height();

        int adX = mCX - adWidth / 2;
        int adY = mCY - pollutionHeight;
//        canvas.drawLine(mCX - 100, adY, mCX + 100, adY, mPaint3);
        canvas.drawText(airDesc, adX, adY, mPaint5);
    }

    private void addEndText(Canvas canvas, String text, int r, double angle) {
        Rect rect = new Rect();
        mPaint3.getTextBounds(text, 0, text.length(), rect);
        int w_0 = rect.width();
        int h_0 = rect.height();

        int p0x = (int) (mCX - Math.cos(angle) * r);
        int p0y = (int) (mCY + Math.sin(angle) * r);

        Paint.FontMetrics fontMetrics = mPaint3.getFontMetrics();
        p0x = p0x - w_0 / 2;
        p0y = (int) (p0y + h_0 - fontMetrics.top);
        canvas.drawText(text, p0x, p0y, mPaint3);
    }
}
