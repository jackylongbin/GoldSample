package com.redbee.goldsample.retrofit;

import com.redbee.goldsample.entity.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @FormUrlEncoded
    @POST("v1/login")
    Call<BaseResponse> login(@Field("accountId")String accountId, @Field("accountPassword")String accountPassword);

    @GET("/")
    Call<String> getBaidu();

    @GET("index")
    Call<String> get12306();


}
