package com.example.a719equipmentmanagement.adapter;

import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.InvalidEquip;

public class InvalidEquipAdapter extends BaseQuickAdapter<InvalidEquip, BaseViewHolder> {
    public InvalidEquipAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvalidEquip item) {
        if (item != null) {
            String equipNo = item.getEquipNo();
            String name = item.getName();
            String validDate = item.getValidDate();
//            SpannableStringBuilder builder = new SpannableStringBuilder();
//            SpannableString spannableString = new SpannableString(equipNo);
//            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(App.getContext(), R.color.blue)), 0, spannableString.length(), 0);
//            builder.append(spannableString).append(name).append("在").append(validDate).append("过期");
            helper
                    .setText(R.id.tv_1, equipNo)
                    .setText(R.id.tv_2, name)
                    .setText(R.id.tv_3, validDate)
                    .setText(R.id.tv_4, "过期")
            ;
        }
    }
}
