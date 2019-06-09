package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.widget.EditText;

import androidx.navigation.Navigation;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;

/**
 * 新建盘点任务
 */
public class NewInventoryTaskFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.edittext)
    EditText edittext;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
    }

    private void initTopbar() {
        topbar.setTitle("新建盘点任务");
        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
            newInventoryTask();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> Navigation.findNavController(v).navigateUp());
    }

    private void newInventoryTask() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_inventory_task;
    }

}
