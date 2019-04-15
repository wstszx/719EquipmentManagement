package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.HomeAdapter;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.databinding.FragmentHomeBinding;
import com.example.a719equipmentmanagement.entity.HomeBean;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private String[] features = {"人员管理", "货柜管理", "建账入库", "借用", "归还", "盘点", "点检", "报废"};

    private static HomeFragment fragment;
    private HomeAdapter adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initView() {
        mbinding.rvWork.setLayoutManager(new GridLayoutManager(getContext(), 4));
        adapter = new HomeAdapter(R.layout.square_match_item);
        mbinding.rvWork.setAdapter(adapter);

    }

    private void initData() {
        List<HomeBean> homeBeanList = new ArrayList<>();
        for (String feature : features) {
            HomeBean bean = new HomeBean(R.mipmap.ic_launcher, feature);
            homeBeanList.add(bean);
        }
        adapter.setNewData(homeBeanList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    public static HomeFragment newInstance() {
        if (fragment == null) {
            fragment = new HomeFragment();
        }
        return fragment;
    }

}
