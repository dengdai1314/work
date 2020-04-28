package com.practice.slideview;

import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/27 15:17
 * @description 随手指滑动的ImageView
 */
public class AnimeSlide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_slide);
        Button btn = findViewById(R.id.animBtn);
        //补间动画
        TranslateAnimation anim = new TranslateAnimation(0,100,0,100);
        anim.setDuration(3000);
        anim.start();
        btn.setAnimation(anim);
        //属性动画
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn,"translationX",0,100);
//        ObjectAnimator.setFrameDelay(3000);
//        objectAnimator.start();
    }
}
