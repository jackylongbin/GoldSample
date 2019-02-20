package com.app.jacky.goldsample.presenter.impl;

import android.content.Context;

import com.app.jacky.goldsample.entity.User;
import com.app.jacky.goldsample.presenter.ILoginPresenter;
import com.app.jacky.goldsample.retrofit.RetrofitHelper;
import com.app.jacky.goldsample.service.UserService;
import com.app.jacky.goldsample.view.interfaceview.ILoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenterImpl implements ILoginPresenter {

    private ILoginView loginView;
    private Context context;
    public LoginPresenterImpl(ILoginView loginView, Context context)
    {
        this.loginView = loginView;
        this.context = context;
    }
    @Override
    public void loginSubmit(String userName, String userPwd) {

        loginView.showLoginProcess();
        UserService request = RetrofitHelper.getInstance(context).getRetrofitAPI(UserService.class);

        Call<User> call = request.login("jacky","12345678");

        call.enqueue(new Callback<User>(){

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                loginView.hideLoginProcess();
                loginView.loginSuccess();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginView.hideLoginProcess();
                loginView.loginFailed();
            }
        });
    }
}
