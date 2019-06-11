package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.HandleHistory;
import com.example.a719equipmentmanagement.entity.ReviewHistory;

public class ReviewHistoryAdapter extends BaseQuickAdapter<ReviewHistory.RowsBean, BaseViewHolder> {
    public ReviewHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReviewHistory.RowsBean item) {
        ReviewHistory.RowsBean.EquipBean equip = item.getEquip();
        if (equip != null) {
            helper.setText(R.id.tv_1, equip.getEquipNo())
                    .setText(R.id.tv_2, equip.getName())
                    .setText(R.id.tv_3, item.getCreateBy() != null ? item.getCreateBy() + "的审核" : "")
                    .setText(R.id.tv_4, item.getCreateTime());
        }
    }
}
