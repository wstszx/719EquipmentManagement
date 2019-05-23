package com.example.a719equipmentmanagement.entity;

import java.io.Serializable;
import java.util.List;

public class DeviceData2 implements Serializable {
    /**
     * total : 11
     * rows : [{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"equipNo":"1","sn":"1","locationId":1,"categoryId":1,"deptId":1,"name":"设备1","source":"来源1","parameter":"parameter1","manufactuer":"厂家1","techState":1,"responsor":"张三","verifyPeriod":1,"latestVerifyDate":"2019-05-09 13:54:30","validDate":"2019-05-16 10:52:07","status":1,"delFlag":0,"location":{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"pid":null,"deptId":1,"name":"设备1","list":null},"dept":{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"deptId":1,"parentId":null,"ancestors":null,"deptName":null,"orderNum":null,"leader":null,"phone":null,"email":null,"status":"1","delFlag":"0","parentName":null,"list":null}},{"id":2,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:49:28","updateBy":"admin","updateTime":"2019-05-17 08:49:36","remark":"222","params":{},"equipNo":"2","sn":"2","locationId":2,"categoryId":2,"deptId":1,"name":"设备2","source":"来源2","parameter":"parameter2","manufactuer":"厂家2","techState":1,"responsor":"李四","verifyPeriod":1,"latestVerifyDate":"2019-05-16 08:49:01","validDate":"2019-05-17 08:49:06","status":0,"delFlag":0,"location":{"id":2,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:49:28","updateBy":"admin","updateTime":"2019-05-17 08:49:36","remark":"222","params":{},"pid":null,"deptId":1,"name":"设备2","list":null},"dept":{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"deptId":1,"parentId":null,"ancestors":null,"deptName":null,"orderNum":null,"leader":null,"phone":null,"email":null,"status":"1","delFlag":"0","parentName":null,"list":null}},{"id":3,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:51:21","updateBy":"admin","updateTime":"2019-05-17 08:51:30","remark":"333","params":{},"equipNo":"3","sn":"3","locationId":3,"categoryId":3,"deptId":2,"name":"设备3","source":"来源3","parameter":"parameter3","manufactuer":"厂家3","techState":0,"responsor":"王五","verifyPeriod":1,"latestVerifyDate":"2019-05-16 08:50:47","validDate":"2019-05-17 08:50:51","status":2,"delFlag":1,"location":{"id":3,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:51:21","updateBy":"admin","updateTime":"2019-05-17 08:51:30","remark":"333","params":{},"pid":null,"deptId":2,"name":"设备3","list":null},"dept":{"id":2,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:49:28","updateBy":"admin","updateTime":"2019-05-17 08:49:36","remark":"222","params":{},"deptId":1,"parentId":null,"ancestors":null,"deptName":null,"orderNum":null,"leader":null,"phone":null,"email":null,"status":"0","delFlag":"0","parentName":null,"list":null}},{"id":4,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:53:00","updateBy":"admin","updateTime":"2019-05-17 08:53:09","remark":"444","params":{},"equipNo":"4","sn":"4","locationId":0,"categoryId":0,"deptId":2,"name":"设备4","source":"来源4","parameter":"parameter4","manufactuer":"厂家4","techState":1,"responsor":"老六","verifyPeriod":1,"latestVerifyDate":"2019-05-16 08:52:39","validDate":"2019-05-17 08:52:43","status":3,"delFlag":0,"location":null,"dept":{"id":2,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:49:28","updateBy":"admin","updateTime":"2019-05-17 08:49:36","remark":"222","params":{},"deptId":1,"parentId":null,"ancestors":null,"deptName":null,"orderNum":null,"leader":null,"phone":null,"email":null,"status":"0","delFlag":"0","parentName":null,"list":null}},{"id":5,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 20:08:27","updateBy":"admin","updateTime":"2019-05-16 20:08:27","remark":null,"params":{},"equipNo":"5","sn":null,"locationId":1,"categoryId":1,"deptId":3,"name":"压力计","source":null,"parameter":"40MPa","manufactuer":"汉阳三厂","techState":null,"responsor":"王五","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":1,"delFlag":0,"location":{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"pid":null,"deptId":1,"name":"设备1","list":null},"dept":{"id":3,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:51:21","updateBy":"admin","updateTime":"2019-05-17 08:51:30","remark":"333","params":{},"deptId":2,"parentId":null,"ancestors":null,"deptName":null,"orderNum":null,"leader":null,"phone":null,"email":null,"status":"2","delFlag":"1","parentName":null,"list":null}},{"id":6,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 21:36:48","updateBy":"admin","updateTime":"2019-05-16 21:36:48","remark":null,"params":{},"equipNo":"6","sn":null,"locationId":2,"categoryId":2,"deptId":3,"name":"压力表","source":null,"parameter":"50MPa","manufactuer":"武汉精密仪器制造厂","techState":null,"responsor":"张三","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":1,"delFlag":0,"location":{"id":2,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:49:28","updateBy":"admin","updateTime":"2019-05-17 08:49:36","remark":"222","params":{},"pid":null,"deptId":1,"name":"设备2","list":null},"dept":{"id":3,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:51:21","updateBy":"admin","updateTime":"2019-05-17 08:51:30","remark":"333","params":{},"deptId":2,"parentId":null,"ancestors":null,"deptName":null,"orderNum":null,"leader":null,"phone":null,"email":null,"status":"2","delFlag":"1","parentName":null,"list":null}},{"id":7,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 21:39:21","updateBy":"admin","updateTime":"2019-05-16 21:39:21","remark":null,"params":{},"equipNo":"7","sn":null,"locationId":3,"categoryId":3,"deptId":0,"name":"减压装置","source":null,"parameter":"30MPa","manufactuer":"华中设备制造厂","techState":null,"responsor":"李四","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":1,"delFlag":0,"location":{"id":3,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:51:21","updateBy":"admin","updateTime":"2019-05-17 08:51:30","remark":"333","params":{},"pid":null,"deptId":2,"name":"设备3","list":null},"dept":null},{"id":8,"searchValue":null,"createBy":"admin","createTime":"2019-05-17 10:45:24","updateBy":"admin","updateTime":"2019-05-17 10:45:24","remark":null,"params":{},"equipNo":"8","sn":null,"locationId":0,"categoryId":0,"deptId":0,"name":" 一起来","source":null,"parameter":" 一步一步","manufactuer":" 湖广会馆","techState":null,"responsor":" hththt","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":1,"delFlag":0,"location":null,"dept":null},{"id":9,"searchValue":null,"createBy":"admin","createTime":"2019-05-17 10:58:43","updateBy":"admin","updateTime":"2019-05-17 10:58:43","remark":null,"params":{},"equipNo":"9","sn":null,"locationId":1,"categoryId":1,"deptId":1,"name":" 二胡","source":null,"parameter":" 地\n","manufactuer":" 震\n","techState":null,"responsor":" 膨胀","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":1,"delFlag":0,"location":{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"pid":null,"deptId":1,"name":"设备1","list":null},"dept":{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"deptId":1,"parentId":null,"ancestors":null,"deptName":null,"orderNum":null,"leader":null,"phone":null,"email":null,"status":"1","delFlag":"0","parentName":null,"list":null}},{"id":10,"searchValue":null,"createBy":"admin","createTime":"2019-05-17 10:59:45","updateBy":"admin","updateTime":"2019-05-17 10:59:45","remark":null,"params":{},"equipNo":"10","sn":null,"locationId":2,"categoryId":2,"deptId":2,"name":" gfv","source":null,"parameter":" 城","manufactuer":" 霏霏械","techState":null,"responsor":" 不折不扣一","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":1,"delFlag":0,"location":{"id":2,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:49:28","updateBy":"admin","updateTime":"2019-05-17 08:49:36","remark":"222","params":{},"pid":null,"deptId":1,"name":"设备2","list":null},"dept":{"id":2,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:49:28","updateBy":"admin","updateTime":"2019-05-17 08:49:36","remark":"222","params":{},"deptId":1,"parentId":null,"ancestors":null,"deptName":null,"orderNum":null,"leader":null,"phone":null,"email":null,"status":"0","delFlag":"0","parentName":null,"list":null}},{"id":11,"searchValue":null,"createBy":"admin","createTime":"2019-05-17 15:12:08","updateBy":"admin","updateTime":"2019-05-17 15:12:08","remark":null,"params":{},"equipNo":"11","sn":null,"locationId":3,"categoryId":3,"deptId":3,"name":" 温度表","source":null,"parameter":" 500","manufactuer":" 河北","techState":null,"responsor":" 010","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":1,"delFlag":0,"location":{"id":3,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:51:21","updateBy":"admin","updateTime":"2019-05-17 08:51:30","remark":"333","params":{},"pid":null,"deptId":2,"name":"设备3","list":null},"dept":{"id":3,"searchValue":null,"createBy":"admin","createTime":"2019-05-16 08:51:21","updateBy":"admin","updateTime":"2019-05-17 08:51:30","remark":"333","params":{},"deptId":2,"parentId":null,"ancestors":null,"deptName":null,"orderNum":null,"leader":null,"phone":null,"email":null,"status":"2","delFlag":"1","parentName":null,"list":null}}]
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

    public static class RowsBean implements Serializable {
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
         * categoryId : 1
         * deptId : 1
         * name : 设备1
         * source : 来源1
         * parameter : parameter1
         * manufactuer : 厂家1
         * techState : 1
         * responsor : 张三
         * verifyPeriod : 1
         * latestVerifyDate : 2019-05-09 13:54:30
         * validDate : 2019-05-16 10:52:07
         * status : 1
         * delFlag : 0
         * location : {"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"pid":null,"deptId":1,"name":"设备1","list":null}
         * dept : {"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"deptId":1,"parentId":null,"ancestors":null,"deptName":null,"orderNum":null,"leader":null,"phone":null,"email":null,"status":"1","delFlag":"0","parentName":null,"list":null}
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

        public String getLatestVerifyDate() {
            return latestVerifyDate == null ? "" : latestVerifyDate;
        }

        public void setLatestVerifyDate(String latestVerifyDate) {
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

        public static class ParamsBean implements Serializable {
        }

        public static class LocationBean implements Serializable {
            /**
             * id : 1
             * searchValue : null
             * createBy : admin
             * createTime : 2019-05-09 13:49:03
             * updateBy : admin
             * updateTime : 2019-05-09 15:21:00
             * remark : 111
             * params : {}
             * pid : null
             * deptId : 1
             * name : 设备1
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
            private Object pid;
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

            public Object getPid() {
                return pid;
            }

            public void setPid(Object pid) {
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

            public static class ParamsBeanX implements Serializable {
            }
        }

        public static class DeptBean implements Serializable {
            /**
             * id : 1
             * searchValue : null
             * createBy : admin
             * createTime : 2019-05-09 13:49:03
             * updateBy : admin
             * updateTime : 2019-05-09 15:21:00
             * remark : 111
             * params : {}
             * deptId : 1
             * parentId : null
             * ancestors : null
             * deptName : null
             * orderNum : null
             * leader : null
             * phone : null
             * email : null
             * status : 1
             * delFlag : 0
             * parentName : null
             * list : null
             */

