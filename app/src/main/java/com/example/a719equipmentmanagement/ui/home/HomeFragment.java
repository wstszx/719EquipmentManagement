package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.HomeAdapter;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.HomeBean;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_more1)
    TextView tvMore1;
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_more2)
    TextView tvMore2;
    @BindView(R.id.recyclerview2)
    RecyclerView recyclerview2;
    private String[] features = {"人员管理", "货柜管理", "设备分类", "建账入库", "借还", "盘点", "点检", "报废"};
    private int[] featuresImg = {R.mipmap.team, R.mipmap.container, R.mipmap.device, R.mipmap.storage, R.mipmap.borrow,
            R.mipmap.inventory, R.mipmap.check, R.mipmap.scrapped};

    private static HomeFragment fragment;
    private HomeAdapter adapter;

    @Override
    protected void init(Bundle savedInstanceState) {

        initView();
        initData();
    }

    private void initView() {
        topbar.setTitle("首页");
        topbar.addLeftTextButton("消息", R.id.message).setOnClickListener(v -> {
            MsgActivity.start(getActivity());
        });
        topbar.addRightImageButton(R.mipmap.scan, R.id.scan).setOnClickListener(v -> ScanActivity.start(getActivity()));

        recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
        adapter = new HomeAdapter(R.layout.square_match_item);
        recyclerview.setAdapter(adapter);

    }

    private void initData() {
        List<HomeBean> homeBeanList = new ArrayList<>();
        for (int i = 0; i < features.length; i++) {
            HomeBean bean = new HomeBean(featuresImg[i], features[i]);
            homeBeanList.add(bean);
        }
        adapter.setNewData(homeBeanList);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case 0:
                    PersonManageActivity.start(getContext());
                    break;
                case 1:
                    ContainerManageActivity.start(getContext());
                    break;
                case 2:
                    DeviceClassifiyActivity.start(getContext());
                    break;
                case 3:
                    AccountingActivity.start(getContext());
                    break;
                case 4:
                    ScanActivity.start(getContext());
                    break;
                case 5:
                    InventoryActivity.start(getContext());
                    break;
                case 6:
                    CheckActivity.start(getContext());
                    break;
                case 7:
                    ScrappedActivity.start(getContext());
                    break;
            }
        });
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
