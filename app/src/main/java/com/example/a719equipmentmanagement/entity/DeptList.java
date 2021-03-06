package com.example.a719equipmentmanagement.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeptList implements Serializable {


    /**
     * id : 155
     * searchValue : null
     * createBy : admin
     * createTime : 2019-06-05 15:26:32
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
     * delFlag : 0
     * parentName : null
     * users : [{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:31:02","updateBy":null,"updateTime":null,"remark":"manager02","userId":120,"deptId":155,"parentId":null,"loginName":"manager02","userName":"manager02","email":"manager02@qq.com","phonenumber":"18674075322","sex":"0","avatar":"","password":"9f15560d81684b285703789436d2e25f","salt":"5a2299","status":"0","delFlag":"0","loginIp":"10.11.48.121","loginDate":"2019-06-12 14:40:01","dept":{"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"deptId":155,"parentId":null,"ancestors":null,"deptName":"301实验室","orderNum":null,"leader":null,"phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"users":null},"roles":[],"roleIds":null,"postIds":null,"list":null,"comUser":false,"admin":false,"manager":false},{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 16:29:15","updateBy":null,"updateTime":null,"remark":"user02","userId":121,"deptId":155,"parentId":null,"loginName":"user02","userName":"user02","email":"user02@qq.com","phonenumber":"1867407532","sex":"0","avatar":"","password":"6b21ef92dc9ec892912c2b1015ce6117","salt":"8b3dff","status":"0","delFlag":"0","loginIp":"10.11.48.121","loginDate":"2019-06-11 18:08:08","dept":{"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"deptId":155,"parentId":null,"ancestors":null,"deptName":"301实验室","orderNum":null,"leader":null,"phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"users":null},"roles":[],"roleIds":null,"postIds":null,"list":null,"comUser":false,"admin":false,"manager":false},{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 16:29:39","updateBy":null,"updateTime":null,"remark":"user03","userId":122,"deptId":155,"parentId":null,"loginName":"user03","userName":"user03","email":"user03@qq.com","phonenumber":"18674075324","sex":"0","avatar":"","password":"e65c9621143a96a873b5cb12c23fb67d","salt":"fa0d1d","status":"0","delFlag":"0","loginIp":"","loginDate":null,"dept":{"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"deptId":155,"parentId":null,"ancestors":null,"deptName":"301实验室","orderNum":null,"leader":null,"phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"users":null},"roles":[],"roleIds":null,"postIds":null,"list":null,"comUser":false,"admin":false,"manager":false}]
     */

    private int id;
    private Object searchValue;
    private String createBy;
    private String createTime;
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
    private String delFlag;
    private Object parentName;
    private List<UsersBean> users;

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

    public List<UsersBean> getUsers() {
        if (users == null) {
            return new ArrayList<>();
        }
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean implements Serializable{
        /**
         * id : null
         * searchValue : null
         * createBy : admin
         * createTime : 2019-06-05 15:31:02
         * updateBy : null
         * updateTime : null
         * remark : manager02
         * userId : 120
         * deptId : 155
         * parentId : null
         * loginName : manager02
         * userName : manager02
         * email : manager02@qq.com
         * phonenumber : 18674075322
         * sex : 0
         * avatar :
         * password : 9f15560d81684b285703789436d2e25f
         * salt : 5a2299
         * status : 0
         * delFlag : 0
         * loginIp : 10.11.48.121
         * loginDate : 2019-06-12 14:40:01
         * dept : {"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"deptId":155,"parentId":null,"ancestors":null,"deptName":"301实验室","orderNum":null,"leader":null,"phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"users":null}
         * roles : []
         * roleIds : null
         * postIds : null
         * list : null
         * comUser : false
         * admin : false
         * manager : false
         */

        private Object id;
        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private String remark;
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
        private String loginDate;
        private DeptBean dept;
        private Object roleIds;
        private Object postIds;
        private Object list;
        private boolean comUser;
        private boolean admin;
        private boolean manager;
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
            return loginName == null ? "" : loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getUserName() {
            return userName == null ? "" : userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email == null ? "" : email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhonenumber() {
            return phonenumber == null ? "" : phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getSex() {
            return sex == null ? "" : sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar == null ? "" : avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPassword() {
            return password == null ? "" : password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt == null ? "" : salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
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

        public String getLoginIp() {
            return loginIp == null ? "" : loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public String getLoginDate() {
            return loginDate == null ? "" : loginDate;
        }

        public void setLoginDate(String loginDate) {
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

        public boolean isComUser() {
            return comUser;
        }

        public void setComUser(boolean comUser) {
            this.comUser = comUser;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public boolean isManager() {
            return manager;
        }

        public void setManager(boolean manager) {
            this.manager = manager;
        }

        public List<?> getRoles() {
            if (roles == null) {
                return new ArrayList<>();
            }
            return roles;
        }

        public void setRoles(List<?> roles) {
            this.roles = roles;
        }

        public static class DeptBean implements Serializable{
            /**
             * id : null
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * deptId : 155
             * parentId : null
             * ancestors : null
             * deptName : 301实验室
             * orderNum : null
             * leader : null
             * phone : null
             * email : null
             * status : null
             * delFlag : null
             * parentName : null
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

            public Object getUsers() {
                return users;
            }

            public void setUsers(Object users) {
                this.users = users;
            }
        }
    }
}
