package com.example.a719equipmentmanagement.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.InventoryAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.InventoryData;
import com.example.a719equipmentmanagement.entity.InventoryHistory;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;


import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class InventoryHistoryActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private List<InventoryData> body;
    private InventoryAdapter adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initAdapter();
        initData();
    }

    private void initAdapter() {
        adapter = new InventoryAdapter(R.layout.return_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        RetrofitClient.getInstance().getService().findInventoryData()
                .compose(CommonCompose.io2main(InventoryHistoryActivity.this))
                .subscribe(new BaseSubscriber<InventoryHistory>(InventoryHistoryActivity.this) {
                    @Override
                    public void onSuccess(InventoryHistory inventoryHistory) {
                        List<InventoryHistory.RowsBean> rows = inventoryHistory.getRows();
                        if (rows != null && rows.size() > 0) {
                            adapter.setNewData(rows);
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_checkcount;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, InventoryHistoryActivity.class);
        context.startActivity(starter);
    }

    private void initView() {
        initTopbar();
    }

    private void initTopbar() {
        topbar.setTitle("盘点历史");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

//    private void initGroupListView() {
//        String time = "时间";
//        String result = "盘点结果：";
//        View.OnClickListener onClickListener = v -> {
//        };
//        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
//            for (int i = 0; i < body.size(); i++) {
//                QMUICommonListItemView item = groupListView.createItemView(
//                        null,
//                        time + body.get(i).getCreateTime(),
//                        result+(body.get(i).getState()==0?"盘点完成":"盘点未完成"),
//                        QMUICommonListItemView.VERTICAL, 0);
//                section.addItemView(item, onClickListener);
//            }
//        section.addTo(groupListView);
//    }
}
