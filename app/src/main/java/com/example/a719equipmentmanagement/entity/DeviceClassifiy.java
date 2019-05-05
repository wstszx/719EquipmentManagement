package com.example.a719equipmentmanagement.entity;

public class DeviceClassifiy {

    /**
     * id : 1
     * searchValue : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * name : null
     * list : null
     * status : 0
     * equip_no : 1
     * sn : 1
     * source : null
     * parameter : null
     * manufactuer : null
     * responsor : null
     * latest_verify_date : null
     * valid_date : null
     * location_id : 1
     * category_id : 1
     * tech_state : 0
     * verify_period : 0
     * del_flag : 0
     */

    private int id;
    private Object searchValue;
    private Object createBy;
    private Object createTime;
    private Object updateBy;
    private Object updateTime;
    private Object remark;
    private ParamsBean params;
    private String name;
    private Object list;
    private int status;
    private String equip_no;
    private String sn;
    private Object source;
    private Object parameter;
    private Object manufactuer;
    private Object responsor;
    private Object latest_verify_date;
    private Object valid_date;
    private int location_id;
    private int category_id;
    private int tech_state;
    private int verify_period;
    private int del_flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(Object searchValue) {
        this.searchValue = searchValue;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEquip_no() {
        return equip_no == null ? "" : equip_no;
    }

    public void setEquip_no(String equip_no) {
        this.equip_no = equip_no;
    }

    public String getSn() {
        return sn == null ? "" : sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public Object getManufactuer() {
        return manufactuer;
    }

    public void setManufactuer(Object manufactuer) {
        this.manufactuer = manufactuer;
    }

    public Object getResponsor() {
        return responsor;
    }

    public void setResponsor(Object responsor) {
        this.responsor = responsor;
    }

    public Object getLatest_verify_date() {
        return latest_verify_date;
    }

    public void setLatest_verify_date(Object latest_verify_date) {
        this.latest_verify_date = latest_verify_date;
    }

    public Object getValid_date() {
        return valid_date;
    }

    public void setValid_date(Object valid_date) {
        this.valid_date = valid_date;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getTech_state() {
        return tech_state;
    }

    public void setTech_state(int tech_state) {
        this.tech_state = tech_state;
    }

    public int getVerify_period() {
        return verify_period;
    }

    public void setVerify_period(int verify_period) {
        this.verify_period = verify_period;
    }

    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }

    public static class ParamsBean {
    }
}
