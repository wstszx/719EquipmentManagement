package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenerateContainerCodeActivity extends BaseActivity {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_generate_container_code;
    }

    private void initTopbar() {
        topbar.setTitle("货柜码");
        topbar.addRightTextButton(R.string.print, R.id.print).setOnClickListener(v -> {

        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, GenerateContainerCodeActivity.class);
        context.startActivity(starter);
    }

}
