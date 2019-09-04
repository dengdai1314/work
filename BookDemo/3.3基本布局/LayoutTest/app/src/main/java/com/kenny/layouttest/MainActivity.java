package com.kenny.layouttest;
/*
 *
 * File: MainActivity.java
 * Author: luohuojin
 * Create: 2019/9/4 14:16
 * Changes (from 2019/9/4)
 * -----------------------------------------------------------------
 * 2019/9/4 : Create MainActivity.java (29003);
 * -----------------------------------------------------------------
 * description:基本布局：线性布局，相对布局，帧布局，百分比布局
 */
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar!= null) {
            actionbar.hide();
        }
    }

}
