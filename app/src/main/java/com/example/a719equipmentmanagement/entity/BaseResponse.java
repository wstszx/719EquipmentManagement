package com.example.a719equipmentmanagement.entity;

import android.content.Context;

import com.example.a719equipmentmanagement.net.NetworkError;
import com.example.a719equipmentmanagement.net.ServerException;

public class BaseResponse<T> {
    private int code = -2;
    private String msg;
    private T res;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }

    public boolean isOk(Context context) {
        if (code == -1) {
            NetworkError.error(context, new ServerException(code, msg));
            return false;
        } else {
            return true;
        }
    }
}
