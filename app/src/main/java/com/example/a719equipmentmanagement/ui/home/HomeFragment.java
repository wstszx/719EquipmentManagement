package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ApplyProgressAdapter;
import com.example.a719equipmentmanagement.adapter.HomeAdapter;
import com.example.a719equipmentmanagement.adapter.InvalidEquipAdapter;
import com.example.a719equipmentmanagement.adapter.ToAuditAdapter;
import com.example.a719equipmentmanagement.adapter.ToDoAdapter;
import com.example.a719equipmentmanagement.adapter.ToReturnAdapter;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.HomeBean;
import com.example.a719equipmentmanagement.entity.InvalidEquip;
import com.example.a719equipmentmanagement.entity.Me;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.example.a719equipmentmanagement.entity.ToDo;
import com.example.a719equipmentmanagement.entity.ToReturn;
import com.example.a719equipmentmanagement.entity.UserToAudit;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_more1)
    TextView tvMore1;
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.tv_more2)
    TextView tvMore2;
    @BindView(R.id.recyclerview2)
    RecyclerView recyclerview2;
    @BindView(R.id.tv_more3)
    TextView tvMore3;
    @BindView(R.id.tv_more4)
    TextView tvMore4;
    @BindView(R.id.tv_more5)
    TextView tvMore5;
    @BindView(R.id.recyclerview3)
    RecyclerView recyclerview3;
    @BindView(R.id.recyclerview4)
    RecyclerView recyclerview4;
    @BindView(R.id.recyclerview5)
    RecyclerView recyclerview5;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;
    private String[] features = {"组织管理", "货柜管理", "设备分类", "建账入库", "盘点"};
    private int[] featuresImg = {R.mipmap.departmanage, R.mipmap.container, R.mipmap.device, R.mipmap.storage,
            R.mipmap.inventory};
    private String[] features1 = {"组织管理", "货柜管理", "设备分类"};
    private int[] featuresImg1 = {R.mipmap.departmanage, R.mipmap.container, R.mipmap.device};

    private static HomeFragment fragment;
    private HomeAdapter adapter;
    //管理员登录后所用adapter
    private InvalidEquipAdapter invalidEquipAdapter;
    private ToAuditAdapter toAuditAdapter;
    private ApplyProgressAdapter applyProgressAdapter;
    private ToDoAdapter toDoAdapter;
    //普通用户登录后所用adapter
    private ToReturnAdapter toReturnAdapter;
    private boolean isManager;


    @Override
    protected void init(Bundle savedInstanceState) {
        initAdapter();
        initView();
        initData();
    }

    private void initAdapter() {
        invalidEquipAdapter = new InvalidEquipAdapter(R.layout.invalid_equip_item);
        toAuditAdapter = new ToAuditAdapter(R.layout.to_audit_item);
        toDoAdapter = new ToDoAdapter(R.layout.invalid_equip_item);
        toReturnAdapter = new ToReturnAdapter(R.layout.invalid_equip_item);
        applyProgressAdapter = new ApplyProgressAdapter(R.layout.invalid_equip_item);
    }

    private void initData() {
        Single<Me> meSingle = RetrofitClient.getInstance().getService().getMe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        meSingle.flatMap((Function<Me, SingleSource<?>>) me -> {
            if (me != null) {
                Me.UserBean user = me.getDeptList();
                if (user != null) {
                    boolean manager = user.isManager();
                    boolean comUser = user.isComUser();
                    boolean admin = user.isAdmin();
                    if (manager) {
                        isManager = true;
                    }
                    if (admin) {
                        isManager = true;
                    }
                    if (comUser) {
                        isManager = false;
                    }
                    SPUtils.getInstance().put("isManager", isManager);
                }
            }
            return zip();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<Object>(getContext()) {
                    @Override
                    public void onSuccess(Object response) {

                    }
                });

    }

    private SingleSource<?> zip() {
        Single<List<InvalidEquip>> invalidEquipSingle = RetrofitClient.getInstance().getService().invalidEquip()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Single<ToAudit> toAuditSingle = RetrofitClient.getInstance().getService().toAudit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Single<ToDo> toDoSingle;
        if (isManager) {
            toDoSingle = RetrofitClient.getInstance().getService().toHandle()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            toDoSingle = RetrofitClient.getInstance().getService().userToDo()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }

        Single<ToReturn> toReturnSingle = RetrofitClient.getInstance().getService().toReturn()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Single<UserToAudit> userToAuditSingle = RetrofitClient.getInstance().getService().userToAudit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return Single.zip(invalidEquipSingle, toAuditSingle, toDoSingle, toReturnSingle, userToAuditSingle, (invalidEquip, toAudit, toDo, toReturn, userToAudit) -> {
            boolean mainThread = ThreadUtils.isMainThread();
            if (mainThread) {
                if (invalidEquip != null && invalidEquip.size() > 0) {
                    tv1.setText("即将过期的设备：" + "(" + invalidEquip.size() + ")");
                    if (invalidEquip.size() > 3) {
                        invalidEquip = invalidEquip.subList(0, 3);
                    }
                    invalidEquipAdapter.setNewData(invalidEquip);
                }
                if (toAudit != null) {
                    List<ToAudit.RowsBean> rows = toAudit.getRows();
                    if (rows != null && rows.size() > 0) {
                        tv2.setText("我的待审任务：" + "(" + rows.size() + ")");
                        if (rows.size() > 3) {
                            rows = rows.subList(0, 3);
                        }
                    }
                    toAuditAdapter.setNewData(rows);
                }
                if (toDo != null) {
                    List<ToDo.RowsBean> rows = toDo.getRows();
                    if (rows != null && rows.size() > 0) {
                        tv3.setText("我的待办事项：" + "(" + rows.size() + ")");
                        if (rows.size() > 3) {
                            rows = rows.subList(0, 3);
                        }
                    }
                    toDoAdapter.setNewData(rows);
                }
                if (toReturn != null) {
                    List<ToReturn.RowsBean> rows = toReturn.getRows();
                    if (rows != null && rows.size() > 0) {
                        tv4.setText("我的待还设备：" + "(" + rows.size() + ")");
                        if (rows.size() > 3) {
                            rows = rows.subList(0, 3);
                        }
                    }
                    toReturnAdapter.setNewData(rows);
                }
                if (userToAudit != null) {
                    List<UserToAudit.RowsBean> rows = userToAudit.getRows();
                    if (rows != null && rows.size() > 0) {
                        tv5.setText("我的申请进度：" + "(" + rows.size() + ")");
                        if (rows.size() > 3) {
                            rows = rows.subList(0, 3);
                        }
                    }
                    applyProgressAdapter.setNewData(rows);
                }

                if (isManager) {
                    recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 5));
                } else {
                    recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3));
                }
                adapter = new HomeAdapter(R.layout.square_match_item);
                recyclerview.setAdapter(adapter);
                initMenu();
            }
            return new Object();
        });
    }

    private void initView() {
        topbar.setTitle("首页");

        View view = LayoutInflater.from(getContext()).inflate(R.layout.borrow_return, null);
        view.setOnClickListener(v -> ScanActivity.start(getContext()));
        RelativeLayout.LayoutParams layoutParams = topbar.generateTopBarImageButtonLayoutParams();
        layoutParams.addRule(Gravity.CENTER | Gravity.RIGHT);
        view.setLayoutParams(layoutParams);
        topbar.addRightView(view, R.id.view);
//        初始化主页下方列表
        recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview3.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview4.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview5.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview1.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        recyclerview2.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        recyclerview3.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        recyclerview4.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        recyclerview5.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));

        recyclerview1.setAdapter(invalidEquipAdapter);
        recyclerview2.setAdapter(toAuditAdapter);
        recyclerview3.setAdapter(toDoAdapter);
        recyclerview4.setAdapter(toReturnAdapter);
        recyclerview5.setAdapter(applyProgressAdapter);

    }

    private void initMenu() {
        List<HomeBean> homeBeanList = new ArrayList<>();
        if (isManager) {
            for (int i = 0; i < features.length; i++) {
                HomeBean bean = new HomeBean(featuresImg[i], features[i]);
                homeBeanList.add(bean);
            }
        } else {
            for (int i = 0; i < features1.length; i++) {
                HomeBean bean = new HomeBean(featuresImg1[i], features1[i]);
                homeBeanList.add(bean);
            }
        }
        adapter.setNewData(homeBeanList);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case 0:
                    DeptManageActivity.start(getContext(), isManager);
                    break;
                case 1:
                    ContainerManageActivity.start(getContext(), isManager);
                    break;
                case 2:
                    DeviceClassifiyActivity.start(getContext(), isManager);
                    break;
                case 3:
                    AccountingListActivity.start(getContext());
                    break;
                case 4:
                    InventoryActivity.start(getContext());
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

    @OnClick({R.id.tv_more1, R.id.tv_more2, R.id.tv_more3, R.id.tv_more4, R.id.tv_more5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_more1:
                InvalidEquipActivity.start(getContext());
                break;
            case R.id.tv_more2:
                ToAuditActivity.start(getContext());
                break;
            case R.id.tv_more3:
                ToDoListActivity.start(getContext(), isManager);
                break;
            case R.id.tv_more4:
                ToReturnDeviceActivity.start(getContext());
                break;
            case R.id.tv_more5:
                ApplyProgressActivity.start(getContext());
                break;
        }
    }
}
