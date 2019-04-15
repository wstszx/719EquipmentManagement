package com.example.a719equipmentmanagement.ui.mine;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.databinding.FragmentMineBinding;
import com.example.a719equipmentmanagement.ui.home.HomeFragment;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

public class MineFragment extends BaseFragment<FragmentMineBinding> {


    private static MineFragment fragment;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        QMUIGroupListView groupListView = mbinding.groupListView;
        QMUICommonListItemView itemWithChevron = groupListView.createItemView("Item 4");
        itemWithChevron.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    public static MineFragment newInstance() {
        if (fragment == null) {
            fragment = new MineFragment();
        }
        return fragment;
    }

}
