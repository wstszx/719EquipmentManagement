package com.example.a719equipmentmanagement.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.DeptOne;
import com.example.a719equipmentmanagement.entity.DeptThree;
import com.example.a719equipmentmanagement.entity.DeptTwo;

import java.util.List;

public class ChoiceDeptAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final int LEVEL_ONE = 0;
    private static final int LEVEL_TWO = 1;
    private static final int LEVEL_THREE = 2;

    public ChoiceDeptAdapter(Context context, List<MultiItemEntity> data) {
        super(data);
        addItemType(LEVEL_ONE, R.layout.base_one_level_item);
        addItemType(LEVEL_TWO, R.layout.base_two_level_item);
        addItemType(LEVEL_THREE, R.layout.base_three_level_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case LEVEL_ONE:
                DeptOne deptOne = (DeptOne) item;
                String name = deptOne.getDept().getName();
                helper.setText(R.id.tv_parent, name);
//                if (deptOne.isExpanded()) {
//                    helper.setImageResource(R.id.iv_right, R.mipmap.shangla);
//                } else {
//                    helper.setImageResource(R.id.iv_right, R.mipmap.xiala);
//                }
//                helper.getView(R.id.constraint).setOnClickListener(v -> {
//                    int pos = helper.getAdapterPosition();
//                    if (deptOne.isExpanded()) {
//                        helper.setImageResource(R.id.iv_right, R.mipmap.shangla);
//                        collapse(pos, true);
//                    } else {
//                        helper.setImageResource(R.id.iv_right, R.mipmap.xiala);
//                        expand(pos, true);
//                    }
//                });
                break;
            case LEVEL_TWO:
                DeptTwo deptTwo = (DeptTwo) item;
                String name1 = deptTwo.getDept().getName();
                helper.setText(R.id.tv_parent, name1);
//                if (deptTwo.isExpanded()) {
//                    helper.setImageResource(R.id.iv_right, R.mipmap.shangla);
//                } else {
//                    helper.setImageResource(R.id.iv_right, R.mipmap.xiala);
//                }
//                helper.getView(R.id.constraint).setOnClickListener(v -> {
//                    int pos = helper.getAdapterPosition();
//                    if (deptTwo.isExpanded()) {
//                        helper.setImageResource(R.id.iv_right, R.mipmap.shangla);
//                        collapse(pos, true);
//                    } else {
//                        helper.setImageResource(R.id.iv_right, R.mipmap.xiala);
//                        expand(pos, true);
//                    }
//                });
                break;
            case LEVEL_THREE:
                DeptThree deptThree = (DeptThree) item;
                String name2 = deptThree.getDept().getName();
                helper.setText(R.id.tv_parent, name2);
                break;
        }

    }
}
