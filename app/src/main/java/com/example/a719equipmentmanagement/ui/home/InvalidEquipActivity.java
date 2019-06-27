package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.InvalidEquipAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.InvalidEquip;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InvalidEquipActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private int pageNum = 1;
    private Map<String, Object> map = new HashMap<>();
    private InvalidEquipAdapter invalidEquipAdapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopBar();
        initAdapter();
        map.put("pageNum", 1);
        map.put("pageSize", 10);
        initData(map);
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(InvalidEquipActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        invalidEquipAdapter = new InvalidEquipAdapter(R.layout.invalid_equip_item);
        recyclerView.setAdapter(invalidEquipAdapter);
        refreshlayout.setOnRefreshListener(refreshLayout1 -> {
            refreshLayout1.finishRefresh();
            LogUtils.i("我在调用下拉");
            pageNum = 1;
            map.put("pageNum", 1);
            map.put("pageSize", 10);
            initData(map);
        });
        refreshlayout.setOnLoadMoreListener(refreshLayout -> {
            refreshLayout.finishLoadMore();
            pageNum++;
            map.put("pageNum", pageNum);
            map.put("pageSize", 10);
            refreshData(map);
        });
    }

    private void initTopBar() {
        topBar.setTitle("即将过期设备");
        topBar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
        });
    }

    private void refreshData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().invalidEquip(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<InvalidEquip>>(this) {
                    @Override
                    public void onSuccess(List<InvalidEquip> invalidEquips) {
                        if (invalidEquips != null) {
                            invalidEquipAdapter.addData(invalidEquips);
                            if (invalidEquips.size() < 10) {
                                refreshlayout.finishLoadMoreWithNoMoreData();
                            }
                        }
                    }
                });
    }

    private void initData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().invalidEquip(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<InvalidEquip>>(this) {
                    @Override
                    public void onSuccess(List<InvalidEquip> invalidEquips) {
                        if (invalidEquips != null && invalidEquips.size() > 0) {
                            invalidEquipAdapter.setNewData(invalidEquips);
                            if (invalidEquips.size() != 10) {
                                refreshlayout.finishLoadMoreWithNoMoreData();
                            }
                        } else {
                            refreshlayout.finishLoadMoreWithNoMoreData();
                            invalidEquipAdapter.setEmptyView(R.layout.empty);
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invalid_equip;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, InvalidEquipActivity.class);
        context.startActivity(starter);
    }

}
