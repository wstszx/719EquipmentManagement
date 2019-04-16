package com.example.a719equipmentmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.qmuiteam.qmui.widget.section.QMUIDefaultStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUISection;

import androidx.annotation.NonNull;
import butterknife.BindView;

public class PeopleManageAdapter extends QMUIDefaultStickySectionAdapter<SectionHeader, SectionItem> {

    @NonNull
    @Override
    protected ViewHolder onCreateSectionHeaderViewHolder(@NonNull ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.base_parent_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @NonNull
    @Override
    protected ViewHolder onCreateSectionItemViewHolder(@NonNull ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.base_child_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindSectionHeader(ViewHolder holder, int position, QMUISection<SectionHeader, SectionItem> section) {
        View view = holder.itemView;
        TextView tvParent = view.findViewById(R.id.tv_parent);
        tvParent.setText(section.getHeader().getText());

    }

    @Override
    protected void onBindSectionItem(ViewHolder holder, int position, QMUISection<SectionHeader, SectionItem> section, int itemIndex) {
        View view = holder.itemView;
        TextView tvChild = view.findViewById(R.id.tv_child);
        tvChild.setText(section.getItemAt(itemIndex).getText());
    }
}
