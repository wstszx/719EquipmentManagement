package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.example.a719equipmentmanagement.entity.UserToAudit;

public class ToAuditAdapter extends BaseQuickAdapter<ToAudit.RowsBean, BaseViewHolder> {
    public ToAuditAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ToAudit.RowsBean item) {
        ToAudit.RowsBean.EquipBean equip = item.getEquip();
        if (equip != null) {
            helper.setText(R.id.deviceName, equip.getName())
                    .setText(R.id.number, equip.getEquipNo())
                    .addOnClickListener(R.id.tv_by)
                    .addOnClickListener(R.id.tv_no_by);
        }
    }
}