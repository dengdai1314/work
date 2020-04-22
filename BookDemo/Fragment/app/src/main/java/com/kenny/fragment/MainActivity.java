package com.kenny.fragment;
/*
 *
 * File: MainActivity.java
 * Author: luohuojin
 * Create: 2019/9/5 10:03
 * Changes (from 2019/9/5)
 * -----------------------------------------------------------------
 * 2019/9/5 : Create MainActivity.java (29003);
 * -----------------------------------------------------------------
 * description:4.2碎片的使用方式
 */
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
        replaceFragment(new RightFragment());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                replaceFragment(new AnotherRightFragment());
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();//获取FragmentManager
        FragmentTransaction transaction = fragmentManager.beginTransaction();//开启一个事务，调用beginTransaction开启
        transaction.replace(R.id.right_layout,fragment);//向容器内添加/替换碎片
        transaction.addToBackStack(null);//可做出返回栈的状态
        transaction.commit();//提交事务。调用commit()方法完成
    }
}
