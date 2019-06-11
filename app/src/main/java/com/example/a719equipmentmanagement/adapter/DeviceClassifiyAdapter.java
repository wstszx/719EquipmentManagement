package com.example.a719equipmentmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ContainerOne;
import com.example.a719equipmentmanagement.entity.ContainerTwo;
import com.example.a719equipmentmanagement.entity.DeviceClassifiy;
import com.example.a719equipmentmanagement.entity.DeviceTypeOne;
import com.example.a719equipmentmanagement.entity.DeviceTypeTwo;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.qmuiteam.qmui.widget.section.QMUIDefaultStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUISection;

import java.util.List;

public class DeviceClassifiyAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int LEVEL_ONE = 0;
    private static final int LEVEL_TWO = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DeviceClassifiyAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(LEVEL_ONE, R.layout.base_one_level_item);
        addItemType(LEVEL_TWO, R.layout.base_two_level_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case LEVEL_ONE:
                DeviceTypeOne deviceTypeOne = (DeviceTypeOne) item;
                if (deviceTypeOne.isExpanded()) {
                    helper.setImageResource(R.id.iv_right, R.mipmap.shangla);
                } else {
                    helper.setImageResource(R.id.iv_right, R.mipmap.xiala);
                }
                String name = deviceTypeOne.getData().getName();
                helper.setText(R.id.tv_parent, name);
                break;
            case LEVEL_TWO:
                DeviceTypeTwo deviceTypeTwo = (DeviceTypeTwo) item;
                String name1 = deviceTypeTwo.getData().getName();
                helper.setText(R.id.tv_parent, name1);
                break;
        }
    }

//    @NonNull
//    @Override
//    protected ViewHolder onCreateSectionHeaderViewHolder(@NonNull ViewGroup viewGroup) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.base_one_level_item, viewGroup, false);
//        return new ViewHolder(view);
//    }
//
//    @NonNull
//    @Override
//    protected ViewHolder onCreateSectionItemViewHolder(@NonNull ViewGroup viewGroup) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.base_child_item, viewGroup, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    protected void onBindSectionHeader(ViewHolder holder, int position, QMUISection<SectionHeader<DeviceClassifiy>, SectionItem<DeviceClassifiy.ListBean>> section) {
//        View view = holder.itemView;
//        TextView tvParent = view.findViewById(R.id.tv_parent);
//        ImageView ivRight = view.findViewById(R.id.iv_right);
//
//        boolean fold = section.isFold();
//        if (fold) {
//            ivRight.setBackgroundResource(R.mipmap.xiala);
//        } else {
//            ivRight.setBackgroundResource(R.mipmap.shangla);
//        }
//        view.setOnClickListener(v -> {
//            int pos = holder.isForStickyHeader ? position : holder.getAdapterPosition();
//            toggleFold(pos, false);
//        });
//        String name = section.getHeader().getText().getName();
//        tvParent.setText(name);
//
//    }
//
//    @Override
//    protected void onBindSectionItem(ViewHolder holder, int position, QMUISection<SectionHeader<DeviceClassifiy>, SectionItem<DeviceClassifiy.ListBean>> section, int itemIndex) {
//        View view = holder.itemView;
//        view.setTag(2);
//        TextView tv_1 = view.findViewById(R.id.tv_1);
//        TextView tv_2 = view.findViewById(R.id.tv_2);
//        TextView tv_3 = view.findViewById(R.id.tv_3);
//        tv_1.setText(section.getItemAt(itemIndex).getListBean().getName());
////        tv_1.setText(section.getItemAt(itemIndex).getTv_1());
////        tv_2.setText(section.getItemAt(itemIndex).getTv_2());
////        tv_3.setText(section.getItemAt(itemIndex).getTv_3());
//    }
}
