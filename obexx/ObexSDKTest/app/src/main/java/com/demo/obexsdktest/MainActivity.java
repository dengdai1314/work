package com.demo.obexsdktest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.obex.lib.UnityCallbackListener;
import com.obex.lib.UnityManager;
import com.obex.lib.UnityPlayerActivity;
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
    LinearLayout ll_3d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add= findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        change_sex = findViewById(R.id.change_sex);
        ll_3d = findViewById(R.id.ll_3d);
        ll_3d.addView(mUnityPlayer);
        unityConstants = new UnityConstants();
        unityConstants.setUnityPlayer(mUnityPlayer);
        UnityManager.getInstance().setLogLevel(4);
        UnityManager.getInstance().setCallbackListener(this);
        addClick();
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

    @Override
    public void initComplete() {
        Toast.makeText(this,"Unity初始化成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeSceneSuccess() {

    }

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
}
