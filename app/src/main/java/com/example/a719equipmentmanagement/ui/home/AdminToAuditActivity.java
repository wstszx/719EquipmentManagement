package com.example.a719equipmentmanagement.ui.home;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ToAuditAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

public class AdminToAuditActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initTopbar() {
        topBar.setTitle("待审事项列表");
        topBar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
        });
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminToAuditActivity.this));
        ToAuditAdapter toAuditAdapter = new ToAuditAdapter(R.layout.admin_to_audit_item);
        recyclerView.setAdapter(toAuditAdapter);
        Intent intent = this.getIntent();

//        List<ToAudit> toAuditList= (List<ToAudit>) intent.getSerializableExtra("serializable");
//        toAuditAdapter.setNewData(toAuditList);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_admin_to_audit;
    }
    public static void start(Context context, Serializable serializable) {
        Intent starter = new Intent(context, AdminToAuditActivity.class);
        starter.putExtra("serializable", serializable);
        context.startActivity(starter);
    }
}
