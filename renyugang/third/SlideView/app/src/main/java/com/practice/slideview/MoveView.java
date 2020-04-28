package com.practice.slideview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2713:46
 * @description 自定义了一个View继承自ImageView，该ImageView可以跟随手指的触摸移动。
 */
public class MoveView extends AppCompatImageView {

    private int background;

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    int lastX = 0;
    int lastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
//                layout(getLeft() + offsetX,getTop() + offsetY,getRight()+offsetX,getBottom()+offsetY);
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) getLayoutParams();
                lp.leftMargin = getLeft()+offsetX;
                lp.topMargin  = getTop()+offsetY;
                setLayoutParams(lp);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
