package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ContainerTwo implements MultiItemEntity {

    private ContainerData.ContainersBean data;
    private boolean isSelect;

    public ContainerData.ContainersBean getData() {
        return data;
    }

    public void setData(ContainerData.ContainersBean data) {
        this.data = data;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }


    public ContainerTwo(ContainerData.ContainersBean data) {
        this.data = data;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
