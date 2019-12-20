package com.example.a719equipmentmanagement.entity;

import java.util.ArrayList;
import java.util.List;

public class HandleHistory {


    /**
     * total : 2
     * rows : [{"id":27,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 14:07:02","updateBy":null,"updateTime":null,"remark":null,"type":13,"equipId":23,"deptId":156,"state":4,"dealer":null,"auditOpinion":null,"equip":{"id":23,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 13:46:34","updateBy":"admin","updateTime":"2019-06-06 14:07:02","remark":"振声检测仪器1","equipNo":"20190606026000003","sn":"6532","locationId":36,"categoryId":27,"category":null,"deptId":156,"name":"振声检测仪器1","source":"上级分配","parameter":"string","manufactuer":"厂商5","techState":1,"responsor":"陈曙初","verifyPeriod":12,"latestVerifyDate":null,"validDate":"2019-06-05 05:56:31","status":0,"delFlag":0,"location":{"id":36,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 20:13:31","updateBy":"admin","updateTime":"2019-06-05 20:13:31","remark":"3#第2层","pid":32,"deptId":156,"name":"3#第2层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 19:57:06","updateBy":"admin","updateTime":"2019-06-05 19:57:25","remark":null,"deptId":156,"parentId":100,"ancestors":"0,100","deptName":"303实验室","orderNum":"3","leader":"manager03","phone":"17826804692","email":"2678133774@qq.com","status":"0","delFlag":"0","parentName":null,"users":null},"opers":[]}},{"id":21,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 17:07:50","updateBy":null,"updateTime":null,"remark":"111","type":9,"equipId":18,"deptId":155,"state":4,"dealer":null,"auditOpinion":null,"equip":{"id":18,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 16:24:00","updateBy":"admin","updateTime":"2019-06-05 17:09:13","remark":"无损检测仪器2","equipNo":"20190605030003","sn":"98764","locationId":30,"categoryId":23,"category":{"id":23,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:34:03","updateBy":"admin","updateTime":"2019-06-05 15:34:03","remark":"无损子类2","pid":21,"name":"无损子类2","delFlag":0,"categorys":null},"deptId":155,"name":"无损检测仪器2","source":"上级分配","parameter":"string","manufactuer":"厂商2","techState":1,"responsor":"徐惟罡","verifyPeriod":12,"latestVerifyDate":null,"validDate":"2019-06-05 08:22:32","status":5,"delFlag":0,"location":{"id":30,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:36:33","updateBy":"admin","updateTime":"2019-06-05 15:36:33","remark":"2#第2层","pid":28,"deptId":155,"name":"2#第2层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:26:32","updateBy":"","updateTime":null,"remark":null,"deptId":155,"parentId":100,"ancestors":"0,100","deptName":"301实验室","orderNum":"2","leader":"manager02","phone":"18674075322","email":"524545375@qq.com","status":"0","delFlag":"0","parentName":null,"users":null},"opers":[]}}]
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
         * id : 27
         * searchValue : null
         * createBy : admin
         * createTime : 2019-06-06 14:07:02
         * updateBy : null
         * updateTime : null
         * remark : null
         * type : 13
         * equipId : 23
         * deptId : 156
         * state : 4
         * dealer : null
         * auditOpinion : null
         * equip : {"id":23,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 13:46:34","updateBy":"admin","updateTime":"2019-06-06 14:07:02","remark":"振声检测仪器1","equipNo":"20190606026000003","sn":"6532","locationId":36,"categoryId":27,"category":null,"deptId":156,"name":"振声检测仪器1","source":"上级分配","parameter":"string","manufactuer":"厂商5","techState":1,"responsor":"陈曙初","verifyPeriod":12,"latestVerifyDate":null,"validDate":"2019-06-05 05:56:31","status":0,"delFlag":0,"location":{"id":36,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 20:13:31","updateBy":"admin","updateTime":"2019-06-05 20:13:31","remark":"3#第2层","pid":32,"deptId":156,"name":"3#第2层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 19:57:06","updateBy":"admin","updateTime":"2019-06-05 19:57:25","remark":null,"deptId":156,"parentId":100,"ancestors":"0,100","deptName":"303实验室","orderNum":"3","leader":"manager03","phone":"17826804692","email":"2678133774@qq.com","status":"0","delFlag":"0","parentName":null,"users":null},"opers":[]}
         */

        private int id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private String updateTime;
        private String remark;
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
             * id : 23
             * searchValue : null
             * createBy : admin
             * createTime : 2019-06-06 13:46:34
             * updateBy : admin
             * updateTime : 2019-06-06 14:07:02
             * remark : 振声检测仪器1
             * equipNo : 20190606026000003
             * sn : 6532
             * locationId : 36
             * categoryId : 27
             * category : null
             * deptId : 156
             * name : 振声检测仪器1
             * source : 上级分配
             * parameter : string
             * manufactuer : 厂商5
             * techState : 1
             * responsor : 陈曙初
             * verifyPeriod : 12
             * latestVerifyDate : null
             * validDate : 2019-06-05 05:56:31
             * status : 0
             * delFlag : 0
             * location : {"id":36,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 20:13:31","updateBy":"admin","updateTime":"2019-06-05 20:13:31","remark":"3#第2层","pid":32,"deptId":156,"name":"3#第2层","containers":null}
             * dept : {"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 19:57:06","updateBy":"admin","updateTime":"2019-06-05 19:57:25","remark":null,"deptId":156,"parentId":100,"ancestors":"0,100","deptName":"303实验室","orderNum":"3","leader":"manager03","phone":"17826804692","email":"2678133774@qq.com","status":"0","delFlag":"0","parentName":null,"users":null}
             * opers : []
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
            private Object category;
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

            public List<?> getOpers() {
                if (opers == null) {
                    return new ArrayList<>();
                }
                return opers;
            }

            public void setOpers(List<?> opers) {
                this.opers = opers;
            }

            public static class LocationBean {
                /**
                 * id : 36
                 * searchValue : null
                 * createBy : admin
                 * createTime : 2019-06-05 20:13:31
                 * updateBy : admin
                 * updateTime : 2019-06-05 20:13:31
                 * remark : 3#第2层
                 * pid : 32
                 * deptId : 156
                 * name : 3#第2层
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
                 * createBy : admin
                 * createTime : 2019-06-05 19:57:06
                 * updateBy : admin
                 * updateTime : 2019-06-05 19:57:25
                 * remark : null
                 * deptId : 156
                 * parentId : 100
                 * ancestors : 0,100
                 * deptName : 303实验室
                 * orderNum : 3
                 * leader : manager03
                 * phone : 17826804692
                 * email : 2678133774@qq.com
                 * status : 0
                 * delFlag : 0
                 * parentName : null
                 * users : null
                 */

                private Object id;
                private Object searchValue;
                private String createBy;
                private String createTime;
                private String updateBy;
                private String updateTime;
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
                private String delFlag;
                private Object parentName;
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

                public String getDelFlag() {
                    return delFlag == null ? "" : delFlag;
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

                public Object getDeptLists() {
                    return users;
                }

                public void setUsers(Object users) {
                    this.users = users;
                }
            }
        }
    }
}
