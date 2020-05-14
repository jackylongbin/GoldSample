package com.redbee.goldsample.model.impl;

import android.content.Context;

import com.redbee.goldsample.entity.BaseResponse;
import com.redbee.goldsample.model.ILoginModel;
import com.redbee.goldsample.retrofit.RetrofitAPI;
import com.redbee.goldsample.retrofit.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginModelImpl implements ILoginModel {

    @Override
    public void doLogin(Context context, String username, String password, Callback<BaseResponse> callback) {

        RetrofitAPI retrofitAPI = RetrofitHelper.getInstance(context).getRetrofitAPI(RetrofitAPI.class);
        Call<BaseResponse> loginCall = retrofitAPI.login("uu1687789e9e99","uu1687789e9e99");
        loginCall.enqueue(callback);
    }
}
