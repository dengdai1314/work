package com.demo.DataBase;

import java.util.List;

/**
 * @author 29003
 * @description
 * @date 2019/11/27
 */
public class AnimBean {

    /**
     * wdID : 1
     * strDes : 通用-空01
     * nSceneID : 1
     * strName : basenull
     * IsLoop : false
     * IsBreak : true
     * nextState :
     * listen : []
     * nAnimationType : 0
     * stateTime : 0.0
     */

    private int wdID;
    private String strDes;
    private int nSceneID;
    private String strName;
    private boolean IsLoop;
    private boolean IsBreak;
    private String nextState;
    private int nAnimationType;
    private double stateTime;
    private List<?> listen;

    public int getWdID() {
        return wdID;
    }

    public void setWdID(int wdID) {
        this.wdID = wdID;
    }

    public String getStrDes() {
        return strDes;
    }

    public void setStrDes(String strDes) {
        this.strDes = strDes;
    }

    public int getNSceneID() {
        return nSceneID;
    }

    public void setNSceneID(int nSceneID) {
        this.nSceneID = nSceneID;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public boolean isIsLoop() {
        return IsLoop;
    }

    public void setIsLoop(boolean IsLoop) {
        this.IsLoop = IsLoop;
    }

    public boolean isIsBreak() {
        return IsBreak;
    }

    public void setIsBreak(boolean IsBreak) {
        this.IsBreak = IsBreak;
    }

    public String getNextState() {
        return nextState;
    }

    public void setNextState(String nextState) {
        this.nextState = nextState;
    }

    public int getNAnimationType() {
        return nAnimationType;
    }

    public void setNAnimationType(int nAnimationType) {
        this.nAnimationType = nAnimationType;
    }

    public double getStateTime() {
        return stateTime;
    }

    public void setStateTime(double stateTime) {
        this.stateTime = stateTime;
    }

    public List<?> getListen() {
        return listen;
    }

    public void setListen(List<?> listen) {
        this.listen = listen;
    }
}
