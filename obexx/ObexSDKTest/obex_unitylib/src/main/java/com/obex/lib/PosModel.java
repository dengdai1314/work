package com.obex.lib;
/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: PosModel.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/5 11:51
 * Changes (from 2019/7/5)
 * -----------------------------------------------------------------
 * 2019/7/5 : Create PosModel.java (liuhai);
 * -----------------------------------------------------------------
 * description:
 */

import java.util.Arrays;

public class PosModel {
    float[] posArray;

    public float[] getPosArray() {
        return posArray;
    }

    public void setPosArray(float[] posArray) {
        this.posArray = posArray;
    }

    @Override
    public String toString() {
        return "PosModel{" +
                "posArray=" + Arrays.toString(posArray) +
                '}';
    }
}
