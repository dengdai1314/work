package com.kenny.demotest;

import android.net.Uri;
import android.util.Log;

import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.DynamicConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * @author 29003
 * @description
 * @date 2019/7/22
 */
public class ObexAudioPlayer  {
    String TAG = "ObexAudioPlayer";
    SimpleExoPlayer mplayer = null;
    //播放控制器
    ControlDispatcher mDispatcher = null;
    public OnMediaListener onAudioListener = null;

    private static ObexAudioPlayer instance;
    //构造方法，让构造函数为private，则该类不会被实例化
    private  ObexAudioPlayer(){
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        AdaptiveTrackSelection.Factory videoTrackSelectionFactory= new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        mplayer = ExoPlayerFactory.newSimpleInstance(ObexApp.getOBexApp(), trackSelector);
        DefaultControlDispatcher mDispatcher = new DefaultControlDispatcher();
        if(mplayer!=null){
            mplayer.setRepeatMode(ExoPlayer.REPEAT_MODE_OFF);
        }
        if(mplayer!=null){
            mplayer.addListener(new Player.DefaultEventListener(){
                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    super.onPlayerStateChanged(playWhenReady,playbackState);
                    Log.d(TAG, "onPlayerStateChanged===playWhenReady====="+playWhenReady+"playbackState==="+playbackState);
                    if (playbackState == Player.STATE_READY) {
                        Log.d(TAG, "开始播报结束了");
                        if(onAudioListener != null){
                            onAudioListener.onStart();
                        }
                    } else if (playbackState == Player.STATE_ENDED) {
                        Log.d(TAG, "播报结束了");
                        if(onAudioListener != null){
                            onAudioListener.onComplete();
                        }
                    }
                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {
                    Log.d(TAG,"onPlayerError播报结束了");
                    //do nothing
                    if(onAudioListener != null){
                        onAudioListener.onComplete();
                    }
                }
            });
        }
    }
    //获取唯一可用的对象
    public static synchronized  ObexAudioPlayer getInstance() {
        if (instance == null) {
            //创建ObexAudioPlayer的一个对象
            instance = new ObexAudioPlayer();
        }
        return instance;
    }

    /**
     * 播放歌曲列表
     */
    public void play(String audioId,int repeatModel, OnMediaListener listener){
        this.onAudioListener = listener;
        DynamicConcatenatingMediaSource source = new DynamicConcatenatingMediaSource();
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        try{
            Uri uri = Uri.parse(audioId);
            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(
                    ObexApp.getOBexApp(),
                    Util.getUserAgent(ObexApp.getOBexApp(),"com.obex.mainservice"),bandwidthMeter
            );
            ExtractorMediaSource mediaSource = new ExtractorMediaSource(
                    uri,
                    dataSourceFactory, new DefaultExtractorsFactory(),null,null
            );
            if(mplayer!=null){
                mplayer.prepare(mediaSource);
                mplayer.setPlayWhenReady(true);
                mplayer.setRepeatMode(repeatModel);
            }
            if(mDispatcher!=null){
                mDispatcher.dispatchSetPlayWhenReady(mplayer,true);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    interface OnMediaListener{
         void onStart();
         void onComplete();
    }
}
