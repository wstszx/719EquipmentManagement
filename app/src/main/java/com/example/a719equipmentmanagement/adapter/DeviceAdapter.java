package com.example.a719equipmentmanagement.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.Device;

import java.util.List;

import androidx.annotation.Nullable;

public class DeviceAdapter extends BaseQuickAdapter<Device, BaseViewHolder> {
    public DeviceAdapter(int layoutResId, @Nullable List<Device> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Device item) {
        helper.setText(R.id.textView5, item.getName())
                .setText(R.id.textView6, item.getStatus());

    }


//    private Context mContext;
//    @Override
//    public void onBindViewHolder(BaseViewHolder holder, int position) {
//        super.onBindViewHolder(holder, position);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext,"click No. is "+position,Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
