package com.example.a719equipmentmanagement.ui.home;

import android.os.Bundle;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmBorrowResultsActivity extends BaseActivity {

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
    }

    private void initTopbar() {
        topbar.setTitle("借用详情");
        topbar.addRightImageButton(R.mipmap.qrcode, R.id.qrcode).setOnClickListener(v -> {

        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirm_borrow_results;
    }
}
