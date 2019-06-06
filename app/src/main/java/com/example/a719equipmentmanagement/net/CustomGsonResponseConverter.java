package com.example.a719equipmentmanagement.net;

import com.blankj.utilcode.util.LogUtils;
import com.example.a719equipmentmanagement.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
        String body = value.string();
        // 获取json中的code，对json进行预处理
        JsonUtils.JSON_TYPE jsonType = JsonUtils.getJSONType(body);
        try {
            switch (jsonType) {
                case JSON_TYPE_ARRAY:
                    JSONArray jsonArray = new JSONArray(body);
                    body = jsonArray.toString();
                    break;
                case JSON_TYPE_OBJECT:
                    JSONObject json = new JSONObject(body);
                    boolean aNull = json.isNull("code");
                    String msg = json.optString("msg");
                    if (!aNull) {
                        int code = json.optInt("code");
                        if (code == -1) {
                            value.close();
                            throw new ServerException(code, msg);
                        } else if (code == 1) {
                            value.close();
                            throw new ServerException(code, msg);
                        } else if (code == 500) {
                            value.close();
                            throw new ServerException(code, msg);
                        }
                    }
                    body = json.toString();
                    break;
                case JSON_TYPE_ERROR:
                    LogUtils.i(jsonType);
                    break;
            }
            return adapter.fromJson(body);
        } catch (JSONException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }


}
