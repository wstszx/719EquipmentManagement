package com.example.a719equipmentmanagement.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ListMyAllInventory;

public class InventoryAdapter extends BaseQuickAdapter<ListMyAllInventory.RowsBean, BaseViewHolder> {
    public InventoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListMyAllInventory.RowsBean item) {
        helper.setText(R.id.textView1, item.getName())
                .setText(R.id.textView3, item.getCreateTime())
                .setText(R.id.textView5, item.getUpdateTime())
                .setText(R.id.textView7, item.getContainerIds())
                .setText(R.id.textView9, item.getRemark());

    }
}
