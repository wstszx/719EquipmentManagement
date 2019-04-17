package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ContainerManageAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.Container;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 货柜管理
 */
public class ContainerManageActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String[] containerArray = {"货柜1", "货柜2", "货柜3", "货柜4", "货柜5", "货柜6", "货柜7"};
    private List<Container> containers = new ArrayList<>();

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        initAdapter();
    }

    private void initData() {
        for (String containerStr : containerArray) {
            Container container = new Container();
            container.setName(containerStr);
            containers.add(container);
        }
    }

    private void initAdapter() {
        ContainerManageAdapter adapter = new ContainerManageAdapter(R.layout.base_item1, containers);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ContainerDetailActivity.start(ContainerManageActivity.this);
            }
        });
    }

    private void initView() {
        topbar.setTitle("货柜管理");
        topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_container_manage;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ContainerManageActivity.class);
        context.startActivity(starter);
    }
}
