package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ApplyProgressAdapter;
import com.example.a719equipmentmanagement.adapter.ToReturnAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.ToReturn;
import com.example.a719equipmentmanagement.entity.UserToAudit;
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

public class ApplyProgressActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private ApplyProgressAdapter adapter;
    private int pageNum = 1;
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
        map.put("pageNum", 1);
        map.put("pageSize", 10);
        initData(map);
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(ApplyProgressActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new ApplyProgressAdapter(R.layout.apply_progress_item);
        adapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_cancel:
                        UserToAudit.RowsBean rowsBean = (UserToAudit.RowsBean) adapter.getData().get(position);
                        int id = rowsBean.getId();
                        cancelApply(id);
                        break;
                }
            }
        });
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

    private void cancelApply(int id) {
        RetrofitClient.getInstance().getService().cancelOperatingEquip(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(ApplyProgressActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        if (response != null) {
                            int code = response.getCode();
                            if (code == 0) {
                                initData(map);
                                ToastUtils.showShort("取消申请成功");
                            }
                        }
                    }
                });
    }

    private void initTopbar() {
        topBar.setTitle("我的申请进度");
        topBar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
        });
    }

    private void refreshData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().userToAudit(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<UserToAudit>(ApplyProgressActivity.this) {
                    @Override
                    public void onSuccess(UserToAudit userToAudit) {
                        if (userToAudit != null) {
                            List<UserToAudit.RowsBean> rows = userToAudit.getRows();
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

    private void initData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().userToAudit(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<UserToAudit>(ApplyProgressActivity.this) {
                    @Override
                    public void onSuccess(UserToAudit userToAudit) {
                        if (userToAudit != null) {
                            List<UserToAudit.RowsBean> rows = userToAudit.getRows();
                            if (rows != null && rows.size() > 0) {
                                adapter.setNewData(rows);
                                if (rows.size() != 10) {
                                    refreshlayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                refreshlayout.finishLoadMoreWithNoMoreData();
                                adapter.setEmptyView(R.layout.empty);
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
        Intent starter = new Intent(context, ApplyProgressActivity.class);
        context.startActivity(starter);
    }
}
