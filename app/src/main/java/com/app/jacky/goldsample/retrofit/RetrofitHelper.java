package com.app.jacky.goldsample.retrofit;

import android.content.Context;
import android.util.Log;

import com.app.jacky.goldsample.R;
import com.app.jacky.goldsample.logger.LoggerUtil;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static RetrofitHelper instance = null;

    private static final String BASE_URL = "http://www.redbeelab-videocloud.com:8080/XP02/";

    private static final String BAIDU_URL = "https://www.baidu.com/";

    private static final String RAILWAY_URL = "https://www.12306.cn/";

    private Context mContext;

//    private OkHttpClient client = new OkHttpClient().newBuilder().sslSocketFactory();

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
        this.mContext = mContext;
        resetApp();
    }

    private void resetApp()
    {
        InputStream certificate = mContext.getResources().openRawResource(R.raw.ssl12306);
        OkHttpClient client = null;
        LoggerUtil.d("Reset APP");
        try {
            client = new OkHttpClient().newBuilder().sslSocketFactory(getSSLSocketFactory(),new MyX509TrustManager(mContext)).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BAIDU_URL)
                .client(client)
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new Converter<ResponseBody, Object>() {
                            @Override
                            public Object convert(ResponseBody value) throws IOException {
                                return value.string();
                            }
                        };
                    }
                })
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private SSLSocketFactory getSSLSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext context = SSLContext.getInstance("TLS");
        TrustManager[] trustManagers = {new MyX509TrustManager(mContext)};
        context.init(null, trustManagers, new SecureRandom());
        return context.getSocketFactory();
    }

    public <T> T getRetrofitAPI(Class<T> req)
    {
        return mRetrofit.create(req);
    }

}
