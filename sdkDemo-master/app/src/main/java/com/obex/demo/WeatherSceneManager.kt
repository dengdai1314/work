package com.obex.demo

import android.util.Log
import com.google.android.exoplayer2.Player
import com.obex.demo.model.AnimalManager
import com.obex.demo.model.SceneBean
import com.obex.demo.model.WeatherModel
import com.obex.demo.play.ObexAudioPlayer
import com.obex.lib.UnityCallbackListener
import com.obex.lib.UnityManager

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: WeatherSceneManager.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/11 13:44
 * Changes (from 2019/7/11)
 * -----------------------------------------------------------------
 * 2019/7/11 : Create WeatherSceneManager.java (liuhai);
 * -----------------------------------------------------------------
 * description:天气场景类模拟
 */

class WeatherSceneManager constructor(var sceneBean: SceneBean?) : UnityCallbackListener,
    ObexAudioPlayer.OnMediaListener {

    val TAG = "WeatherSceneManager"

    var firstId: Int = 0



    /**
     * 开始切换场景
     */
    open fun changeScene() {
        UnityManager.getInstance().addCallBackListener(this)
        sceneBean?.wdID?.let {
            Log.d(TAG, "开始切换天气场景")
            UnityManager.getInstance().changeScene("ChangeScene", it)
        }
    }

    /**
     * 动作回调成功
     */
    override fun playAnimComplete(animlId: Int) {
        if (animlId == firstId) {//如果回调的是第一个动作则开始播报TTS
            speakTTS()
        } else {//如果非第一个动作就播回调动作的下一个动作
            var id = AnimalManager.instant.getNextAnimalId(animlId)
            Log.d(TAG, "获取到animlId==$animlId" + "   下一个动作===$id")
            UnityManager.getInstance().playAnimation("PlayNextId", id)
        }
    }

    /**
     * 第一个动作播报完成后回调开始播报TTS
     */
    private fun speakTTS() {
        Log.d(TAG, "开始播报TTS")
        ObexAudioPlayer.instant.play(
            "file:///android_asset/tts_weather.wav", Player.REPEAT_MODE_OFF
            , this
        )
    }

    /**
     * 开始播报嘴部动画，女模的嘴部动画是3 男模的是20001
     * 嘴部动画是可以跟其它动作同时播报的
     * 1.播嘴部动画时同时播第一个动作的下一个动作以便衔接动作播报
     *
     */
    override fun onStart() {
        Log.d(TAG, "开始播报嘴部动画")
        UnityManager.getInstance().playAnimation("startmouth", 3)

        var id = AnimalManager.instant.getNextAnimalId(firstId)
        Log.d(TAG, "获取到animlId==$firstId" + "   下一个动作===$id")
        UnityManager.getInstance().playAnimation("PlayNextId", id)

    }

    /**
     * 结束播报后，停止嘴部动画
     *
     * 然后播报TTS结束动作
     */
    override fun onComplete() {
        Log.d(TAG, "停止播报嘴部动画")
        UnityManager.getInstance().playAnimation("startmouth", 2)
        sceneBean?.nUIID?.let { UnityManager.getInstance().hideUI("HideWeatherUi", it) }//隐藏天气的3D UI
        val ttsEnd = sceneBean?.ttsEndState?.get(0)
        ttsEnd?.let { UnityManager.getInstance().playAnimation("PlayEndTTSAnimal", it) }
        UnityManager.getInstance().removeCallBacListener(this)

        UnityManager.getInstance().changeScene("changeDefault",1)//切换回默认场景
    }


    /**
     * 切换场景成功
     */
    override fun changeSceneSuccess(sceneId: Int) {
        if (sceneId == sceneBean?.wdID) {//如果当前返回场景是天气
            playFirstAnimal()
        }
    }

    /**
     * 场景切换成功以后播放该场景的第一个动作,并同时显示天气UI
     */
    private fun playFirstAnimal() {
        sceneBean?.let {
            firstId = it.firstState[0]
            UnityManager.getInstance().playAnimation("PlayAnimal", firstId)
            showUI()
        }
    }
    override fun changeRoleSuccess() {
        Log.e(TAG, "切换角色成====")
     }
    /**
     * 显示3DUI，此处显示天气，用户根据自己的实际场景与Unity定义好对象即可
     */
    private fun showUI() {
        sceneBean?.let {
            UnityManager.getInstance().showUI("ShowWeatherUI", it.nUIID, WeatherModel())
        }
    }

    override fun initComplete() {

    }

}