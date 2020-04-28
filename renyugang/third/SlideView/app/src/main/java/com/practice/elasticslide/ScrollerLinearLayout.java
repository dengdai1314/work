package com.practice.elasticslide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2715:19
 * @description 自定义LinearLayout,外部拦截法https://juejin.im/entry/5831b80e2e958a0069dcb9d1
 */
public class ScrollerLinearLayout extends LinearLayout {
    private final Scroller mScroller;
    private int mLeftBorder;
    private int mRightBorder;
    private float mXDown;
    private float mXMove;
    private float mXLastMove;
    private int mLastXIntercept;
    private int mLastYIntercept;

    public ScrollerLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++){
            View childAt = getChildAt(i);
            //为每一个子view测量大小
            measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int childCount = getChildCount();
            for (int i=0;i<childCount;i++){
                View childAt = getChildAt(i);
                //为每一个子view重新布局
                childAt.layout(i*childAt.getMeasuredWidth(),0,(i+1)*childAt.getMeasuredWidth(),childAt.getMeasuredHeight());
            }
            //初始化左右边界值
            mLeftBorder = getChildAt(0).getLeft();
            mRightBorder = getChildAt(getChildCount()-1).getRight();
        }
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

                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                // 判断是否左右滑动，是则拦截事件
                if(Math.abs(deltaX) > Math.abs(deltaY)){
                    intercepted = true;
                }else{
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
            default:break;
        }
        mLastXIntercept = x;
        mLastYIntercept = y;
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrolledX = (int) (mXLastMove - mXMove);
                if(getScrollX()+scrolledX < mLeftBorder){
                    scrollTo(mLeftBorder,0);
                    return true;
                }else if(getScrollX() + getWidth() + scrolledX>mRightBorder){
                    scrollTo(mRightBorder -getWidth(),0);
                    return true;
                }
                scrollBy(scrolledX,0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
                int targetIndex = (getScrollX() + getWidth() / 2)/getWidth();
                int dx = targetIndex*getWidth() - getScrollX();
                mScroller.startScroll(getScrollX(),0,dx,0);
                invalidate();
                break;
            default:break;
        }
        return super.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    public void startScroll(){
        mScroller.startScroll(getScrollX(),getScrollY(),-50,-50);
        invalidate();
    }
}
