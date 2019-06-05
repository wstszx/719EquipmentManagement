package com.example.a719equipmentmanagement.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.HandleAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.HandleHistory;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class HandleHistoryActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private String[] date = new String[]{"2019年4月1日", "2019年3月1日", "2019年5月1日"};
    private String[] details = new String[]{"温度计，型号参数，20100401", "压力表，型号参数，20100301", "传感器，型号参数，20100024"};
    private String[] result = new String[]{"，点检合格", "，点检不合格，请用户申请报废", "，点检合格"};
    private HandleAdapter adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initAdapter();
        initData();
    }

    private void initAdapter() {
        adapter = new HandleAdapter(R.layout.return_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        RetrofitClient.getInstance().getService().getHandle()
                .compose(CommonCompose.io2main(HandleHistoryActivity.this))
                .subscribe(new BaseSubscriber<HandleHistory>(HandleHistoryActivity.this) {
                    @Override
                    public void onSuccess(HandleHistory histories) {
                        List<?> rows = histories.getRows();
                        if (rows != null && rows.size() > 0) {
//                            adapter.setNewData(histories);
                        }
                    }
                });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_checkon;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, HandleHistoryActivity.class);
        context.startActivity(starter);
    }

    private void initView() {
        initTopbar();
    }

    private void initTopbar() {
        topbar.setTitle("处理历史");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }
}
