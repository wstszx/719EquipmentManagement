package com.example.a719equipmentmanagement.entity;

import java.io.Serializable;

public class InvalidEquip implements Serializable{
    private int number;



    private  String deviceName;
    private String currentStatus;
    private String person;
    public InvalidEquip(int number,String deviceName,String currentStatus,String person) {
        this.number=number;
        this.deviceName=deviceName;
        this.currentStatus=currentStatus;
        this.person=person;
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
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public String getPerson() {
        return person == null ? "" : person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
