package com.example.a719equipmentmanagement.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.InventoryData;

import java.util.List;

public class InventoryAdapter extends BaseQuickAdapter<InventoryData, BaseViewHolder> {

    public InventoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, InventoryData item) {

        helper.setText(R.id.tv_1, item.getName())
                .setText(R.id.tv_2, item.getUpdateBy())
                .setText(R.id.tv_3, item.getRemark())
                .setText(R.id.tv_4, item.getCreateTime());
    }
}
