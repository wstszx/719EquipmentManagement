package com.example.a719equipmentmanagement.entity;

import java.util.ArrayList;
import java.util.List;

public class InRecordData {

    /**
     * id : 1
     * searchValue : null
     * createBy : admin
     * createTime : 2019-04-17 16:38:12
     * updateBy : admin
     * updateTime : 2019-05-16 08:57:59
     * remark : 111
     * params : {}
     * name : name1
     * list : [{"id":1,"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"setupId":1,"equipId":1,"equip":null}]
     */

    private int id;
    private Object searchValue;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String remark;
    private ParamsBean params;
    private String name;
    private List<ListBean> list;

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

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListBean> getList() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ParamsBean {
    }

    public static class ListBean {
        /**
         * id : 1
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * setupId : 1
         * equipId : 1
         * equip : null
         */

        private int id;
        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBeanX params;
        private int setupId;
        private int equipId;
        private Object equip;

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

        public int getSetupId() {
            return setupId;
        }

        public void setSetupId(int setupId) {
            this.setupId = setupId;
        }

        public int getEquipId() {
            return equipId;
        }

        public void setEquipId(int equipId) {
            this.equipId = equipId;
        }

        public Object getEquip() {
            return equip;
        }

        public void setEquip(Object equip) {
            this.equip = equip;
        }

        public static class ParamsBeanX {
        }
    }
}
