package com.obex.demo.model;
/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: NLPSongInfo.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/9 16:08
 * Changes (from 2019/7/9)
 * -----------------------------------------------------------------
 * 2019/7/9 : Create NLPSongInfo.java (liuhai);
 * -----------------------------------------------------------------
 * description:
 */

public class NLPSongInfo {
    public String author = "";//歌手
    public String songName = "";//歌曲名称
    public String audioPath = "";//路径
    public String ablumName = "";//专辑
    public String introduct = "";//介绍
    public long publishtime;

    public NLPSongInfo(String author, String songName) {
        this.author = author;
        this.songName = songName;
    }
}
