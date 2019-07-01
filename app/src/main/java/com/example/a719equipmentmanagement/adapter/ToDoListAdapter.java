package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.example.a719equipmentmanagement.entity.ToDo;
import com.example.a719equipmentmanagement.entity.ToReturn;
import com.example.a719equipmentmanagement.entity.UserToDo;
import com.example.a719equipmentmanagement.utils.Constant;

public class ToDoListAdapter extends BaseQuickAdapter<ToDo.RowsBean, BaseViewHolder> {
    public ToDoListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ToDo.RowsBean item) {
        if (item != null) {
            ToDo.RowsBean.EquipBean equip = item.getEquip();
            String createBy = item.getCreateBy();
            String createTime = item.getCreateTime();
            if (equip != null) {
                int type = item.getType();
                String equipType = Constant.getEquipType(type);
                String equipNo = equip.getEquipNo();
                String name = equip.getName();
                helper.setText(R.id.tv_1, equipNo)
                        .setText(R.id.tv_2, name)
                        .setText(R.id.tv_4, equipType);
            }
            helper.setText(R.id.tv_3, createTime)
                    .setText(R.id.tv_5, createBy);
        }
    }
}
