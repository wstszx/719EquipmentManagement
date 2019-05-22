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
import com.example.a719equipmentmanagement.entity.ListMyAllInventory;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.a719equipmentmanagement.App.getContext;

public class CheckcountActivity extends BaseActivity {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<ListMyAllInventory.RowsBean> rows;
    private InventoryAdapter adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
        initData();
}

    private void initAdapter() {
        adapter = new InventoryAdapter(R.layout.base_inventory);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(CheckcountActivity.this, DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(adapter);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                DeviceData.RowsBean currentItemData=rows.get(position);
//                DeviceDetailActivity.start(getContext(), currentItemData);
//            }
//        });
    }

    private void initData() {
        RetrofitClient.getInstance().getService().getListMyAllInventory()
                .compose(CommonCompose.io2main(CheckcountActivity.this))
                .subscribe(new BaseSubscriber<ListMyAllInventory>(CheckcountActivity.this) {
                    @Override
                    public void onSuccess(ListMyAllInventory datas) {
                        if (datas != null) {
                            rows = datas.getRows();
                            if (rows != null && rows.size() > 0) {
                                adapter.setNewData(rows);
                            }
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_checkcount;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CheckcountActivity.class);
        context.startActivity(starter);
    }


    private void initTopbar() {
        topbar.setTitle("盘点记录");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
//                        time + body.get(i).getRows().get(0).getCreateTime(),
//                        result+(body.get(i).getRows().get(0).getState()==0?"盘点完成":"盘点未完成"),
//                        QMUICommonListItemView.VERTICAL, 0);
//                section.addItemView(item, onClickListener);
//            }
//        section.addTo(groupListView);
//    }
}
