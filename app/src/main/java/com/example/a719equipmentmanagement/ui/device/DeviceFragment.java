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
import com.example.a719equipmentmanagement.net.NetworkError;
import com.example.a719equipmentmanagement.net.RetrofitClient;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceFragment extends BaseFragment {

    private static DeviceFragment fragment;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
//    private TextView mTextView5;

    @BindView(R.id.topbar)
    QMUITopBar topbar;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private DeviceAdapter adapter;
    private List<DeviceData> body;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
        initAdapter();

    }

    private void initTopbar() {
        topbar.setTitle("设备");
//        topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> addDeviceTextDialog());

        topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),AddDeviceActivity.class);
            startActivity(intent);
        });
        topbar.addLeftImageButton(R.mipmap.search,R.id.search).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),SearchActivity.class);
            startActivity(intent);
        });
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
        RetrofitClient.getInstance().getService().findDeviceData().enqueue(new Callback<BaseResponse<List<DeviceData>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<DeviceData>>> call, Response<BaseResponse<List<DeviceData>>> response) {
                if (response.body() != null) {
                    boolean ok = response.body().isOk(App.getContext());
                    if (ok) {
                        body = response.body().getRes();
                        if (body != null && body.size() > 0) {
                            bindUi();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<DeviceData>>> call, Throwable t) {
                NetworkError.error(App.getContext(),t);
            }
        });
    }

    private void bindUi() {
        adapter.setNewData(body);
    }

    private void initAdapter() {
//        adapter = new DeviceAdapter(R.layout.base_device);
        adapter = new DeviceAdapter(R.layout.base_device02);

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
