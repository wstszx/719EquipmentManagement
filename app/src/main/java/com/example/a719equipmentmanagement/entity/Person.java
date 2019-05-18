package com.example.a719equipmentmanagement.entity;

import java.util.List;

public class Person {

    /**
     * total : 5
     * rows : [{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-05-18 12:55:43","updateBy":null,"updateTime":null,"remark":"","params":{},"userId":105,"deptId":129,"parentId":null,"loginName":"ws","userName":"ws","email":"d@qq.com","phonenumber":"15689755696","sex":"0","avatar":"","password":"eaeee930525bb424a161dd375535f9e8","salt":"ba705b","status":"0","delFlag":"0","loginIp":"","loginDate":null,"dept":{"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"deptId":129,"parentId":null,"ancestors":null,"deptName":"geggege","orderNum":null,"leader":null,"phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"list":null},"roles":[],"roleIds":null,"postIds":null,"list":null,"admin":false},{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-05-18 11:10:22","updateBy":null,"updateTime":null,"remark":"我是新增的员工","params":{},"userId":104,"deptId":112,"parentId":null,"loginName":"test","userName":"新加人员","email":"45@qq.com","phonenumber":"15656896325","sex":"0","avatar":"","password":"0a26992a9811fb55399104d9afeef58b","salt":"501648","status":"0","delFlag":"0","loginIp":"","loginDate":null,"dept":{"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"deptId":112,"parentId":null,"ancestors":null,"deptName":"303实验室","orderNum":null,"leader":null,"phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"list":null},"roles":[],"roleIds":null,"postIds":null,"list":null,"admin":false},{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-04-01 16:13:54","updateBy":null,"updateTime":null,"remark":"","params":{},"userId":102,"deptId":110,"parentId":null,"loginName":"user","userName":"user","email":"user@qq.com","phonenumber":"18712345678","sex":"0","avatar":"","password":"89e18347c8a74334121fd771c15ab19d","salt":"94dedf","status":"0","delFlag":"0","loginIp":"127.0.0.1","loginDate":"2019-04-01 16:20:57","dept":{"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"deptId":110,"parentId":null,"ancestors":null,"deptName":"301实验室","orderNum":null,"leader":null,"phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"list":null},"roles":[],"roleIds":null,"postIds":null,"list":null,"admin":false},{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-04-01 16:13:03","updateBy":null,"updateTime":null,"remark":"","params":{},"userId":101,"deptId":110,"parentId":null,"loginName":"manager","userName":"manager","email":"manager@qq.com","phonenumber":"18612345678","sex":"0","avatar":"","password":"0d2e2f9447716a9dc0913d95ccbede4d","salt":"fdd9dd","status":"0","delFlag":"0","loginIp":"127.0.0.1","loginDate":"2019-04-01 16:35:37","dept":{"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"deptId":110,"parentId":null,"ancestors":null,"deptName":"301实验室","orderNum":null,"leader":null,"phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"list":null},"roles":[],"roleIds":null,"postIds":null,"list":null,"admin":false},{"id":null,"searchValue":null,"createBy":"admin","createTime":"2018-03-16 11:33:00","updateBy":null,"updateTime":null,"remark":"管理员","params":{},"userId":1,"deptId":100,"parentId":null,"loginName":"admin","userName":"王刚","email":"wg@163.com","phonenumber":"15888888888","sex":"0","avatar":"2019/04/26/dce395967e9963c1aa1d6ba6297e4b9f.jpg","password":"29c67a30398638269fe600f73a054934","salt":"111111","status":"0","delFlag":"0","loginIp":"10.11.57.112","loginDate":"2019-05-18 14:32:06","dept":{"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"deptId":100,"parentId":null,"ancestors":null,"deptName":"三亚试验","orderNum":null,"leader":null,"phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"list":null},"roles":[],"roleIds":null,"postIds":null,"list":null,"admin":true}]
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
         * id : null
         * searchValue : null
         * createBy : admin
         * createTime : 2019-05-18 12:55:43
         * updateBy : null
         * updateTime : null
         * remark :
         * params : {}
         * userId : 105
         * deptId : 129
         * parentId : null
         * loginName : ws
         * userName : ws
         * email : d@qq.com
         * phonenumber : 15689755696
         * sex : 0
         * avatar :
         * password : eaeee930525bb424a161dd375535f9e8
         * salt : ba705b
         * status : 0
         * delFlag : 0
         * loginIp :
         * loginDate : null
         * dept : {"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"deptId":129,"parentId":null,"ancestors":null,"deptName":"geggege","orderNum":null,"leader":null,"phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"list":null}
         * roles : []
         * roleIds : null
         * postIds : null
         * list : null
         * admin : false
         */

        private Object id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private String remark;
        private ParamsBean params;
        private int userId;
        private int deptId;
        private Object parentId;
        private String loginName;
        private String userName;
        private String email;
        private String phonenumber;
        private String sex;
        private String avatar;
        private String password;
        private String salt;
        private String status;
        private String delFlag;
        private String loginIp;
        private Object loginDate;
        private DeptBean dept;
        private Object roleIds;
        private Object postIds;
        private Object list;
        private boolean admin;
        private List<?> roles;

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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
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

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public Object getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(Object loginDate) {
            this.loginDate = loginDate;
        }

        public DeptBean getDept() {
            return dept;
        }

        public void setDept(DeptBean dept) {
            this.dept = dept;
        }

        public Object getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(Object roleIds) {
            this.roleIds = roleIds;
        }

        public Object getPostIds() {
            return postIds;
        }

        public void setPostIds(Object postIds) {
            this.postIds = postIds;
        }

        public Object getList() {
            return list;
        }

        public void setList(Object list) {
            this.list = list;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public List<?> getRoles() {
            return roles;
        }

        public void setRoles(List<?> roles) {
            this.roles = roles;
        }

        public static class ParamsBean {
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
             * deptId : 129
             * parentId : null
             * ancestors : null
             * deptName : geggege
             * orderNum : null
             * leader : null
             * phone : null
             * email : null
             * status : null
             * delFlag : null
             * parentName : null
             * list : null
             */

            private Object id;
            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBeanX params;
            private int deptId;
            private Object parentId;
            private Object ancestors;
            private String deptName;
            private Object orderNum;
            private Object leader;
            private Object phone;
            private Object email;
            private Object status;
            private Object delFlag;
            private Object parentName;
            private Object list;

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

            public ParamsBeanX getParams() {
                return params;
            }

            public void setParams(ParamsBeanX params) {
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
                return deptName;
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

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(Object delFlag) {
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

            public static class ParamsBeanX {
            }
        }
    }
}
