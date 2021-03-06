package com.example.a719equipmentmanagement.entity;

import java.util.ArrayList;
import java.util.List;

public class DeviceScanData {

    /**
     * msg : 操作成功
     * code : 0
     * data : {"id":20,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 16:25:25","updateBy":"admin","updateTime":"2019-06-05 16:25:25","remark":"无损检测仪器4","equipNo":"20190605031005","sn":"98762","locationId":31,"categoryId":23,"category":{"id":23,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:34:03","updateBy":"admin","updateTime":"2019-06-05 15:34:03","remark":"无损子类2","pid":21,"name":"无损子类2","delFlag":0,"categorys":null},"deptId":155,"name":"无损检测仪器4","source":"上级分配","parameter":"string","manufactuer":"厂商3","techState":1,"responsor":"徐惟罡","verifyPeriod":12,"latestVerifyDate":null,"validDate":"2019-06-05 08:22:32","status":0,"delFlag":0,"location":{"id":31,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:36:41","updateBy":"admin","updateTime":"2019-06-05 15:36:41","remark":"2#第3层","pid":28,"deptId":155,"name":"2#第3层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"deptId":155,"parentId":100,"ancestors":"0,100","deptName":"301实验室","orderNum":"2","leader":"manager02","phone":"18674075322","email":"524545375@qq.com","status":"0","delFlag":null,"parentName":"三亚试验","users":null},"opers":["Borrow","App4Inspec","App4Scrap"]}
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
         * id : 20
         * searchValue : null
         * createBy : admin
         * createTime : 2019-06-05 16:25:25
         * updateBy : admin
         * updateTime : 2019-06-05 16:25:25
         * remark : 无损检测仪器4
         * equipNo : 20190605031005
         * sn : 98762
         * locationId : 31
         * categoryId : 23
         * category : {"id":23,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:34:03","updateBy":"admin","updateTime":"2019-06-05 15:34:03","remark":"无损子类2","pid":21,"name":"无损子类2","delFlag":0,"categorys":null}
         * deptId : 155
         * name : 无损检测仪器4
         * source : 上级分配
         * parameter : string
         * manufactuer : 厂商3
         * techState : 1
         * responsor : 徐惟罡
         * verifyPeriod : 12
         * latestVerifyDate : null
         * validDate : 2019-06-05 08:22:32
         * status : 0
         * delFlag : 0
         * location : {"id":31,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:36:41","updateBy":"admin","updateTime":"2019-06-05 15:36:41","remark":"2#第3层","pid":28,"deptId":155,"name":"2#第3层","containers":null}
         * dept : {"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"deptId":155,"parentId":100,"ancestors":"0,100","deptName":"301实验室","orderNum":"2","leader":"manager02","phone":"18674075322","email":"524545375@qq.com","status":"0","delFlag":null,"parentName":"三亚试验","users":null}
         * opers : ["Borrow","App4Inspec","App4Scrap"]
         */

        private int id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String remark;
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
        private Object latestVerifyDate;
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

        public String getEquipNo() {
            return equipNo == null ? "" : equipNo;
        }

        public void setEquipNo(String equipNo) {
            this.equipNo = equipNo;
        }

        public String getSn() {
            return sn == null ? "" : sn;
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
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSource() {
            return source == null ? "" : source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getParameter() {
            return parameter == null ? "" : parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public String getManufactuer() {
            return manufactuer == null ? "" : manufactuer;
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
            return responsor == null ? "" : responsor;
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

        public Object getLatestVerifyDate() {
            return latestVerifyDate;
        }

        public void setLatestVerifyDate(Object latestVerifyDate) {
            this.latestVerifyDate = latestVerifyDate;
        }

        public String getValidDate() {
            return validDate == null ? "" : validDate;
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
            if (opers == null) {
                return new ArrayList<>();
            }
            return opers;
        }

        public void setOpers(List<String> opers) {
            this.opers = opers;
        }

        public static class CategoryBean {
            /**
             * id : 23
             * searchValue : null
             * createBy : admin
             * createTime : 2019-06-05 15:34:03
             * updateBy : admin
             * updateTime : 2019-06-05 15:34:03
             * remark : 无损子类2
             * pid : 21
             * name : 无损子类2
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

            public String getName() {
                return name == null ? "" : name;
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

        public static class LocationBean {
            /**
             * id : 31
             * searchValue : null
             * createBy : admin
             * createTime : 2019-06-05 15:36:41
             * updateBy : admin
             * updateTime : 2019-06-05 15:36:41
             * remark : 2#第3层
             * pid : 28
             * deptId : 155
             * name : 2#第3层
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

        public static class DeptBean {
            /**
             * id : null
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * deptId : 155
             * parentId : 100
             * ancestors : 0,100
             * deptName : 301实验室
             * orderNum : 2
             * leader : manager02
             * phone : 18674075322
             * email : 524545375@qq.com
             * status : 0
             * delFlag : null
             * parentName : 三亚试验
             * users : null
             */

            private Object id;
            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
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
                return ancestors == null ? "" : ancestors;
            }

            public void setAncestors(String ancestors) {
                this.ancestors = ancestors;
            }

            public String getDeptName() {
                return deptName == null ? "" : deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public String getOrderNum() {
                return orderNum == null ? "" : orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getLeader() {
                return leader == null ? "" : leader;
            }

            public void setLeader(String leader) {
                this.leader = leader;
            }

            public String getPhone() {
                return phone == null ? "" : phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email == null ? "" : email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getStatus() {
                return status == null ? "" : status;
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
                return parentName == null ? "" : parentName;
            }

            public void setParentName(String parentName) {
                this.parentName = parentName;
            }

            public Object getDeptLists() {
                return users;
            }

            public void setUsers(Object users) {
                this.users = users;
            }
        }
    }
}
