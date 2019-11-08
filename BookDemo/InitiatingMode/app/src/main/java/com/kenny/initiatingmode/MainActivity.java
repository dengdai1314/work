package com.kenny.initiatingmode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 启动模式
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("MainActivity",this.toString());
        setContentView(R.layout.activity_main);
        Button standard = findViewById(R.id.standard);
        Button singleTop = findViewById(R.id.singleTop);
        Button singleInstance = findViewById(R.id.singleInstance);
        Button singleTask = findViewById(R.id.singleTask);
        standard.setOnClickListener(this);
        singleTop.setOnClickListener(this);
        singleInstance.setOnClickListener(this);
        singleTask.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.standard:
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);

                break;
            case R.id.singleTop:
                Intent intent1 = new Intent(MainActivity.this,singleTopActivity.class);
                startActivity(intent1);

                break;
            case R.id.singleTask:
                Intent intent2 = new Intent(MainActivity.this,singleTaskActivity.class);
                startActivity(intent2);

                break;
            case R.id.singleInstance:
                Intent intent3 = new Intent(MainActivity.this,singleInstanceActivity.class);
                startActivity(intent3);

                break;
                default:
                    break;
        }
    }
}
