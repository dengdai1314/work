package com.kenny.activitytest;
/*
 *
 * File: FirstActivity.java
 * Author: luohuojin
 * Create: 2019/9/4 14:03
 * Changes (from 2019/9/4)
 * -----------------------------------------------------------------
 * 2019/9/4 : Create FirstActivity.java (29003);
 * -----------------------------------------------------------------
 * description:监听按钮点击事件，跳转活动。菜单menu项
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
//    /**
//     * 点击按钮显示toast提示
//     * onclicklistener 点击监听事件
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.first_layout);
//        Button change = (Button) findViewById(R.id.change);
//        change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(FirstActivity.this,"爱你",Toast.LENGTH_SHORT).show();
//            }
//
//        });
//    }

//    /**
//     * 点击按钮关闭应用
//     * onclicklistener 点击监听事件
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.first_layout);
//        Button change = (Button) findViewById(R.id.change);
//        change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//
//        });
//    }

        /**
          * 点击按钮切换活动
          * 显示Intent
          */
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.first_layout);
            Button change = (Button) findViewById(R.id.change);
            change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                    startActivity(intent);
                }

            });
        }

    /**
     * 菜单项
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    /*菜单项点击*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "you click add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "you click remove", Toast.LENGTH_LONG).show();
                break;
            default:
        }
        return true;
    }
}
