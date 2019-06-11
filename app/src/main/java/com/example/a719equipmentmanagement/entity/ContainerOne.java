package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.ChoiceContainerAdapter;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;

public class ContainerOne extends AbstractExpandableItem<ContainerTwo> implements MultiItemEntity {

    private ContainerData data;
    private boolean isSelect;

    public void setData(ContainerData data) {
        this.data = data;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public ContainerData getData() {
        return data;
    }

    public ContainerOne(ContainerData data) {
        this.data = data;
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
