package com.obex.demo

import android.app.Application

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: ObexApp.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/11 14:28
 * Changes (from 2019/7/11)
 * -----------------------------------------------------------------
 * 2019/7/11 : Create ObexApp.java (liuhai);
 * -----------------------------------------------------------------
 * description:
 */

class ObexApp : Application() {
    companion object {
        private lateinit var obexApp: ObexApp

        fun getOBexApp(): ObexApp {
            return obexApp
        }
    }

    init {
        obexApp = this
    }

    override fun onCreate() {
        super.onCreate()
    }
}