package com.example.a719equipmentmanagement.ui.device;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.databinding.FragmentDeviceBinding;
import com.example.a719equipmentmanagement.ui.home.HomeFragment;

public class DeviceFragment extends BaseFragment<FragmentDeviceBinding> {


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
