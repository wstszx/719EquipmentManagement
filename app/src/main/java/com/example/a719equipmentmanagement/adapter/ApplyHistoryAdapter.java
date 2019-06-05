package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ApplyHistory;

public class ApplyHistoryAdapter extends BaseQuickAdapter<ApplyHistory, BaseViewHolder> {
    public ApplyHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplyHistory item) {
//        helper.setText(R.id.tv_1, item.getName())
//                .setText(R.id.tv_2, item.getUpdateBy())
//                .setText(R.id.tv_3, item.getCreateBy())
//                .setText(R.id.tv_4, item.getCreateTime());
    }
}
