package com.example.a719equipmentmanagement.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ApplyHistoryAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.ApplyHistory;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import butterknife.BindView;

public class ApplyHistoryActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private String[] date = new String[]{"2019年4月1日", "2019年3月1日", "2019年5月1日"};
    private String[] details = new String[]{"温度计，型号参数，20100401", "压力表，型号参数，20100301", "传感器，型号参数，20100024"};
    private ApplyHistoryAdapter adapter;


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initAdapter();
        initData();
    }

    private void initData() {
        RetrofitClient.getInstance().getService().findUserApplyHistory()
                .compose(CommonCompose.io2main(ApplyHistoryActivity.this))
                .subscribe(new BaseSubscriber<List<ApplyHistory>>(ApplyHistoryActivity.this) {
                    @Override
                    public void onSuccess(List<ApplyHistory> applyHistory) {
                        if (applyHistory != null && applyHistory.size() > 0) {
                            adapter.setNewData(applyHistory);
                        }
                    }
                });
    }

    private void initAdapter() {
        adapter = new ApplyHistoryAdapter(R.layout.return_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
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
//        initGroupListView();
    }

    private void initTopbar() {
        topbar.setTitle("申请历史");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

//    private void initGroupListView() {
//        String time = "时间";
//        String result = "，已完成报废";
//        View.OnClickListener onClickListener = v -> {
//        };
//        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
//        for (int i = 0; i < date.length; i++) {
//            QMUICommonListItemView item = groupListView.createItemView(
//                    null,
//                    time + date[i],
//                    details[i] + result,
//                    QMUICommonListItemView.VERTICAL, 0);
//            section.addItemView(item, onClickListener);
//        }
//        section.addTo(groupListView);
//    }

}
