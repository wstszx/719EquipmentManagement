package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ChoiceDeptAdapter;
import com.example.a719equipmentmanagement.adapter.DeviceAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseSingleFilter;
import com.example.a719equipmentmanagement.entity.DeptOne;
import com.example.a719equipmentmanagement.entity.DeptThree;
import com.example.a719equipmentmanagement.entity.DeptTwo;
import com.example.a719equipmentmanagement.entity.DeviceData;
import com.example.a719equipmentmanagement.entity.PersonOne;
import com.example.a719equipmentmanagement.entity.PersonThree;
import com.example.a719equipmentmanagement.entity.PersonTwo;
import com.example.a719equipmentmanagement.entity.TreeData;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.device.DeviceDetailActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChoiceDeptActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ChoiceDeptAdapter adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initData() {
        RetrofitClient.getInstance().getService().getTreeData()
                .compose(CommonCompose.io2main(ChoiceDeptActivity.this))
                .subscribe(new BaseSubscriber<List<TreeData>>(ChoiceDeptActivity.this) {
                    @Override
                    public void onSuccess(List<TreeData> treeData) {
                        if (treeData != null && treeData.size() > 0) {
                            createSection(treeData);
                        }
                    }
                });
    }

    private void createSection(List<TreeData> treeData) {
        List<MultiItemEntity> list = new ArrayList<>();
        for (TreeData treeData1 : treeData) {
            int id = treeData1.getId();
            if (100 == id) {
                DeptOne deptOne = new DeptOne(treeData1);
                for (TreeData treeData2 : treeData) {
                    int pId2 = treeData2.getPId();
                    if (id == pId2) {
                        int id2 = treeData2.getId();

                        DeptTwo deptTwo = new DeptTwo(treeData2);
                        for (TreeData treeData3 : treeData) {
                            int pId3 = treeData3.getPId();
                            if (id2 == pId3) {
                                DeptThree deptThree = new DeptThree(treeData3);
                                deptTwo.addSubItem(deptThree);
                            }
                        }
                        deptOne.addSubItem(deptTwo);
                    }
                }
                list.add(deptOne);
            }
        }
        initAdapter(list);
    }

    private int mPosition = -1;

    private void initAdapter(List<MultiItemEntity> list) {
        adapter = new ChoiceDeptAdapter(this, list);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(adapter);

        for (int i = 0; i < adapter.getData().size(); i++) {
            adapter.expand(i, true);
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                singleChoice(position);
                view.setBackgroundResource(R.color.app_color_blue);
            }
        });
    }

    /**
     * 单选
     *
     * @param position
     */
    private void singleChoice(int position) {
        if (adapter.getData().get(position) instanceof DeptOne) {

        } else if (adapter.getData().get(position) instanceof DeptTwo) {

        } else if (adapter.getData().get(position) instanceof DeptThree) {

        }
//        if (mPosition == position) {
//            adapter.getData().get(position).setSelect(false);
//            mPosition = -1;
//        } else if (mPosition != -1) {
//            adapter.getData().get(mPosition).setSelect(false);
//            adapter.getData().get(position).setSelect(true);
//            mPosition = position;
//        } else {
//            adapter.getData().get(position).setSelect(true);
//            mPosition = position;
//        }

    }



    private void initTopbar() {
        topbar.setTitle("添加部门");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {

        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choice_dept;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ChoiceDeptActivity.class);
        context.startActivity(starter);
    }
}
