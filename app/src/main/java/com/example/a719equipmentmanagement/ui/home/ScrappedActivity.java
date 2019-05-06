package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrappedActivity extends BaseActivity {


    @BindView(R.id.topbar)
    QMUITopBar topbar;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
    }

    private void initTopbar() {
        topbar.setTitle("报废");
        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_scrapped;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ScrappedActivity.class);
        context.startActivity(starter);
    }

}
