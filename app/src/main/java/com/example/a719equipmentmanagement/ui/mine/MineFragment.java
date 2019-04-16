package com.example.a719equipmentmanagement.ui.mine;


import android.os.Bundle;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;

public class MineFragment extends BaseFragment {


    private static MineFragment fragment;
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
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
