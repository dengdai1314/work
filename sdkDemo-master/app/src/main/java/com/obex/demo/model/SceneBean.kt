package com.obex.demo.model

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: SceneNeab.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/6 13:56
 * Changes (from 2019/7/6)
 * -----------------------------------------------------------------
 * 2019/7/6 : Create SceneNeab.java (liuhai);
 * -----------------------------------------------------------------
 * description:
 */

data class SceneBean(
    var IsProgress: Boolean,
    var IsRhythm: Boolean,
    var endState: List<Int>,
    var errorState: List<Int>,
    var firstState: List<Int>,
    var gender: Int,
    var idle: List<Int>,
    var intType: Int,
    var listen: List<Int>,
    var nUIID: Int,
    var nUIIDs: List<Any>,
    var nextScene: List<Int>,
    var okState: List<Int>,
    var strScene: String,
    var ttsEndState: List<Int>,
    var ttsStartState: List<Int>,
    var wdID: Int
)