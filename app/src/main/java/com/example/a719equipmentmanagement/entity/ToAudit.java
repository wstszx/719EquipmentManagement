package com.example.a719equipmentmanagement.entity;

import java.io.Serializable;

public class ToAudit implements Serializable {
    private  String deviceName;
    private String returnDate;
    private int days;
    public ToAudit(String deviceName,String returnDate,int days) {
        this.deviceName=deviceName;
        this.returnDate=returnDate;
        this.days=days;
    }

    public String getDeviceName() {
        return deviceName == null ? "" : deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getReturnDate() {
        return returnDate == null ? "" : returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
