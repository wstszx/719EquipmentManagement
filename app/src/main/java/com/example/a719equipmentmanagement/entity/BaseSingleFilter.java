package com.example.a719equipmentmanagement.entity;

public class BaseSingleFilter {
    private int id;
    private String name;
    private boolean isExistRight;

    public boolean isExistRight() {
        return isExistRight;
    }

    public void setExistRight(boolean existRight) {
        isExistRight = existRight;
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
