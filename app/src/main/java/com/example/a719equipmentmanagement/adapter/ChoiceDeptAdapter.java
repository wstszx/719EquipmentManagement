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
import com.example.a719equipmentmanagement.entity.PersonOne;

import java.util.List;

public class ChoiceDeptAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final int LEVEL_ONE = 0;

    public ChoiceDeptAdapter(Context context, List<MultiItemEntity> data) {
        super(data);
        addItemType(LEVEL_ONE, R.layout.base_one_level_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        if (item.getItemType() == LEVEL_ONE) {
            PersonOne personOne = (PersonOne) item;
            String name = personOne.getUser().getDeptName();
            helper.setText(R.id.tv_parent, name);
        }

    }
}
