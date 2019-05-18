package com.example.a719equipmentmanagement.entity;

public class TreeData {

    /**
     * id : 100
     * pId : 0
     * name : 三亚试验
     * title : 三亚试验
     * checked : false
     * open : false
     * nocheck : false
     */

    private int id;
    private int pId;
    private String name;
    private String title;
    private boolean checked;
    private boolean open;
    private boolean nocheck;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }
}
