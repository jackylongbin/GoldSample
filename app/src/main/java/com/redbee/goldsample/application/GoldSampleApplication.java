package com.redbee.goldsample.application;

import android.app.Application;

import com.redbee.goldsample.logger.LoggerUtil;

import org.litepal.LitePal;

public class GoldSampleApplication extends Application {

    public static final String BASE_URL = "http://192.168.1.187:80/";
    @Override
    public void onCreate() {
        super.onCreate();

        //log initial
        LoggerUtil.init(LoggerUtil.LOGGER_ACTIVE_PROFILE.LOCAL);
        //SQLite util initial
        LitePal.initialize(this);


    }
}
