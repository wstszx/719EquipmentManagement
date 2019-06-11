package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ApplyProgressAdapter;
import com.example.a719equipmentmanagement.adapter.ToReturnAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.ToReturn;
import com.example.a719equipmentmanagement.entity.UserToAudit;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApplyProgressActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private ApplyProgressAdapter adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
        initData();
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(ApplyProgressActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new ApplyProgressAdapter(R.layout.to_audit_item);
        recyclerView.setAdapter(adapter);
    }

    private void initTopbar() {
        topBar.setTitle("我的待还设备");
        topBar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
        });
    }

    private void initData() {
        RetrofitClient.getInstance().getService().userToAudit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<UserToAudit>(ApplyProgressActivity.this) {
                    @Override
                    public void onSuccess(UserToAudit userToAudit) {
                        if (userToAudit != null) {
                            List<UserToAudit.RowsBean> rows = userToAudit.getRows();
                            if (rows != null && rows.size() > 0) {
                                adapter.setNewData(rows);
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
