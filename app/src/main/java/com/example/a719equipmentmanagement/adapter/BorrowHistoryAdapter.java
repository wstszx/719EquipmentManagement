package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ApplyHistory;
import com.example.a719equipmentmanagement.entity.BorrowHistory;
import com.example.a719equipmentmanagement.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

public class BorrowHistoryAdapter extends BaseQuickAdapter<BorrowHistory.RowsBean, BaseViewHolder> {

    private String equipNo;
    private String name;

    public BorrowHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BorrowHistory.RowsBean item) {
        if (item != null) {
            BorrowHistory.RowsBean.EquipBean equip = item.getEquip();
            String createTime = item.getCreateTime();
            int type = item.getType();
            String equipType = Constant.getEquipType(type);
            if (equip != null) {
                equipNo = equip.getEquipNo();
                name = equip.getName();
            }
            helper.setText(R.id.tv_1, equipNo)
                    .setText(R.id.tv_2, name)
                    .setText(R.id.tv_3, createTime)
                    .setText(R.id.tv_4, equipType);
        }
    }
}
