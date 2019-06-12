package com.example.a719equipmentmanagement.entity;

import java.util.ArrayList;
import java.util.List;

public class Inventories {

    /**
     * total : 10
     * rows : [{"id":24,"searchValue":null,"createBy":"manager02","createTime":"2019-06-11 17:31:34","updateBy":"manager02","updateTime":"2019-06-11 17:31:34","remark":null,"name":"gt","state":1,"containerIds":"","details":[]},{"id":23,"searchValue":null,"createBy":"manager02","createTime":"2019-06-11 17:29:18","updateBy":"manager02","updateTime":"2019-06-11 17:29:18","remark":null,"name":"ff","state":1,"containerIds":"","details":[]},{"id":22,"searchValue":null,"createBy":"manager02","createTime":"2019-06-11 17:29:10","updateBy":"manager02","updateTime":"2019-06-11 17:29:10","remark":null,"name":"gf","state":1,"containerIds":"","details":[]},{"id":21,"searchValue":null,"createBy":"manager02","createTime":"2019-06-11 17:29:03","updateBy":"manager02","updateTime":"2019-06-11 17:29:03","remark":null,"name":"gf","state":1,"containerIds":"","details":[]},{"id":20,"searchValue":null,"createBy":"manager02","createTime":"2019-06-11 17:28:22","updateBy":"manager02","updateTime":"2019-06-11 17:28:22","remark":null,"name":"rg","state":1,"containerIds":"","details":[]},{"id":19,"searchValue":null,"createBy":"manager02","createTime":"2019-06-11 17:27:24","updateBy":"manager02","updateTime":"2019-06-11 17:27:24","remark":null,"name":"ed","state":1,"containerIds":"","details":[]},{"id":18,"searchValue":null,"createBy":"manager02","createTime":"2019-06-11 17:26:00","updateBy":"manager02","updateTime":"2019-06-11 17:26:00","remark":null,"name":"ty","state":1,"containerIds":"","details":[]},{"id":17,"searchValue":null,"createBy":"manager02","createTime":"2019-06-11 17:16:05","updateBy":"manager02","updateTime":"2019-06-11 17:16:05","remark":null,"name":"gg","state":1,"containerIds":"","details":[]},{"id":16,"searchValue":null,"createBy":"manager02","createTime":"2019-06-11 17:10:35","updateBy":"manager02","updateTime":"2019-06-11 17:10:35","remark":null,"name":"tgf","state":1,"containerIds":"","details":[]},{"id":15,"searchValue":null,"createBy":"manager02","createTime":"2019-06-11 17:10:26","updateBy":"manager02","updateTime":"2019-06-11 17:10:26","remark":null,"name":"gg","state":1,"containerIds":"","details":[]}]
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
         * id : 24
         * searchValue : null
         * createBy : manager02
         * createTime : 2019-06-11 17:31:34
         * updateBy : manager02
         * updateTime : 2019-06-11 17:31:34
         * remark : null
         * name : gt
         * state : 1
         * containerIds :
         * details : []
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
        private List<?> details;

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

        public List<?> getDetails() {
            if (details == null) {
                return new ArrayList<>();
            }
            return details;
        }

        public void setDetails(List<?> details) {
            this.details = details;
        }
    }
}
