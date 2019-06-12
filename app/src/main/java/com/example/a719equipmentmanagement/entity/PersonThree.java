package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class PersonThree implements MultiItemEntity {
    private DeptList deptList;
    private String parentTitle;

    public String getParentTitle() {
        return parentTitle == null ? "" : parentTitle;
    }

    public DeptList getDeptList() {
        return deptList;
    }

    public PersonThree(DeptList deptList, String parentTitle) {
        this.deptList = deptList;
        this.parentTitle = parentTitle;
    }

    @Override
    public int getItemType() {
        return 2;
    }
}
