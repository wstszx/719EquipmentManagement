package com.example.a719equipmentmanagement.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.Device;
import com.example.a719equipmentmanagement.entity.DeviceData;

import java.util.List;

import androidx.annotation.Nullable;

public class DeviceAdapter extends BaseQuickAdapter<DeviceData.RowsBean, BaseViewHolder> {
    public DeviceAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceData.RowsBean item) {
        helper.setText(R.id.textView1, item.getName())
//                .setText(R.id.textView2,item.getTarget())
                .setText(R.id.textView3, item.getEquipNo())
//                .setText(R.id.textView4,item.getDepartment())
//                .setText(R.id.textView5, item.getStatus() + "")
        ;
//                .setText(R.id.textView6,item.getLocation());
        switch (item.getStatus()) {
            case 0:
                helper.setText(R.id.textView5, "封存");
                helper.setTextColor(R.id.textView5, Color.parseColor("#EF5362"));
                break;
            case 1:
                helper.setTextColor(R.id.textView5, Color.parseColor("#00574B"));
                helper.setText(R.id.textView5, "占用");
                break;
            case 2:
                helper.setTextColor(R.id.textView5, Color.parseColor("#FFCF47"));
                helper.setText(R.id.textView5, "报废");
                break;
//            case 3:
//                helper.setTextColor(R.id.textView5, Color.parseColor("#B0C4DE"));
//                helper.setText(R.id.textView5, "可借用");
//                break;
//            case 4:
//                break;

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
