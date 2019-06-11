package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.InvalidEquip;
import com.example.a719equipmentmanagement.entity.ToDo;

public class ToDoAdapter extends BaseQuickAdapter<ToDo.RowsBean, BaseViewHolder> {
    public ToDoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ToDo.RowsBean item) {
        ToDo.RowsBean.EquipBean equip = item.getEquip();
        if (equip != null) {
            helper.setText(R.id.deviceName, equip.getName())
                    .setText(R.id.number, equip.getEquipNo());
        }
    }
}
