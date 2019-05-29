package com.example.a719equipmentmanagement.entity;

import java.io.Serializable;

public class InvalidEquip implements Serializable{
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private  String deviceName;
    private String currentStatus;
    public InvalidEquip(int number,String deviceName,String currentStatus) {
        this.number=number;
        this.deviceName=deviceName;
        this.currentStatus=currentStatus;
    }

    public String getDeviceName() {
        return deviceName == null ? "" : deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getCurrentStatus() {
        return currentStatus == null ? "" : currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
