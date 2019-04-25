package com.example.a719equipmentmanagement.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.Device;

import java.util.List;

import androidx.annotation.Nullable;

public class DeviceAdapter extends BaseQuickAdapter<Device, BaseViewHolder> {
    public DeviceAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Device item) {
        helper.setText(R.id.textView1, item.getName())
                .setText(R.id.textView2,item.getTarget())
                .setText(R.id.textView3,String.valueOf(item.getId()))
                .setText(R.id.textView4,item.getDepartment())
                .setText(R.id.textView5, item.getStatus())
                .setText(R.id.textView6,item.getLocation());
//        .setText(R.id.textView7,item.getUserName())
//        helper.setBackgroundColor(R.id.textView5,)
        switch (item.getStatus()){
            case "在用":
                helper.setTextColor(R.id.textView5, Color.parseColor("#EF5362"));
                break;
            case "可借":
                helper.setTextColor(R.id.textView5,Color.parseColor("#00574B"));

                break;
            case "送检":
                helper.setTextColor(R.id.textView5,Color.parseColor("#FFCF47"));
                break;
            case "报废":
                helper.setTextColor(R.id.textView5,Color.parseColor("#B0C4DE"));

                break;
            case "状态5":
                break;

        }
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
