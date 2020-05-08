package com.hencoder.hencoderpracticelayout2.Step;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;

import com.hencoder.hencoderpracticelayout2.R;

import java.io.File;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/3017:47
 * @description 计步器 https://blog.csdn.net/m366917/article/details/52976877
 * 总结：坑：button.setColor有可能无效，为什么呢，方法重载了，要填入正确的参数
 *      波纹只有低于api24后才可以用
 *
 */
public class StepActivity extends AppCompatActivity implements View.OnClickListener{
//    CircleWaveButton start;
//    CircleButton stop;
//    CircleButton bt_continue;
//    WaveView waveView;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.step_anim);
//        start = findViewById(R.id.start);
//        stop = findViewById(R.id.stop);
//        bt_continue = findViewById(R.id.bt_continue);
//        waveView = findViewById(R.id.wave_view);
//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                stop.setVisibility(View.VISIBLE);
//                bt_continue.setVisibility(View.VISIBLE);
//            }
//        });
//        stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                waveView.stop();
//            }
//        });
//    }

    private boolean isPause = false;

    WaveView waveView;
    CircleButton stopButton;
    CircleButton btnContinue;
    CircleWaveButton startButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
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
        btnContinue.setPaintColor(0xff1e78be);
        startButton.setPaintColor(0xffbbd4e7);
        stopButton.setPaintColor(0xffcd3a33);
        startButton.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stop:     //停止并退出
                Intent intent = new Intent(StepActivity.this,StepActivity.class);
                startActivity(intent);
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

    public File getDiskCacheDir(Context context,String uniqueName){
        String cachePath;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                ||!Environment.isExternalStorageRemovable()){
            cachePath = context.getExternalCacheDir().getPath();
        }else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

}
