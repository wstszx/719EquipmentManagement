package com.example.a719equipmentmanagement.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeviceData2 implements Serializable {

    /**
     * total : 22
     * rows : [{"id":16,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 14:08:22","updateBy":"admin","updateTime":"2019-06-05 14:21:38","remark":"测试","equipNo":"20190605020001","sn":"12345","locationId":26,"categoryId":20,"category":{"id":20,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 14:02:58","updateBy":"admin","updateTime":"2019-06-05 14:02:58","remark":"油液子类2","pid":18,"name":"油液子类2","delFlag":0,"categorys":null},"deptId":154,"name":"油液仪器1","source":"上级分配","parameter":"string","manufactuer":"厂商1","techState":1,"responsor":"段丽君","verifyPeriod":12,"latestVerifyDate":"2019-06-05 00:00:00","validDate":"2020-08-19 00:00:00","status":0,"delFlag":0,"location":{"id":26,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 14:00:12","updateBy":"admin","updateTime":"2019-06-05 14:00:12","remark":null,"pid":24,"deptId":154,"name":"1#第2层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 13:53:22","updateBy":"","updateTime":null,"remark":null,"deptId":154,"parentId":100,"ancestors":"0,100","deptName":"304实验室","orderNum":"1","leader":"段","phone":"18627099254","email":"18627099254@qq.com","status":"0","delFlag":"0","parentName":null,"users":null},"opers":[]},{"id":17,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:44:29","updateBy":"admin","updateTime":"2019-06-05 18:42:04","remark":"无损检测仪器1","equipNo":"20190605029002","sn":"98765","locationId":29,"categoryId":22,"category":{"id":22,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:33:55","updateBy":"admin","updateTime":"2019-06-05 15:33:55","remark":"无损子类1","pid":21,"name":"无损子类1","delFlag":0,"categorys":null},"deptId":155,"name":"无损检测仪器1","source":"上级分配","parameter":"string","manufactuer":"厂商2","techState":1,"responsor":"徐惟罡","verifyPeriod":12,"latestVerifyDate":null,"validDate":"2019-06-05 07:25:08","status":9,"delFlag":0,"location":{"id":29,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:36:24","updateBy":"admin","updateTime":"2019-06-05 15:36:24","remark":"2#第1层","pid":28,"deptId":155,"name":"2#第1层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:26:32","updateBy":"","updateTime":null,"remark":null,"deptId":155,"parentId":100,"ancestors":"0,100","deptName":"301实验室","orderNum":"2","leader":"manager02","phone":"18674075322","email":"524545375@qq.com","status":"0","delFlag":"0","parentName":null,"users":null},"opers":[]},{"id":18,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 16:24:00","updateBy":"admin","updateTime":"2019-06-05 17:09:13","remark":"无损检测仪器2","equipNo":"20190605030003","sn":"98764","locationId":30,"categoryId":23,"category":{"id":23,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:34:03","updateBy":"admin","updateTime":"2019-06-05 15:34:03","remark":"无损子类2","pid":21,"name":"无损子类2","delFlag":0,"categorys":null},"deptId":155,"name":"无损检测仪器2","source":"上级分配","parameter":"string","manufactuer":"厂商2","techState":1,"responsor":"徐惟罡","verifyPeriod":12,"latestVerifyDate":null,"validDate":"2019-06-05 08:22:32","status":5,"delFlag":0,"location":{"id":30,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:36:33","updateBy":"admin","updateTime":"2019-06-05 15:36:33","remark":"2#第2层","pid":28,"deptId":155,"name":"2#第2层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:26:32","updateBy":"","updateTime":null,"remark":null,"deptId":155,"parentId":100,"ancestors":"0,100","deptName":"301实验室","orderNum":"2","leader":"manager02","phone":"18674075322","email":"524545375@qq.com","status":"0","delFlag":"0","parentName":null,"users":null},"opers":[]},{"id":19,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 16:24:29","updateBy":"admin","updateTime":"2019-06-05 16:24:29","remark":"无损检测仪器3","equipNo":"20190605031004","sn":"98763","locationId":31,"categoryId":22,"category":{"id":22,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:33:55","updateBy":"admin","updateTime":"2019-06-05 15:33:55","remark":"无损子类1","pid":21,"name":"无损子类1","delFlag":0,"categorys":null},"deptId":155,"name":"无损检测仪器3","source":"上级分配","parameter":"string","manufactuer":"厂商2","techState":1,"responsor":"徐惟罡","verifyPeriod":12,"latestVerifyDate":null,"validDate":"2019-06-05 08:22:32","status":0,"delFlag":0,"location":{"id":31,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:36:41","updateBy":"admin","updateTime":"2019-06-05 15:36:41","remark":"2#第3层","pid":28,"deptId":155,"name":"2#第3层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:26:32","updateBy":"","updateTime":null,"remark":null,"deptId":155,"parentId":100,"ancestors":"0,100","deptName":"301实验室","orderNum":"2","leader":"manager02","phone":"18674075322","email":"524545375@qq.com","status":"0","delFlag":"0","parentName":null,"users":null},"opers":[]},{"id":20,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 16:25:25","updateBy":"admin","updateTime":"2019-06-05 16:25:25","remark":"无损检测仪器4","equipNo":"20190605031005","sn":"98762","locationId":31,"categoryId":23,"category":{"id":23,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:34:03","updateBy":"admin","updateTime":"2019-06-05 15:34:03","remark":"无损子类2","pid":21,"name":"无损子类2","delFlag":0,"categorys":null},"deptId":155,"name":"无损检测仪器4","source":"上级分配","parameter":"string","manufactuer":"厂商3","techState":1,"responsor":"徐惟罡","verifyPeriod":12,"latestVerifyDate":null,"validDate":"2019-06-05 08:22:32","status":0,"delFlag":0,"location":{"id":31,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:36:41","updateBy":"admin","updateTime":"2019-06-05 15:36:41","remark":"2#第3层","pid":28,"deptId":155,"name":"2#第3层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 15:26:32","updateBy":"","updateTime":null,"remark":null,"deptId":155,"parentId":100,"ancestors":"0,100","deptName":"301实验室","orderNum":"2","leader":"manager02","phone":"18674075322","email":"524545375@qq.com","status":"0","delFlag":"0","parentName":null,"users":null},"opers":[]},{"id":21,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 11:25:05","updateBy":"admin","updateTime":"2019-06-06 11:25:05","remark":"声噪仪器1","equipNo":"20190606030046001","sn":"string","locationId":46,"categoryId":30,"category":{"id":30,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 10:43:14","updateBy":"admin","updateTime":"2019-06-06 10:43:14","remark":"声噪子类1","pid":29,"name":"声噪子类1","delFlag":0,"categorys":null},"deptId":157,"name":"声噪仪器1","source":"上级分配","parameter":"string","manufactuer":"厂商4","techState":1,"responsor":"余珊珊","verifyPeriod":12,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":{"id":46,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 10:37:16","updateBy":"admin","updateTime":"2019-06-06 10:37:16","remark":"4#第2层","pid":38,"deptId":157,"name":"4#第2层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 09:44:45","updateBy":"admin","updateTime":"2019-06-06 16:11:03","remark":null,"deptId":157,"parentId":100,"ancestors":"0,100","deptName":"302实验室","orderNum":"0","leader":"manager06","phone":"15607174133","email":"2417685836@qq.com","status":"0","delFlag":"0","parentName":null,"users":null},"opers":[]},{"id":22,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 11:40:04","updateBy":"admin","updateTime":"2019-06-06 11:40:04","remark":"声噪仪器2","equipNo":"20190606031047002","sn":"12345","locationId":47,"categoryId":31,"category":{"id":31,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 10:43:21","updateBy":"admin","updateTime":"2019-06-06 10:43:21","remark":"声噪子类2","pid":29,"name":"声噪子类2","delFlag":0,"categorys":null},"deptId":157,"name":"声噪仪器2","source":"上级分配","parameter":"string","manufactuer":"厂商4","techState":1,"responsor":"余珊珊","verifyPeriod":12,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":{"id":47,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 10:37:16","updateBy":"admin","updateTime":"2019-06-06 10:37:16","remark":"4#第3层","pid":38,"deptId":157,"name":"4#第3层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 09:44:45","updateBy":"admin","updateTime":"2019-06-06 16:11:03","remark":null,"deptId":157,"parentId":100,"ancestors":"0,100","deptName":"302实验室","orderNum":"0","leader":"manager06","phone":"15607174133","email":"2417685836@qq.com","status":"0","delFlag":"0","parentName":null,"users":null},"opers":[]},{"id":23,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 13:46:34","updateBy":"admin","updateTime":"2019-06-06 14:07:02","remark":"振声检测仪器1","equipNo":"20190606026000003","sn":"6532","locationId":36,"categoryId":27,"category":{"id":27,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 20:18:27","updateBy":"admin","updateTime":"2019-06-05 20:18:27","remark":"振声子类1","pid":32,"name":"振声子类1","delFlag":0,"categorys":null},"deptId":156,"name":"振声检测仪器1","source":"上级分配","parameter":"string","manufactuer":"厂商5","techState":1,"responsor":"陈曙初","verifyPeriod":12,"latestVerifyDate":null,"validDate":"2019-06-05 05:56:31","status":0,"delFlag":0,"location":{"id":36,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 20:13:31","updateBy":"admin","updateTime":"2019-06-05 20:13:31","remark":"3#第2层","pid":32,"deptId":156,"name":"3#第2层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 19:57:06","updateBy":"admin","updateTime":"2019-06-05 19:57:25","remark":null,"deptId":156,"parentId":100,"ancestors":"0,100","deptName":"303实验室","orderNum":"3","leader":"manager03","phone":"17826804692","email":"2678133774@qq.com","status":"0","delFlag":"0","parentName":null,"users":null},"opers":[]},{"id":24,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 13:49:40","updateBy":"admin","updateTime":"2019-06-06 13:49:40","remark":"振声检测仪器2","equipNo":"20190606026000004","sn":"6532","locationId":37,"categoryId":28,"category":{"id":28,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 20:18:42","updateBy":"admin","updateTime":"2019-06-05 20:18:42","remark":"振声子类2","pid":32,"name":"振声子类2","delFlag":0,"categorys":null},"deptId":156,"name":"振声检测仪器2","source":"上级分配","parameter":"string","manufactuer":"厂商5","techState":1,"responsor":"陈曙初","verifyPeriod":12,"latestVerifyDate":null,"validDate":"2019-06-05 05:56:31","status":0,"delFlag":0,"location":{"id":37,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 20:13:50","updateBy":"admin","updateTime":"2019-06-05 20:13:50","remark":"3#第3层","pid":32,"deptId":156,"name":"3#第3层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 19:57:06","updateBy":"admin","updateTime":"2019-06-05 19:57:25","remark":null,"deptId":156,"parentId":100,"ancestors":"0,100","deptName":"303实验室","orderNum":"3","leader":"manager03","phone":"17826804692","email":"2678133774@qq.com","status":"0","delFlag":"0","parentName":null,"users":null},"opers":[]},{"id":25,"searchValue":null,"createBy":"admin","createTime":"2019-06-06 13:50:31","updateBy":"admin","updateTime":"2019-06-06 13:50:31","remark":"振声检测仪器3","equipNo":"20190606026000005","sn":"6532","locationId":35,"categoryId":27,"category":{"id":27,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 20:18:27","updateBy":"admin","updateTime":"2019-06-05 20:18:27","remark":"振声子类1","pid":32,"name":"振声子类1","delFlag":0,"categorys":null},"deptId":156,"name":"振声检测仪器3","source":"上级分配","parameter":"string","manufactuer":"厂商5","techState":1,"responsor":"陈曙初","verifyPeriod":12,"latestVerifyDate":null,"validDate":"2019-06-05 05:56:31","status":0,"delFlag":0,"location":{"id":35,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 20:13:21","updateBy":"admin","updateTime":"2019-06-05 20:13:21","remark":"3#第1层","pid":32,"deptId":156,"name":"3#第1层","containers":null},"dept":{"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 19:57:06","updateBy":"admin","updateTime":"2019-06-05 19:57:25","remark":null,"deptId":156,"parentId":100,"ancestors":"0,100","deptName":"303实验室","orderNum":"3","leader":"manager03","phone":"17826804692","email":"2678133774@qq.com","status":"0","delFlag":"0","parentName":null,"users":null},"opers":[]},{"id":26,"searchValue":null,"createBy":"admin","createTime":"2019-06-09 13:00:42","updateBy":"admin","updateTime":"2019-06-09 13:00:42","remark":null,"equipNo":"20190609000000001","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"按","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":null,"dept":null,"opers":[]},{"id":27,"searchValue":null,"createBy":"admin","createTime":"2019-06-09 13:00:42","updateBy":"admin","updateTime":"2019-06-09 13:00:42","remark":null,"equipNo":"20190609000000002","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"快看","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":null,"dept":null,"opers":[]},{"id":28,"searchValue":null,"createBy":"admin","createTime":"2019-06-09 13:05:23","updateBy":"admin","updateTime":"2019-06-09 13:05:23","remark":null,"equipNo":"20190609000000003","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"啊","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":null,"dept":null,"opers":[]},{"id":29,"searchValue":null,"createBy":"admin","createTime":"2019-06-09 13:05:23","updateBy":"admin","updateTime":"2019-06-09 13:05:23","remark":null,"equipNo":"20190609000000004","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"恩","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":null,"dept":null,"opers":[]},{"id":30,"searchValue":null,"createBy":"admin","createTime":"2019-06-09 13:41:58","updateBy":"admin","updateTime":"2019-06-09 13:41:58","remark":null,"equipNo":"20190609000000005","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"不","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":null,"dept":null,"opers":[]},{"id":31,"searchValue":null,"createBy":"admin","createTime":"2019-06-09 13:41:58","updateBy":"admin","updateTime":"2019-06-09 13:41:58","remark":null,"equipNo":"20190609000000006","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"的","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":null,"dept":null,"opers":[]},{"id":32,"searchValue":null,"createBy":"admin","createTime":"2019-06-09 13:43:19","updateBy":"admin","updateTime":"2019-06-09 13:43:19","remark":null,"equipNo":"20190609000000007","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"\u2018","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":null,"dept":null,"opers":[]},{"id":33,"searchValue":null,"createBy":"admin","createTime":"2019-06-09 13:43:19","updateBy":"admin","updateTime":"2019-06-09 13:43:19","remark":null,"equipNo":"20190609000000008","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"不","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":null,"dept":null,"opers":[]},{"id":34,"searchValue":null,"createBy":"admin","createTime":"2019-06-09 14:51:44","updateBy":"admin","updateTime":"2019-06-09 14:51:44","remark":null,"equipNo":"20190609000000009","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"不","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":null,"dept":null,"opers":[]},{"id":35,"searchValue":null,"createBy":"admin","createTime":"2019-06-09 14:51:44","updateBy":"admin","updateTime":"2019-06-09 14:51:44","remark":null,"equipNo":"20190609000000010","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"的","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":null,"dept":null,"opers":[]},{"id":36,"searchValue":null,"createBy":"admin","createTime":"2019-06-09 14:58:26","updateBy":"admin","updateTime":"2019-06-09 14:58:26","remark":null,"equipNo":"20190609000000011","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"包括","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":null,"dept":null,"opers":[]},{"id":37,"searchValue":null,"createBy":"admin","createTime":"2019-06-09 14:58:26","updateBy":"admin","updateTime":"2019-06-09 14:58:26","remark":null,"equipNo":"20190609000000012","sn":null,"locationId":0,"categoryId":0,"category":null,"deptId":0,"name":"具体","source":null,"parameter":"","manufactuer":"","techState":0,"responsor":"","verifyPeriod":null,"latestVerifyDate":null,"validDate":null,"status":0,"delFlag":0,"location":null,"dept":null,"opers":[]}]
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

    public static class RowsBean implements Serializable{
        /**
         * id : 16
         * searchValue : null
         * createBy : admin
         * createTime : 2019-06-05 14:08:22
         * updateBy : admin
         * updateTime : 2019-06-05 14:21:38
         * remark : 测试
         * equipNo : 20190605020001
         * sn : 12345
         * locationId : 26
         * categoryId : 20
         * category : {"id":20,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 14:02:58","updateBy":"admin","updateTime":"2019-06-05 14:02:58","remark":"油液子类2","pid":18,"name":"油液子类2","delFlag":0,"categorys":null}
         * deptId : 154
         * name : 油液仪器1
         * source : 上级分配
         * parameter : string
         * manufactuer : 厂商1
         * techState : 1
         * responsor : 段丽君
         * verifyPeriod : 12
         * latestVerifyDate : 2019-06-05 00:00:00
         * validDate : 2020-08-19 00:00:00
         * status : 0
         * delFlag : 0
         * location : {"id":26,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 14:00:12","updateBy":"admin","updateTime":"2019-06-05 14:00:12","remark":null,"pid":24,"deptId":154,"name":"1#第2层","containers":null}
         * dept : {"id":null,"searchValue":null,"createBy":"admin","createTime":"2019-06-05 13:53:22","updateBy":"","updateTime":null,"remark":null,"deptId":154,"parentId":100,"ancestors":"0,100","deptName":"304实验室","orderNum":"1","leader":"段","phone":"18627099254","email":"18627099254@qq.com","status":"0","delFlag":"0","parentName":null,"users":null}
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

        public List<String> getOpers() {
            if (opers == null) {
                return new ArrayList<>();
            }
            return opers;
        }

        public void setOpers(List<String> opers) {
            this.opers = opers;
        }

        public static class CategoryBean implements Serializable{
            /**
             * id : 20
             * searchValue : null
             * createBy : admin
             * createTime : 2019-06-05 14:02:58
             * updateBy : admin
             * updateTime : 2019-06-05 14:02:58
             * remark : 油液子类2
             * pid : 18
             * name : 油液子类2
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

        public static class LocationBean implements Serializable{
            /**
             * id : 26
             * searchValue : null
             * createBy : admin
             * createTime : 2019-06-05 14:00:12
             * updateBy : admin
             * updateTime : 2019-06-05 14:00:12
             * remark : null
             * pid : 24
             * deptId : 154
             * name : 1#第2层
             * containers : null
             */

            private int id;
            private Object searchValue;
            private String createBy;
            private String createTime;
            private String updateBy;
            private String updateTime;
            private Object remark;
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

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
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

        public static class DeptBean implements Serializable{
            /**
             * id : null
             * searchValue : null
             * createBy : admin
             * createTime : 2019-06-05 13:53:22
             * updateBy :
             * updateTime : null
             * remark : null
             * deptId : 154
             * parentId : 100
             * ancestors : 0,100
             * deptName : 304实验室
             * orderNum : 1
             * leader : 段
             * phone : 18627099254
             * email : 18627099254@qq.com
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

            public Object getUsers() {
                return users;
            }

            public void setUsers(Object users) {
                this.users = users;
            }
        }
    }
}
