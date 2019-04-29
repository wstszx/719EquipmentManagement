package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckActivity extends BaseActivity {

    @Override
    protected void init(Bundle savedInstanceState) {
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_check;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CheckActivity.class);
        context.startActivity(starter);
    }

}
