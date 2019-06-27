package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ToAuditAdapter;
import com.example.a719equipmentmanagement.adapter.ToReturnAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.ToDo;
import com.example.a719equipmentmanagement.entity.ToReturn;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ToReturnDeviceActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private int pageNum = 1;
    private Map<String, Object> map = new HashMap<>();
    private ToReturnAdapter toReturnAdapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
        map.put("pageNum", 1);
        map.put("pageSize", 10);
        initData(map);
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(ToReturnDeviceActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        toReturnAdapter = new ToReturnAdapter(R.layout.to_audit_item);
        recyclerView.setAdapter(toReturnAdapter);
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

    private void initTopbar() {
        topBar.setTitle("我的待还设备");
        topBar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
        });
    }

    private void refreshData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().toReturn(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ToReturn>(ToReturnDeviceActivity.this) {
                    @Override
                    public void onSuccess(ToReturn toReturn) {
                        if (toReturn != null) {
                            List<ToReturn.RowsBean> rows = toReturn.getRows();
                            if (rows != null) {
                                toReturnAdapter.addData(rows);
                                if (rows.size() < 10) {
                                    refreshlayout.finishLoadMoreWithNoMoreData();
                                }
                            }
                        }
                    }
                });
    }

    private void initData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().toReturn(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ToReturn>(ToReturnDeviceActivity.this) {
                    @Override
                    public void onSuccess(ToReturn toReturn) {
                        if (toReturn != null) {
                            List<ToReturn.RowsBean> rows = toReturn.getRows();
                            if (rows != null && rows.size() > 0) {
                                toReturnAdapter.setNewData(rows);
                                if (rows.size() != 10) {
                                    refreshlayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                refreshlayout.finishLoadMoreWithNoMoreData();
                                toReturnAdapter.setEmptyView(R.layout.empty);
                            }
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_to_audit;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ToReturnDeviceActivity.class);
        context.startActivity(starter);
    }
}
