package com.example.a719equipmentmanagement.entity;

public class HomeBean {
    private int imageRes;
    private String name;

    public HomeBean(int imageRes, String name) {
        this.imageRes = imageRes;
        this.name = name;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
