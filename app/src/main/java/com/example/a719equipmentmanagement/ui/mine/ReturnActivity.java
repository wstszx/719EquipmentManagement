package com.example.a719equipmentmanagement.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.BorrowAdapter;
import com.example.a719equipmentmanagement.adapter.InventoryAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.ListMyAllBorrow;
import com.example.a719equipmentmanagement.entity.ListMyAllInventory;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReturnActivity extends BaseActivity {
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private BorrowAdapter adapter;
    private List<ListMyAllBorrow.RowsBean> rows;


//    private String[] date = new String[]{"2019年4月1日", "2019年3月1日", "2019年5月1日"};
//    private String[] details = new String[]{"温度计，型号参数，20100401", "压力表，型号参数，20100301", "传感器，型号参数，20100024"};
//    private String[] action = new String[]{"，归还", "，借用", "，归还"};

    @Override
    protected void init(Bundle savedInstanceState) {
        // int id = getIntent().getIntExtra("id", -1);
        initTopbar();
        initAdapter();
        initData();
    }

    private void initAdapter() {
        adapter = new BorrowAdapter(R.layout.base_borrow);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(ReturnActivity.this, DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(adapter);
    }

    private void initData() {
        RetrofitClient.getInstance().getService().getListMyAllBorrow()
                .compose(CommonCompose.io2main(ReturnActivity.this))
                .subscribe(new BaseSubscriber<ListMyAllBorrow>(ReturnActivity.this) {
                    @Override
                    public void onSuccess(ListMyAllBorrow datas) {
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
        return R.layout.activity_return;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ReturnActivity.class);
        //starter.putExtra("id", id);
        context.startActivity(starter);
    }


    private void initTopbar() {
        topbar.setTitle("借还记录");
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
//        View.OnClickListener onClickListener = v -> {};
//        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
//        for (int i = 0; i < date.length; i++) {
//            QMUICommonListItemView item = groupListView.createItemView(
//                    null,
//                    time +date[i],
//                    details[i]+action[i],
//                    QMUICommonListItemView.VERTICAL, 0);
//            section.addItemView(item, onClickListener);
//        }
//        section.addTo(groupListView);
//    }

}


