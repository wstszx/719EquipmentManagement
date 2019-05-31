package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.ChoiceContainerAdapter;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;

public class ContainerOne extends AbstractExpandableItem<ContainerTwo> implements MultiItemEntity {

    private ContainerData dept;
    private boolean isSelect;

    public void setDept(ContainerData dept) {
        this.dept = dept;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public ContainerData getDept() {
        return dept;
    }

    public ContainerOne(ContainerData dept) {
        this.dept = dept;
    }

    @Override
    public int getLevel() {
        return ChoiceContainerAdapter.LEVEL_ONE;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
