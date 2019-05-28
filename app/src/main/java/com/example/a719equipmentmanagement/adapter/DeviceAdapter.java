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
import com.example.a719equipmentmanagement.entity.DeviceData2;

import java.util.List;

import androidx.annotation.Nullable;

public class DeviceAdapter extends BaseQuickAdapter<DeviceData2.RowsBean, BaseViewHolder> {
    public DeviceAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceData2.RowsBean item) {
        helper.setText(R.id.textView1, item.getName())
                .setText(R.id.textView2, item.getParameter())
                .setText(R.id.textView3, item.getEquipNo());

        DeviceData2.RowsBean.DeptBean dept = item.getDept();
        helper.setText(R.id.textView4,dept==null?"无部门信息":dept.getDeptName());
        DeviceData2.RowsBean.LocationBean location = item.getLocation();
        helper.setText(R.id.textView6,location==null?"无位置信息":location.getName());
//        //暂时添加货架
//        switch (item.getLocationId()) {
//            case 0:
//                helper.setText(R.id.textView6, "1架1层");
//                break;
//            case 1:
//                helper.setText(R.id.textView6, "2架1层");
//                break;
//            case 2:
//                helper.setText(R.id.textView6, "3架1层");
//                break;
//            case 3:
//                helper.setText(R.id.textView6, "4架1层");
//                break;
//        }

        switch (item.getStatus()) {
            case 0:
                helper.setText(R.id.textView5, "可用");
                helper.setTextColor(R.id.textView5, Color.parseColor("#8B008B"));
                break;
            case 1:
                helper.setTextColor(R.id.textView5, Color.parseColor("#0000FF"));
                helper.setText(R.id.textView5, "借用");
                break;
            case 2:
                helper.setTextColor(R.id.textView5, Color.parseColor("#00BFFF"));
                helper.setText(R.id.textView5, "送检占用");
                break;
            case 3:
                helper.setTextColor(R.id.textView5, Color.parseColor("#00FFFF"));
                helper.setText(R.id.textView5, "送检");
                break;
            case 4:
                helper.setText(R.id.textView5, "报废占用");
                helper.setTextColor(R.id.textView5, Color.parseColor("#00FF7F"));
                break;
            case 5:
                helper.setTextColor(R.id.textView5, Color.parseColor("#006400"));
                helper.setText(R.id.textView5, "报废");
                break;
            case 6:
                helper.setTextColor(R.id.textView5, Color.parseColor("#FFD700"));
                helper.setText(R.id.textView5, "封存");
                break;
            case 7:
                helper.setTextColor(R.id.textView5, Color.parseColor("#FFA500"));
                helper.setText(R.id.textView5, "解封占用");
                break;
            case 8:
                helper.setTextColor(R.id.textView5, Color.parseColor("#D2691E"));
                helper.setText(R.id.textView5, "过期");
                break;
            case 9:
                helper.setTextColor(R.id.textView5, Color.parseColor("#B22222"));
                helper.setText(R.id.textView5, "外借");
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
