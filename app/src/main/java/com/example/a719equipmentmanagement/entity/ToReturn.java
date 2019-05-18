package com.example.a719equipmentmanagement.entity;

import java.util.List;

public class ToReturn {

    /**
     * total : 1
     * rows : [{"id":8,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 15:18:41","updateBy":null,"updateTime":null,"remark":"1","params":{},"type":1,"equipId":1,"deptId":1,"state":1,"dealer":null,"auditOpinion":null,"equip":null}]
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

    public static class RowsBean {
        /**
         * id : 8
         * searchValue : null
         * createBy : admin
         * createTime : 2019-05-09 15:18:41
         * updateBy : null
         * updateTime : null
         * remark : 1
         * params : {}
         * type : 1
         * equipId : 1
         * deptId : 1
         * state : 1
         * dealer : null
         * auditOpinion : null
         * equip : null
         */

        private int id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private String remark;
        private ParamsBean params;
        private int type;
        private int equipId;
        private int deptId;
        private int state;
        private Object dealer;
        private Object auditOpinion;
        private Object equip;

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
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
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

        public String getRemark() {
            return remark;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getEquipId() {
            return equipId;
        }

        public void setEquipId(int equipId) {
            this.equipId = equipId;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Object getDealer() {
            return dealer;
        }

        public void setDealer(Object dealer) {
            this.dealer = dealer;
        }

        public Object getAuditOpinion() {
            return auditOpinion;
        }

        public void setAuditOpinion(Object auditOpinion) {
            this.auditOpinion = auditOpinion;
        }

        public Object getEquip() {
            return equip;
        }

        public void setEquip(Object equip) {
            this.equip = equip;
        }

        public static class ParamsBean {
        }
    }
}
