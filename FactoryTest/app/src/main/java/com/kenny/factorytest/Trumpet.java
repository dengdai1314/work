package com.kenny.factorytest;
/*
 *
 * File: Trumpet.java
 * Author: 29003
 * Create: 2019/8/9 17:44
 * Changes (from 2019/8/9)
 * -----------------------------------------------------------------
 * 2019/8/9 : Create Trumpet.java (29003);
 * -----------------------------------------------------------------
 * description:喇叭测试
 */
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Trumpet extends BaseActivity implements View.OnClickListener {

    MediaPlayer mediaPlayer;
    boolean test_finish = false;//测试是否结束

    TextView left;
    TextView right;
    LinearLayout trumpet_home;
    LinearLayout trumpet_result;
    Button up;
    Button down;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trumpet);
        mediaPlayer = new MediaPlayer();
        left = findViewById(R.id.trumpet_left);
        right = findViewById(R.id.trumpet_right);
        trumpet_home = findViewById(R.id.trumpet_home);
        trumpet_result = findViewById(R.id.trumpet_result);
        up = findViewById(R.id.key_volume_up);
        down = findViewById(R.id.key_volume_down);
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        checkPermission();
    }

    /**
     * 检查权限
     */
    public void checkPermission(){
        //权限判断，如果没有权限就动态申请请求WRITE_EXTERNAL_STORAGE权限
        if (ContextCompat.checkSelfPermission(Trumpet.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Trumpet.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);//请求权限
        } else {
            initMediaPlayer();//初始化播放器
        }
    }

    /**
     * 初始化播放器
     */
    public void initMediaPlayer() {
        try {
            //指定音频文件的路径
            mediaPlayer = MediaPlayer.create(this, R.raw.left);
            //判断当前播放器是否在播放
            if(!mediaPlayer.isPlaying()){
                //开始播放
                mediaPlayer.start();
                saveJson("trumpetTe_left","测试成功");
                left.setTextColor(Color.RED);
            }
            //音频播放完成后回调
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    left.setText("左声道测试完成");
                    mediaPlayer = MediaPlayer.create(Trumpet.this,R.raw.right);//再次创建资源
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            right.setText("右声道测试完成");
                            trumpet_result.setVisibility(View.VISIBLE);
                            test_finish=true;//测试完成
                        }
                    });
                    mediaPlayer.start();
                    saveJson("trumpetTe_right","测试成功");
                    right.setTextColor(Color.RED);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 请求权限回调结果
     * 如果用户拒绝权限申请，调用finish方法将程序直接关掉
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }else{
                    Toast.makeText(this, "拒绝权限，将无法使用程序。", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

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
        if(test_finish){//测试完成后点击按钮才可以用
            switch (view.getId()){
                case R.id.key_volume_up:
                    saveJson("喇叭测试","成功");
                    skip();
                    break;
                case  R.id.key_volume_down:
                    saveJson("喇叭测试","失败");
                    skip();
                    break;
            }
        }
    }

    /**
     * 跳转活动
     */
    public void skip(){
        if(test_finish){
            Intent intent_result = new Intent(Trumpet.this,MicroPhone.class);
            startActivity(intent_result);
            finish();
        }
    }
}
