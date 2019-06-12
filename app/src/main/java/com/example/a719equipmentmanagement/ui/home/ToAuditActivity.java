package com.example.a719equipmentmanagement.ui.home;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ToAuditAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.device.DeviceDetailActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.HashMap;
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
    private int equipId;
    private String dealer;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
        initData();
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(ToAuditActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        toAuditAdapter = new ToAuditAdapter(R.layout.to_audit_items);
        recyclerView.setAdapter(toAuditAdapter);
        toAuditAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            TextView textView = (TextView) view;
            String string = textView.getText().toString();
            ToAudit.RowsBean rowsBean = toAuditAdapter.getData().get(position);
            equipId = rowsBean.getEquipId();
            dealer = rowsBean.getDealer();
            switch (string) {
                case "通过":
                    operatingEquip(2, string);
                    break;
                case "不通过":
                    operatingEquip(1, string);
                    break;
            }
        });
    }

    private void operatingEquip(int operState, String string) {
        String nowString = TimeUtils.getNowString();
        HashMap<String, Object> map = new HashMap<>();
        map.put("equipId", equipId);
        map.put("operType", 4);
        map.put("msg", "");
        map.put("operState", operState);
        map.put("dealer", dealer);
        map.put("validDate", nowString);
        map.put("latestVerifyDate", nowString);
        RetrofitClient.getInstance().getService().operatingEquip(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(ToAuditActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        int code = baseResponse.getCode();
                        if (code == 0) {
                            initData();
                            ToastUtils.showShort(string + "成功");
                        }
                    }
                });
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
