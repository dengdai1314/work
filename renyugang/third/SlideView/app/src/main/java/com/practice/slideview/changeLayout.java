package com.practice.slideview;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class changeLayout extends AppCompatActivity {
    MoveView moveView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_layout);
        moveView = findViewById(R.id.moveView);
    }
    public void set(View v){
        moveView.setBackground(R.drawable.ic_launcher_background);
    }
}
