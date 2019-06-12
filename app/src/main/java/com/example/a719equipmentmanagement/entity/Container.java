package com.example.a719equipmentmanagement.entity;

public class Container {

    /**
     * msg : 操作成功
     * code : 0
     * data : {"id":28,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:35:38","updateBy":"admin","updateTime":"2019-06-05 15:35:38","remark":"2号货柜","pid":0,"deptId":155,"name":"2号货柜","containers":null}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 28
         * searchValue : null
         * createBy : admin
         * createTime : 2019-06-05 15:35:38
         * updateBy : admin
         * updateTime : 2019-06-05 15:35:38
         * remark : 2号货柜
         * pid : 0
         * deptId : 155
         * name : 2号货柜
         * containers : null
         */

        private int id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String remark;
        private int pid;
        private int deptId;
        private String name;
        private Object containers;

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

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
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

        public Object getContainers() {
            return containers;
        }

        public void setContainers(Object containers) {
            this.containers = containers;
        }
    }
}
