package com.example.a719equipmentmanagement.entity;

import java.util.ArrayList;
import java.util.List;

public class UserToAudit {


    /**
     * total : 1
     * rows : [{"id":29,"searchValue":null,"createBy":"user02","createTime":"2019-06-10 15:43:58","updateBy":null,"updateTime":null,"remark":null,"type":3,"equipId":27,"deptId":155,"state":1,"dealer":null,"auditOpinion":null,"equip":{"id":27,"searchValue":null,"createBy":"admin","createTime":"2019-06-10 09:40:44","updateBy":"user02","updateTime":"2019-06-10 15:43:58","remark":null,"equipNo":"20190610000000002","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"不","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":2,"delFlag":0,"location":null,"dept":null,"opers":[]}}]
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
         * id : 29
         * searchValue : null
         * createBy : user02
         * createTime : 2019-06-10 15:43:58
         * updateBy : null
         * updateTime : null
         * remark : null
         * type : 3
         * equipId : 27
         * deptId : 155
         * state : 1
         * dealer : null
         * auditOpinion : null
         * equip : {"id":27,"searchValue":null,"createBy":"admin","createTime":"2019-06-10 09:40:44","updateBy":"user02","updateTime":"2019-06-10 15:43:58","remark":null,"equipNo":"20190610000000002","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"不","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":2,"delFlag":0,"location":null,"dept":null,"opers":[]}
         */

        private int id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
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

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
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

        public static class EquipBean {
            /**
             * id : 27
             * searchValue : null
             * createBy : admin
             * createTime : 2019-06-10 09:40:44
             * updateBy : user02
             * updateTime : 2019-06-10 15:43:58
             * remark : null
             * equipNo : 20190610000000002
             * sn : null
             * locationId : 0
             * categoryId : 0
             * category : null
             * deptId : 0
             * name : 不
             * source : null
             * parameter :
             * manufactuer :
             * techState : 0
             * responsor :
             * verifyPeriod : null
             * latestVerifyDate : null
             * validDate : null
             * status : 2
             * delFlag : 0
             * location : null
             * dept : null
             * opers : []
             */

            private int id;
            private Object searchValue;
            private String createBy;
            private String createTime;
            private String updateBy;
            private String updateTime;
            private Object remark;
            private String equipNo;
            private String sn;
            private int locationId;
            private int categoryId;
            private Object category;
            private int deptId;
            private String name;
            private Object source;
            private String parameter;
            private String manufactuer;
            private int techState;
            private String responsor;
            private Object verifyPeriod;
            private Object latestVerifyDate;
            private Object validDate;
            private int status;
            private int delFlag;
            private Object location;
            private Object dept;
            private List<?> opers;

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

            public Object getCategory() {
                return category;
            }

            public void setCategory(Object category) {
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

            public Object getSource() {
                return source;
            }

            public void setSource(Object source) {
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

            public Object getVerifyPeriod() {
                return verifyPeriod;
            }

            public void setVerifyPeriod(Object verifyPeriod) {
                this.verifyPeriod = verifyPeriod;
            }

            public Object getLatestVerifyDate() {
                return latestVerifyDate;
            }

            public void setLatestVerifyDate(Object latestVerifyDate) {
                this.latestVerifyDate = latestVerifyDate;
            }

            public Object getValidDate() {
                return validDate;
            }

            public void setValidDate(Object validDate) {
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

            public List<?> getOpers() {
                if (opers == null) {
                    return new ArrayList<>();
                }
                return opers;
            }

            public void setOpers(List<?> opers) {
                this.opers = opers;
            }
        }
    }
}
