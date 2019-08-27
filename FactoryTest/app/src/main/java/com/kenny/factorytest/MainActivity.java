package com.kenny.factorytest;
/*
 *
 * File: MainActivity.java
 * Author: 29003
 * Create: 2019/8/20 15:02
 * Changes (from 2019/8/20)
 * -----------------------------------------------------------------
 * 2019/8/20 : Create MainActivity.java (29003);
 * -----------------------------------------------------------------
 * description:按键测试
 */
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static String TAG = MainActivity.class.getSimpleName();
    Button ButtonMute;
    Button ButtonUp;
    Button ButtonDown;
    Button ButtonWifi;
    TextView MuteText;
    TextView UpText;
    TextView DownText;
    TextView WifiText;
    TextView MainHint;
    Boolean isMute = false;
    Boolean isUp = false;
    Boolean isDown = false;
    Boolean isWifi = false;
    Boolean isalldown = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化只能在oncreate里，请记住了
        ButtonMute = findViewById(R.id.key_mute);
        ButtonUp = findViewById(R.id.key_up);
        ButtonDown = findViewById(R.id.key_down);
        ButtonWifi = findViewById(R.id.key_wifi);
        MuteText = findViewById(R.id.main_mute);
        UpText = findViewById(R.id.main_up);
        DownText = findViewById(R.id.main_down);
        WifiText = findViewById(R.id.main_wifi);
        MainHint =  findViewById(R.id.main_hint);
        //设置点击监听器（一定要设置，不然点了没用
        ButtonMute.setOnClickListener(this);
        ButtonUp.setOnClickListener(this);
        ButtonDown.setOnClickListener(this);
        ButtonWifi.setOnClickListener(this);
        initPermission();       //调用BaseActivity初始化权限
    }

    /**
     * 点击按键监听
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.key_mute:
                clickMute();
                break;
            case R.id.key_up:
                clickUp();
                break;
            case R.id.key_down:
                clickDown();
                break;
            case R.id.key_wifi:
                clickWifi();
                break;
        }
    }

    /**
     * 按键点击事件
     */
    private void clickMute() {
        Log.e(TAG,"Mute按键响应成功");
        MuteText.setTextColor(Color.RED);//设置字体为红色
        MuteText.setText("Mute键：响应成功");//改变当前字体
        isMute = true;
        isalldown();
    }
    private void clickUp(){
        Log.e(TAG,"音量+键响应成功");
        UpText.setTextColor(Color.RED);
        UpText.setText("音量+键：响应成功");
        isUp = true;
        isalldown();
        skip("+");
    }
    private void clickDown(){
        Log.e(TAG,"音量-键响应成功");
        DownText.setTextColor(Color.RED);
        DownText.setText("音量-键：响应成功");
        isDown = true;
        isalldown();
        skip("-");
    }
    private void clickWifi(){
        Log.e(TAG,"Wifi按键响应成功");
        WifiText.setTextColor(Color.RED);
        WifiText.setText("Wifi键：响应成功");
        isWifi = true;
        isalldown();
    }
    /**
     * 判断按键是否全部按下，如果按下，显示测试提示
     * @return
     */
    private boolean isalldown(){
        if(isMute == true&&isDown == true&&isUp == true&&isWifi == true){
            MainHint.setVisibility(View.VISIBLE);            //显示测试完成提示
            isDown=false;
            isUp=false;
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
    private void skip(String key){
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
            skip(MainActivity.this,LcdActivity.class);
        }
    }
}
