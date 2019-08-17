package com.kenny.xmlresolver;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
//        String wifiTest = sharedPreferences.getString("wifiTest","");
//        Log.d("MainActivity",wifiTest);
    }
}
