package com.app.jacky.goldsample.model;

import android.content.Context;

import com.app.jacky.goldsample.entity.BaseResponse;
import com.jacky.mvplibrary.model.IModel;

import retrofit2.Callback;

public interface ILoginModel extends IModel {

    void doLogin(Context context, String username, String password, Callback<BaseResponse> callback);
}
