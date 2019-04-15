package com.example.a719equipmentmanagement.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpProvider {
    static OkHttpClient createOkHttpClient(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        //打印一次请求的全部信息
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        builder.addInterceptor(new HttpInterceptor());
        return builder.build();
    }
}
