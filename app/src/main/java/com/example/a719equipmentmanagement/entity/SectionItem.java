package com.example.a719equipmentmanagement.entity;

import com.qmuiteam.qmui.widget.section.QMUISection;

public class SectionItem implements QMUISection.Model<SectionItem> {
    private final String text;

    public SectionItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public SectionItem cloneForDiff() {
        return new SectionItem(getText());
    }

    @Override
    public boolean isSameItem(SectionItem other) {
        return text.equals(other.text) || text.equals(other.text);
    }

    @Override
    public boolean isSameContent(SectionItem other) {
        return true;
    }
}
