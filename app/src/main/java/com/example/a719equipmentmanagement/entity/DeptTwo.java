package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;

import java.util.List;

public class DeptTwo extends AbstractExpandableItem<DeptThree> implements MultiItemEntity {

    private DeptList dept;
    private boolean isSelect;
    private DeptList.UsersBean usersBean;
    private boolean isExpand;

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public DeptList.UsersBean getUsersBean() {
        return usersBean;
    }

    public void setUsersBean(DeptList.UsersBean usersBean) {
        this.usersBean = usersBean;
    }

    public void setDept(DeptList dept) {
        this.dept = dept;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public DeptList getDept() {
        return dept;
    }

    public DeptTwo() {

    }

    @Override
    public int getLevel() {
        return DeptManageAdapter.LEVEL_TWO;
    }

    @Override
    public int getItemType() {

        return isExpand ? DeptManageAdapter.LEVEL_TWO : DeptManageAdapter.LEVEL_FOUR;
    }
}
