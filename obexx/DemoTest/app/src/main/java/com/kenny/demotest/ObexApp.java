package com.kenny.demotest;

import android.app.Application;

/**
 * @author 29003
 * @description
 * @date 2019/7/23
 */
public class ObexApp extends Application {
    public static ObexApp obexApp;

    public static ObexApp getOBexApp() {
        return obexApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        obexApp=this;
    }
}

