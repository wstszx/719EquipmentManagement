package com.example.a719equipmentmanagement.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a719equipmentmanagement.MainActivity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;

public class ResultActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.tv_result)
    TextView tvResult;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            tvResult.setText(title);
        }
    }

    private void initTopbar() {
        topbar.setTitle("结果页");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            MainActivity.start(this);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_result;
    }

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, ResultActivity.class);
        starter.putExtra("title", title);
        context.startActivity(starter);
    }
}
