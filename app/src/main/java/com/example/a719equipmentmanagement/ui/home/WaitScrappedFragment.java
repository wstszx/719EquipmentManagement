package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.DeviceAdapter;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.Objects;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitScrappedFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initView();
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
        topbar.addLeftBackImageButton().setOnClickListener(v -> Objects.requireNonNull(getActivity()).finish());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wait_scrapped;
    }

}
