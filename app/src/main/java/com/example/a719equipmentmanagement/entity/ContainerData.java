package com.example.a719equipmentmanagement.entity;

import java.io.Serializable;
import java.util.List;

public class ContainerData {

    /**
     * id : 1
     * searchValue : null
     * createBy : admin
     * createTime : 2019-05-14 15:56:30
     * updateBy : admin
     * updateTime : 2019-05-14 15:56:39
     * remark : remark
     * params : {}
     * pid : 0
     * deptId : 1
     * name : #1货柜
     * list : [{"id":2,"searchValue":null,"createBy":"admin","createTime":"2019-05-14 15:57:00","updateBy":"admin","updateTime":"2019-05-14 15:57:24","remark":"remark","params":{},"pid":1,"deptId":2,"name":"#1货柜第一层","list":null},{"id":3,"searchValue":null,"createBy":"admin","createTime":"2019-05-14 15:57:03","updateBy":"admin","updateTime":"2019-05-14 15:57:27","remark":"remark","params":{},"pid":1,"deptId":3,"name":"#1货柜第二层","list":null}]
     */

    private int id;
    private Object searchValue;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String remark;
    private ParamsBean params;
    private int pid;
    private int deptId;
    private String name;
    private List<ListBean> list;

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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ParamsBean {
    }

    public static class ListBean implements Serializable {
        /**
         * id : 2
         * searchValue : null
         * createBy : admin
         * createTime : 2019-05-14 15:57:00
         * updateBy : admin
         * updateTime : 2019-05-14 15:57:24
         * remark : remark
         * params : {}
         * pid : 1
         * deptId : 2
         * name : #1货柜第一层
         * list : null
         */

        private int id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String remark;
        private ParamsBeanX params;
        private int pid;
        private int deptId;
        private String name;
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

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public ParamsBeanX getParams() {
            return params;
        }

        public void setParams(ParamsBeanX params) {
            this.params = params;
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
            return name;
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

        public static class ParamsBeanX implements Serializable{
        }
    }
}
