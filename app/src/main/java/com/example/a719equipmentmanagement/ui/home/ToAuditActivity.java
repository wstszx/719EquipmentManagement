package com.example.a719equipmentmanagement.ui.home;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ToAuditAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ToAuditActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private ToAuditAdapter toAuditAdapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
        initData();
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(ToAuditActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        toAuditAdapter = new ToAuditAdapter(R.layout.to_audit_item);
        recyclerView.setAdapter(toAuditAdapter);
    }

    private void initTopbar() {
        topBar.setTitle("我的待审任务");
        topBar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
        });
    }

    private void initData() {
        RetrofitClient.getInstance().getService().toAudit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ToAudit>(ToAuditActivity.this) {
                    @Override
                    public void onSuccess(ToAudit toAudit) {
                        if (toAudit != null) {
                            List<ToAudit.RowsBean> rows = toAudit.getRows();
                            if (rows != null && rows.size() > 0) {
                                toAuditAdapter.setNewData(rows);
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
        Intent starter = new Intent(context, ToAuditActivity.class);
        context.startActivity(starter);
    }
}
