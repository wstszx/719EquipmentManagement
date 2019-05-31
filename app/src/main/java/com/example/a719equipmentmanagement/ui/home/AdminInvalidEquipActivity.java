package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.ui.device.DeviceDetailActivity;

import java.io.Serializable;

public class AdminInvalidEquipActivity extends BaseActivity {
    public AdminInvalidEquipActivity() {
    }
    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_admin_invalid_equip;
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, AdminInvalidEquipActivity.class);
        context.startActivity(starter);
    }
}
