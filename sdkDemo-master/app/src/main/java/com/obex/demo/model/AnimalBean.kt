package com.obex.demo.model

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: AnimalBean.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/6 13:44
 * Changes (from 2019/7/6)
 * -----------------------------------------------------------------
 * 2019/7/6 : Create AnimalBean.java (liuhai);
 * -----------------------------------------------------------------
 * description:
 */
data class AnimalBean(
    var IsBreak: Boolean,
    var IsLoop: Boolean,
    var listen: List<Int>,
    var nAnimationType: Int,
    var nSceneID: Int,
    var nextState: String,
    var stateTime: Double,
    var strDes: String,
    var strName: String,
    var wdID: Int
)


