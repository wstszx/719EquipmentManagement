package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmScanFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        Bundle arguments = getArguments();
        if (arguments != null) {
            String title = arguments.getString("title");
            tvName.setText(TextUtils.isEmpty(title) ? "" : title);
        }
    }

    private void initView() {
        topbar.setTitle("扫描货柜码");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_confirm_scan;
    }

    @OnClick({R.id.button1, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Navigation.findNavController(view).navigateUp();
                break;
            case R.id.button2:
                Navigation.findNavController(view).navigate(R.id.action_confirmScanFragment_to_scanFragment);
                break;
        }
    }
}