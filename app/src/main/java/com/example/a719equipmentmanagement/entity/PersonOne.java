package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;

public class PersonOne extends AbstractExpandableItem<PersonTwo> implements MultiItemEntity {

    private DeptList deptList;

    public String getParentTitle() {
        return "无";
    }

    public DeptList getDeptList() {
        return deptList;
    }

    public PersonOne(DeptList deptList) {
        this.deptList = deptList;
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
