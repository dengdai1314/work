package com.kenny.sdktest;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.obex.lib.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity  {
    private static final String TAG = MainActivity.class.getSimpleName();
    LinearLayout ll_3d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_3d = findViewById(R.id.ll_3d);
        ll_3d.addView(mUnityPlayer);
//        UnityManager.getInstance().setLogLevel(4);
//        UnityManager.getInstance().addCallBackListener( this);
    }

//    /**
//     * Unity初始化成功，只有这个地方回调Unity初始化成功，才能调用操作
//     * 接口，如切换场景，播放动作等
//     */
//    @Override
//    public void initComplete() {
//        Log.e(TAG,"Unity初始化完成");
//        Toast.makeText(MainActivity.this, "Unity初始化完成", Toast.LENGTH_SHORT).show();
//        UnityManager.getInstance().changeScene("changeDefault", 1);//切换默认场景
//    }
//
//    /**
//     * 切换场景成功。调用切换场景方法后都会在此回调场景切换成功，然后根据场景作不同操作
//     * sceneId切换场景ID
//     * @param sceneId
//     */
//    @Override
//    public void changeSceneSuccess(int sceneId) {
////        UnityManager.getInstance().changeScene("changeScene",1);//改变当前场景
//        Log.e(TAG,"切换场景成功");
//    }
//
//    /**
//     * 动作播放回调
//     * animId 回调结束动作ID
//     * @param animId
//     */
//    @Override
//    public void playAnimComplete(int animId) {
////        UnityManager.getInstance().playAnimation("playanimal",208);
//        Log.e(TAG,"播放动作结束："+animId);
//    }
//
//    /**
//     * 角色切换成功
//     */
//    @Override
//    public void changeRoleSuccess() {
////        UnityManager.getInstance().changeRole("ChangeRole",1002);//改变当前角色
//        Log.e(TAG,"切换角色成功");
//    }
//
//    /**
//     * 显示任务UI
//     * @param functionkey   事件名称
//     * @param canvasId      UI显示类型，如天气是2
//     * @param javaObject    显示数据类型，必须与Unity定义好对应的字段，且字段的修饰是public
//     */
//    public void showUI(String functionkey,int canvasId,Object javaObject){
////        UnityManager.getInstance().showUI("ShowUi",UID,WeatherModel());
//    }
//
//    public void hideUI(String functionkey,int canvasId){
////        UnityManager.getInstance().hideUI("hideUi",UID);
//    }
}
