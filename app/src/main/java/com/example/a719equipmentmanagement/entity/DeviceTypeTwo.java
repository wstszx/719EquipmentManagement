package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;

public class DeviceTypeTwo implements MultiItemEntity {

    private DeviceClassifiy.ListBean dept;
    private boolean isSelect;

    public void setDept(DeviceClassifiy.ListBean dept) {
        this.dept = dept;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public DeviceClassifiy.ListBean getDept() {
        return dept;
    }

    public DeviceTypeTwo(DeviceClassifiy.ListBean dept) {
        this.dept = dept;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
