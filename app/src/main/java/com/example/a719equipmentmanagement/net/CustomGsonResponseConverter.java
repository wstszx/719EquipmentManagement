package com.example.a719equipmentmanagement.net;

import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class CustomGsonResponseConverter<T> implements Converter<ResponseBody, T> {
    private Gson gson;
    private final TypeAdapter<T> adapter;

    public CustomGsonResponseConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        BaseResponse baseResponse = gson.fromJson(response, BaseResponse.class);
        boolean ok = baseResponse.isOk(App.getContext());

        return null;
    }
}
