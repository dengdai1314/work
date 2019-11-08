package com.kenny.demotest;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 29003
 * @description
 * @date 2019/7/23
 */
public class AnimalManager {
    List<State> allAnimalList = null;

    private static AnimalManager instance;
    private AnimalManager (){

    }
    public static synchronized AnimalManager getInstance(){
        if (instance == null){
            instance = new AnimalManager();
        }
        return instance;
    }

    public void setAnimalList(List<State> mutableList){
        this.allAnimalList = mutableList;
    }

    public int getNextAnimalId(int animalId){
        int id = 0;
        String nextState = null;
        String[] next = null;
        if (allAnimalList!=null){
            for(int x=0;x<allAnimalList.size();x++){
                if(allAnimalList.get(x).getWdID() == animalId){
                    nextState = allAnimalList.get(x).getNextState()
                            .replace("\\[", "").replace("\\]", "");
                    break;
                }
            }
            if (nextState!=null){
                next =  nextState.split(",");
            }
            if (next!=null){
                id = getWeightRandom(Arrays.asList(next));
            }
        }
        return id;
    }

    private Double weightArraySum(double[] weightArrays){
        double weightSum = 0.0;
        for (double weightValue :weightArrays) {
            weightSum += weightValue;
        }
        return weightSum;
    }
    /**
     * 根据随机数获取动作ID
     *
     * @param actions
     * @return
     */
    public int getWeightRandom(List<String> actions){
        int action = 0;
        if (actions.size() !=0){
            double[] weightArrays = new double[actions.size()];
            int[] actionIds = new int[actions.size()];
            List<String> ids = new ArrayList<>();
            for (int i=0;i<actions.size();i++){
                for (String s : actions.get(i).split("_")) {
                    if (!s.isEmpty()) {
                        ids.add(s);
                    }
                }
                actionIds[i] = Integer.parseInt(ids.get(0));
                weightArrays[i] = Double.valueOf(ids.get(1));
            }
            if(actionIds.length ==1){
                return actionIds[0];
            }
            Double weightSum = weightArraySum(weightArrays);
            Log.d("随机", "weighsunm==="+weightSum);
            double stepWeightSum=0.0;
            for(int i =0;i<weightArrays.length;i++){
                stepWeightSum += weightArrays[i];
                if (Math.random() <= stepWeightSum / weightSum) {
                    Log.d("随机", "随机下一个动作 i===$i");
                    return actionIds[i];
                }
            }
            Log.d("随机", "随机下一个动作错误===0");
            action = actionIds[0];
        }
        return  action;
    }
}
