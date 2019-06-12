package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;

public class PersonTwo extends AbstractExpandableItem<PersonThree> implements MultiItemEntity {
    private DeptList.UsersBean listBean;
    private String parentTitle;

    public DeptList.UsersBean getDeptList() {
        return listBean;
    }

    public String getParentTitle() {
        return parentTitle == null ? "" : parentTitle;
    }

    public PersonTwo(DeptList.UsersBean listBean) {
        this.listBean = listBean;
    }

    @Override
    public int getLevel() {
        return DeptManageAdapter.LEVEL_TWO;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
