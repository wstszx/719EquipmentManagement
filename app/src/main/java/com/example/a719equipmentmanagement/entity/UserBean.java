package com.example.a719equipmentmanagement.entity;

public class UserBean {
    private int userId;
    private String userName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
