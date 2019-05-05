package com.example.a719equipmentmanagement.net;


import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.utils.SPUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = SPUtils.getString(App.getContext(), "token", "");
        Request original = chain.request();
        Request request = original.newBuilder()
                .addHeader("token", token)
                .addHeader("Header", "Content-Type:application/json")
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
