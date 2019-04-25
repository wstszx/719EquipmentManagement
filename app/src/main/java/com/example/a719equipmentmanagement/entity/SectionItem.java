package com.example.a719equipmentmanagement.entity;

import com.qmuiteam.qmui.widget.section.QMUISection;

public class SectionItem implements QMUISection.Model<SectionItem> {
    private String tv_1;
    private String tv_2;
    private String tv_3;

    public SectionItem(String tv_1) {
        this.tv_1 = tv_1;
    }

    public SectionItem(String tv_1, String tv_2) {
        this.tv_1 = tv_1;
        this.tv_2 = tv_2;
    }

    public SectionItem(String tv_1, String tv_2, String tv_3) {
        this.tv_1 = tv_1;
        this.tv_2 = tv_2;
        this.tv_3 = tv_3;
    }

    public String getTv_1() {
        return tv_1 == null ? "" : tv_1;
    }

    public String getTv_2() {
        return tv_2 == null ? "" : tv_2;
    }

    public String getTv_3() {
        return tv_3 == null ? "" : tv_3;
    }


    @Override
    public SectionItem cloneForDiff() {
        return new SectionItem(getTv_1(), getTv_2(), getTv_3());
    }

    @Override
    public boolean isSameItem(SectionItem other) {
        return tv_1.equals(other.tv_1) && tv_2.equals(other.tv_2) && tv_3.equals(other.tv_3);
    }

    @Override
    public boolean isSameContent(SectionItem other) {
        return true;
    }
}
