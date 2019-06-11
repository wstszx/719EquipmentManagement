package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ApplyHistory;

public class ApplyHistoryAdapter extends BaseQuickAdapter<ApplyHistory.RowsBean, BaseViewHolder> {
    public ApplyHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplyHistory.RowsBean item) {
        ApplyHistory.RowsBean.EquipBean equip = item.getEquip();
        if (equip != null) {
            helper.setText(R.id.tv_1, equip.getEquipNo())
                    .setText(R.id.tv_2, equip.getName() != null ? equip.getName() : "")
                    .setText(R.id.tv_3, item.getCreateBy() != null ? item.getCreateBy() + "的申请" : "")
                    .setText(R.id.tv_4, item.getCreateTime());
        }
    }
}
