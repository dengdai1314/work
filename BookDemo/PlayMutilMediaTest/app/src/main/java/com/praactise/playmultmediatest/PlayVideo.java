package com.praactise.playmultmediatest;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;

public class PlayVideo extends AppCompatActivity implements View.OnClickListener {
    public Button play;
    public Button pause;
    public Button replay;
    public VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        replay = findViewById(R.id.replay);
        videoView = findViewById(R.id.video_view);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);
        initVideoPlayer();
    }

    private void initVideoPlayer() {
        File videoFile = new File(Environment.getExternalStorageDirectory(),"movie.mp4");

        //设置要播放的视频文件的位置
        videoView.setVideoPath(videoFile.getPath());
        int length = videoView.getDuration();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                if(!videoView.isPlaying()){
                    //开始或继续播放视频
                    videoView.start();
                }
                break;
            case R.id.pause:
                if(videoView.isPlaying()){
                    //暂停播放视频
                    videoView.pause();
                }
                break;
            case R.id.replay:
                if(videoView.isPlaying()){
                    //将视频从头开始播放
                    videoView.resume();
                }
                break;
                default:break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView!=null){
            videoView.suspend();
        }
    }
}
