package com.hencoder.hencoderpracticelayout2.Step;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/3017:06
 * @description 水纹
 */
public class WaveView extends View {

    //波纹颜色
    private int waveColor = 0xff0099CC;
    //振幅
    private float swing = 0;
    private int height;
    private int width;
    private int ms = 30;
    private float isPause=0f;
    private boolean isRun = false;
    //绘制波纹的画笔
    private Paint wavePaint;

    //Path类可以预先在View上将N个点连成一条"路径"
    // 然后调用Canvas的drawPath(path,paint)即可沿着路径绘制图形
    private Path path1;
    private Path path2;
    private Path path3;

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        // 初始绘制波纹的画笔
        wavePaint = new Paint();
        // 去除画笔锯齿
        wavePaint.setAntiAlias(true);
        //设置画笔颜色
        wavePaint.setColor(waveColor);
        //设置线宽
        wavePaint.setStrokeWidth(5);
        //设置风格为空心
        wavePaint.setStyle(Paint.Style.STROKE);
        path1 = new Path();
        path2 = new Path();
        path3 = new Path();
    }

    /**
     * 计算view高度宽度的大小
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initLayoutParams();
    }

    private void initLayoutParams() {
        height = this.getHeight();
        width = this.getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setPath();
        wavePaint.setStrokeWidth(6);
        wavePaint.setAlpha(100);
        canvas.drawPath(path1,wavePaint);

        wavePaint.setStrokeWidth(3);
        wavePaint.setAlpha(80);
        canvas.drawPath(path2,wavePaint);

        wavePaint.setStrokeWidth(4);
        wavePaint.setAlpha(60);
        canvas.drawPath(path3,wavePaint);
    }

    private void setPath(){
        int x = 0;
        int y = 0;
        path1.reset();//清除掉path里的线条和曲线
        for (int i = 0; i < width; i++) {
            x = i;
            y = (int) (isPause*40* Math.sin(i * 2*0.7f * Math.PI / width+swing) + height*0.5);
            if (i == 0) {
                path1.moveTo(x, y);//定位绘画开始位置
            }
            //绘制圆滑曲线，即贝塞尔曲线；(x, y)为控制点，(x + 1, y)为结束点
            path1.quadTo(x, y, x + 1, y);
        }
        path2.reset();
        for (int i = 0; i < width; i++) {
            x = i;
            y = (int) (isPause*40* Math.sin(i * 2*0.7f * Math.PI / width+swing+0.3f) + height*0.5);
            if (i == 0) {
                path2.moveTo(x, y);
            }
            path2.quadTo(x, y, x + 1, y);
        }
        path3.reset();
        for (int i = 0; i < width; i++) {
            x = i;
            y = (int) (isPause*40* Math.sin(i * 2*0.7f * Math.PI / width+swing+0.3f) + height*0.5);
            if (i == 0) {
                path3.moveTo(x, y);
            }
            path3.quadTo(x, y, x + 1, y);
        }

        path1.close();//回到初始点形成封闭的曲线
        path2.close();
        path3.close();
    }

    public void start(){
        this.isRun=true;
        this.isPause=1.0f;
        new MyThread().start();//让波纹在子线程中运行
    }
    public void stop(){
        this.isRun=false;
        this.isPause=0.0f;
        invalidate();//请求重新绘制的界面
    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            while (isRun) {
                swing+=-0.25f;
                mHandler.sendEmptyMessage(1);
                try {
                    Thread.sleep(ms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                invalidate();//请求重新绘制的界面
            }
        }
    };

}
