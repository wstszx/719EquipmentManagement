package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.InvalidEquip;

public class InvalidEquipAdapter extends BaseQuickAdapter<InvalidEquip, BaseViewHolder> {
    public InvalidEquipAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvalidEquip item) {
        helper.setText(R.id.deviceName, item.getName())
                .setText(R.id.number, item.getDept().getDeptName());
    }
}
