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
        ToReturn.RowsBean.EquipBean equip = item.getEquip();
        ToReturn.RowsBean.EquipBean.DeptBean dept = equip.getDept();
        helper.setText(R.id.deviceName, equip.getName())
                .setText(R.id.number, equip.getEquipNo());
//        if (dept != null) {
//            helper.setText(R.id.invalidDate, dept.getDeptName());
//                    .setText(R.id.days, dept.getAncestors());
//        }
    }
}
