package com.example.a719equipmentmanagement.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.InvalidEquip;

import java.io.Serializable;

import butterknife.BindView;

public class WaitApprovalItem extends AppCompatActivity {

    @BindView(R.id.tv02)
    TextView textView_deviceName;
    @BindView(R.id.tv04)
    TextView textView_status;
    @BindView(R.id.tv06)
    TextView textView_person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_approval_item);
        initData();
    }

    private void initData() {
        Intent intent=this.getIntent();
        InvalidEquip invalidEquip= (InvalidEquip) intent.getSerializableExtra("serializable");
        String deviceName = invalidEquip.getDeviceName();
        String currentStatus = invalidEquip.getCurrentStatus();

//        textView_deviceName=new TextView(this);
////        textView_deviceName.setText(deviceName);
////        textView_status=new TextView(this);
////        textView_status.setText(currentStatus);
////        textView_person=new TextView(this);
////        textView_person.setText("无");
    }


    public static void start(Context context, Serializable serializable) {
        Intent starter = new Intent(context, WaitApprovalItem.class);
        starter.putExtra("serializable",serializable);
        context.startActivity(starter);
    }
}
