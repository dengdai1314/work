package com.praactise.playmultmediatest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/14 14:10
 * @description 8.4 播放多媒体文件
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Button play;
    public Button pause;
    public Button stop;
    public Button intentVideo;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        intentVideo = findViewById(R.id.intent_video);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        intentVideo.setOnClickListener(this);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            initMediaPlayer();
        }
    }

    private void initMediaPlayer() {
        try{
            File file = new File(Environment.getExternalStorageDirectory(),"music.mp3");
            //设置要播放的音频文件的位置
            mediaPlayer.setDataSource(file.getPath());
            //获取载入的音频文件的时长
            //在开始播放之前调用这个方法完成准备工作
            mediaPlayer.prepare();
            int length = mediaPlayer.getDuration();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play:
                //判断当前MediaPlayer是否正在播放音频
                if(!mediaPlayer.isPlaying()){

                    //开始或继续播放音频
                    mediaPlayer.start();
                }
                break;
            case R.id.pause:
                if(mediaPlayer.isPlaying()){
                    //暂停播放音频
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if(mediaPlayer.isPlaying()){
                    //将MediaPlayer对象重置到刚创建的状态
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            case R.id.intent_video:
                Intent intentVideo = new Intent(this,PlayVideo.class);
                startActivity(intentVideo);
                default:break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length>0&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }else {
                    Toast.makeText(MainActivity.this,"你拒绝了权限将无法使用应用程序",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null){
            //停止播放音频，调用这个方法后的MediaPlayer对象无法在播放音频
            mediaPlayer.stop();
            //释放掉与MediaPlayer对象相关的资源
            mediaPlayer.release();
        }
    }
}
