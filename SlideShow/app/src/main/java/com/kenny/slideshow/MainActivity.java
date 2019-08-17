package com.kenny.slideshow;

import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    Timer timer = new Timer();
    Task task = new Task();
    Integer[] arrayImage = {R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4};
    List<Integer> images = Arrays.asList(arrayImage);
    ImageView pollImage;
    int poll_flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);

        pollImage = (ImageView) findViewById(R.id.poll_image);
        timer.schedule(task,5*1000,5*1000);
    }

    public class Task extends TimerTask{
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    pollImage.setImageResource(images.get(poll_flag));
                    poll_flag++;
                    if(poll_flag==4){finish();}
                }
            });
        }
    }

    /**
     * 活动消失后，调用该方法
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}



