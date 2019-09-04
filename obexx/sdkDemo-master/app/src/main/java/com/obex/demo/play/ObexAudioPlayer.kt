package com.obex.demo.play

import android.net.Uri
import android.util.Log
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.DynamicConcatenatingMediaSource
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.obex.demo.ObexApp

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: ObexAudioPlayer.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/11 14:13
 * Changes (from 2019/7/11)
 * -----------------------------------------------------------------
 * 2019/7/11 : Create ObexAudioPlayer.java (liuhai);
 * -----------------------------------------------------------------
 * description:
 */

class ObexAudioPlayer {
    val TAG = "ObexAudioPlayer"
    var mPlayer: SimpleExoPlayer? = null
    //播放控制器
    var mDispatcher: ControlDispatcher? = null
    internal var onAudioListener: OnMediaListener? = null

    companion object {
        val instant: ObexAudioPlayer by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ObexAudioPlayer()
        }
    }

    private constructor() {
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        mPlayer = ExoPlayerFactory.newSimpleInstance(ObexApp.getOBexApp(), trackSelector)
        mDispatcher = DefaultControlDispatcher()
        mPlayer?.repeatMode = Player.REPEAT_MODE_OFF
        mPlayer?.addListener(object : Player.DefaultEventListener() {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                super.onPlayerStateChanged(playWhenReady, playbackState)
                Log.d(TAG, "onPlayerStateChanged===playWhenReady=====$playWhenReady   playbackState===$playbackState")
                if (playbackState == Player.STATE_READY) {
                    Log.d(TAG, "开始播报结束了")
                    onAudioListener?.onStart()
                } else if (playbackState == Player.STATE_ENDED) {
                    Log.d(TAG, "播报结束了")
                    onAudioListener?.onComplete()
                }
            }

            override fun onPlayerError(error: ExoPlaybackException?) {
                Log.d(TAG, "onPlayerError播报结束了")
                // Do nothing.
                onAudioListener?.onComplete()
            }
        })
    }


    /**
     * 播放歌曲列表
     */
   open fun play(audioId: String, repeatModel: Int, listener: OnMediaListener) {
        this.onAudioListener = listener
        val source = DynamicConcatenatingMediaSource()
        val bandwidthMeter = DefaultBandwidthMeter()
        try {
            val uri = Uri.parse(audioId)
            val dataSourceFactory = DefaultDataSourceFactory(
                ObexApp.getOBexApp(),
                Util.getUserAgent(ObexApp.getOBexApp(), "com.obex.mainservice"), bandwidthMeter
            )
            val mediaSource = ExtractorMediaSource(
                uri,
                dataSourceFactory, DefaultExtractorsFactory(), null, null
            )
            mPlayer?.prepare(mediaSource)
            mPlayer?.setPlayWhenReady(true)
            mPlayer?.setRepeatMode(repeatModel)
            mDispatcher?.dispatchSetPlayWhenReady(mPlayer, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 暂停播放，1是用户主动暂停，2是唤醒暂停
     */
    fun pause() {
        onAudioListener = null
        mDispatcher?.dispatchSetPlayWhenReady(mPlayer, false)
    }

    interface OnMediaListener {
        fun onStart()

        fun onComplete()
    }
}