package com.example.a719equipmentmanagement.ui.device;

import android.os.Bundle;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;

public class DeviceBaseActivity extends BaseActivity {

    private static DeviceBaseActivity base;
    private TextView mTextView5;

    @Override
    protected void init(Bundle savedInstanceState) {
        mTextView5=findViewById(R.id.textView5);
        String statusWord= (String)mTextView5.getText();
        switch (statusWord){
            case "在用":
                mTextView5.setTextColor(0x808080);
                break;
            case "可借":
                mTextView5.setTextColor(0x008000);
                break;
            case "送检":
                mTextView5.setTextColor(0xFFFF00);
                break;
            case "报废":
                mTextView5.setTextColor(0xff0000);
                break;
            case "状态5":
                break;

        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.base_device;
    }

}
