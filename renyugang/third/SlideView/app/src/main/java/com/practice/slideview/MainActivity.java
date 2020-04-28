package com.practice.slideview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/27 15:18
 * @description 自定义控件之 View 的滑动（二）https://blog.csdn.net/airsaid/article/details/53143754#%E9%9A%8F%E6%89%8B%E6%8C%87%E6%BB%91%E5%8A%A8%E7%9A%84imageview
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_scrollSlide = findViewById(R.id.btn_scrollSlide);
        final Button btn_displacementSlide = findViewById(R.id.btn_displacementSlide);
        Button btn_layoutparamsSlide = findViewById(R.id.btn_layoutparamsSlide);
        btn_scrollSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ScrollSlide.class);
                startActivity(intent);
            }
        });
        btn_displacementSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AnimeSlide.class);
                startActivity(intent);
            }
        });
        btn_layoutparamsSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,changeLayout.class);
                startActivity(intent);
            }
        });
    }
}
