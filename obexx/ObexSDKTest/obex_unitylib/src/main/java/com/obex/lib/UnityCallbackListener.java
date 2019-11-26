package com.obex.lib;
/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: UnityCallbackListener.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/5 11:05
 * Changes (from 2019/7/5)
 * -----------------------------------------------------------------
 * 2019/7/5 : Create UnityCallbackListener.java (liuhai);
 * -----------------------------------------------------------------
 * description:
 */

public interface UnityCallbackListener {
    /**
     * 初始化成功
     */
    void initComplete();

    /**
     * 切换场景成功
     */
    void changeSceneSuccess();

    /**
     * 动作播放结束
     *
     * @param animId 动作ID
     */
    void playAnimComplete(int animId);

}
