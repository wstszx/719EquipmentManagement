package com.example.a719equipmentmanagement.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.Objects;

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
        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.scanFragment);
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> Objects.requireNonNull(getActivity()).finish());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inventory_task;
    }

    @OnClick(R.id.constraint)
    public void onViewClicked(View view) {
        Navigation.findNavController(view).navigate(R.id.newInventoryTaskFragment);
//        startActivityForResult(new Intent(AccountingListActivity.this, AccountingActivity.class), ACCOUNTING);
    }
}
