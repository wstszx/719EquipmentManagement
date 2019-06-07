package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.HandleHistory;

public class HandleAdapter extends BaseQuickAdapter<HandleHistory.RowsBean, BaseViewHolder> {

    public HandleAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HandleHistory.RowsBean item) {
        helper.setText(R.id.tv_1, item.getEquip().getName())
                .setText(R.id.tv_2, item.getEquip().getDept().getDeptName())
                .setText(R.id.tv_3, item.getEquip().getResponsor())
                .setText(R.id.tv_4, item.getCreateTime());
    }
}
