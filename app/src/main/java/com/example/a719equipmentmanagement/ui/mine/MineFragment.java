package com.example.a719equipmentmanagement.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseEditActivity;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.ui.home.GenarateQRActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;

public class MineFragment extends BaseFragment {


    private static MineFragment fragment;
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private QMUICommonListItemView listItemView;
    private String[] containerAttrs = {"借还记录", "盘点记录", "送检记录", "报废记录", "退出登录"};

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initGroupListView();
    }

    private void initTopbar() {
        topbar.setTitle("我的");
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
//            listItemView = (QMUICommonListItemView) v;
//            int tag = (int) listItemView.getTag();
//            Intent intent = new Intent();
//            intent.putExtra("text", listItemView.getDetailText().toString());
//            intent.setClass(this, BaseEditActivity.class);
//            startActivityForResult(intent, tag);
        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(getContext());
        for (int i = 0; i < containerAttrs.length; i++) {
            QMUICommonListItemView item = groupListView.createItemView(
                    null,
                    containerAttrs[i],
                    null,
                    QMUICommonListItemView.HORIZONTAL,
                    QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
            item.setTag(i);
            section.addItemView(item, onClickListener);
        }
        section.addTo(groupListView);
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
