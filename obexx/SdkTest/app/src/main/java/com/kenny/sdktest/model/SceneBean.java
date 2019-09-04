package com.kenny.sdktest.model;

import java.util.List;

/**
 * @author 29003
 * @description
 * @date 2019/9/2
 */
public class SceneBean {

    /**
     * wdID : 1
     * strScene : default
     * intType : 1
     * gender : 2
     * listen : [5]
     * idle : [4]
     * firstState : [4]
     * ttsStartState : [9]
     * ttsEndState : [4]
     * errorState : [8]
     * endState : []
     * okState : [11]
     * nUIID : 0
     * nUIIDs : []
     * nextScene : [13,3,4]
     * IsProgress : false
     * IsRhythm : false
     */

    private int wdID;
    private String strScene;
    private int intType;
    private int gender;
    private int nUIID;
    private boolean IsProgress;
    private boolean IsRhythm;
    private List<Integer> listen;
    private List<Integer> idle;
    private List<Integer> firstState;
    private List<Integer> ttsStartState;
    private List<Integer> ttsEndState;
    private List<Integer> errorState;
    private List<?> endState;
    private List<Integer> okState;
    private List<?> nUIIDs;
    private List<Integer> nextScene;

    public int getWdID() {
        return wdID;
    }

    public void setWdID(int wdID) {
        this.wdID = wdID;
    }

    public String getStrScene() {
        return strScene;
    }

    public void setStrScene(String strScene) {
        this.strScene = strScene;
    }

    public int getIntType() {
        return intType;
    }

    public void setIntType(int intType) {
        this.intType = intType;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getNUIID() {
        return nUIID;
    }

    public void setNUIID(int nUIID) {
        this.nUIID = nUIID;
    }

    public boolean isIsProgress() {
        return IsProgress;
    }

    public void setIsProgress(boolean IsProgress) {
        this.IsProgress = IsProgress;
    }

    public boolean isIsRhythm() {
        return IsRhythm;
    }

    public void setIsRhythm(boolean IsRhythm) {
        this.IsRhythm = IsRhythm;
    }

    public List<Integer> getListen() {
        return listen;
    }

    public void setListen(List<Integer> listen) {
        this.listen = listen;
    }

    public List<Integer> getIdle() {
        return idle;
    }

    public void setIdle(List<Integer> idle) {
        this.idle = idle;
    }

    public List<Integer> getFirstState() {
        return firstState;
    }

    public void setFirstState(List<Integer> firstState) {
        this.firstState = firstState;
    }

    public List<Integer> getTtsStartState() {
        return ttsStartState;
    }

    public void setTtsStartState(List<Integer> ttsStartState) {
        this.ttsStartState = ttsStartState;
    }

    public List<Integer> getTtsEndState() {
        return ttsEndState;
    }

    public void setTtsEndState(List<Integer> ttsEndState) {
        this.ttsEndState = ttsEndState;
    }

    public List<Integer> getErrorState() {
        return errorState;
    }

    public void setErrorState(List<Integer> errorState) {
        this.errorState = errorState;
    }

    public List<?> getEndState() {
        return endState;
    }

    public void setEndState(List<?> endState) {
        this.endState = endState;
    }

    public List<Integer> getOkState() {
        return okState;
    }

    public void setOkState(List<Integer> okState) {
        this.okState = okState;
    }

    public List<?> getNUIIDs() {
        return nUIIDs;
    }

    public void setNUIIDs(List<?> nUIIDs) {
        this.nUIIDs = nUIIDs;
    }

    public List<Integer> getNextScene() {
        return nextScene;
    }

    public void setNextScene(List<Integer> nextScene) {
        this.nextScene = nextScene;
    }
}
