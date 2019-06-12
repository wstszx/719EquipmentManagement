package com.example.a719equipmentmanagement.entity;

import java.util.ArrayList;
import java.util.List;

public class Me {


    /**
     * postGroup : 董事长
     * user : {"id":null,"searchValue":null,"createBy":null,"createTime":"2018-03-16 11:33:00","updateBy":null,"updateTime":null,"remark":"管理员","userId":1,"deptId":100,"parentId":null,"loginName":"admin","userName":"王刚","email":"wg@163.com","phonenumber":"15888888888","sex":"男","avatar":"2019/06/03/65f81e08ca118375d3e5005e1162db0c.jpg","password":"1033075292f90df5af9af9e4361677c2","salt":"c2c2d4","status":"0","delFlag":"0","loginIp":"127.0.0.1","loginDate":"2019-06-07 23:58:14","dept":{"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"deptId":100,"parentId":0,"ancestors":null,"deptName":"三亚试验","orderNum":"0","leader":null,"phone":null,"email":null,"status":"0","delFlag":null,"parentName":null,"users":null},"roles":[{"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"roleId":1,"roleName":"超级系统管理员","roleKey":"admin","roleSort":"1","dataScope":"1","status":"0","delFlag":null,"flag":false,"menuIds":null,"deptIds":null}],"roleIds":[1],"postIds":null,"list":null,"comUser":false,"admin":true,"manager":false}
     * roleGroup : 超级系统管理员
     */

    private String postGroup;
    private UserBean user;
    private String roleGroup;

    public String getPostGroup() {
        return postGroup;
    }

    public void setPostGroup(String postGroup) {
        this.postGroup = postGroup;
    }

    public UserBean getDeptList() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(String roleGroup) {
        this.roleGroup = roleGroup;
    }

    public static class UserBean {
        /**
         * id : null
         * searchValue : null
         * createBy : null
         * createTime : 2018-03-16 11:33:00
         * updateBy : null
         * updateTime : null
         * remark : 管理员
         * userId : 1
         * deptId : 100
         * parentId : null
         * loginName : admin
         * userName : 王刚
         * email : wg@163.com
         * phonenumber : 15888888888
         * sex : 男
         * avatar : 2019/06/03/65f81e08ca118375d3e5005e1162db0c.jpg
         * password : 1033075292f90df5af9af9e4361677c2
         * salt : c2c2d4
         * status : 0
         * delFlag : 0
         * loginIp : 127.0.0.1
         * loginDate : 2019-06-07 23:58:14
         * dept : {"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"deptId":100,"parentId":0,"ancestors":null,"deptName":"三亚试验","orderNum":"0","leader":null,"phone":null,"email":null,"status":"0","delFlag":null,"parentName":null,"users":null}
         * roles : [{"id":null,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"roleId":1,"roleName":"超级系统管理员","roleKey":"admin","roleSort":"1","dataScope":"1","status":"0","delFlag":null,"flag":false,"menuIds":null,"deptIds":null}]
         * roleIds : [1]
         * postIds : null
         * list : null
         * comUser : false
         * admin : true
         * manager : false
         */

        private int id;
        private Object searchValue;
        private Object createBy;
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
        private Object postIds;
        private Object list;
        private boolean comUser;
        private boolean admin;
        private boolean manager;
        private List<RolesBean> roles;
        private List<Integer> roleIds;

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

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
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

        public int getDeptListId() {
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

        public String getDeptListName() {
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

        public List<RolesBean> getRoles() {
            if (roles == null) {
                return new ArrayList<>();
            }
            return roles;
        }

        public void setRoles(List<RolesBean> roles) {
            this.roles = roles;
        }

        public List<Integer> getRoleIds() {
            if (roleIds == null) {
                return new ArrayList<>();
            }
            return roleIds;
        }

        public void setRoleIds(List<Integer> roleIds) {
            this.roleIds = roleIds;
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
             * deptId : 100
             * parentId : 0
             * ancestors : null
             * deptName : 三亚试验
             * orderNum : 0
             * leader : null
             * phone : null
             * email : null
             * status : 0
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
            private int parentId;
            private Object ancestors;
            private String deptName;
            private String orderNum;
            private Object leader;
            private Object phone;
            private Object email;
            private String status;
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

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
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

            public String getOrderNum() {
                return orderNum == null ? "" : orderNum;
            }

            public void setOrderNum(String orderNum) {
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

        public static class RolesBean {
            /**
             * id : null
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * roleId : 1
             * roleName : 超级系统管理员
             * roleKey : admin
             * roleSort : 1
             * dataScope : 1
             * status : 0
             * delFlag : null
             * flag : false
             * menuIds : null
             * deptIds : null
             */

            private Object id;
            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private int roleId;
            private String roleName;
            private String roleKey;
            private String roleSort;
            private String dataScope;
            private String status;
            private Object delFlag;
            private boolean flag;
            private Object menuIds;
            private Object deptIds;

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

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }

            public String getRoleName() {
                return roleName == null ? "" : roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public String getRoleKey() {
                return roleKey == null ? "" : roleKey;
            }

            public void setRoleKey(String roleKey) {
                this.roleKey = roleKey;
            }

            public String getRoleSort() {
                return roleSort == null ? "" : roleSort;
            }

            public void setRoleSort(String roleSort) {
                this.roleSort = roleSort;
            }

            public String getDataScope() {
                return dataScope == null ? "" : dataScope;
            }

            public void setDataScope(String dataScope) {
                this.dataScope = dataScope;
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

            public boolean isFlag() {
                return flag;
            }

            public void setFlag(boolean flag) {
                this.flag = flag;
            }

            public Object getMenuIds() {
                return menuIds;
            }

            public void setMenuIds(Object menuIds) {
                this.menuIds = menuIds;
            }

            public Object getDeptIds() {
                return deptIds;
            }

            public void setDeptIds(Object deptIds) {
                this.deptIds = deptIds;
            }
        }
    }
}
