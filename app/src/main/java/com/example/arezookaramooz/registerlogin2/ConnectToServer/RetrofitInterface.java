package com.example.arezookaramooz.registerlogin2.ConnectToServer;

import com.example.arezookaramooz.registerlogin2.DataBase.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface RetrofitInterface {
        @POST("UserInfo")
        Call<User> getStringScalar(@Body User body);
    }
