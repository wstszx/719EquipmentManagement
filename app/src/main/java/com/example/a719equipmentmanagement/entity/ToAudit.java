package com.example.a719equipmentmanagement.entity;

import java.io.Serializable;

public class ToAudit implements Serializable {
    private int number;
    private  String deviceName;
    private String auditName;
    private String person;
    public ToAudit(int number,String deviceName,String auditName,String person) {
        this.number=number;
        this.deviceName=deviceName;
        this.auditName=auditName;
        this.person=person;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDeviceName() {
        return deviceName == null ? "" : deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAuditName() {
        return auditName == null ? "" : auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getPerson() {
        return person == null ? "" : person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
