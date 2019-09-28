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
        String BASE_URL = "http://106.15.44.173/lims/";
//        String BASE_URL = "http://222.20.76.74:8081/lims/";

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
