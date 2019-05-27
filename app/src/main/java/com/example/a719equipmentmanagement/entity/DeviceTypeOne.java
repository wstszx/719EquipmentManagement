package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;

public class DeviceTypeOne extends AbstractExpandableItem<DeviceTypeTwo> implements MultiItemEntity {

    private DeviceClassifiy dept;
    private boolean isSelect;

    public void setDept(DeviceClassifiy dept) {
        this.dept = dept;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public DeviceClassifiy getDept() {
        return dept;
    }

    public DeviceTypeOne(DeviceClassifiy dept) {
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
