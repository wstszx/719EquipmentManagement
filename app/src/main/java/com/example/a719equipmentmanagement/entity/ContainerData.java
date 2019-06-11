package com.example.a719equipmentmanagement.entity;

import java.util.ArrayList;
import java.util.List;

public class ContainerData {


    /**
     * id : 24
     * searchValue : null
     * createBy : admin
     * createTime : 2019-06-06 10:34:57
     * updateBy : admin
     * updateTime : 2019-06-06 10:35:04
     * remark :
     * pid : 0
     * deptId : 154
     * name : 1#
     * containers : [{"id":25,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 14:00:12","updateBy":"admin","updateTime":"2019-06-05 14:00:12","remark":null,"pid":24,"deptId":154,"name":"1#第1层","containers":null},{"id":26,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 14:00:12","updateBy":"admin","updateTime":"2019-06-05 14:00:12","remark":null,"pid":24,"deptId":154,"name":"1#第2层","containers":null},{"id":27,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 14:00:12","updateBy":"admin","updateTime":"2019-06-05 14:00:12","remark":null,"pid":24,"deptId":154,"name":"1#第3层","containers":null}]
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
    private List<ContainersBean> containers;

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

    public List<ContainersBean> getContainers() {
        if (containers == null) {
            return new ArrayList<>();
        }
        return containers;
    }

    public void setContainers(List<ContainersBean> containers) {
        this.containers = containers;
    }

    public static class ContainersBean {
        /**
         * id : 25
         * searchValue : null
         * createBy : admin
         * createTime : 2019-06-05 14:00:12
         * updateBy : admin
         * updateTime : 2019-06-05 14:00:12
         * remark : null
         * pid : 24
         * deptId : 154
         * name : 1#第1层
         * containers : null
         */

        private int id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object remark;
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

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
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
