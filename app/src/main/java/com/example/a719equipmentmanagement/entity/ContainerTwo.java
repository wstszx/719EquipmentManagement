package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ContainerTwo implements MultiItemEntity {

    private ContainerData.ListBean dept;
    private boolean isSelect;

    public void setDept(ContainerData.ListBean dept) {
        this.dept = dept;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public ContainerData.ListBean getDept() {
        return dept;
    }

    public ContainerTwo(ContainerData.ListBean dept) {
        this.dept = dept;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
