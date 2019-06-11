package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ReviewHistory;

public class ReviewHistoryAdapter extends BaseQuickAdapter<ReviewHistory.RowsBean, BaseViewHolder> {
    public ReviewHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReviewHistory.RowsBean item) {
        helper.setText(R.id.tv_1, item.getEquip().getName())
                .setText(R.id.tv_2, item.getEquip().getDept().getDeptName())
                .setText(R.id.tv_3, item.getEquip().getLocation().getName())
                .setText(R.id.tv_4, item.getCreateTime());
    }
}
