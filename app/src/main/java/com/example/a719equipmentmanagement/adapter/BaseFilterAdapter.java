package com.example.a719equipmentmanagement.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.BaseSingleFilter;

public class BaseFilterAdapter extends BaseQuickAdapter<BaseSingleFilter, BaseViewHolder> {
    public BaseFilterAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BaseSingleFilter item) {
        if (item != null) {
            boolean existRight = item.isExistRight();
            helper.setVisible(R.id.imageView2, existRight)
                    .setText(R.id.tv_name, item.getName());
        }
    }
}
