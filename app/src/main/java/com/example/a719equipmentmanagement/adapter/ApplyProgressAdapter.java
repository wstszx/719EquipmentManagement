package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ApplyProgress;
import com.example.a719equipmentmanagement.entity.ToAudit;

public class ApplyProgressAdapter extends BaseQuickAdapter<ApplyProgress, BaseViewHolder> {
    public ApplyProgressAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplyProgress item) {
//        helper.setText(R.id.deviceName, item.getDeviceName())
//                .setText(R.id.number, item.getNumber() + "")
//                .setText(R.id.auditName, item.getAuditName())
//                .setText(R.id.person, item.getPerson());
    }

}