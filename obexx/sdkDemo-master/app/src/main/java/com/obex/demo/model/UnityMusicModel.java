package com.obex.demo.model;
/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: UnityMusicModel.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/9 15:56
 * Changes (from 2019/7/9)
 * -----------------------------------------------------------------
 * 2019/7/9 : Create UnityMusicModel.java (liuhai);
 * -----------------------------------------------------------------
 * description:
 */

public class UnityMusicModel {
    public Object[] nlpSongInfos;//歌曲列表
    public int  index;//当前正在播放歌舞索引

    public Object[] getNlpSongInfos() {
        return nlpSongInfos;
    }

    public void setNlpSongInfos(Object[] nlpSongInfos) {
        this.nlpSongInfos = nlpSongInfos;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "UnityMusicModel{" +
                "nlpSongInfos=" + nlpSongInfos +
                ", index=" + index +
                '}';
    }
}
