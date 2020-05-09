package com.example.a719equipmentmanagement.adapter;

import android.graphics.Color;
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
            String sn = item.getSn();
            String name = item.getName();
            String validDate = item.getValidDate();
//            SpannableStringBuilder builder = new SpannableStringBuilder();
//            SpannableString spannableString = new SpannableString(equipNo);
//            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(App.getContext(), R.color.blue)), 0, spannableString.length(), 0);
//            builder.append(spannableString).append(name).append("在").append(validDate).append("过期");
            helper
                    .setText(R.id.tv_1, sn)
                    .setText(R.id.tv_2, name)
                    .setText(R.id.tv_3, validDate)
            ;
            switch (item.getStatus()) {
                case 0:
                    helper.setText(R.id.tv_4, "可用");
                    break;
                case 1:
                    helper.setText(R.id.tv_4, "借用");
                    break;
                case 2:
                    helper.setText(R.id.tv_4, "送检占用");
                    break;
                case 3:
                    helper.setText(R.id.tv_4, "送检");
                    break;
                case 4:
                    helper.setText(R.id.tv_4, "报废占用");
                    break;
                case 5:
                    helper.setText(R.id.tv_4, "报废");
                    break;
                case 6:
                    helper.setText(R.id.tv_4, "封存");
                    break;
                case 7:
                    helper.setText(R.id.tv_4, "解封占用");
                    break;
                case 8:
                    helper.setText(R.id.tv_4, "过期");
                    break;
                case 9:
                    helper.setText(R.id.tv_4, "外借");
                    break;
                case 10:
                    helper.setText(R.id.tv_4, "未送检");
                    break;

            }

        }
    }
}
