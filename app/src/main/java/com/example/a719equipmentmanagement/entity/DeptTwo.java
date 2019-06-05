package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;

public class DeptTwo extends AbstractExpandableItem<DeptThree> implements MultiItemEntity {

    private TreeData dept;
    private boolean isSelect;

    public void setDept(TreeData dept) {
        this.dept = dept;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public TreeData getDept() {
        return dept;
    }

    public DeptTwo(TreeData dept) {
        this.dept = dept;
    }

    @Override
    public int getLevel() {
        return DeptManageAdapter.LEVEL_TWO;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
