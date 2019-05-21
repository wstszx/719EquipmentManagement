package com.example.a719equipmentmanagement.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.Person;
import com.example.a719equipmentmanagement.entity.User;

import java.util.List;

public class PersonManageAdapter extends BaseQuickAdapter<User.ListBean, BaseViewHolder> {

    private SwitchListener listener;

    public interface SwitchListener {
        void check(Switch aSwitch, boolean isCheck);
    }

    public PersonManageAdapter(int layoutResId) {
        super(layoutResId);
    }

    public void setListener(SwitchListener listener) {
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, User.ListBean item) {
        helper.setText(R.id.tv_username, item.getUserName())
                .setText(R.id.tv_dept, item.getDept().getDeptName())
                .setText(R.id.tv_phone, item.getPhonenumber())
                .setText(R.id.tv_create_time, item.getCreateTime())
                .addOnClickListener(R.id.tv_edit)
                .addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.tv_reset);

        Switch aSwitch = helper.getView(R.id.switch1);

        String status = item.getStatus();
        switch (status) {
            case "0":
                aSwitch.setChecked(true);
                break;
            case "1":
                aSwitch.setChecked(false);
                break;
        }
//        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    listener.check(false);
//                } else {
//                    listener.check(true);
//                }
//            }
//        });
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = aSwitch.isChecked();
                listener.check(aSwitch, checked);
            }
        });
    }
}
