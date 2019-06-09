package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.InvalidEquip;
import com.example.a719equipmentmanagement.entity.ToReturn;

public class ToReturnAdapter extends BaseQuickAdapter<ToReturn.RowsBean, BaseViewHolder> {
    public ToReturnAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ToReturn.RowsBean item) {
        helper.setText(R.id.deviceName, item.getEquip().getName())
                .setText(R.id.number, item.getEquip().getEquipNo())
                .setText(R.id.auditName, item.getEquip().getDept().getDeptName())
                .setText(R.id.person, item.getEquip().getDept().getAncestors());
    }
}
