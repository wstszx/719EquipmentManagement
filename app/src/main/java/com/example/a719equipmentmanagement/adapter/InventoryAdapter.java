package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.BorrowHistory;
import com.example.a719equipmentmanagement.entity.InventoryHistory;

import java.util.List;

public class InventoryAdapter extends BaseQuickAdapter<InventoryHistory.RowsBean, BaseViewHolder> {

    public InventoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, InventoryHistory.RowsBean item) {
        if (item != null) {
            helper.setText(R.id.tv_1, item.getName())
                    .setText(R.id.tv_2, item.getUpdateBy())
                    .setText(R.id.tv_3, item.getCreateBy() != null ? item.getCreateBy() + "的盘点" : "")
                    .setText(R.id.tv_4, item.getCreateTime())
                    .setText(R.id.tv_5, item.getRemark());
        }
    }
}
