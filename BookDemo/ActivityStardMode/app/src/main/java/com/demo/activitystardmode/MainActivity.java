package com.demo.activitystardmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/14 13:55
 * @description 2.5 活动启动模式
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_standard = findViewById(R.id.btn_standard);
        Button btn_singleTop = findViewById(R.id.btn_singletop);
        Button btn_singleTask = findViewById(R.id.btn_singletask);
        Button btn_singleInstance = findViewById(R.id.btn_singleinstance);
        btn_standard.setOnClickListener(this);
        btn_singleTop.setOnClickListener(this);
        btn_singleTask.setOnClickListener(this);
        btn_singleInstance.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_standard:
                intent = new Intent(MainActivity.this,ActivityStandard.class);
                startActivity(intent);
                break;
            case R.id.btn_singletop:
                intent = new Intent(MainActivity.this,ActivitySingleTop.class);
                startActivity(intent);
                break;
            case R.id.btn_singletask:
                intent = new Intent(MainActivity.this,ActivitySingleTask.class);
                startActivity(intent);
                break;
            case R.id.btn_singleinstance:
                intent = new Intent();
                intent.setAction("com.demo.activitySingleInstance.demosingleinstance");
                startActivity(intent);
                break;
                default:break;
        }
    }
}
