package com.practice.kawaii_loadingview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart,buttonFinish;
    private Kawaii_LoadingView kawaiiLoadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kawaiiLoadingView = findViewById(R.id.Kawaii_LoadingView);
        buttonStart = (Button)findViewById(R.id.start);
        buttonFinish = (Button)findViewById(R.id.finish);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kawaiiLoadingView.startMoving();
            }

        });

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kawaiiLoadingView.stopMoving();
            }

        });
    }
}
