package com.practice.intenttest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Intent intent = getIntent();
        String data = intent.getStringExtra("键");
        Toast.makeText(DataActivity.this,"传输的数据：  "+data,Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent();
        intent1.putExtra("data_return","Hello FirstActivity");
        setResult(RESULT_OK,intent1);
        finish();
    }
}
