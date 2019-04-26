package com.example.a719equipmentmanagement.entity;

public class DeviceSearch {
    private int id;
    private String name;

    public DeviceSearch(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
