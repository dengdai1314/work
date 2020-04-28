package com.kenny.layouttest;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author 29003
 * @description 自定义标题控件，新建titlelayout继承自linearlayout
 * @date 2019/7/11
 */
public class TitleLayout extends LinearLayout {
    public TitleLayout (Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.title_layout,this);
        Button titleBack= (Button) findViewById(R.id.back);
        Button titleEdit= (Button) findViewById(R.id.edit);
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) getContext()).finish();
            }
        });
        titleEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "you click buttonn", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
