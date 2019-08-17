package com.kenny.factorytest;
/*
 *
 * File: MainActivity.java
 * Author: 29003
 * Create: 2019/8/3 11:51
 * Changes (from 2019/8/3)
 * -----------------------------------------------------------------
 * 2019/8/3 : Create MainActivity.java (29003);
 * -----------------------------------------------------------------
 * description:应用首页/按钮测试
 */
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    String TAG = MainActivity.class.getSimpleName();
    //按钮未按下
    boolean isMute = false;
    boolean isVolumeUp = false;
    boolean isVolumeDown = false;
    boolean isWifi = false;
    boolean isalldown = false;
    TextView button_hint;
    Button Mute;
    Button VolumeUp;
    Button VolumeDown;
    Button Wifi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化只能在oncreate里，请记住了
        button_hint= (TextView) findViewById(R.id.button_hint);
        Mute = (Button) findViewById(R.id.key_mute);
        VolumeUp = (Button) findViewById(R.id.key_volume_up);
        VolumeDown = (Button) findViewById(R.id.key_volume_down);
        Wifi = (Button) findViewById(R.id.key_wifi);
        //设置点击监听器（一定要设置，不然点了没用
        Mute.setOnClickListener(this);
        VolumeUp.setOnClickListener(this);
        VolumeDown.setOnClickListener(this);
        Wifi.setOnClickListener(this);
    }

    /**
     * 设置点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.key_mute:
                keyMuteEvent();
                break;
            case R.id.key_volume_up:
                keyVolumeUpEvent();
                break;
            case R.id.key_volume_down:
                keyVolumeDownEvent();
                break;
            case R.id.key_wifi:
                keyWifiDownEvent();
                break;
        }
    }

    /**
     * Mute按键点击事件
     */
    public void keyMuteEvent(){
        Log.e(TAG,"Mute按键响应成功");
        TextView muteView = (TextView) findViewById(R.id.mute);
        muteView.setTextColor(Color.RED);//设置字体为红色
        muteView.setText("Mute键：响应成功");//改变当前字体
        isMute=true;
        isalldown();
    }

    /**
     * 音量+键按键点击事件
     */
    public void keyVolumeUpEvent(){
        Log.e(TAG,"音量+键响应成功");
        TextView upView = (TextView) findViewById(R.id.volume_up);
        upView.setTextColor(Color.RED);
        upView.setText("音量+键：响应成功");
        isVolumeUp=true;
        isalldown();
        skip("+");
    }

    /**
     * 音量-键按键点击事件
     */
    public void keyVolumeDownEvent(){
        Log.e(TAG,"音量-键响应成功");
        TextView downView = (TextView) findViewById(R.id.volume_down);
        downView.setTextColor(Color.RED);
        downView.setText("音量-键：响应成功");
        isVolumeDown=true;
        isalldown();
        skip("-");
    }

    /**
     *  wifi按键点击事件
     */
    public void keyWifiDownEvent(){
        Log.e(TAG,"Wifi按键响应成功");
        TextView wifiView = (TextView) findViewById(R.id.wifi);
        wifiView.setTextColor(Color.RED);
        wifiView.setText("Wifi键：响应成功");
        isWifi=true;
        isalldown();
    }

    /**
     * 判断按键是否全部按下，如果按下，显示测试提示
     * @return
     */
    public boolean isalldown(){
        if(isMute == true&&isVolumeDown == true&&isVolumeUp == true&&isWifi == true){
            button_hint.setVisibility(View.VISIBLE);            //显示测试完成提示
            isVolumeDown=false;
            isVolumeUp=false;
            saveJson("按键+键","测试成功");
            saveJson("按键-键","测试成功");
            saveJson("Mute按键","测试成功");
            saveJson("Wifi按键","测试成功");
            isalldown = true;
        }
        return isalldown;
    }

    /**
     * 根据按键+/-判断测试结果并保存，随后跳转lcd测试
     * @param key
     */
    public void skip(String key){
        if (isalldown()){
            switch (key){
                case "+":
                    saveJson("按键测试","成功");
                    break;
                case "-":
                    saveJson("按键测试","失败");
                    break;
            }
            //跳转活动
            skip(MainActivity.this,LcdTestFirst.class);
        }
    }
}
