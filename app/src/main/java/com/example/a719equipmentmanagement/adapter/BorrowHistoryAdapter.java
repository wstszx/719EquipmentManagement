package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ApplyHistory;
import com.example.a719equipmentmanagement.entity.BorrowHistory;

import org.json.JSONException;
import org.json.JSONObject;

public class BorrowHistoryAdapter extends BaseQuickAdapter<BorrowHistory.RowsBean, BaseViewHolder> {

    public BorrowHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BorrowHistory.RowsBean item) {
        BorrowHistory.RowsBean.EquipBean equip = item.getEquip();
        if (equip != null) {
            helper.setText(R.id.tv_1, equip.getEquipNo())
                    .setText(R.id.tv_2, equip.getName() != null ? equip.getName() : "")
                    .setText(R.id.tv_3, item.getCreateBy() != null ? item.getCreateBy() + "的借用" : "")
                    .setText(R.id.tv_4, item.getCreateTime());
        }
    }
}
