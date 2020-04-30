package com.hencoder.hencoderpracticelayout2.Step;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;

import com.hencoder.hencoderpracticelayout2.R;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/3017:47
 * @description
 */
public class StepActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean isPause = false;

    WaveView waveView;
    CircleButton stopButton;
    CircleButton btnContinue;
    CircleWaveButton startButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_anim);

        waveView = findViewById(R.id.wave_view);
        stopButton  = findViewById(R.id.stop);
        btnContinue = findViewById(R.id.bt_continue);
        startButton = findViewById(R.id.start);
        stopButton.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
        startButton.setOnClickListener(this);
        initView();
    }

    private void initView() {

        btnContinue.setTextColor(0x1e78be);
        startButton.setTextColor(0xbbd4e7);
        stopButton.setTextColor(0xcd3a33);
        startButton.start();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stop:     //停止并退出
                System.exit(0);
                break;
            case R.id.start:  //开始
                startAnimation();
                waveView.start();
                break;
            case R.id.bt_continue:  //暂停继续
                if (isPause){
                    btnContinue.setText("暂停");
                    isPause = false;
                    waveView.start();
                }else {
                    btnContinue.setText("继续");
                    isPause = true;
                    waveView.stop();
                }
                break;
        }
    }


    /**
     * 按钮动画效果实现的方法
     */
    private void startAnimation() {
        btnContinue.setVisibility(View.VISIBLE);//显示暂停按钮
        stopButton.setVisibility(View.VISIBLE);//显示停止按钮
        //获取屏幕的大小，并把屏幕的宽也就是x赋值给width
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        //属性动画，传入"alpha"参数，将开始按钮从不透明改变为透明
        Animator animatorStart = ObjectAnimator.ofFloat(startButton, "alpha", 1.0f, 0f);
        AnimatorSet animatorSetStart = new AnimatorSet();//组合动画
        animatorSetStart.playTogether(animatorStart);
        animatorSetStart.setInterpolator(new DecelerateInterpolator());//动画效果设置为减速
        animatorSetStart.setDuration(1000);//设置动画时长

        //将暂停按钮由透明改变为不透明
        Animator animatorContinue1 = ObjectAnimator.ofFloat(btnContinue, "alpha", 0f, 1.0f);
        animatorContinue1.setDuration(3000);//设置动画时长
        //将暂停按钮从中间向右边移动
        Animator animatorContinue2 = ObjectAnimator.ofFloat(btnContinue, "translationX", -width / 3, btnContinue.getX());
        animatorContinue2.setDuration(2000);//设置动画时长
        AnimatorSet animatorSetContinue = new AnimatorSet();
        animatorSetContinue.playTogether(animatorContinue1, animatorContinue2);// 并行
        animatorSetContinue.setInterpolator(new DecelerateInterpolator());//动画效果设置为减速

        //将结束按钮由透明改变为不透明
        Animator animatorStop1 = ObjectAnimator.ofFloat(stopButton, "alpha", 0f, 1.0f);
        animatorStop1.setDuration(3000);//设置动画时长
        //将结束按钮从中间向左边移动
        Animator animatorStop2 = ObjectAnimator.ofFloat(stopButton, "translationX", width / 3, 0);
        animatorStop2.setDuration(2000);//设置动画时长
        AnimatorSet animatorSetStop = new AnimatorSet();
        animatorSetStop.playTogether(animatorStop1, animatorStop2);// 并行
        animatorSetStop.setInterpolator(new DecelerateInterpolator());//动画效果设置为减速

        //启动三个按钮动画
        animatorSetStart.start();
        animatorSetStop.start();
        animatorSetContinue.start();

        startButton.setVisibility(View.GONE);
    }
}
