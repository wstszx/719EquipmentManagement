package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.InvalidEquip;

public class WaitApprovalItemAdapter extends BaseQuickAdapter<InvalidEquip, BaseViewHolder> {
    public WaitApprovalItemAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvalidEquip item) {
        helper.setText(R.id.deviceName,item.getDeviceName())
                .setText(R.id.currentStatus,item.getCurrentStatus())
                .setText(R.id.number,item.getNumber()+"");
    }
}
