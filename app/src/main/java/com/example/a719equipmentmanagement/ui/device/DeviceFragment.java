package com.example.a719equipmentmanagement.ui.device;


import android.os.Bundle;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;

import butterknife.BindView;

public class DeviceFragment extends BaseFragment {


    private static DeviceFragment fragment;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_device;
    }

    public static DeviceFragment newInstance() {
        if (fragment == null) {
            fragment = new DeviceFragment();
        }
        return fragment;
    }

}
