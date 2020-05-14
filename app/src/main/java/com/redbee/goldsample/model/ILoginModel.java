package com.redbee.goldsample.model;

import android.content.Context;

import com.redbee.goldsample.entity.BaseResponse;
import com.redbee.mvplibrary.model.IModel;

import retrofit2.Callback;

public interface ILoginModel extends IModel {

    void doLogin(Context context, String username, String password, Callback<BaseResponse> callback);
}
