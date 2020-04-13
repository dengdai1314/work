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
public class ActivitySingleTask extends BaseActivity implements View.OnClickListener {
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleask);
        Button btn_singleTask = findViewById(R.id.btn_singleTask);
        Button btn_otherActivity = findViewById(R.id.btn_otherActivity);
        btn_singleTask.setOnClickListener(this);
        btn_otherActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_singleTask:
                intent = new Intent(ActivitySingleTask.this,ActivitySingleTask.class);
                startActivity(intent);
                break;
            case R.id.btn_otherActivity:
                intent = new Intent(ActivitySingleTask.this, OtherActivity.class);
                startActivity(intent);
                break;
                default:break;
        }
    }
}
