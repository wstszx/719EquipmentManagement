package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmScanFragment extends BaseFragment {

    @Override
    protected void init(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String title = arguments.getString("title");
            LogUtils.i(title);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_again_scan;
    }

}
