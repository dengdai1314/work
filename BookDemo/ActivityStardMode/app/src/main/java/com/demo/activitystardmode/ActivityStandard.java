package com.demo.activitystardmode;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author 29003
 * @description
 * @date 2020/3/31
 */
public class ActivityStandard extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ActivityStandard",this.toString());
        setContentView(R.layout.activity_standard);
    }
}
