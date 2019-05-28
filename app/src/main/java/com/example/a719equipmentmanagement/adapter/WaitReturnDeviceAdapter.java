package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ToAudit;

public class WaitReturnDeviceAdapter extends BaseQuickAdapter<ToAudit, BaseViewHolder> {
    public WaitReturnDeviceAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ToAudit item) {
        helper.setText(R.id.deviceName,item.getDeviceName())
                .setText(R.id.returnDate,item.getReturnDate())
                .setText(R.id.days,item.getDays()+"");
    }
}
