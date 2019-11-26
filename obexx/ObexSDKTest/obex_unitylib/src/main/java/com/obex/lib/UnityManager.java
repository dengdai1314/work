package com.obex.lib;
/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: UnityManager.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/5 11:11
 * Changes (from 2019/7/5)
 * -----------------------------------------------------------------
 * 2019/7/5 : Create UnityManager.java (liuhai);
 * -----------------------------------------------------------------
 * description:
 */

import android.util.Log;

public class UnityManager {

    public static final String TAG = UnityManager.class.getSimpleName();

    private static UnityManager sInstance;
    public AndroidProxy mUnityAndroidProxy;

    public UnityCallbackListener callbackListener;//Unity返回回调

    private int logLevel = 0;//日志等级

    public static UnityManager getInstance() {
        if (sInstance == null) {
            synchronized (UnityManager.class) {
                if (sInstance == null) {
                    sInstance = new UnityManager();
                }
            }
        }
        return sInstance;
    }

    public UnityCallbackListener getCallbackListener() {
        return callbackListener;
    }

    public void setCallbackListener(UnityCallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }

    /**
     * 错误回调
     *
     * @param type
     * @param errCode
     * @param errMessage
     */
    public void UnityCallBack(final int type, final int errCode, final String errMessage) {
        Log.d(TAG, "type==" + type + "errorCode=" + errCode + "errMessage===" + errMessage);

    }


    /**
     * Android主动调用动作、切换场景等回调
     *
     * @param type
     * @param message
     */
    public void ResultToAndroid(int type, String message) {

    }

    public int getLogLevel() {
        return logLevel;
    }

    /**
     * 主动通知客户端事件，如：初始化完成
     *
     * @param type    事件类型
     * @param message
     */
    public void UnityCallAndroid(int type, String message) {
        Log.d(TAG, "type===" + type + "   message==" + message);
        if (type == UnityType.CHANGE_SCENE) {
            //切换成功以后设置切换成功并设置当前场景
            if (callbackListener != null) {
                callbackListener.changeSceneSuccess();
            }
        } else if (type == UnityType.PLAY_ANIM_END) {//动作播放结束
            if (callbackListener != null) {
                callbackListener.playAnimComplete(Integer.parseInt(message));
            }
        } else if (type == UnityType.UNITY_INIT) {
            Log.d(TAG, "Unity初始化完成了---------");
            if (callbackListener != null) {
                callbackListener.initComplete();
            }
        } else if (type == UnityType.UNITY_MANAGER_INIT) {
            setLogLevel(logLevel);
        }
    }

//    /**
//     * 播放特效
//     *
//     * @param functionKey
//     * @param effectPath
//     * @param effectId
//     */
//    public void playEffect(String functionKey, String effectPath, int effectId) {
//        if (mUnityAndroidProxy != null) {
//            mUnityAndroidProxy.PlayEffect(functionKey,
//                    effectPath,
//                    effectId);
//        }
//    }


    /**
     * 显示任务UI
     *
     * @param functionkey 事件名称
     * @param canvasID    UI显示类型
     * @param javaObject
     */
    public void showUI(String functionkey, int canvasID, Object javaObject) {
        if (mUnityAndroidProxy != null && canvasID != 0) {
            mUnityAndroidProxy.ShowUI(functionkey, canvasID, javaObject);
        }
    }

    /**
     * 隐藏任务UI
     *
     * @param functionkey 事件名称
     * @param canvasID
     */
    public void hideUI(String functionkey, int canvasID) {
        if (mUnityAndroidProxy != null) {
            mUnityAndroidProxy.HideUI(functionkey, canvasID);
        }
    }

    /// <summary>
    /// 播放转场动画
    /// </summary>

    /**
     * 设置位置
     *
     * @param postion
     */
    public void playTransAnimation(float[] postion) {
        Log.d(TAG, "播放转场动画");
        Log.d(TAG, "设置Position" + postion);
        PosModel posModel = new PosModel();
        posModel.setPosArray(postion);
        if (mUnityAndroidProxy != null) {
            mUnityAndroidProxy.PlayTransAnimation("playTransAnimation", posModel);
        }
    }


    /**
     * 设置位置和旋转 javaObject 包含字段float[] posArray 长度为6 前三位是位置 后三位是旋转
     *
     * @param postion
     */
    public void setPlayerPosAndRot(float[] postion) {
        if (mUnityAndroidProxy != null) {
            PosModel posModel = new PosModel();
            posModel.setPosArray(postion);
            mUnityAndroidProxy.SetPlayerPosAndRot("SetPlayerPosAndRot", posModel);
        }
    }

    /**
     * 播放动画
     *
     * @param functionkey
     * @param animId      动画ID
     */
    public void PlayAnimation(String functionkey, int animId) {
        if (mUnityAndroidProxy != null) {
            mUnityAndroidProxy.PlayAnimation(functionkey, animId);

        }
    }


    /**
     * 切换场景
     *
     * @param sceneID
     */
    public void changeScene(String functionkey, int sceneID) {
        if (mUnityAndroidProxy != null) {
            mUnityAndroidProxy.ChangeScene(functionkey, sceneID);
        }
    }


    /**
     * 刷新模块数据
     *
     * @param functionkey
     * @param modelType
     * @param javaObject
     */

    public void refreshData(String functionkey, int modelType, Object javaObject) {
        if (mUnityAndroidProxy != null) {
            mUnityAndroidProxy.RefreshData(functionkey, modelType, javaObject);
        }
    }


    /// <summary>
    /// 更换人物角色
    /// </summary>
    /// <param name="functionKey"></param>
    /// <param name="roleID">角色ID</param>

    /**
     * 切换角色
     *
     * @param functionKey
     * @param roleID      角色ID
     */
    public void changeRole(String functionKey, int roleID) {
        if (mUnityAndroidProxy != null) {
            mUnityAndroidProxy.ChangeRole(functionKey, roleID);
        }
    }

    /**
     * 设置日志等级
     *
     * @param logLevel int值，0为无log，1为Error，2为warning，3为info，4为全部
     */
    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
        if (mUnityAndroidProxy != null) {
            mUnityAndroidProxy.SetLogLevel("SetLogLevel", logLevel);
        }
    }

}





