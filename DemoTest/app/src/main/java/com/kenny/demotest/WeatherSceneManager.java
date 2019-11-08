package com.kenny.demotest;

import android.util.Log;

import com.google.android.exoplayer2.Player;
import com.obex.lib.UnityCallbackListener;
import com.obex.lib.UnityManager;


/**
 * @author 29003
 * @description
 * @date 2019/7/20
 */
public class WeatherSceneManager implements UnityCallbackListener,ObexAudioPlayer.OnMediaListener{
    private Scene scene ;

    public WeatherSceneManager(Scene scene){
            this.scene=scene;
    }
    String TAG = "WeatherSceneManager";
    int firstId = 0;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * 开始切换场景
     */
    public void changeScene(){
        Log.d("test","切换场景");
        UnityManager.getInstance().addCallBackListener(this);
        if (scene!=null&&scene.getWdID()!=0){
            Log.d(TAG, "开始切换天气场景");
            UnityManager.getInstance().changeScene("ChangeScene", scene.getWdID());
        }
    }


    @Override
    public void initComplete() {

    }
    /**
     * 切换场景成功
     */
    @Override
    public void changeSceneSuccess(int sceneId) {
        Log.d("test","切换场景成功"+sceneId);
        if(scene!=null){
            //如果当前返回场景是天气
            if(sceneId==scene.getWdID()){
                playFirstAnimal();
            }
        }
    }
    /**
     * 场景切换成功以后播放该场景的第一个动作,并同时显示天气UI
     */
    private void playFirstAnimal(){
        Log.d("test","播放第一个动作");
        if (scene!=null){
            firstId = scene.getFirstState().get(0);
            UnityManager.getInstance().playAnimation("PlayAnimal",firstId);
            showUI();
        }
    }

    /**
     * 显示3DUI，此处显示天气，用户根据自己的实际场景与Unity定义好对象即可
     */
    private void showUI() {
        Log.d("test","显示UI");
        if(scene!=null){
            UnityManager.getInstance().showUI("ShowWeatherUI",scene.getNUIID(),new WeatherModel());
        }
    }

    /**
     * 动作回调成功
     */
    @Override
    public void playAnimComplete(int animlId) {
        Log.d("test","播放动作完成"+animlId);
        if (animlId == firstId){
            Log.d(TAG,"获取到animlID=="+animlId+"下=="+firstId);
            //如果回调的是第一个动作则开始播报TTS
            speakTTS();
        }
        else {
            Log.d(TAG,"获取到animlID=="+animlId+"下一=="+firstId);
            //如果非第一个动作就播回调动作的下一个动作
            int id = AnimalManager.getInstance().getNextAnimalId(animlId);
            Log.d(TAG,"获取到animlID=="+animlId+"下一个动作=="+id);
            UnityManager.getInstance().playAnimation("PlayNextId",id);
        }
    }

    /**
     * 第一个动作播放完成后回调开始播放tts
     */
    private void speakTTS(){
        Log.d("test","播放tts");
        Log.d(TAG, "开始播报TTS");
        ObexAudioPlayer.getInstance().play(
                "file:///android_asset/tts_weather.wav", Player.REPEAT_MODE_OFF,this
        );
    }

    @Override
    public void changeRoleSuccess() {
        Log.e(TAG, "切换角色成====");
    }



    /**
     * 开始播报嘴部动画，女模的嘴部动画是3 男模的是20001
     * 嘴部动画是可以跟其它动作同时播报的
     * 1.播嘴部动画时同时播第一个动作的下一个动作以便衔接动作播报
     *
     */
    @Override
    public void onStart() {
        Log.d(TAG, "开始播报嘴部动画");
        UnityManager.getInstance().playAnimation("startmouth", 3);
        int id = AnimalManager.getInstance().getNextAnimalId(firstId);
        Log.d(TAG, "获取到animlId=="+firstId + "   下一个动作==="+id);
        UnityManager.getInstance().playAnimation("PlayNextId", id);
    }

    /**
     * 结束播报后，停止嘴部动画
     *
     * 然后播报TTS结束动作
     */
    @Override
    public void onComplete() {
        Log.d(TAG,"停止播报嘴部动作");
        int ttsEnd =0;
        UnityManager.getInstance().playAnimation("startmouth",2);
        if (scene!=null&&scene.getNUIID()!=0){
            UnityManager.getInstance().hideUI("HideWeatherUi",scene.getNUIID());
        }
        if (scene!=null&&scene.getTtsStartState()!=null){
            ttsEnd = scene.getTtsEndState().get(0);
        }
        if(ttsEnd!=0){
            UnityManager.getInstance().playAnimation("PlayEndTTSAnimal",ttsEnd);
        }
        UnityManager.getInstance().removeCallBacListener(this);
        UnityManager.getInstance().changeScene("changeDefaultScene",1);
    }
}
