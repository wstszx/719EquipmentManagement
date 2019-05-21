package com.example.a719equipmentmanagement.entity;

import java.util.List;

public class DeviceClassifiy {


    /**
     * id : 1
     * searchValue : null
     * createBy : admin
     * createTime : 2019-05-09 14:03:21
     * updateBy : admin
     * updateTime : 2019-05-14 09:50:33
     * remark : remark
     * params : {}
     * pid : 0
     * name : 分类1
     * delFlag : 0
     * list : [{"id":3,"searchValue":null,"createBy":"admin","createTime":"2019-05-14 15:28:13","updateBy":"admin","updateTime":"2019-05-14 15:28:22","remark":"remark","params":{},"pid":1,"name":"分类2","delFlag":0,"list":null},{"id":4,"searchValue":null,"createBy":"admin","createTime":"2019-05-14 15:49:32","updateBy":"admin","updateTime":"2019-05-14 15:49:38","remark":"remark","params":{},"pid":1,"name":"温度计","delFlag":0,"list":null}]
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
    private String name;
    private int delFlag;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ParamsBean {
    }

    public static class ListBean {
        /**
         * id : 3
         * searchValue : null
         * createBy : admin
         * createTime : 2019-05-14 15:28:13
         * updateBy : admin
         * updateTime : 2019-05-14 15:28:22
         * remark : remark
         * params : {}
         * pid : 1
         * name : 分类2
         * delFlag : 0
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
        private String name;
        private int delFlag;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public Object getList() {
            return list;
        }

        public void setList(Object list) {
            this.list = list;
        }

        public static class ParamsBeanX {
        }
    }
}
