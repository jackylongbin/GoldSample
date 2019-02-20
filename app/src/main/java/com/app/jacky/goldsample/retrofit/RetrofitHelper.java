package com.app.jacky.goldsample.retrofit;

import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static RetrofitHelper instance = null;

    private Context mContext;

    private OkHttpClient client = new OkHttpClient();

    private GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());

    private Retrofit mRetrofit = null;

    public static RetrofitHelper getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new RetrofitHelper(context);
        }

        return instance;
    }


    private RetrofitHelper(Context mContext)
    {
        mContext = mContext;
        resetApp();
    }

    private void resetApp()
    {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.187:80/")
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T getRetrofitAPI(Class<T> req)
    {
        return mRetrofit.create(req);
    }

}
