package com.app.jacky.goldsample.service;

import com.app.jacky.goldsample.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @GET("users/{user}/detail")
    Call<User> getUserByName(@Path("user") String userName);

    @POST("account/login")
    Call<User> login(@Query("userName") String userName, @Query("userPassword") String userPassword);
}
