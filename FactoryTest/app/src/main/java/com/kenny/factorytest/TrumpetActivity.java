package com.kenny.factorytest;
/*
 *
 * File: TrumpetActivity.java
 * Author: 29003
 * Create: 2019/8/20 15:25
 * Changes (from 2019/8/20)
 * -----------------------------------------------------------------
 * 2019/8/20 : Create TrumpetActivity.java (29003);
 * -----------------------------------------------------------------
 * description:喇叭测试
 */

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TrumpetActivity extends BaseActivity implements View.OnClickListener {
    MediaPlayer mediaPlayer;                    //媒体播放器
    TextView trumpet_left;
    TextView trumpet_right;
    LinearLayout trumpet_home;
    LinearLayout trumpet_result;
    Button trumpet_up;
    Button trumpet_down;
    boolean trumpet_finish = false;             //测试是否结束
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trumpet);
        mediaPlayer = new MediaPlayer();        //实例媒体播放器
        trumpet_left = findViewById(R.id.trumpet_left);
        trumpet_right = findViewById(R.id.trumpet_right);
        trumpet_home = findViewById(R.id.trumpet_home);
        trumpet_result = findViewById(R.id.trumpet_result);
        trumpet_up = findViewById(R.id.key_up);
        trumpet_down = findViewById(R.id.key_down);
        trumpet_up.setOnClickListener(this);
        trumpet_down.setOnClickListener(this);
        initPermission();                      //调用BaseActivity初始化权限
        initMediaPlayer();
    }

    private void initMediaPlayer(){
        try{
            //指定当前音频文件路径
            mediaPlayer = MediaPlayer.create(this,R.raw.left);
            //判断当前播放器是否在播放
            if(!mediaPlayer.isPlaying()){
                mediaPlayer.start();           //开始播放
                saveJson("左声道","测试成功");
                trumpet_left.setTextColor(Color.RED);
            }
            //音频播放完成后回调
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    trumpet_left.setText("左声道测试完成");
                    mediaPlayer = MediaPlayer.create(TrumpetActivity.this,R.raw.right);//再次创建资源
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            trumpet_right.setText("右声道测试完成");
                            trumpet_result.setVisibility(View.VISIBLE);
                            trumpet_finish = true; //测试完成
                        }
                    });
                    mediaPlayer.start();
                    saveJson("trumpetTe_right","测试成功");
                    trumpet_right.setTextColor(Color.RED);
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 活动跳转后调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            //将播放器的相关资源释放
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    /**
     * 按钮点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.key_up:
                saveJson("喇叭测试","成功");
                skip();
                break;
            case  R.id.key_down:
                saveJson("喇叭测试","失败");
                skip();
                break;
        }
    }

    /**
     * 跳转活动
     */
    private void skip(){
        Intent intent_result = new Intent(TrumpetActivity.this,MicroActivity.class);
        startActivity(intent_result);
        finish();
    }
}
