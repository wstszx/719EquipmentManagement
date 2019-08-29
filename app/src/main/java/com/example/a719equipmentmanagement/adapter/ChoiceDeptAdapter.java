package com.example.a719equipmentmanagement.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.DeptList;
import com.example.a719equipmentmanagement.entity.DeptOne;
import com.example.a719equipmentmanagement.entity.DeptTwo;
import com.example.a719equipmentmanagement.entity.PersonOne;

import java.util.List;

public class ChoiceDeptAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final int LEVEL_ONE = 0;
    private static final int LEVEL_TWO = 1;

    public ChoiceDeptAdapter(Context context, List<MultiItemEntity> data) {
        super(data);
        addItemType(LEVEL_ONE, R.layout.base_one_level_item);
        addItemType(LEVEL_TWO, R.layout.base_two_level_item);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MultiItemEntity item) {
        if (item.getItemType() == LEVEL_ONE) {
            DeptOne deptOne = (DeptOne) item;
            String name = deptOne.getDept().getDeptName();
            helper.setText(R.id.tv_one, name);
        } else if (item.getItemType() == LEVEL_TWO) {
            DeptTwo deptTwo = (DeptTwo) item;
            boolean expand = deptTwo.isExpand();
            if (expand) {
                DeptList dept = deptTwo.getDept();
                String deptName = dept.getDeptName();
                helper.setText(R.id.tv_two, deptName);
            }
        }
    }
}
