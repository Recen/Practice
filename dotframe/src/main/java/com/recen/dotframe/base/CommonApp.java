package com.recen.dotframe.base;

import android.app.Application;

public class CommonApp extends Application {
    private static CommonApp instance;

    public static CommonApp getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
