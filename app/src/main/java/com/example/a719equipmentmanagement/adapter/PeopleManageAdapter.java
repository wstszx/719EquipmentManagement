package com.example.a719equipmentmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.example.a719equipmentmanagement.entity.User;
import com.qmuiteam.qmui.widget.section.QMUIDefaultStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUISection;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PeopleManageAdapter extends QMUIDefaultStickySectionAdapter<SectionHeader<User>, SectionItem<User>> {

    private List<QMUISection<SectionHeader<User>, SectionItem<User>>> list = new ArrayList<>();

    public void setList(List<QMUISection<SectionHeader<User>, SectionItem<User>>> list) {
        setData(list);
        if (list.size() > 0) {
            list.clear();
        }
//        this.list = list;
//        refreshCustomData();
    }

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
    protected void onBindSectionHeader(ViewHolder holder, int position, QMUISection<SectionHeader<User>, SectionItem<User>> section) {
        View view = holder.itemView;
        TextView tvParent = view.findViewById(R.id.tv_parent);
        TextView tv_leader = view.findViewById(R.id.tv_leader);
        TextView tv_status = view.findViewById(R.id.tv_status);
//        TextView tv_create_time = view.findViewById(R.id.tv_create_time);
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
        User user = section.getHeader().getText();
        String deptName = user.getDeptName();
        String leader = user.getLeader();
        String status = user.getStatus();
//        String createTime = user.getCreateTime();
        tv_leader.setText(leader);
        switch (status) {
            case "0":
                tv_status.setText("正常");
                break;
            case "1":
                tv_status.setText("停用");
                break;
        }
//        tv_create_time.setText(createTime);
        tvParent.setText(deptName);

    }

    @Override
    protected void onBindSectionItem(ViewHolder holder, int position, QMUISection<SectionHeader<User>, SectionItem<User>> section, int itemIndex) {
        View view = holder.itemView;
        view.setTag(2);
        TextView tv_1 = view.findViewById(R.id.tv_1);
        TextView tv_2 = view.findViewById(R.id.tv_2);
        TextView tv_3 = view.findViewById(R.id.tv_3);
        User user = section.getItemAt(itemIndex).getListBean();
        String deptName = user.getDeptName();
        String leader = user.getLeader();
        String status = user.getStatus();
        tv_1.setText(deptName);
        tv_2.setText(leader);
        switch (status) {
            case "0":
                tv_3.setText("正常");
                break;
            case "1":
                tv_3.setText("停用");
                break;
        }
    }
}
