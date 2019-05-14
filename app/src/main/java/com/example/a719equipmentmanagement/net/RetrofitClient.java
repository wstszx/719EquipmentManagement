package com.example.a719equipmentmanagement.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance;
    private ApiService service;

    public ApiService getService() {
        return service;
    }


    private RetrofitClient() {
        OkHttpClient okHttpClient = OkHttpProvider.createOkHttpClient();
        String BASE_URL = "http://222.20.72.248/";
//        String BASE_URL = "http://192.168.0.102/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())
//                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();
        this.service = retrofit.create(ApiService.class);
    }

    public synchronized static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }
}
