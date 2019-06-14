package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.a719equipmentmanagement.MainActivity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends BaseFragment {

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
        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("title");
            tvResult.setText(title);
        }
    }

    private void initTopbar() {
        topbar.setTitle("结果页");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            MainActivity.start(getContext());
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_result;
    }

}
