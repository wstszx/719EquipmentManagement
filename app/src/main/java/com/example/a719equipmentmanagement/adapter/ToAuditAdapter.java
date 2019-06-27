package com.example.a719equipmentmanagement.adapter;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.example.a719equipmentmanagement.entity.UserToAudit;
import com.example.a719equipmentmanagement.utils.Constant;

public class ToAuditAdapter extends BaseQuickAdapter<ToAudit.RowsBean, BaseViewHolder> {
    public ToAuditAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ToAudit.RowsBean item) {
        if (item != null) {
            ToAudit.RowsBean.EquipBean equip = item.getEquip();
            String createBy = item.getCreateBy();
            String createTime = item.getCreateTime();
            int type = item.getType();
            String equipType = Constant.getEquipType(type);
            String equipNo = equip.getEquipNo();
            String name = equip.getName();

//            SpannableStringBuilder builder = new SpannableStringBuilder();
//            SpannableString spannableString = new SpannableString(equipNo);
//            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(App.getContext(), R.color.blue)), 0, spannableString.length(), 0);
//            builder.append(createBy).append(createTime).append(equipType).append(spannableString).append(name);
            helper
                    .setText(R.id.tv_1, equipNo)
                    .setText(R.id.tv_2, name)
                    .setText(R.id.tv_3, createTime)
                    .setText(R.id.tv_4, equipType)
                    .setText(R.id.tv_5, createBy)
            ;
//            helper.setText(R.id.number, equip.getEquipNo())
//                    .addOnClickListener(R.id.tv_by)
//                    .addOnClickListener(R.id.tv_no_by);
        }
    }
}