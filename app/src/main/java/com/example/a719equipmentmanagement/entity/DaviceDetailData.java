package com.example.a719equipmentmanagement.entity;

import java.util.List;

public class DaviceDetailData {
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
     * categoryId : 3
     * category : {"id":3,"searchValue":null,"createBy":"admin","createTime":"2019-05-14 15:28:13","updateBy":"admin","updateTime":"2019-05-27 15:49:06","remark":"remark","params":{},"pid":1,"name":"分类1123","delFlag":0,"categorys":null}
     * deptId : 139
     * name : 设备1
     * source : 来源1
     * parameter : parameter1
     * manufactuer : 厂家1
     * techState : 1
     * responsor : 张三
     * verifyPeriod : 1
     * latestVerifyDate : 2019-05-09 13:54:30
     * validDate : 2019-05-16 10:52:07
     * status : 0
     * delFlag : 0
     * location : {"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-14 15:56:30","updateBy":"admin","updateTime":"2019-05-14 15:56:39","remark":"remark","params":{},"pid":0,"deptId":1,"name":"#1货柜","containers":null}
     * dept : {"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"deptId":139,"parentId":110,"ancestors":"0,100,110","deptName":"一室1组","orderNum":"56","leader":"王工","phone":"13936253625","email":"hh@qq.com","status":"0","delFlag":null,"parentName":"301实验室","users":null}
     * opers : ["Edit","Del"]
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
    private CategoryBean category;
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
    private LocationBean location;
    private DeptBean dept;
    private List<String> opers;

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

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    public String getSn() {
        return sn;
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

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getManufactuer() {
        return manufactuer;
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
        return responsor;
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
        return latestVerifyDate;
    }

    public void setLatestVerifyDate(String latestVerifyDate) {
        this.latestVerifyDate = latestVerifyDate;
    }

    public String getValidDate() {
        return validDate;
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

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public DeptBean getDept() {
        return dept;
    }

    public void setDept(DeptBean dept) {
        this.dept = dept;
    }

    public List<String> getOpers() {
        return opers;
    }

    public void setOpers(List<String> opers) {
        this.opers = opers;
    }

    public static class ParamsBean {
    }

    public static class CategoryBean {
        /**
         * id : 3
         * searchValue : null
         * createBy : admin
         * createTime : 2019-05-14 15:28:13
         * updateBy : admin
         * updateTime : 2019-05-27 15:49:06
         * remark : remark
         * params : {}
         * pid : 1
         * name : 分类1123
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
        private ParamsBeanX params;
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

        public Object getCategorys() {
            return categorys;
        }

        public void setCategorys(Object categorys) {
            this.categorys = categorys;
        }

        public static class ParamsBeanX {
        }
    }

    public static class LocationBean {
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
         * containers : null
         */

        private int id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String remark;
        private ParamsBeanXX params;
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

        public ParamsBeanXX getParams() {
            return params;
        }

        public void setParams(ParamsBeanXX params) {
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

        public Object getContainers() {
            return containers;
        }

        public void setContainers(Object containers) {
            this.containers = containers;
        }

        public static class ParamsBeanXX {
        }
    }

    public static class DeptBean {
        /**
         * id : null
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * deptId : 139
         * parentId : 110
         * ancestors : 0,100,110
         * deptName : 一室1组
         * orderNum : 56
         * leader : 王工
         * phone : 13936253625
         * email : hh@qq.com
         * status : 0
         * delFlag : null
         * parentName : 301实验室
         * users : null
         */

        private Object id;
        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBeanXXX params;
        private int deptId;
        private int parentId;
        private String ancestors;
        private String deptName;
        private String orderNum;
        private String leader;
        private String phone;
        private String email;
        private String status;
        private Object delFlag;
        private String parentName;
        private Object users;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
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

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsBeanXXX getParams() {
            return params;
        }

        public void setParams(ParamsBeanXXX params) {
            this.params = params;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getAncestors() {
            return ancestors;
        }

        public void setAncestors(String ancestors) {
            this.ancestors = ancestors;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getLeader() {
            return leader;
        }

        public void setLeader(String leader) {
            this.leader = leader;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Object delFlag) {
            this.delFlag = delFlag;
        }

        public String getParentName() {
            return parentName;
        }

        public void setParentName(String parentName) {
            this.parentName = parentName;
        }

        public Object getUsers() {
            return users;
        }

        public void setUsers(Object users) {
            this.users = users;
        }

        public static class ParamsBeanXXX {
        }
    }
}
