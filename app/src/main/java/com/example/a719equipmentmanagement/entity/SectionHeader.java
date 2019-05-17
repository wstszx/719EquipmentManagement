package com.example.a719equipmentmanagement.entity;

import com.qmuiteam.qmui.widget.section.QMUISection;

public class SectionHeader<T> implements QMUISection.Model<SectionHeader<T>> {
    private final T text;
    private boolean isExpand;

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public SectionHeader(T text) {
        this.text = text;
    }

    public T getText() {
        return text;
    }

    @Override
    public SectionHeader cloneForDiff() {
        return new SectionHeader(getText());
    }

    @Override
    public boolean isSameItem(SectionHeader other) {
        return text.equals(other.text) || text.equals(other.text);
    }

    @Override
    public boolean isSameContent(SectionHeader other) {
        return true;
    }
}
