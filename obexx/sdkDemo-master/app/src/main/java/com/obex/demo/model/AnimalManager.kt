package com.obex.demo.model

import android.util.Log

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: AnimalManager.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/11 15:01
 * Changes (from 2019/7/11)
 * -----------------------------------------------------------------
 * 2019/7/11 : Create AnimalManager.java (liuhai);
 * -----------------------------------------------------------------
 * description:
 */

class AnimalManager {
    var allAnimalList: MutableList<AnimalBean>? = null //动作列表

    companion object {
        val instant: AnimalManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AnimalManager()
        }
    }

    open fun setAnimalList(mutableList: MutableList<AnimalBean>) {
        this.allAnimalList = mutableList
    }

    open fun getNextAnimalId(animalId: Int): Int {
        var id = 0
        val nextState = allAnimalList?.filter {
            it.wdID == animalId
        }?.first()?.nextState?.replace("\\[", "")?.replace("\\]", "")

        val next = nextState?.split(",")
        next.let {
            id = getWeightRandom(it)
        }
        return id

    }

    private fun weightArraySum(weightArrays: DoubleArray): Double {
        var weightSum = 0.0
        for (weightValue in weightArrays) {
            weightSum += weightValue
        }
        return weightSum
    }

    /**
     * 根据随机数获取动作ID
     *
     * @param actions
     * @return
     */
    fun getWeightRandom(actions: List<String>?): Int {
        var action = 0
        actions?.let {
            val weightArrays = DoubleArray(actions.size)
            val actionIds = IntArray(actions.size)
            for (i in actions.indices) {
                val ids = actions[i].split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                actionIds[i] = Integer.parseInt(ids[0])
                weightArrays[i] = java.lang.Double.valueOf(ids[1])
            }
            if (actionIds.size == 1) {
                return actionIds[0]
            }
            val weightSum = weightArraySum(weightArrays)
            Log.d("随机", "weighsunm===$weightSum")
            var stepWeightSum = 0.0
            for (i in weightArrays.indices) {
                stepWeightSum += weightArrays[i]
                if (Math.random() <= stepWeightSum / weightSum) {
                    Log.d("随机", "随机下一个动作 i===$i")
                    return actionIds[i]
                }
            }
            Log.d("随机", "随机下一个动作错误===0")
            action = actionIds[0]
        }
        return action
    }

}