            private int id;
            private Object searchValue;
            private String createBy;
            private String createTime;
            private String updateBy;
            private String updateTime;
            private String remark;
            private ParamsBeanXX params;
            private int deptId;
            private Object parentId;
            private Object ancestors;
            private String deptName;
            private Object orderNum;
            private Object leader;
            private Object phone;
            private Object email;
            private String status;
            private String delFlag;
            private Object parentName;
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

            public ParamsBeanXX getParams() {
                return params;
            }

            public void setParams(ParamsBeanXX params) {
                this.params = params;
            }

            public int getDeptId() {
                return deptId;
            }

            public void setDeptId(int deptId) {
                this.deptId = deptId;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public Object getAncestors() {
                return ancestors;
            }

            public void setAncestors(Object ancestors) {
                this.ancestors = ancestors;
            }

            public String getDeptName() {
                return deptName == null ? "" : deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public Object getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(Object orderNum) {
                this.orderNum = orderNum;
            }

            public Object getLeader() {
                return leader;
            }

            public void setLeader(Object leader) {
                this.leader = leader;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public Object getParentName() {
                return parentName;
            }

            public void setParentName(Object parentName) {
                this.parentName = parentName;
            }

            public Object getList() {
                return list;
            }

            public void setList(Object list) {
                this.list = list;
            }

            public static class ParamsBeanXX implements Serializable {
            }
        }
    }
}
