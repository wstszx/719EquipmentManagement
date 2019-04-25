package com.example.a719equipmentmanagement.ui.device;


import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.MainActivity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.DeviceAdapter;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.Device;
import com.example.a719equipmentmanagement.ui.home.ContainerManageActivity;
import com.example.a719equipmentmanagement.view.CustomInputDialog;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

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
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
//    private TextView mTextView5;

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
        topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> addDeviceTextDialog());
        topbar.addLeftImageButton(R.mipmap.search,R.id.search).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),SearchActivity.class);
            startActivity(intent);
        });
    }

    private void addDeviceTextDialog(){
        CustomInputDialog deviceInputDialog=new CustomInputDialog(getActivity());
        final QMUIDialog.EditTextDialogBuilder builder=new QMUIDialog.EditTextDialogBuilder(getActivity());
        deviceInputDialog.setTitle("添加设备").setPlaceholder("设备名称").setPlaceholder1("设备编号")
//                .setPlaceholder("属性01").setPlaceholder("属性02")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("确定", (dialog, index) -> {
                    CharSequence text = deviceInputDialog.getEditText().getText();
                    CharSequence text1 = deviceInputDialog.getEditText1().getText();
                    if (text1 != null && text1.length() > 0) {
                        Toast.makeText(getActivity(), "成功" + "添加设备" + ":" + text + text1, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getActivity(), "输入不能为空", Toast.LENGTH_SHORT).show();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }
    private void initData() {
        devices = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
//            Device device = new Device(i, "设备" + i, "状态" + i);
            Device device=new Device();
            int i1=Integer.valueOf("19042300"+i);
            device.setId(i1);

            if(i%4==1){
                device.setName("压力变送器");
                device.setTarget("5MPa");
                device.setDepartment("三室2组");
                device.setLocation("2号货架1层");
//                device.setUserName("无");
                device.setStatus("可借");
            }
            else if(i%4==2){
                device.setName("温度计");
                device.setTarget("500℃");
                device.setDepartment("三室1组");
                device.setLocation("1号货架2层");
//                device.setUserName("张三四");
                device.setStatus("在用");
            }
            else if(i%4==3){
                device.setName("压力表");
                device.setTarget("3MPa");
                device.setDepartment("三室4组");
                device.setLocation("4号货架第3层");
//                device.setUserName("无");
                device.setStatus("送检");
            }else {
                device.setName("流量计");
                device.setTarget("100m³/h");
                device.setDepartment("三室4组");
                device.setLocation("5号货架第1层");
//                device.setUserName("无");
                device.setStatus("报废");
            }

            devices.add(device);
        }
    }

    private void initAdapter() {
//        adapter = new DeviceAdapter(R.layout.base_device);
        adapter = new DeviceAdapter(R.layout.base_device02);
        adapter.setNewData(devices);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
//        recyclerview.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()),DividerItemDecoration.VERTICAL,10,getResources().getColor(R.color.app_color_blue)));
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(),"当前点击条目为"+(position+1),Toast.LENGTH_SHORT).show();
                DeviceDetailActivity.start(getActivity());
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
