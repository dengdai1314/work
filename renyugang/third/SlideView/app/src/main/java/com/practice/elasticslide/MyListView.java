package com.practice.elasticslide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2813:41
 * @description 内部拦截法
 */
public class MyListView extends ListView {
    private Scroller mScroller;
    private float mXDown;
    private float mXMove;
    private float mXLastMove;
    private int mLastXIntercept;
    private int mLastYIntercept;

    public MyListView(Context context) {
        super(context);
        mScroller = new Scroller(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int mLastX;
    private int mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if(Math.abs(deltaX)>Math.abs(deltaY)){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:break;
        }
        mLastY = y;
        mLastX = x;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mXLastMove = mXDown;

                intercepted = false;
                // 如果滑动没有完成，就继续由父控件处理
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                mXLastMove = mXMove;

                intercepted = true;
                break;
            case MotionEvent.ACTION_UP:
                intercepted = true;
                break;
            default:break;
        }
        mLastXIntercept = x;
        mLastYIntercept = y;
        return intercepted;
    }
}
