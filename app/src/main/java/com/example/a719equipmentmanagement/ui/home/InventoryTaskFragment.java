package com.example.a719equipmentmanagement.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InventoryTaskFragment extends BaseFragment {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.constraint)
    ConstraintLayout constraint;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
    }

    private void initTopbar() {
        topbar.setTitle("盘点任务");
//        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
//
//        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> Navigation.findNavController(v).navigateUp());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inventory_task;
    }

    @OnClick(R.id.constraint)
    public void onViewClicked() {
//        startActivityForResult(new Intent(AccountingListActivity.this, AccountingActivity.class), ACCOUNTING);
    }
}
