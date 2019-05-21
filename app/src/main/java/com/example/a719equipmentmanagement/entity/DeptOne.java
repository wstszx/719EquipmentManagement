package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;

public class DeptOne extends AbstractExpandableItem<DeptTwo> implements MultiItemEntity {

    private TreeData dept;

    public TreeData getDept() {
        return dept;
    }

    public DeptOne(TreeData dept) {
        this.dept = dept;
    }

    @Override
    public int getLevel() {
        return DeptManageAdapter.LEVEL_ONE;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
