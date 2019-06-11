package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ApplyProgress;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.example.a719equipmentmanagement.entity.UserToAudit;

public class ApplyProgressAdapter extends BaseQuickAdapter<UserToAudit.RowsBean, BaseViewHolder> {
    public ApplyProgressAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserToAudit.RowsBean item) {
        UserToAudit.RowsBean.EquipBean equip = item.getEquip();
        if (equip != null) {
            helper.setText(R.id.deviceName, equip.getName())
                    .setText(R.id.number, equip.getEquipNo());
        }
    }
}