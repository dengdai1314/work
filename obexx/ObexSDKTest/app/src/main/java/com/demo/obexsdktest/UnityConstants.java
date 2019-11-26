package com.demo.obexsdktest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.unity3d.player.UnityPlayer;

/**
 * @author 29003
 * @description
 * @date 2019/11/26
 */
public class UnityConstants {
    UnityPlayer mUnityPlayer = null;

    public UnityPlayer getUnityPlayer(){
        return mUnityPlayer;
    }

    public void setUnityPlayer(UnityPlayer unityPlayer){
        this.mUnityPlayer = unityPlayer;
    }

    public void addUnityView(ViewGroup viewGroup){
        View playView = null;
        if(mUnityPlayer!=null){
            playView= mUnityPlayer.getView();
        }
        if(playView!=null && playView.getParent()!=null){
            ViewGroup view = (ViewGroup) playView.getParent();
            view.removeAllViews();
        }
        viewGroup.addView(playView, FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        mUnityPlayer.requestFocus();
    }

    public void removeUnityView(){
        ViewGroup parent = (ViewGroup) mUnityPlayer.getView().getParent();
        if(parent!=null){
            parent.removeAllViews();
        }
    }
}
