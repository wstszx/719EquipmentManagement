package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.ChoiceDeviceClassifiyAdapter;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;
import com.example.a719equipmentmanagement.adapter.DeviceClassifiyAdapter;

public class DeviceTypeOne extends AbstractExpandableItem<DeviceTypeTwo> implements MultiItemEntity {

    private DeviceClassifiy data;
    private boolean isSelect;

    public void setData(DeviceClassifiy data) {
        this.data = data;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public DeviceClassifiy getData() {
        return data;
    }

    public DeviceTypeOne(DeviceClassifiy data) {
        this.data = data;
    }

    @Override
    public int getLevel() {
        return DeviceClassifiyAdapter.LEVEL_ONE;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
