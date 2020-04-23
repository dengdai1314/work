package com.demo.activitystardmode;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * @author 29003
 * @description
 * @date 2020/4/1
 */
public class ActivitySingleInstance extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleinstance);
    }
}
