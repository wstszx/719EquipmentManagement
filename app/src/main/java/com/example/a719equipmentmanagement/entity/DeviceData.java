package com.example.a719equipmentmanagement.entity;

import java.io.Serializable;
import java.util.List;

public class DeviceData implements Serializable{


    /**
     * total : 2
     * rows : [{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"equipNo":"1","sn":"1","locationId":1,"categoryId":1,"deptId":1,"name":"设备","source":"11","parameter":"1","manufactuer":"1","techState":1,"responsor":"1","verifyPeriod":1,"latestVerifyDate":"2019-05-09 13:54:30","validDate":"2019-05-08 10:52:07","status":1,"delFlag":0},{"id":2,"searchValue":null,"createBy":null,"createTime":null,"updateBy":"","updateTime":null,"remark":null,"params":{},"equipNo":"2","sn":"2","locationId":2,"categoryId":2,"deptId":1,"name":"2","source":null,"parameter":null,"manufactuer":null,"techState":null,"responsor":null,"verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":null,"delFlag":0}]
     * code : 0
     */

    private int total;
    private int code;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * id : 1
         * searchValue : null
         * createBy : admin
         * createTime : 2019-05-09 13:49:03
         * updateBy : admin
         * updateTime : 2019-05-09 15:21:00
         * remark : 111
         * params : {}
         * equipNo : 1
         * sn : 1
         * locationId : 1
         * categoryId : 1
         * deptId : 1
         * name : 设备
         * source : 11
         * parameter : 1
         * manufactuer : 1
         * techState : 1
         * responsor : 1
         * verifyPeriod : 1
         * latestVerifyDate : 2019-05-09 13:54:30
         * validDate : 2019-05-08 10:52:07
         * status : 1
         * delFlag : 0
         */

        private int id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String remark;
        private ParamsBean params;
        private String equipNo;
        private String sn;
        private int locationId;
        private int categoryId;
        private int deptId;
        private String name;
        private String source;
        private String parameter;
        private String manufactuer;
        private int techState;
        private String responsor;
        private int verifyPeriod;
        private String latestVerifyDate;
        private String validDate;
        private int status;
        private int delFlag;

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

        public String getCreateBy() {
            return createBy == null ? "" : createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy == null ? "" : updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getRemark() {
            return remark == null ? "" : remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public String getEquipNo() {
            return equipNo == null ? "" : equipNo;
        }

        public void setEquipNo(String equipNo) {
            this.equipNo = equipNo;
        }

        public String getSn() {
            return sn == null ? "" : sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public int getLocationId() {
            return locationId;
        }

        public void setLocationId(int locationId) {
            this.locationId = locationId;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSource() {
            return source == null ? "" : source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getParameter() {
            return parameter == null ? "" : parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public String getManufactuer() {
            return manufactuer == null ? "" : manufactuer;
        }

        public void setManufactuer(String manufactuer) {
            this.manufactuer = manufactuer;
        }

        public int getTechState() {
            return techState;
        }

        public void setTechState(int techState) {
            this.techState = techState;
        }

        public String getResponsor() {
            return responsor == null ? "" : responsor;
        }

        public void setResponsor(String responsor) {
            this.responsor = responsor;
        }

        public int getVerifyPeriod() {
            return verifyPeriod;
        }

        public void setVerifyPeriod(int verifyPeriod) {
            this.verifyPeriod = verifyPeriod;
        }

        public String getLatestVerifyDate() {
            return latestVerifyDate == null ? "" : latestVerifyDate;
        }

        public void setLatestVerifyDate(String latestVerifyDate) {
            this.latestVerifyDate = latestVerifyDate;
        }

        public String getValidDate() {
            return validDate == null ? "" : validDate;
        }

        public void setValidDate(String validDate) {
            this.validDate = validDate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public static class ParamsBean implements Serializable{
        }
    }
}
