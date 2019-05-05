package com.example.a719equipmentmanagement.entity;

import com.qmuiteam.qmui.widget.section.QMUISection;

import java.io.Serializable;

public class SectionItem implements QMUISection.Model<SectionItem> {
    private User.ListBean listBean;

    public SectionItem(User.ListBean listBean) {
        this.listBean = listBean;
    }

    public User.ListBean getList() {
        return listBean;
    }

    @Override
    public SectionItem cloneForDiff() {
        return new SectionItem(listBean);
    }

    @Override
    public boolean isSameItem(SectionItem other) {
        return listBean.equals(other.listBean);
    }

    @Override
    public boolean isSameContent(SectionItem other) {
        return true;
    }
}
