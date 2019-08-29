package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;

import java.util.List;

public class DeptThree implements MultiItemEntity {

    private DeptList.UsersBean usersBean;
    private boolean isSelect;

    public void setusersBean(DeptList.UsersBean usersBean) {
        this.usersBean = usersBean;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public DeptList.UsersBean getUsersBean() {
        return usersBean;
    }

    public DeptThree(DeptList.UsersBean usersBean) {
        this.usersBean = usersBean;
    }

    @Override
    public int getItemType() {
        return 2;
    }
}
