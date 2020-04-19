package com.demo.activitystardmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

/**
 * @author 29003
 * @description
 * @date 2020/3/31
 */
public class ActivitySingleTop extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singletop);
        Button btn_othertop = findViewById(R.id.btn_otherTop);
        btn_othertop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySingleTop.this, OtherActivity.class);
                startActivity(intent);
            }
        });
    }
}
