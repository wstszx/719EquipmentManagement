package com.example.a719equipmentmanagement.ui.device;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.DeviceAdapter;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.Device;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnItemClick;

public class DeviceFragment extends BaseFragment {

    private static DeviceFragment fragment;

    @BindView(R.id.topbar)
    QMUITopBar topbar;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private DeviceAdapter adapter;
    private List<Device> devices;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
        initAdapter();
    }

    private void initTopbar() {
        topbar.setTitle("设备");
    }

    private void initData() {
        devices = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Device device = new Device(i, "设备" + i, "状态" + i);
            devices.add(device);
        }

//        adapter.setOnItemChildClickListener(new DeviceAdapter)()
    }

    private void initAdapter() {
        adapter = new DeviceAdapter(R.layout.base_device, devices);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(),"dfjdkfj",Toast.LENGTH_SHORT).show();
            }
        });
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
