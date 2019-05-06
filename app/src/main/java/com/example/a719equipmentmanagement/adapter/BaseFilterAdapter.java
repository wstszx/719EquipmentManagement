package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.BaseSingleFilter;

public class BaseFilterAdapter extends BaseQuickAdapter<BaseSingleFilter, BaseViewHolder> {
    public BaseFilterAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseSingleFilter item) {
        helper.setText(R.id.tv_name, item.getName());
    }
}
