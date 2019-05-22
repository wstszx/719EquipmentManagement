package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ListMyAllBorrow;
import com.example.a719equipmentmanagement.entity.ListMyAllInventory;

public class BorrowAdapter extends BaseQuickAdapter<ListMyAllBorrow.RowsBean, BaseViewHolder> {
    public BorrowAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, ListMyAllBorrow.RowsBean item) {
        helper.setText(R.id.textView2, item.getEquip().getName())
                .setText(R.id.textView4, item.getCreateTime())
                .setText(R.id.textView6, item.getEquip().getEquipNo())
                .setText(R.id.textView8, item.getEquip().getSn());
        switch (item.getEquip().getStatus()) {
            case 0:
                helper.setText(R.id.textView10, "已归还");
                break;
            case 1:
                helper.setText(R.id.textView10, "未归还");
                break;
        }
    }
}
