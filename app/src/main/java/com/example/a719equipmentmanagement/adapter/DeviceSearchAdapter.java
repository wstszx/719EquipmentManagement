package com.example.a719equipmentmanagement.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.DeviceSearch;

import java.util.List;

public class DeviceSearchAdapter extends BaseQuickAdapter<DeviceSearch,BaseViewHolder> {
    public DeviceSearchAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceSearch item) {
        helper.setText(R.id.tv_id,item.getId()+"")
                .setText(R.id.tv_name,item.getName());

    }

}
