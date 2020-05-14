package com.redbee.goldsample.logger;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;

import androidx.annotation.Nullable;

public class LoggerUtil {


    public enum LOGGER_ACTIVE_PROFILE
    {
        LOCAL,DEVELOP,PRODUCT
    }
    public static void init(LOGGER_ACTIVE_PROFILE profile)
    {

        switch (profile)
        {
            case LOCAL:
                Logger.addLogAdapter(new AndroidLogAdapter(
                ));
                break;
            case DEVELOP:
                //At product environment do not print debug log
                Logger.addLogAdapter(new AndroidLogAdapter(
                ){
                    @Override
                    public boolean isLoggable(int priority, @Nullable String tag) {
                        if(Logger.DEBUG == priority)
                            return false;
                        return true;
                    }
                });
                break;
            case PRODUCT:
                FormatStrategy formatStrategy = CsvFormatStrategy.newBuilder()
                        //.tag("custom")
                        .folder("com.jacky.goldsample.logger")
                        .build();
                Logger.addLogAdapter(new DiskLogAdapter(formatStrategy){
                    @Override
                    public boolean isLoggable(int priority, @Nullable String tag) {
                        if(Logger.DEBUG == priority)
                            return false;
                        return true;
                    }
                });
                break;
        }
    }

    public static void d(Object obj)
    {
        Logger.d(obj);
    }

    public static void e(String message,Object... args)
    {
        Logger.e(message,args);
    }

    public static void w(String message,Object... args)
    {
        Logger.w(message,args);
    }

    public static void v(String message,Object... args)
    {
        Logger.v(message,args);
    }

    public static void i(String message,Object... args)
    {
        Logger.i(message,args);
    }

    public static void wtf(String message,Object... args)
    {
        Logger.wtf(message,args);
    }
}
