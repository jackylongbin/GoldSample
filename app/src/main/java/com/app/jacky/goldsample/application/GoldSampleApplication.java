package com.app.jacky.goldsample.application;

import android.app.Application;

import com.app.jacky.goldsample.logger.LoggerUtil;

import org.litepal.LitePal;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
