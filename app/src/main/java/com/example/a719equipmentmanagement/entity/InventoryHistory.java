package com.example.a719equipmentmanagement.entity;

import java.util.List;

public class InventoryHistory {


    /**
     * total : 9
     * rows : [{"id":15,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 19:42:19","updateBy":"admin","updateTime":"2019-06-06 19:42:19","remark":null,"name":"string","state":1,"containerIds":"","details":null},{"id":13,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 16:40:41","updateBy":"admin","updateTime":"2019-06-06 16:40:41","remark":null,"name":null,"state":1,"containerIds":"","details":null},{"id":12,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 16:20:44","updateBy":"admin","updateTime":"2019-06-06 16:20:44","remark":null,"name":null,"state":1,"containerIds":"","details":null},{"id":11,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 16:07:26","updateBy":"admin","updateTime":"2019-06-06 16:07:26","remark":null,"name":null,"state":1,"containerIds":"","details":null},{"id":10,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 16:07:05","updateBy":"admin","updateTime":"2019-06-06 16:07:05","remark":null,"name":null,"state":1,"containerIds":"","details":null},{"id":9,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 16:05:15","updateBy":"admin","updateTime":"2019-06-06 16:05:15","remark":null,"name":null,"state":1,"containerIds":"","details":null},{"id":8,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 10:31:42","updateBy":"admin","updateTime":"2019-06-06 10:31:42","remark":null,"name":null,"state":1,"containerIds":"","details":null},{"id":7,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 16:33:34","updateBy":"admin","updateTime":"2019-06-06 16:48:20","remark":"该盘点已被取消","name":null,"state":2,"containerIds":"","details":null},{"id":14,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 16:43:20","updateBy":"admin","updateTime":"2019-06-06 17:08:07","remark":"匹配0,盘亏1,盘盈1","name":"我的盘点","state":3,"containerIds":",25,26","details":null}]
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
         * id : 15
         * searchValue : null
         * createBy : admin
         * createTime : 2019-06-06 19:42:19
         * updateBy : admin
         * updateTime : 2019-06-06 19:42:19
         * remark : null
         * name : string
         * state : 1
         * containerIds :
         * details : null
         */

        private int id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object remark;
        private String name;
        private int state;
        private String containerIds;
        private Object details;

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

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getContainerIds() {
            return containerIds == null ? "" : containerIds;
        }

        public void setContainerIds(String containerIds) {
            this.containerIds = containerIds;
        }

        public Object getDetails() {
            return details;
        }

        public void setDetails(Object details) {
            this.details = details;
        }
    }
}
