package com.example.a719equipmentmanagement.net;


import com.example.a719equipmentmanagement.entity.LoginBean;
import com.example.a719equipmentmanagement.entity.User;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 *
 */
public interface ApiService {

    //登陆
    @FormUrlEncoded
    @POST("loginByApp")
    Call<LoginBean> login(@Field("username") String username,
                          @Field("password") String password);

    //登出
    @FormUrlEncoded
    @POST("logoutByApp")
    Call<LoginBean> loginout(@Field("username") String username,
                             @Field("password") String password);

    //注册
    @FormUrlEncoded
    @POST("user/register/")
    Call<LoginBean> register(@Field("username") String username,
                             @Field("password") String password,
                             @Field("repassword") String repassword);

    //用户列表
    @GET("system/dept/deptlist")
    Call<List<User>> getUser();

    //个人中心
    @GET("system/user/profile/me")
    Call<User> getMe();

    //个人中心
    @POST("system/user/add")
    Call<User> getIdf(@Body RequestBody requestBody);

}
