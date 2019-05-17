package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.HomeAdapter;
import com.example.a719equipmentmanagement.adapter.WaitApprovalItemAdapter;
import com.example.a719equipmentmanagement.adapter.WaitReturnDeviceAdapter;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.HomeBean;
import com.example.a719equipmentmanagement.entity.Me;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.example.a719equipmentmanagement.entity.ToDo;
import com.example.a719equipmentmanagement.entity.ToReturn;
import com.example.a719equipmentmanagement.entity.UserToDo;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
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
                .subscribeOn(Schedulers.io())               // （初始被观察者）切换到IO线程进行网络请求1
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Me, SingleSource<Object>>() {
                    @Override
                    public SingleSource<Object> apply(Me me) throws Exception {
                        int roleId = me.getUser().getRoles().get(0).getRoleId();
                        SPUtils.getInstance().put("roleId", roleId);
                        return convertRequest(roleId);
                    }
                }).subscribe(new BaseSubscriber<Object>(getContext()) {
            @Override
            public void onSuccess(Object o) {

            }
        });
    }


    private Single<Object> convertRequest(int roleId) {
        switch (roleId) {
            case 1:
            case 2:
                return Single.zip(RetrofitClient.getInstance().getService().toDo(),
                        RetrofitClient.getInstance().getService().toAudit(), new BiFunction<ToDo, ToAudit, Object>() {

                            @Override
                            public Object apply(ToDo toDo, ToAudit toAudit) throws Exception {
                                return new Object();
                            }
                        }).subscribeOn(Schedulers.io())               // （初始被观察者）切换到IO线程进行网络请求1
                        .observeOn(AndroidSchedulers.mainThread());
            case 3:
                return Single.zip(RetrofitClient.getInstance().getService().userToDo(),
                        RetrofitClient.getInstance().getService().toReturn(), new BiFunction<UserToDo, ToReturn, Object>() {

                            @Override
                            public Object apply(UserToDo userToDo, ToReturn toReturn) throws Exception {
                                return new Object();
                            }
                        }).subscribeOn(Schedulers.io())               // （初始被观察者）切换到IO线程进行网络请求1
                        .observeOn(AndroidSchedulers.mainThread());
        }
        return null;
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
