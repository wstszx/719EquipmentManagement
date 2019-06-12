package com.example.a719equipmentmanagement.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ChoiceDeptAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.DeptList;
import com.example.a719equipmentmanagement.entity.PersonOne;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChoiceDeptActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String name;
    private int pid;
    private int deptId;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initData() {
        RetrofitClient.getInstance().getService().getDeptList()
                .compose(CommonCompose.io2main(ChoiceDeptActivity.this))
                .subscribe(new BaseSubscriber<List<DeptList>>(ChoiceDeptActivity.this) {
                    @Override
                    public void onSuccess(List<DeptList> deptLists) {
                        if (deptLists != null && deptLists.size() > 0) {
                            createSection(deptLists);
                        }
                    }
                });
//        RetrofitClient.getInstance().getService().getTreeData()
//                .compose(CommonCompose.io2main(ChoiceDeptActivity.this))
//                .subscribe(new BaseSubscriber<List<TreeData>>(ChoiceDeptActivity.this) {
//                    @Override
//                    public void onSuccess(List<TreeData> treeData) {
//                        if (treeData != null && treeData.size() > 0) {
//                            createSection(treeData);
//                        }
//                    }
//                });
    }

    private void createSection(List<DeptList> deptLists) {
        List<MultiItemEntity> list = new ArrayList<>();
        for (DeptList deptList : deptLists) {
            PersonOne personOne = new PersonOne(deptList);
            list.add(personOne);
        }
        initAdapter(list);
    }

    private int mPosition = -1;

    private void initAdapter(List<MultiItemEntity> list) {
        ChoiceDeptAdapter adapter = new ChoiceDeptAdapter(this, list);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(adapter);

//        for (int i = 0; i < adapter.getData().size(); i++) {
//            adapter.expand(i, true);
//        }
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            MultiItemEntity multiItemEntity = (MultiItemEntity) adapter1.getData().get(position);
            int itemType = multiItemEntity.getItemType();
            if (itemType == 0) {
                PersonOne deptOne = (PersonOne) multiItemEntity;
                name = deptOne.getDeptList().getDeptName();
                pid = deptOne.getDeptList().getParentId();
                deptId = deptOne.getDeptList().getDeptId();
            }
            setChoice(position, view);
        });
    }

    private SparseArray choiceArray = new SparseArray();

    private void setChoice(int position, View view) {
        if (mPosition == position) {
            view.setBackgroundResource(R.color.white);
            mPosition = -1;
            choiceArray.delete(mPosition);
        } else if (mPosition != -1) {
            View mView = (View) choiceArray.get(mPosition);
            mView.setBackgroundResource(R.color.white);
            view.setBackgroundResource(R.color.app_color_blue);
            choiceArray.put(position, view);
            mPosition = position;
        } else {
            view.setBackgroundResource(R.color.app_color_blue);
            choiceArray.put(position, view);
            mPosition = position;
        }
    }

    /**
     * 单选
     *
     * @param position
     */
    private void singleChoice(int position) {

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
        topbar.setTitle("选择部门");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("pid", pid);
            intent.putExtra("name", name);
            intent.putExtra("deptId", deptId);
            setResult(RESULT_OK, intent);
            finish();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choice_dept;
    }

//    public static void start(Context context) {
//        Intent starter = new Intent(context, ChoiceDeptActivity.class);
//        context.startActivity(starter);
//    }

}
