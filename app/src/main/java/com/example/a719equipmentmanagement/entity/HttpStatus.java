package com.example.a719equipmentmanagement.entity;

public class HttpStatus {
    private int code = -1;
    private String msg;

    public boolean isSuccess() {
        return code == 0;
    }
}
