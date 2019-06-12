package com.example.a719equipmentmanagement.entity;

public class Device {
    private int id;
    private String name,target,department,status,location,userName;


    public Device(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Device(){
        id=0;
        name="";
        target="";
        department="";
        status="";
        location="";
        userName="";
    }

    public String getDepartment() { return department == null ? "" : department; }
    public void setDepartment(String department) { this.department = department; }

    public String getLocation() { return location == null ? "" : location; }
    public void setLocation(String location) { this.location = location; }

    public String getDeptListName() { return userName == null ? "" : userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getTarget() { return target == null ? "" : target; }
    public void setTarget(String target) { this.target = target; }

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

    public String getStatus() {
        return status == null ? "" : status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
