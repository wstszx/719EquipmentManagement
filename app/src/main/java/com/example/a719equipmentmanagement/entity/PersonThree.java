package com.example.a719equipmentmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class PersonThree implements MultiItemEntity {
    private User user;
    private String parentTitle;

    public String getParentTitle() {
        return parentTitle == null ? "" : parentTitle;
    }

    public User getUser() {
        return user;
    }

    public PersonThree(User user, String parentTitle) {
        this.user = user;
        this.parentTitle = parentTitle;
    }

    @Override
    public int getItemType() {
        return 2;
    }
}
