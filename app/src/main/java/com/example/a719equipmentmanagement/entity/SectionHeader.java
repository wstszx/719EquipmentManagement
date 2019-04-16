package com.example.a719equipmentmanagement.entity;

import com.qmuiteam.qmui.widget.section.QMUISection;

public class SectionHeader implements QMUISection.Model<SectionHeader> {
    private final String text;

    public SectionHeader(String text) {
        this.text = text;
    }

    public String getText() {
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
