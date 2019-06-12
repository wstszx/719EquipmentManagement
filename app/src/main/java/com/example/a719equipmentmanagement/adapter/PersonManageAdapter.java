package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.DeptList;

public class PersonManageAdapter extends BaseQuickAdapter<DeptList.UsersBean, BaseViewHolder> {

    public PersonManageAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, DeptList.UsersBean item) {
        helper.setText(R.id.tv_username, item.getUserName())
                .setText(R.id.tv_dept, item.getDept().getDeptName())
                .setText(R.id.tv_phone, item.getPhonenumber())
                .setText(R.id.tv_create_time, item.getCreateTime())
                .addOnClickListener(R.id.tv_edit)
                .addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.tv_reset);

        String status = item.getStatus();
        switch (status) {
            case "0":
                helper.setText(R.id.tv_status, "正常");
                break;
            case "1":
                helper.setText(R.id.tv_status, "停用");
                break;
        }
    }
}
