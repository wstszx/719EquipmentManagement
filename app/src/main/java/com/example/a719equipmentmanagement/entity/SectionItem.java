package com.example.a719equipmentmanagement.entity;

import com.qmuiteam.qmui.widget.section.QMUISection;

public class SectionItem implements QMUISection.Model<SectionItem> {
    private ContainerData.ListBean listBean;

    public SectionItem(ContainerData.ListBean listBean) {
        this.listBean = listBean;
    }

    public ContainerData.ListBean getListBean() {
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
