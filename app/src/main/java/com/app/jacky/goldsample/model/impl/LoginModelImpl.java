package com.app.jacky.goldsample.model.impl;

import android.content.Context;

import com.app.jacky.goldsample.entity.BaseResponse;
import com.app.jacky.goldsample.logger.LoggerUtil;
import com.app.jacky.goldsample.model.ILoginModel;
import com.app.jacky.goldsample.retrofit.RetrofitAPI;
import com.app.jacky.goldsample.retrofit.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModelImpl implements ILoginModel {

    @Override
    public void doLogin(Context context, String username, String password, Callback<BaseResponse> callback) {

        RetrofitAPI retrofitAPI = RetrofitHelper.getInstance(context).getRetrofitAPI(RetrofitAPI.class);
        Call<BaseResponse> loginCall = retrofitAPI.login("uu1687789e9e99","uu1687789e9e99");
        loginCall.enqueue(callback);
    }
}
