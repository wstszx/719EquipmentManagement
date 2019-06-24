package com.example.a719equipmentmanagement.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.DeviceData2;


public class DeviceAdapter extends BaseQuickAdapter<DeviceData2.RowsBean, BaseViewHolder> {
    public DeviceAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceData2.RowsBean item) {
        helper.setText(R.id.textView1, item.getName())
                .setText(R.id.textView2, item.getParameter())
                .setText(R.id.textView3, item.getEquipNo())
                .setText(R.id.textView7,item.getCreateTime());

        DeviceData2.RowsBean.DeptBean dept = item.getDept();
        helper.setText(R.id.textView4, dept == null ? "无部门信息" : dept.getDeptName());
        DeviceData2.RowsBean.LocationBean location = item.getLocation();
        helper.setText(R.id.textView6, location == null ? "无位置信息" : location.getName());

        switch (item.getStatus()) {
            case 0:
                helper.setText(R.id.textView5, "可用");
                helper.setTextColor(R.id.textView5, Color.GREEN);
                break;
            case 1:
                helper.setTextColor(R.id.textView5, Color.GREEN);
                helper.setText(R.id.textView5, "借用");
                break;
            case 2:
                helper.setTextColor(R.id.textView5, Color.RED);
                helper.setText(R.id.textView5, "送检占用");
                break;
            case 3:
                helper.setTextColor(R.id.textView5, Color.RED);
                helper.setText(R.id.textView5, "送检");
                break;
            case 4:
                helper.setText(R.id.textView5, "报废占用");
                helper.setTextColor(R.id.textView5, Color.RED);
                break;
            case 5:
                helper.setTextColor(R.id.textView5, Color.RED);
                helper.setText(R.id.textView5, "报废");
                break;
            case 6:
                helper.setTextColor(R.id.textView5, Color.RED);
                helper.setText(R.id.textView5, "封存");
                break;
            case 7:
                helper.setTextColor(R.id.textView5, Color.RED);
                helper.setText(R.id.textView5, "解封占用");
                break;
            case 8:
                helper.setTextColor(R.id.textView5, Color.RED);
                helper.setText(R.id.textView5, "过期");
                break;
            case 9:
                helper.setTextColor(R.id.textView5, Color.RED);
                helper.setText(R.id.textView5, "外借");
                break;
            default:
                helper.setBackgroundColor(R.id.textView5, Color.GREEN);
                break;

        }
    }
}
