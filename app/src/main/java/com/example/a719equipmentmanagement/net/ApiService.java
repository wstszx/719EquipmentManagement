package com.example.a719equipmentmanagement.net;


import com.example.a719equipmentmanagement.entity.LoginBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 *
 */
public interface ApiService {

    //登陆
    @POST("login")
    Call<LoginBean> login(@Body RequestBody requestBody);

    //注册
    @FormUrlEncoded
    @POST("user/register/")
    Call<LoginBean> register(@Field("username") String username,
                             @Field("password") String password,
                             @Field("repassword") String repassword);
}
