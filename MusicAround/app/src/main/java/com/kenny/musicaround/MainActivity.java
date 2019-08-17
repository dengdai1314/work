package com.kenny.musicaround;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer = new MediaPlayer();
    int i=0 ;
    TextView left;
    TextView right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        left = findViewById(R.id.trumpet_left);
        right = findViewById(R.id.trumpet_right);
        //权限判断，如果没有权限就动态申请请求WRITE_EXTERNAL_STORAGE权限
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initMediaPlayer();//初始化播放器 MediaPlayer
        }
    }

    public void initMediaPlayer() {
        try {
            //创建File对象指定音频文件的路径
            mediaPlayer = MediaPlayer.create(this, R.raw.right);
            if(!mediaPlayer.isPlaying()){
                mediaPlayer.start();
                left.setTextColor(Color.RED);
            }
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if (i==0){
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.left);
                        mediaPlayer.start();
                        right.setTextColor(Color.RED);
                        i++;
                    }else if (i==1){

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
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
        }
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.Play:
//                //如果没在播放中，立刻开始播放。
//                if(!mediaPlayer.isPlaying()){
//                    mediaPlayer.start();
//                }
//                break;
//            default:
//                break;
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            //将播放器的相关资源释放
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
