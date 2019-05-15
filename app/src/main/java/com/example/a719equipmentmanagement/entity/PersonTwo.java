package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.adapter.PersonManageAdapter;

public class PersonTwo extends AbstractExpandableItem<PersonThree> implements MultiItemEntity {
    private User user;

    public User getUser() {
        return user;
    }

    public PersonTwo(User user) {
        this.user = user;
    }

    @Override
    public int getLevel() {
        return PersonManageAdapter.LEVEL_TWO;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
