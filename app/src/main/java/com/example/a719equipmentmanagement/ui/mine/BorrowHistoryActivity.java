package com.example.a719equipmentmanagement.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.BorrowHistoryAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BorrowHistory;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import butterknife.BindView;

public class BorrowHistoryActivity extends BaseActivity {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    private String[] date = new String[]{"2019年4月1日", "2019年3月1日", "2019年5月1日"};
    private String[] details = new String[]{"温度计，型号参数，20100401", "压力表，型号参数，20100301", "传感器，型号参数，20100024"};
    private String[] action = new String[]{"，归还", "，借用", "，归还"};
    private BorrowHistoryAdapter adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
        initData();
    }

    private void initAdapter() {
        adapter = new BorrowHistoryAdapter(R.layout.return_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        RetrofitClient.getInstance().getService().getBorrowHistory()
                .compose(CommonCompose.io2main(BorrowHistoryActivity.this))
                .subscribe(new BaseSubscriber<BorrowHistory>(BorrowHistoryActivity.this){
                    @Override
                    public void onSuccess(BorrowHistory borrowHistory) {
                        if (borrowHistory != null) {
                            List<BorrowHistory.RowsBean> rows = borrowHistory.getRows();
                            adapter.setNewData(rows);
                        }
                    }
                });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_return;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, BorrowHistoryActivity.class);
        context.startActivity(starter);
    }


    private void initTopbar() {
        topbar.setTitle("借用历史");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
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


