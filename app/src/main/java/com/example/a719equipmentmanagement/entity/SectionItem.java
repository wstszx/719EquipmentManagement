package com.example.a719equipmentmanagement.entity;

import com.qmuiteam.qmui.widget.section.QMUISection;

public class SectionItem<T> implements QMUISection.Model<SectionItem<T>> {
    private T listBean;

    public SectionItem(T listBean) {
        this.listBean = listBean;
    }

    public T getListBean() {
        return listBean;
    }

    @Override
    public SectionItem cloneForDiff() {
        return new SectionItem(getListBean());
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
