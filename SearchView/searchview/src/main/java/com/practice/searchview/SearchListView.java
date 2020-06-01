package com.practice.searchview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/1911:00
 * @description 显示历史搜索内容listView
 */
public class SearchListView extends ListView {

    public SearchListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SearchListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchListView(Context context) {
        super(context);
    }

    // 通过复写其onMeasure方法，达到对ScrollView适配的效果
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置视图高度，并设置视图模式为AT_MOST,自适应大小
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
