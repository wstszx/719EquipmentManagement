package com.example.a719equipmentmanagement.entity;

public class LoginBean {


    /**
     * body : {"access_token":"f476c61c-d31d-4547-bbb3-f13db31c4053","admin":{"account":"admin","addDate":"2018-11-08 17:19:49","id":9,"name":"新疆凯亚","password":"202cb962ac59075b964b07152d234b70","roleId":"1","state":1,"updateDate":"2019-02-27 17:15:17"},"emp":{"addDate":"","addUser":"","address":"","deptId":0,"eId":"","eName":"","idcard":"","img":"","incomeDate":"","qrCode":"","qualiId":"","sex":"","state":"","telphone":"","updateDate":"","workId":""}}
     * code : 200
     * error :
     */

    private BodyBean body;
    private String code;
    private String error;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class BodyBean {
        /**
         * access_token : f476c61c-d31d-4547-bbb3-f13db31c4053
         * admin : {"account":"admin","addDate":"2018-11-08 17:19:49","id":9,"name":"新疆凯亚","password":"202cb962ac59075b964b07152d234b70","roleId":"1","state":1,"updateDate":"2019-02-27 17:15:17"}
         * emp : {"addDate":"","addUser":"","address":"","deptId":0,"eId":"","eName":"","idcard":"","img":"","incomeDate":"","qrCode":"","qualiId":"","sex":"","state":"","telphone":"","updateDate":"","workId":""}
         */

        private String access_token;
        private AdminBean admin;
        private EmpBean emp;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public AdminBean getAdmin() {
            return admin;
        }

        public void setAdmin(AdminBean admin) {
            this.admin = admin;
        }

        public EmpBean getEmp() {
            return emp;
        }

        public void setEmp(EmpBean emp) {
            this.emp = emp;
        }

        public static class AdminBean {
            /**
             * account : admin
             * addDate : 2018-11-08 17:19:49
             * id : 9
             * name : 新疆凯亚
             * password : 202cb962ac59075b964b07152d234b70
             * roleId : 1
             * state : 1
             * updateDate : 2019-02-27 17:15:17
             */

            private String account;
            private String addDate;
            private int id;
            private String name;
            private String password;
            private String roleId;
            private int state;
            private String updateDate;

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getAddDate() {
                return addDate;
            }

            public void setAddDate(String addDate) {
                this.addDate = addDate;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getRoleId() {
                return roleId;
            }

            public void setRoleId(String roleId) {
                this.roleId = roleId;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }
        }

        public static class EmpBean {
            /**
             * addDate :
             * addUser :
             * address :
             * deptId : 0
             * eId :
             * eName :
             * idcard :
             * img :
             * incomeDate :
             * qrCode :
             * qualiId :
             * sex :
             * state :
             * telphone :
             * updateDate :
             * workId :
             */

            private String addDate;
            private String addUser;
            private String address;
            private int deptId;
            private String eId;
            private String eName;
            private String idcard;
            private String img;
            private String incomeDate;
            private String qrCode;
            private String qualiId;
            private String sex;
            private String state;
            private String telphone;
            private String updateDate;
            private String workId;

            public String getAddDate() {
                return addDate;
            }

            public void setAddDate(String addDate) {
                this.addDate = addDate;
            }

            public String getAddUser() {
                return addUser;
            }

            public void setAddUser(String addUser) {
                this.addUser = addUser;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getDeptId() {
                return deptId;
            }

            public void setDeptId(int deptId) {
                this.deptId = deptId;
            }

            public String getEId() {
                return eId;
            }

            public void setEId(String eId) {
                this.eId = eId;
            }

            public String getEName() {
                return eName;
            }

            public void setEName(String eName) {
                this.eName = eName;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getIncomeDate() {
                return incomeDate;
            }

            public void setIncomeDate(String incomeDate) {
                this.incomeDate = incomeDate;
            }

            public String getQrCode() {
                return qrCode;
            }

            public void setQrCode(String qrCode) {
                this.qrCode = qrCode;
            }

            public String getQualiId() {
                return qualiId;
            }

            public void setQualiId(String qualiId) {
                this.qualiId = qualiId;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getTelphone() {
                return telphone;
            }

            public void setTelphone(String telphone) {
                this.telphone = telphone;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public String getWorkId() {
                return workId;
            }

            public void setWorkId(String workId) {
                this.workId = workId;
            }
        }
    }
}
