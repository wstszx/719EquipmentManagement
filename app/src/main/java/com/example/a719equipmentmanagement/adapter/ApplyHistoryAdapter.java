package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ApplyHistory;
import com.example.a719equipmentmanagement.utils.Constant;

public class ApplyHistoryAdapter extends BaseQuickAdapter<ApplyHistory.RowsBean, BaseViewHolder> {

    private String equipNo;
    private String name;

    public ApplyHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplyHistory.RowsBean item) {
        if (item != null) {
            String remark = item.getRemark();
            String createBy = item.getCreateBy();
            String createTime = item.getCreateTime();
            int type = item.getType();
            String equipType = Constant.getEquipType(type);
            String auditOpinion = item.getAuditOpinion();
            String updateBy = item.getUpdateBy();
            String updateTime = item.getUpdateTime();
            int state = item.getState();
            String deviceState = Constant.getDeviceState(state);
            ApplyHistory.RowsBean.EquipBean equip = item.getEquip();
            if (equip != null) {
                equipNo = equip.getEquipNo();
                name = equip.getName();
            }
            helper.setText(R.id.tv_1, equipNo)
                    .setText(R.id.tv_2, name)
                    .setText(R.id.tv_3, remark)
                    .setText(R.id.tv_4, createBy)
                    .setText(R.id.tv_5, createTime + "的")
                    .setText(R.id.tv_6, equipType)
                    .setText(R.id.tv_7, auditOpinion)
                    .setText(R.id.tv_8, updateBy)
                    .setText(R.id.tv_9, updateTime)
                    .setText(R.id.tv_10, deviceState)
            ;
        }
    }
}
