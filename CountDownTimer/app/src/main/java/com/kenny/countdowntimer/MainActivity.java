package com.kenny.countdowntimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    public void initview(){
        if(timer == null){
            timer = new CountDownTimer(10000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.e("cshi",millisUntilFinished/1000+"");
                }

                @Override
                public void onFinish() {
                    Log.e("cshi","nihaio");
                }
            };
            timer.start();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

}
