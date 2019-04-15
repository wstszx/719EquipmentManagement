package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.HomeBean;

public class HomeAdapter extends BaseQuickAdapter<HomeBean, BaseViewHolder> {

    public HomeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {
        helper.setText(R.id.tv_schedule_attendance, item.getName())
                .setImageResource(R.id.iv_schedule_attendance, item.getImageRes());
    }
}
