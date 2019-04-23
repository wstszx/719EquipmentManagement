package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends BaseFragment {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.zxingview)
    ZXingView zxingview;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_scan;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        new NavController(Objects.requireNonNull(getActivity())).popBackStack();
    }
}
