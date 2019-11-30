package com.demo.obexsdktest;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.demo.DataBase.AnimBean;
import com.demo.DataBase.SceneBean;
import com.demo.DataBase.UiBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.obex.lib.UnityCallbackListener;
import com.obex.lib.UnityManager;
import com.obex.lib.UnityPlayerActivity;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;
import org.angmarch.views.SpinnerTextFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: MainActivity.java
 * Author: 29003
 * Version: V100R001C01
 * Create: 2019/11/26 16:45
 * Changes (from 2019/11/26 )
 * -----------------------------------------------------------------
 * 2019/11/26 : Create MainActivity.java (29003);
 * -----------------------------------------------------------------
 * description:ObexSDK仿写
 */
public class MainActivity extends UnityPlayerActivity implements UnityCallbackListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    UnityConstants unityConstants;
    int sex = 1;
    int femalId = 1001;
    int manId = 2002;

    Button add;
    Button remove;
    Button change_sex;
    Button play_weather;
    NiceSpinner sp_scene;
    Button change_scene;
    NiceSpinner sp_anim;
    Button play_anim;
    NiceSpinner sp_ui;
    Button change_ui;
    LinearLayout ll_3d;

    List<SceneBean> sceneList = new ArrayList();//场景列表
    List<AnimBean> animList = new ArrayList();//动作列表
    List<UiBean> uiList = new ArrayList();//ui列表
    SceneBean sceneItem;//当前选中场景
    AnimBean animItem;//当前选中动作
    UiBean uiItem;//当前选中ui

    boolean isShowU3D = true; //是否显示3Dui
    boolean isUnityIinitSuccess = false;//是否Unity初始化成功
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new Gson();
        initData();
        unityConstants = new UnityConstants();
        unityConstants.setUnityPlayer(mUnityPlayer);
        UnityManager.getInstance().setLogLevel(4);
        UnityManager.getInstance().setCallbackListener(this);
        addClick();
    }

    private void initData(){
        add= findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        change_sex = findViewById(R.id.change_sex);
        play_weather = findViewById(R.id.play_weather);
        sp_scene = findViewById(R.id.spinner_scene);
        change_scene = findViewById(R.id.change_scene);
        sp_anim = findViewById(R.id.spinner_animal);
        play_anim = findViewById(R.id.play_anim);
        sp_ui = findViewById(R.id.spinner_ui);
        change_ui = findViewById(R.id.change_ui);
        ll_3d = findViewById(R.id.ll_3d);
        ll_3d.addView(mUnityPlayer);

        String sceneResult =getJson("SceneDatabase.json");
        String animResult =getJson("StateDatabase.json");
        String uiResult =getJson("UiDatabase.json");
        sceneList = gson.fromJson(sceneResult,new TypeToken<List<SceneBean>>(){}.getType());
        animList = gson.fromJson(animResult,new TypeToken<List<AnimBean>>(){}.getType());
        uiList = gson.fromJson(uiResult,new TypeToken<List<UiBean>>(){}.getType());

        sceneItem = sceneList.get(0);
        animItem = animList.get(0);
        uiItem = uiList.get(0);

        initSceneSpinner();
        initAnimSpinner();
        initUiSpinner();
    }

    private void initSceneSpinner(){
        SpinnerTextFormatter text_scene = new SpinnerTextFormatter<SceneBean>() {
            @Override
            public Spannable format(SceneBean item) {
                return new SpannableString(item.getStrScene());
            }
        };
        sp_scene.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                if(sp_scene.getSelectedItem() instanceof SceneBean){
                    sceneItem = (SceneBean) sp_scene.getSelectedItem();
                }
            }
        });
        sp_scene.setSpinnerTextFormatter(text_scene);
        sp_scene.setSelectedTextFormatter(text_scene);
        sp_scene.attachDataSource(sceneList);
    }

    private void initAnimSpinner(){
        SpinnerTextFormatter text_anim = new SpinnerTextFormatter<AnimBean>() {
            @Override
            public Spannable format(AnimBean item) {
                return new SpannableString(item.getStrName());
            }
        };
        sp_anim.setSpinnerTextFormatter(text_anim);
        sp_anim.setSelectedTextFormatter(text_anim);
        sp_anim.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                if(sp_anim.getSelectedItem() instanceof AnimBean){
                    animItem = (AnimBean) sp_anim.getSelectedItem();
                }
            }
        });
        sp_anim.attachDataSource(animList);
    }

    private void initUiSpinner(){
        SpinnerTextFormatter text_ui = new SpinnerTextFormatter<UiBean>() {
            @Override
            public Spannable format(UiBean item) {
                return new SpannableString(item.getStrName());
            }
        };
        sp_ui.setSpinnerTextFormatter(text_ui);
        sp_ui.setSelectedTextFormatter(text_ui);
        sp_ui.attachDataSource(uiList);
    }
    public void addClick(){
        addOrRemoveClick();
        changeSex();
    }

    public void addOrRemoveClick(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("测试","添加");
                unityConstants.addUnityView(ll_3d);
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("测试","移除");
                unityConstants.removeUnityView();
            }
        });
    }

    public void changeSex(){
        change_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(sex){
                    case 1:
                        Log.d("测试","改变");
                        UnityManager.getInstance().changeRole("ChangeRole",femalId);
                        sex = 2;
                        break;
                    case 2:
                        Log.d("测试","改变");
                        UnityManager.getInstance().changeRole("ChangeRole",manId);
                        sex = 2;
                        break;
                        default:break;
                }
            }
        });
    }

    /**
     * Unity初始化成功，只有这个地方回调Unity初始化成功，才能调用操作接口，如切换成功，播放动作等
     */
    @Override
    public void initComplete() {
        Toast.makeText(this,"Unity初始化成功",Toast.LENGTH_SHORT).show();
    }

    /**
     * 切换场景成功。调用切换场景方法后都会在此处回调场景切换成功，然后根据场景做不同的操作
     */
    @Override
    public void changeSceneSuccess() {

    }

    /**
     * 动作播放回调，每次用户播放动画结束都会在此处回调，这个时候用户可以接着播放下一个动作
     * @param animId 动作ID
     */
    @Override
    public void playAnimComplete(int animId) {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 读取json文件
     */
    public String getJson(String filename) {
        //创建实例stringBuilder以拼接字符串
        StringBuilder stringBuilder = new StringBuilder();
        String data = null;
        try {
            //调用方法访问asset文件夹，getAssets.list("a")//访问assets文件夹下的a目录
            //InputStreamReader:通过相应的字符编码方式读取字节输入流解码为字符输入流，处理字节
            //BufferedReader:字符输入流中的子类,读取字符流，处理字符文本
            AssetManager assetManager = MainActivity.this.getAssets();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            String line;
            //readLine:读取文本行，并将其返回为字符串。若无数据可读，则返回null
            //循环的从缓冲区读取数据（一行一行读取），拼接到stringBuilder中
            while ((line = bfr.readLine()) != null) {
                stringBuilder.append(line);
            }
            data = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
