package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.Inventories;

public class InventoriesAdapter extends BaseQuickAdapter<Inventories.RowsBean, BaseViewHolder> {
    public InventoriesAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Inventories.RowsBean item) {
        if (item != null) {
            String name = item.getName();
            helper.setText(R.id.name, name)
                    .addOnClickListener(R.id.tv_cancel)
                    .addOnClickListener(R.id.tv_continue);
        }
    }
}
