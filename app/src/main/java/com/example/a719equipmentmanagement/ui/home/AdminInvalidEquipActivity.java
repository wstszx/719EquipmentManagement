package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.AdminInvalidEquipAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.InvalidEquip;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

public class AdminInvalidEquipActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private AdminInvalidEquipAdapter adminInvalidEquipAdapter;

    public AdminInvalidEquipActivity() {
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        initTopBar();
        initDate();
    }

    private void initTopBar() {
        topBar.setTitle("即将过期设备");
        topBar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
        });
    }

    private void initDate() {
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminInvalidEquipActivity.this));
        adminInvalidEquipAdapter = new AdminInvalidEquipAdapter(R.layout.admin_invalid_equip_item);
        recyclerView.setAdapter(adminInvalidEquipAdapter);
        Intent intent = this.getIntent();
        List<InvalidEquip> invalidEquipList= (List<InvalidEquip>) intent.getSerializableExtra("serializable");
        adminInvalidEquipAdapter.setNewData(invalidEquipList);
        adminInvalidEquipAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                InvalidEquip invalidEquip = invalidEquipList.get(position);
                AdminInvalidItemActivity.start(AdminInvalidEquipActivity.this, invalidEquip);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_admin_invalid_equip;
    }

    public static void start(Context context, Serializable serializable) {
        Intent starter = new Intent(context, AdminInvalidEquipActivity.class);
        starter.putExtra("serializable", serializable);
        context.startActivity(starter);
    }
}
