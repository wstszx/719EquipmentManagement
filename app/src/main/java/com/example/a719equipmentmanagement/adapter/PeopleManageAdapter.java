package com.example.a719equipmentmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.qmuiteam.qmui.widget.section.QMUIDefaultStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUISection;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
        ImageView ivRight = view.findViewById(R.id.iv_right);

        boolean fold = section.isFold();
        if (fold) {
            ivRight.setBackgroundResource(R.mipmap.xiala);
        } else {
            ivRight.setBackgroundResource(R.mipmap.shangla);
        }
        view.setOnClickListener(v -> {
            int pos = holder.isForStickyHeader ? position : holder.getAdapterPosition();
            toggleFold(pos, false);
        });
        tvParent.setText(section.getHeader().getText());

    }

    @Override
    protected void onBindSectionItem(ViewHolder holder, int position, QMUISection<SectionHeader, SectionItem> section, int itemIndex) {
        View view = holder.itemView;
        view.setTag(2);
        TextView tv_1 = view.findViewById(R.id.tv_1);
        TextView tv_2 = view.findViewById(R.id.tv_2);
        TextView tv_3 = view.findViewById(R.id.tv_3);
        tv_1.setText(section.getItemAt(itemIndex).getList().getDeptId()+"");
        tv_2.setText(section.getItemAt(itemIndex).getList().getLoginName());
        tv_3.setText(section.getItemAt(itemIndex).getList().getPhonenumber());
    }
}
