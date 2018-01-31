package com.zy.yaoproject.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by muzi on 2018/1/31.
 * 727784430@qq.com
 */

public class App extends Application {

    public static App mInstance;

    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        LeakCanary.install(this);
    }
}
