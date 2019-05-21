package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;

public class PersonOne extends AbstractExpandableItem<PersonTwo> implements MultiItemEntity {

    private User user;

    public User getUser() {
        return user;
    }

    public PersonOne(User user) {
        this.user = user;
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
