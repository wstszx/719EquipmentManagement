package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ToAudit;

public class AdminToAuditAdapter extends BaseQuickAdapter<ToAudit, BaseViewHolder> {
    public AdminToAuditAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ToAudit item) {
        helper.setText(R.id.deviceName, item.getDeviceName())
                .setText(R.id.number, item.getNumber() + "")
                .setText(R.id.auditName, item.getAuditName())
                .setText(R.id.person, item.getPerson());
    }

}