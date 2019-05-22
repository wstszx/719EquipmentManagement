package com.example.a719equipmentmanagement.entity;


import java.util.ArrayList;
import java.util.List;

public class ListMyAllInventory {

    /**
     * total : 4
     * rows : [{"id":4,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:56:43","updateBy":"admin","updateTime":"2019-05-16 08:56:48","remark":"444","params":{},"name":"44","state":0,"containerIds":"2","list":null},{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:54:27","updateBy":"admin","updateTime":"2019-05-16 08:54:41","remark":"111","params":{},"name":"11","state":1,"containerIds":"1","list":null},{"id":2,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:55:35","updateBy":"admin","updateTime":"2019-05-16 08:55:40","remark":"222","params":{},"name":"22","state":2,"containerIds":"1","list":null},{"id":3,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:56:02","updateBy":"admin","updateTime":"2019-05-16 08:56:09","remark":"333","params":{},"name":"33","state":3,"containerIds":"2","list":null}]
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
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 4
         * searchValue : null
         * createBy : admin
         * createTime : 2019-05-16 08:56:43
         * updateBy : admin
         * updateTime : 2019-05-16 08:56:48
         * remark : 444
         * params : {}
         * name : 44
         * state : 0
         * containerIds : 2
         * list : null
         */

        private int id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String remark;
        private ParamsBean params;
        private String name;
        private int state;
        private String containerIds;
        private Object list;

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

        public Object getList() {
            return list;
        }

        public void setList(Object list) {
            this.list = list;
        }

        public static class ParamsBean {
        }
    }
}
