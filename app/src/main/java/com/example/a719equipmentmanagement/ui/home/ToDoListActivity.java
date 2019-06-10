package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ToAuditAdapter;
import com.example.a719equipmentmanagement.adapter.ToDoListAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.example.a719equipmentmanagement.entity.ToDo;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ToDoListActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private ToDoListAdapter toDoListAdapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
        initData();
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(ToDoListActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        toDoListAdapter = new ToDoListAdapter(R.layout.to_audit_item);
        recyclerView.setAdapter(toDoListAdapter);
    }

    private void initTopbar() {
        topBar.setTitle("我的待办事项");
        topBar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
        });
    }

    private void initData() {
        RetrofitClient.getInstance().getService().toDo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ToDo>(ToDoListActivity.this) {
                    @Override
                    public void onSuccess(ToDo toDo) {
                        if (toDo != null) {
//                            List<ToDo.RowsBean> rows = toAudit.getRows();
//                            if (rows != null && rows.size() > 0) {
//                                toAuditAdapter.setNewData(rows);
//                            }
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_to_audit;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ToDoListActivity.class);
        context.startActivity(starter);
    }
}
