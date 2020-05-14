package com.redbee.goldsample.presenter;

import android.content.Context;

import com.redbee.goldsample.entity.BaseResponse;
import com.redbee.goldsample.model.ILoginModel;
import com.redbee.goldsample.view.interfaceview.ILoginView;
import com.redbee.mvplibrary.presenter.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends BasePresenter<ILoginModel, ILoginView> {

    public void doLogin(Context context, String username, String password)
    {
        if(model != null)
        {
            model.doLogin(context, username, password, new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {

                }
            });
        }
    }
    @Override
    protected void onViewDestroy() {

    }
}
