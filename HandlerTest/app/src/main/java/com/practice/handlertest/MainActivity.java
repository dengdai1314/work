package com.practice.handlertest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/8 15:41
 * @description Handler实例  https://www.jianshu.com/p/e172a2d58905
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button inner;
    Button anonymous;
    Button post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inner = findViewById(R.id.innerClass);
        anonymous = findViewById(R.id.anonymousClass);
        post = findViewById(R.id.post);
        inner.setOnClickListener(this);
        anonymous.setOnClickListener(this);
        post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.innerClass:
                Intent intent = new Intent(MainActivity.this,InnerActivity.class);
                startActivity(intent);
                break;
            case R.id.anonymousClass:
                Intent intent1 = new Intent(MainActivity.this,AnonymousInnerActivity.class);
                startActivity(intent1);
                break;
            case R.id.post:
                Intent intent2 = new Intent(MainActivity.this,PostActivity.class);
                startActivity(intent2);
                break;
            default:break;
        }
    }
}
