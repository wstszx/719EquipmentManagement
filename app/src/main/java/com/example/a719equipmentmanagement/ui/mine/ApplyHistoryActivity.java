package com.example.a719equipmentmanagement.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ApplyHistoryAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.ApplyHistory;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class ApplyHistoryActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private ApplyHistoryAdapter adapter;
    private int pageNum = 1;
    private Map<String, Object> map = new HashMap<>();


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initAdapter();
        map.put("pageNum", 1);
        map.put("pageSize", 10);
        initData(map);
    }

    private void initData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().findUserApplyHistory(map)
                .compose(CommonCompose.io2main(ApplyHistoryActivity.this))
                .subscribe(new BaseSubscriber<ApplyHistory>(ApplyHistoryActivity.this) {
                    @Override
                    public void onSuccess(ApplyHistory applyHistory) {
                        if (applyHistory != null) {
                            List<ApplyHistory.RowsBean> rows = applyHistory.getRows();
                            if (rows != null && rows.size() > 0) {
                                adapter.setNewData(rows);
                                if (rows.size() != 10) {
                                    refreshlayout.finishLoadMoreWithNoMoreData();
                                }
                            }else {
                                refreshlayout.finishLoadMoreWithNoMoreData();
                                adapter.setEmptyView(R.layout.empty);
                            }
                        }
                    }
                });
    }

    private void refreshData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().findUserApplyHistory(map)
                .compose(CommonCompose.io2main(ApplyHistoryActivity.this))
                .subscribe(new BaseSubscriber<ApplyHistory>(ApplyHistoryActivity.this) {
                    @Override
                    public void onSuccess(ApplyHistory applyHistory) {
                        if (applyHistory != null) {
                            List<ApplyHistory.RowsBean> rows = applyHistory.getRows();
                            if (rows != null) {
                                adapter.addData(rows);
                                if (rows.size() < 10) {
                                    refreshlayout.finishLoadMoreWithNoMoreData();
                                }
                            }
                        }
                    }
                });
    }

    private void initAdapter() {
        adapter = new ApplyHistoryAdapter(R.layout.apply_history);
        adapter.bindToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ApplyHistoryActivity.class);
        context.startActivity(starter);
    }

    private void initView() {
        initTopbar();
    }

    private void initTopbar() {
        topbar.setTitle("申请历史");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

}
