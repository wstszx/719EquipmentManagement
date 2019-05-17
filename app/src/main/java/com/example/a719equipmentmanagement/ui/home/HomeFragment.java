package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.HomeAdapter;
import com.example.a719equipmentmanagement.adapter.WaitApprovalItemAdapter;
import com.example.a719equipmentmanagement.adapter.WaitReturnDeviceAdapter;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.HomeBean;
import com.example.a719equipmentmanagement.entity.InvalidEquip;
import com.example.a719equipmentmanagement.entity.Me;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.example.a719equipmentmanagement.entity.ToDo;
import com.example.a719equipmentmanagement.entity.ToReturn;
import com.example.a719equipmentmanagement.entity.UserToDo;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
        initMenu();
        initData();
    }

    private void initData() {
//        1,超级系统管理员2，普通用户3，实验室管理员
        RetrofitClient.getInstance().getService().getMe()
                .compose(CommonCompose.io2main(getContext()))
                .subscribe(new BaseSubscriber<Me>(getContext()) {
                    @Override
                    public void onSuccess(Me me) {
                        if (me != null) {
                            getMeData(me);
                        }
                    }
                });
//        RetrofitClient.getInstance().getService().getWaitApprovalItem()
//                .compose(CommonCompose.io2main(getContext()))
//                .subscribe(new BaseSubscriber<InvalidEquip>(getContext()) {
//                    @Override
//                    public void onSuccess(InvalidEquip invalidEquip) {
//
//                    }
//                });
//        RetrofitClient.getInstance().getService().getWaitReturnDevice()
//                .compose(CommonCompose.io2main(getContext()))
//                .subscribe(new BaseSubscriber<ToAudit>(getContext()) {
//                    @Override
//                    public void onSuccess(ToAudit waitReturnDevice) {
//
//                    }
//                });
    }

    private void getMeData(Me me) {
        int roleId = me.getUser().getRoles().get(0).getRoleId();
        SPUtils.getInstance().put("roleId", roleId);
        switch (roleId) {
            case 1:
            case 2:
//                getAdminData();
                break;
            case 3:
//                getUserData();
                break;
        }
    }

    /**
     * 获取普通用户事项
     */
    private void getUserData() {
        RetrofitClient.getInstance().getService().userToDo()
                .compose(CommonCompose.io2main(getContext()))
                .subscribe(new BaseSubscriber<UserToDo>(getContext()) {
                    @Override
                    public void onSuccess(UserToDo data) {

                    }
                });
        RetrofitClient.getInstance().getService().toReturn()
                .compose(CommonCompose.io2main(getContext()))
                .subscribe(new BaseSubscriber<ToReturn>(getContext()) {
                    @Override
                    public void onSuccess(ToReturn data) {

                    }
                });

    }

    /**
     * 获取管理员用户事项
     */
    private void getAdminData() {
        RetrofitClient.getInstance().getService().toDo()
                .compose(CommonCompose.io2main(getContext()))
                .subscribe(new BaseSubscriber<ToDo>(getContext()) {
                    @Override
                    public void onSuccess(ToDo data) {

                    }
                });
        RetrofitClient.getInstance().getService().toAudit()
                .compose(CommonCompose.io2main(getContext()))
                .subscribe(new BaseSubscriber<ToAudit>(getContext()) {
                    @Override
                    public void onSuccess(ToAudit data) {

                    }
                });
    }

    private void initView() {
        topbar.setTitle("首页");
        topbar.addLeftTextButton("消息", R.id.message).setOnClickListener(v -> {
            MsgActivity.start(getActivity());
        });
        topbar.addRightImageButton(R.mipmap.scan, R.id.scan).setOnClickListener(v -> ScanActivity.start(getActivity()));

        recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview2.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeAdapter(R.layout.square_match_item);
        WaitApprovalItemAdapter adapter1 = new WaitApprovalItemAdapter(R.layout.wait_approval_item);
        WaitReturnDeviceAdapter adapter2 = new WaitReturnDeviceAdapter(R.layout.wait_return_device);
        recyclerview.setAdapter(adapter);
        recyclerview1.setAdapter(adapter1);
        recyclerview2.setAdapter(adapter2);
    }

    private void initMenu() {
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
