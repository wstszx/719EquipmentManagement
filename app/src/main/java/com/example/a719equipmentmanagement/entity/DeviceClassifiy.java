package com.example.a719equipmentmanagement.entity;

import java.util.List;

public class DeviceClassifiy {


    /**
     * id : 18
     * searchValue : null
     * createBy : admin
     * createTime : 2019-06-05 14:02:08
     * updateBy : admin
     * updateTime : 2019-06-05 14:02:08
     * remark : 油液
     * pid : 0
     * name : 油液
     * delFlag : 0
     * categorys : [{"id":19,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 14:02:44","updateBy":"admin","updateTime":"2019-06-05 14:02:44","remark":"油液子类1","pid":18,"name":"油液子类1","delFlag":0,"categorys":null},{"id":20,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 14:02:58","updateBy":"admin","updateTime":"2019-06-05 14:02:58","remark":"油液子类2","pid":18,"name":"油液子类2","delFlag":0,"categorys":null},{"id":33,"searchValue":null,"createBy":"admin","createTime":"2019-06-09 11:36:34","updateBy":"admin","updateTime":"2019-06-09 11:36:34","remark":null,"pid":18,"name":"大气","delFlag":0,"categorys":null}]
     */

    private int id;
    private Object searchValue;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String remark;
    private int pid;
    private String name;
    private int delFlag;
    private List<CategorysBean> categorys;

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

    public List<CategorysBean> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategorysBean> categorys) {
        this.categorys = categorys;
    }

    public static class CategorysBean {
        /**
         * id : 19
         * searchValue : null
         * createBy : admin
         * createTime : 2019-06-05 14:02:44
         * updateBy : admin
         * updateTime : 2019-06-05 14:02:44
         * remark : 油液子类1
         * pid : 18
         * name : 油液子类1
         * delFlag : 0
         * categorys : null
         */

        private int id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String remark;
        private int pid;
        private String name;
        private int delFlag;
        private Object categorys;

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

        public Object getCategorys() {
            return categorys;
        }

        public void setCategorys(Object categorys) {
            this.categorys = categorys;
        }
    }
}
