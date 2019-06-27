package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ApplyHistory;
import com.example.a719equipmentmanagement.entity.HandleHistory;
import com.example.a719equipmentmanagement.entity.ReviewHistory;
import com.example.a719equipmentmanagement.utils.Constant;

public class ReviewHistoryAdapter extends BaseQuickAdapter<ReviewHistory.RowsBean, BaseViewHolder> {

    private String equipNo;
    private String name;

    public ReviewHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReviewHistory.RowsBean item) {
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
            String reviewState = Constant.getReviewState(state);
            ReviewHistory.RowsBean.EquipBean equip = item.getEquip();
            if (equip != null) {
                equipNo = equip.getEquipNo();
                name = equip.getName();
            }
            helper.setText(R.id.tv_1, equipNo)
                    .setText(R.id.tv_2, name)
                    .setText(R.id.tv_3, "备注：" + remark)
                    .setText(R.id.tv_4, createTime)
                    .setText(R.id.tv_5, createBy)
                    .setText(R.id.tv_6, equipType)
                    .setText(R.id.tv_7, "审核意见：" + auditOpinion)
                    .setText(R.id.tv_8, updateTime)
                    .setText(R.id.tv_9, updateBy)
                    .setText(R.id.tv_10, reviewState)
            ;
        }
    }
}
