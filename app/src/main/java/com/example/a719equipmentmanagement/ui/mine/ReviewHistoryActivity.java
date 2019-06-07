package com.example.a719equipmentmanagement.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.BorrowHistoryAdapter;
import com.example.a719equipmentmanagement.adapter.ReviewHistoryAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.ReviewHistory;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewHistoryActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ReviewHistoryAdapter adapter;


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initAdapter();
        initData();
    }

    private void initData() {
        RetrofitClient.getInstance().getService().findReviewHistory()
                .compose(CommonCompose.io2main(ReviewHistoryActivity.this))
                .subscribe(new BaseSubscriber<ReviewHistory>(ReviewHistoryActivity.this) {
                    @Override
                    public void onSuccess(ReviewHistory reviewHistory) {
                        List<ReviewHistory.RowsBean> rows = reviewHistory.getRows();
                        if (rows != null && rows.size() > 0) {
                            adapter.setNewData(rows);
                        }
                    }
                });
    }

    private void initAdapter() {
        adapter = new ReviewHistoryAdapter(R.layout.return_item);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(adapter);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ReviewHistoryActivity.class);
        context.startActivity(starter);
    }

    private void initView() {
        initTopbar();
//        initGroupListView();
    }

    private void initTopbar() {
        topbar.setTitle("审核历史");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
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
