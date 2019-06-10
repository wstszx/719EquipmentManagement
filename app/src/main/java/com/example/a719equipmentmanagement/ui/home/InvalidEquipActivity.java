package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.InvalidEquipAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.InvalidEquip;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InvalidEquipActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private InvalidEquipAdapter invalidEquipAdapter;

    public InvalidEquipActivity() {
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopBar();
        initAdapter();
        initDate();
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(InvalidEquipActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        invalidEquipAdapter = new InvalidEquipAdapter(R.layout.invalid_equip_item);
        recyclerView.setAdapter(invalidEquipAdapter);
    }

    private void initTopBar() {
        topBar.setTitle("即将过期设备");
        topBar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
        });
    }

    private void initDate() {
        RetrofitClient.getInstance().getService().invalidEquip()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<InvalidEquip>>(this) {
                    @Override
                    public void onSuccess(List<InvalidEquip> invalidEquips) {
                        if (invalidEquips != null && invalidEquips.size() > 0) {
                            invalidEquipAdapter.setNewData(invalidEquips);
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
