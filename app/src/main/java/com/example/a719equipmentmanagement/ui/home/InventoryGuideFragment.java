package com.example.a719equipmentmanagement.ui.home;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
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

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
    }

    private void initTopbar() {
        topbar.setTitle("货柜详情");
        topbar.addRightImageButton(R.mipmap.qrcode, R.id.qrcode).setOnClickListener(v -> {
            Navigation.findNavController(topbar).navigate(R.id.action_inventoryGuideFragment2_to_scanFragment);
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            new NavController(Objects.requireNonNull(getContext())).popBackStack();
//            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_inventory_guide;
    }

}
