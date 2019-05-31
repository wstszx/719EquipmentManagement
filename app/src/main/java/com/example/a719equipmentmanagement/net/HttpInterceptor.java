package com.example.a719equipmentmanagement.net;


import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = SPUtils.getInstance().getString( "token", "");
        Request original = chain.request();
        Request request = original.newBuilder()
                .addHeader("token", token)
                .addHeader("Header", "Content-Type:application/json")
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
