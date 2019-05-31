package com.example.a719equipmentmanagement.ui.home;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.AdminInvalidEquipAdapter;
import com.example.a719equipmentmanagement.adapter.AdminToAuditAdapter;
import com.example.a719equipmentmanagement.adapter.AdminToDoAdapter;
import com.example.a719equipmentmanagement.adapter.HomeAdapter;
import com.example.a719equipmentmanagement.adapter.UserToAuditAdapter;
import com.example.a719equipmentmanagement.adapter.UserToDoAdapter;
import com.example.a719equipmentmanagement.adapter.UserToReturnAdapter;
import com.example.a719equipmentmanagement.adapter.WaitApprovalItemAdapter;
import com.example.a719equipmentmanagement.adapter.WaitReturnDeviceAdapter;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.DeviceData2;
import com.example.a719equipmentmanagement.entity.HomeBean;
import com.example.a719equipmentmanagement.entity.InvalidEquip;
import com.example.a719equipmentmanagement.entity.Me;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.example.a719equipmentmanagement.entity.ToDo;
import com.example.a719equipmentmanagement.entity.ToReturn;
import com.example.a719equipmentmanagement.entity.UserToAudit;
import com.example.a719equipmentmanagement.entity.UserToDo;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.view.SpaceItemDecoration;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_more3)
    TextView tvMore3;
    @BindView(R.id.recyclerview3)
    RecyclerView recyclerview3;
    private String[] features = {"部门管理", "人员管理", "货柜管理", "设备分类", "建账入库", "借还", "盘点", "点检", "报废"};
    private int[] featuresImg = {R.mipmap.departmanage, R.mipmap.team, R.mipmap.container, R.mipmap.device, R.mipmap.storage, R.mipmap.borrow,
            R.mipmap.inventory, R.mipmap.check, R.mipmap.scrapped};

    private static HomeFragment fragment;
    private HomeAdapter adapter;
    //管理员登录后所用adapter
    private AdminInvalidEquipAdapter adminInvalidEquipAdapter;
    private AdminToAuditAdapter adminToAuditAdapter;
    private AdminToDoAdapter adminToDoAdapter;
    //普通用户登录后所用adapter
    private UserToReturnAdapter userToReturnAdapter;
    private UserToAuditAdapter userToAuditAdapter;
    private UserToDoAdapter userToDoAdapter;

    //展示用adapter，待后台有数据后删除
    private WaitReturnDeviceAdapter adapter2;
    private WaitApprovalItemAdapter adapter1;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initAdapter();
        initMenu();
        initData();
    }

    private void initAdapter() {
        adminInvalidEquipAdapter = new AdminInvalidEquipAdapter(R.layout.square_match_item);
        adminToAuditAdapter = new AdminToAuditAdapter(R.layout.square_match_item);
        adminToDoAdapter = new AdminToDoAdapter(R.layout.square_match_item);
        userToReturnAdapter = new UserToReturnAdapter(R.layout.square_match_item);
        userToAuditAdapter = new UserToAuditAdapter(R.layout.square_match_item);
        userToDoAdapter = new UserToDoAdapter(R.layout.square_match_item);
    }

    private void initData() {
//        1,超级系统管理员2，普通用户3，实验室管理员
        RetrofitClient.getInstance().getService().getMe()
                .subscribeOn(Schedulers.io())               // （初始被观察者）切换到IO线程进行网络请求1
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<Me, SingleSource<Object>>) me -> {
                    int roleId = me.getUser().getRoles().get(0).getRoleId();
                    SPUtils.getInstance().put("roleId", roleId);
                    return convertRequest(roleId);
                }).subscribe(new BaseSubscriber<Object>(getContext()) {
            @Override
            public void onSuccess(Object o) {

            }
        });
    }

    private Single<Object> convertRequest(int roleId) {
        switch (roleId) {
            case 1:
            case 3:
                Single<InvalidEquip> invalidEquipSingle = RetrofitClient.getInstance().getService().invalidEquip()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                Single<ToAudit> toAuditSingle = RetrofitClient.getInstance().getService().toAudit()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                Single<ToDo> toDoSingle = RetrofitClient.getInstance().getService().toDo()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                return Single.zip(invalidEquipSingle, toAuditSingle, toDoSingle,
                        (invalidEquip, toAudit, toDo) -> {
                            boolean mainThread = ThreadUtils.isMainThread();
                            if (mainThread) {
                                if (invalidEquip != null) {

                                }
                                if (toAudit != null) {

                                }
                                if (toDo != null) {

                                }
                            }
                            return new Object();
                        }).subscribeOn(Schedulers.io())               // （初始被观察者）切换到IO线程进行网络请求1
                        .observeOn(AndroidSchedulers.mainThread());
            case 2:
                Single<ToReturn> toReturnSingle = RetrofitClient.getInstance().getService().toReturn()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                Single<UserToAudit> userToAuditSingle = RetrofitClient.getInstance().getService().userToAudit()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                Single<UserToDo> userToDoSingle = RetrofitClient.getInstance().getService().userToDo()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                return Single.zip(toReturnSingle, userToAuditSingle, userToDoSingle,
                        (toReturn, UserToAudit, userToDo) -> {
                            boolean mainThread = ThreadUtils.isMainThread();
                            if (mainThread) {
                                if (toReturn != null) {

                                }
                                if (UserToAudit != null) {

                                }
                                if (userToDo != null) {

                                }
                            }
                            return new Object();
                        }).subscribeOn(Schedulers.io())               // （初始被观察者）切换到IO线程进行网络请求1
                        .observeOn(AndroidSchedulers.mainThread());
        }
        return null;
    }

    private void initView() {
        recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
        adapter = new HomeAdapter(R.layout.square_match_item);
        recyclerview.setAdapter(adapter);
        int roleId = SPUtils.getInstance().getInt("roleId", 0);
        topbar.setTitle("首页");
        topbar.addLeftTextButton("消息", R.id.message).setOnClickListener(v -> {
            MsgActivity.start(getActivity());
        });

        recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview3.setLayoutManager(new LinearLayoutManager(getContext()));
        switch (roleId) {
            case 1:
            case 3:        //管理员
                tv1.setText("即将过期的设备:");
                tv2.setText("我的待审任务:");
                tv3.setText("我的待办事项:");
                recyclerview1.setAdapter(adminInvalidEquipAdapter);
                recyclerview2.setAdapter(adminToAuditAdapter);
                recyclerview3.setAdapter(adminToDoAdapter);
//                adminInvalidEquipAdapter.setNewData();
//                adminToAuditAdapter.setNewData();
//                adminToDoAdapter.setNewData();
                tvMore1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AdminInvalidEquipActivity.start(getContext());
                    }
                });
                break;
            case 2:         //普通用户
                tv1.setText("我的待还设备:");
                tv2.setText("我的申请进度:");
                tv3.setText("我的待办事项:");
                recyclerview1.setAdapter(userToReturnAdapter);
                recyclerview2.setAdapter(userToAuditAdapter);
                recyclerview3.setAdapter(userToDoAdapter);
