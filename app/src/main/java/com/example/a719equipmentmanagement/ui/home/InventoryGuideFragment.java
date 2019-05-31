package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.util.Objects;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryGuideFragment extends BaseFragment {

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private String[] containerAttrs = {"步骤：", "1.扫描货柜二维码", "2.逐一扫描该货柜上二维码", "3.该货柜设备扫描完毕，进行下一货柜扫描",
            "4.重复1、2、3,直到完成所有", "5.生成报告，保存"};

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initGroupListView();

    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {

        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(getContext());
        for (int i = 0; i < containerAttrs.length; i++) {
            QMUICommonListItemView item = groupListView.createItemView(
                    null,
                    containerAttrs[i],
                    null,
                    QMUICommonListItemView.HORIZONTAL,
                    QMUICommonListItemView.ACCESSORY_TYPE_NONE);
            item.setTag(i);
            section.addItemView(item, onClickListener);
        }
        section.addTo(groupListView);

    }

    private void initTopbar() {
        topbar.setTitle("盘点");
        Button button = topbar.addRightTextButton(R.string.carry_on, R.id.carry_on);
        button.setTextColor(getResources().getColor(R.color.blue));
        button.setOnClickListener(v -> Navigation.findNavController(topbar).navigate(R.id.action_inventoryGuideFragment2_to_scanFragment));
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            Objects.requireNonNull(getActivity()).finish();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_inventory_guide;
    }

}
