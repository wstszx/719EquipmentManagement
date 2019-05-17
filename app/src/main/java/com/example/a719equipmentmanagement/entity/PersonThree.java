package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class PersonThree implements MultiItemEntity {
    private User user;

    public User getUser() {
        return user;
    }

    public PersonThree(User user) {
        this.user = user;
    }

    @Override
    public int getItemType() {
        return 2;
    }
}