//                userToReturnAdapter.setNewData();
//                userToAuditAdapter.setNewData();
//                userToDoAdapter.setNewData();
                break;
        }


        //展示用，待后台数据后删
        adapter1 = new WaitApprovalItemAdapter(R.layout.wait_approval_item);
        adapter2 = new WaitReturnDeviceAdapter(R.layout.wait_return_device);
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
                    DeptManageActivity.start(getContext());
                    break;
                case 1:
                    PersonManageActivity.start(getContext());
                    break;
                case 2:
                    ContainerManageActivity.start(getContext());
                    break;
                case 3:
                    DeviceClassifiyActivity.start(getContext());
                    break;
                case 4:
                    AccountingListActivity.start(getContext());
                    break;
                case 5:
                    ScanActivity.start(getContext());
                    break;
                case 6:
                    InventoryRangeActivity.start(getContext());
                    break;
                case 7:
                    CheckActivity.start(getContext());
                    break;
                case 8:
                    ScrappedActivity.start(getContext());
                    break;
            }
        });


        //展示用数据和点击，待后台数据后删
        List<InvalidEquip> test1 = new ArrayList<>();
        InvalidEquip t1;
        t1 = new InvalidEquip(1, "热感温度计", "申请报废", "王二");
        test1.add(t1);
        t1 = new InvalidEquip(2, "气压表", "申请送检", "李四");
        test1.add(t1);
        t1 = new InvalidEquip(3, "减压装置", "申请类型3", "张三");
        test1.add(t1);
        t1 = new InvalidEquip(4, "压差变送器", "申请类型4", "麻子");
        test1.add(t1);
        adapter1.setNewData(test1);
        adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                InvalidEquip invalidEquip = test1.get(position);
                WaitApprovalItemActivity.start(getContext(), invalidEquip);
            }
        });
        List<ToAudit> test2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ToAudit t2;
            t2 = new ToAudit("差压变送器", "2019.7.1", 20);
            test2.add(t2);
        }
        adapter2.setNewData(test2);
        adapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToAudit toAudit = test2.get(position);
                WaitReturnDeviceActivity.start(getContext(), toAudit);
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
