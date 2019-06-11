package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.BorrowHistory;
import com.example.a719equipmentmanagement.entity.HandleHistory;

public class HandleAdapter extends BaseQuickAdapter<HandleHistory.RowsBean, BaseViewHolder> {

    public HandleAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HandleHistory.RowsBean item) {
        HandleHistory.RowsBean.EquipBean equip = item.getEquip();
        if (equip != null) {
            helper.setText(R.id.tv_1, equip.getEquipNo())
                    .setText(R.id.tv_2, equip.getName())
                    .setText(R.id.tv_3, item.getCreateBy() != null ? item.getCreateBy() + "的处理" : "")
                    .setText(R.id.tv_4, item.getUpdateTime());
        }
    }
}
