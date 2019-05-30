package com.example.a719equipmentmanagement.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.a719equipmentmanagement.MainActivity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.InvalidEquip;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.Serializable;

import butterknife.BindView;

public class WaitApprovalItemActivity extends BaseActivity {

    @BindView(R.id.tv02)
    TextView textView_deviceName;
    @BindView(R.id.tv04)
    TextView textView_status;
    @BindView(R.id.tv06)
    TextView textView_person;
    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wait_approval_item;
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
        topBar.setTitle("事项审批");
        topBar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
        });
    }

    private void initData() {
        Intent intent = this.getIntent();
        InvalidEquip invalidEquip = (InvalidEquip) intent.getSerializableExtra("serializable");
        String deviceName = invalidEquip.getDeviceName();
        String currentStatus = invalidEquip.getCurrentStatus();
        String person = invalidEquip.getPerson();

        textView_deviceName.setText(deviceName);
        textView_status.setText(currentStatus);
        textView_person.setText(person);
    }


    public static void start(Context context, Serializable serializable) {
        Intent starter = new Intent(context, WaitApprovalItemActivity.class);
        starter.putExtra("serializable", serializable);
        context.startActivity(starter);
    }
}
