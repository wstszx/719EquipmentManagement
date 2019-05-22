package com.example.a719equipmentmanagement.entity;

import java.util.ArrayList;
import java.util.List;

public class ListMyAllBorrow {

    /**
     * total : 7
     * rows : [{"id":8,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 15:18:41","updateBy":null,"updateTime":null,"remark":"1","params":{},"type":1,"equipId":1,"deptId":1,"state":1,"dealer":null,"auditOpinion":null,"equip":{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"equipNo":"1","sn":"1","locationId":1,"categoryId":1,"deptId":100,"name":"设备1","source":"来源1","parameter":"parameter1","manufactuer":"厂家1","techState":1,"responsor":"张三","verifyPeriod":1,"latestVerifyDate":"2019-05-09 13:54:30","validDate":"2019-05-16 10:52:07","status":1,"delFlag":0,"location":null,"dept":null}},{"id":7,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 15:12:37","updateBy":"admin","updateTime":"2019-05-09 15:15:18","remark":"1","params":{},"type":1,"equipId":1,"deptId":1,"state":3,"dealer":"admin","auditOpinion":"1","equip":{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"equipNo":"1","sn":"1","locationId":1,"categoryId":1,"deptId":100,"name":"设备1","source":"来源1","parameter":"parameter1","manufactuer":"厂家1","techState":1,"responsor":"张三","verifyPeriod":1,"latestVerifyDate":"2019-05-09 13:54:30","validDate":"2019-05-16 10:52:07","status":1,"delFlag":0,"location":null,"dept":null}},{"id":5,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:37:47","updateBy":"admin","updateTime":"2019-05-09 13:41:12","remark":"1","params":{},"type":1,"equipId":1,"deptId":null,"state":3,"dealer":"admin","auditOpinion":null,"equip":{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"equipNo":"1","sn":"1","locationId":1,"categoryId":1,"deptId":100,"name":"设备1","source":"来源1","parameter":"parameter1","manufactuer":"厂家1","techState":1,"responsor":"张三","verifyPeriod":1,"latestVerifyDate":"2019-05-09 13:54:30","validDate":"2019-05-16 10:52:07","status":1,"delFlag":0,"location":null,"dept":null}},{"id":4,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:27:06","updateBy":"admin","updateTime":"2019-05-09 13:30:04","remark":"1","params":{},"type":1,"equipId":1,"deptId":null,"state":3,"dealer":"admin","auditOpinion":null,"equip":{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"equipNo":"1","sn":"1","locationId":1,"categoryId":1,"deptId":100,"name":"设备1","source":"来源1","parameter":"parameter1","manufactuer":"厂家1","techState":1,"responsor":"张三","verifyPeriod":1,"latestVerifyDate":"2019-05-09 13:54:30","validDate":"2019-05-16 10:52:07","status":1,"delFlag":0,"location":null,"dept":null}},{"id":3,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:22:13","updateBy":"admin","updateTime":"2019-05-09 13:25:25","remark":"111","params":{},"type":1,"equipId":1,"deptId":null,"state":3,"dealer":"admin","auditOpinion":null,"equip":{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"equipNo":"1","sn":"1","locationId":1,"categoryId":1,"deptId":100,"name":"设备1","source":"来源1","parameter":"parameter1","manufactuer":"厂家1","techState":1,"responsor":"张三","verifyPeriod":1,"latestVerifyDate":"2019-05-09 13:54:30","validDate":"2019-05-16 10:52:07","status":1,"delFlag":0,"location":null,"dept":null}},{"id":2,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:16:16","updateBy":"admin","updateTime":"2019-05-09 13:22:29","remark":"111","params":{},"type":1,"equipId":1,"deptId":null,"state":3,"dealer":"admin","auditOpinion":null,"equip":{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"equipNo":"1","sn":"1","locationId":1,"categoryId":1,"deptId":100,"name":"设备1","source":"来源1","parameter":"parameter1","manufactuer":"厂家1","techState":1,"responsor":"张三","verifyPeriod":1,"latestVerifyDate":"2019-05-09 13:54:30","validDate":"2019-05-16 10:52:07","status":1,"delFlag":0,"location":null,"dept":null}},{"id":1,"searchValue":null,"createBy":"admin","createTime":null,"updateBy":"","updateTime":null,"remark":"","params":{},"type":1,"equipId":1,"deptId":null,"state":3,"dealer":"1","auditOpinion":null,"equip":{"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"equipNo":"1","sn":"1","locationId":1,"categoryId":1,"deptId":100,"name":"设备1","source":"来源1","parameter":"parameter1","manufactuer":"厂家1","techState":1,"responsor":"张三","verifyPeriod":1,"latestVerifyDate":"2019-05-09 13:54:30","validDate":"2019-05-16 10:52:07","status":1,"delFlag":0,"location":null,"dept":null}}]
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
         * id : 8
         * searchValue : null
         * createBy : admin
         * createTime : 2019-05-09 15:18:41
         * updateBy : null
         * updateTime : null
         * remark : 1
         * params : {}
         * type : 1
         * equipId : 1
         * deptId : 1
         * state : 1
         * dealer : null
         * auditOpinion : null
         * equip : {"id":1,"searchValue":null,"createBy":"admin","createTime":"2019-05-09 13:49:03","updateBy":"admin","updateTime":"2019-05-09 15:21:00","remark":"111","params":{},"equipNo":"1","sn":"1","locationId":1,"categoryId":1,"deptId":100,"name":"设备1","source":"来源1","parameter":"parameter1","manufactuer":"厂家1","techState":1,"responsor":"张三","verifyPeriod":1,"latestVerifyDate":"2019-05-09 13:54:30","validDate":"2019-05-16 10:52:07","status":1,"delFlag":0,"location":null,"dept":null}
         */

        private int id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private String remark;
        private ParamsBean params;
        private int type;
        private int equipId;
        private int deptId;
        private int state;
        private Object dealer;
        private Object auditOpinion;
        private EquipBean equip;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getEquipId() {
            return equipId;
        }

        public void setEquipId(int equipId) {
            this.equipId = equipId;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Object getDealer() {
            return dealer;
        }

        public void setDealer(Object dealer) {
            this.dealer = dealer;
        }

        public Object getAuditOpinion() {
            return auditOpinion;
        }

        public void setAuditOpinion(Object auditOpinion) {
            this.auditOpinion = auditOpinion;
        }

        public EquipBean getEquip() {
            return equip;
        }

        public void setEquip(EquipBean equip) {
            this.equip = equip;
        }

        public static class ParamsBean {
        }

        public static class EquipBean {
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
             * deptId : 100
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
             * location : null
             * dept : null
             */

            private int id;
            private Object searchValue;
            private String createBy;
            private String createTime;
            private String updateBy;
            private String updateTime;
            private String remark;
            private ParamsBeanX params;
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
            private Object location;
            private Object dept;

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

            public ParamsBeanX getParams() {
                return params;
            }

            public void setParams(ParamsBeanX params) {
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

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
                this.location = location;
            }

            public Object getDept() {
                return dept;
            }

            public void setDept(Object dept) {
                this.dept = dept;
            }

            public static class ParamsBeanX {
            }
        }
    }
}
