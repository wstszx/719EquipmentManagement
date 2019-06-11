package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;

public class DeviceTypeTwo implements MultiItemEntity {

    private DeviceClassifiy.CategorysBean data;
    private boolean isSelect;

    public void setData(DeviceClassifiy.CategorysBean data) {
        this.data = data;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public DeviceClassifiy.CategorysBean getData() {
        return data;
    }

    public DeviceTypeTwo(DeviceClassifiy.CategorysBean data) {
        this.data = data;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
