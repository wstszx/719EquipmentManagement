package com.example.a719equipmentmanagement.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AccountingListAdapter extends BaseQuickAdapter<JSONObject, BaseViewHolder> {

    public AccountingListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, JSONObject item) {
        try {
            JSONObject equip = (JSONObject) item.get("equip");
            String name = equip.getString("name");
            int status = equip.getInt("status");
            helper.setText(R.id.tv_name, name);
            switch (status) {
                case 0:
                    helper.setText(R.id.tv_status, "可用");
                    break;
                case 1:
                    helper.setText(R.id.tv_status, "借用");
                    break;
                case 2:
                    helper.setText(R.id.tv_status, "送检占用");
                    break;
                case 3:
                    helper.setText(R.id.tv_status, "送检");
                    break;
                case 4:
                    helper.setText(R.id.tv_status, "报废占用");
                    break;
                case 5:
                    helper.setText(R.id.tv_status, "报废");
                    break;
                case 6:
                    helper.setText(R.id.tv_status, "封存");
                    break;
                case 7:
                    helper.setText(R.id.tv_status, "解封占用");
                    break;
                case 8:
                    helper.setText(R.id.tv_status, "过期");
                    break;
                case 9:
                    helper.setText(R.id.tv_status, "外借");
                    break;
                case 10:
                    helper.setText(R.id.tv_status, "不限");
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
