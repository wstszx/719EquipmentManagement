package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.Container;

import java.util.List;

import androidx.annotation.Nullable;

public class ContainerManageAdapter extends BaseQuickAdapter<Container, BaseViewHolder> {
    public ContainerManageAdapter(int layoutResId, @Nullable List<Container> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Container item) {
        helper.setText(R.id.tv_name, item.getName());
    }
}
