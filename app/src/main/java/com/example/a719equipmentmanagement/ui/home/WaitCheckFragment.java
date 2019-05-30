package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.DeviceAdapter;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.Device;
import com.example.a719equipmentmanagement.entity.InvalidEquip;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.Objects;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitCheckFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initView();
        initData();
    }

    private void initData() {
        RetrofitClient.getInstance().getService().invalidEquip()
                .compose(CommonCompose.io2main(getContext()))
                .subscribe(new BaseSubscriber<InvalidEquip>(getContext()){
                    @Override
                    public void onSuccess(InvalidEquip invalidEquip) {
                        if (invalidEquip != null) {
                            bindUi(invalidEquip);
                        }
                    }
                });
    }

    private void bindUi(InvalidEquip invalidEquip) {
        
    }

    private void initView() {
        DeviceAdapter adapter = new DeviceAdapter(R.layout.base_device02);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(adapter);
    }

    private void initTopbar() {
        topbar.setTitle("点检");
        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> Objects.requireNonNull(getActivity()).finish());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wait_check;
    }

}
