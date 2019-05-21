package com.example.a719equipmentmanagement.ui.device;


import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.MainActivity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.DeviceAdapter;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.Device;
import com.example.a719equipmentmanagement.entity.DeviceClassifiy;
import com.example.a719equipmentmanagement.entity.DeviceData;
import com.example.a719equipmentmanagement.entity.DeviceData2;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.NetworkError;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.home.AddDeptActivity;
import com.example.a719equipmentmanagement.ui.home.ContainerManageActivity;
import com.example.a719equipmentmanagement.view.CustomInputDialog;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceFragment extends BaseFragment {

    private static final int ADD_DEVICE = 1;
    private static DeviceFragment fragment;

    @BindView(R.id.topbar)
    QMUITopBar topbar;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private DeviceAdapter adapter;

    private List<DeviceData2.RowsBean> rows;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
        initData();

    }

    private void initTopbar() {
        topbar.setTitle("设备");
//        topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> addDeviceTextDialog());

//        topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> {
//            Intent intent = new Intent(getActivity(), AddDeviceActivity.class);
//            startActivityForResult(intent,ADD_DEVICE);
//        });
        topbar.addLeftImageButton(R.mipmap.search, R.id.search).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case ADD_DEVICE:
                initData();
                break;
        }
    }

    //    private void addDeviceTextDialog(){
//        CustomInputDialog deviceInputDialog=new CustomInputDialog(getActivity());
//        final QMUIDialog.EditTextDialogBuilder builder=new QMUIDialog.EditTextDialogBuilder(getActivity());
//        deviceInputDialog.setTitle("添加设备").setPlaceholder("设备名称").setPlaceholder1("设备编号")
////                .setPlaceholder("属性01").setPlaceholder("属性02")
//                .setInputType(InputType.TYPE_CLASS_TEXT)
//                .addAction("取消", (dialog, index) -> dialog.dismiss())
//                .addAction("确定", (dialog, index) -> {
//                    CharSequence text = deviceInputDialog.getEditText().getText();
//                    CharSequence text1 = deviceInputDialog.getEditText1().getText();
//                    if (text1 != null && text1.length() > 0) {
//                        Toast.makeText(getActivity(), "成功" + "添加设备" + ":" + text + text1, Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    } else {
//                        Toast.makeText(getActivity(), "输入不能为空", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .create(mCurrentDialogStyle).show();
//    }


    private void initData() {
        RetrofitClient.getInstance().getService().findDeviceData()
                .compose(CommonCompose.io2main(getContext()))
                .subscribe(new BaseSubscriber<DeviceData2>(getContext()) {
                    @Override
                    public void onSuccess(DeviceData2 datas) {
                        if (datas != null ) {
                            rows = datas.getRows();
                            if (rows != null && rows.size() > 0) {
                                adapter.setNewData(rows);
                            }
                        }
                    }
                });
    }

    private void initAdapter() {
        adapter = new DeviceAdapter(R.layout.base_device02);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DeviceData2.RowsBean currentItemData=rows.get(position);
                DeviceDetailActivity.start(getContext(), currentItemData);
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
