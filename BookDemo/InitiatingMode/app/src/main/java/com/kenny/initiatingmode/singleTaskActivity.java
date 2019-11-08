package com.kenny.initiatingmode;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class singleTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("singleTaskActivity",this.toString());
        setContentView(R.layout.activity_single_task);
    }
}
