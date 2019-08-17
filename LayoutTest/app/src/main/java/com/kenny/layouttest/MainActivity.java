package com.kenny.layouttest;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar!= null) {
            actionbar.hide();
        }
    }

}
