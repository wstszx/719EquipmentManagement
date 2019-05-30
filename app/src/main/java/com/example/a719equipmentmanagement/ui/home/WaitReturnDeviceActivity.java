package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.a719equipmentmanagement.MainActivity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.InvalidEquip;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.Serializable;

import butterknife.BindView;

public class WaitReturnDeviceActivity extends BaseActivity {

    @BindView(R.id.tv02)
    TextView textView_deviceName;
    @BindView(R.id.tv04)
    TextView textView_date;
    @BindView(R.id.tv06)
    TextView textView_days;
    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wait_return_device;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
        initBtn();
    }

    private void initBtn() {
        btn1.setOnClickListener(v -> {

        });
        btn2.setOnClickListener(v -> {
            finish();
        });
    }

    private void initTopbar() {
        topBar.setTitle("归还设备");
        topBar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
        });
    }

    private void initData() {
        Intent intent = this.getIntent();
        ToAudit toAudit = (ToAudit) intent.getSerializableExtra("serializable");
        String deviceName = toAudit.getDeviceName();
        String returnDate = toAudit.getReturnDate();
        int days = toAudit.getDays();
        textView_deviceName.setText(deviceName);
        textView_date.setText(returnDate);
        textView_days.setText(days+"");
    }


    public static void start(Context context, Serializable serializable) {
        Intent starter = new Intent(context, WaitReturnDeviceActivity.class);
        starter.putExtra("serializable", serializable);
        context.startActivity(starter);
    }
}
