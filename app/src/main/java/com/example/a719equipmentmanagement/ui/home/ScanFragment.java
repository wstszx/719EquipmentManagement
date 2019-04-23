package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends BaseFragment implements QRCodeView.Delegate {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.zxingview)
    ZXingView zxingview;

    @Override
    protected void init(Bundle savedInstanceState) {
        zxingview.setDelegate(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_scan;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        zxingview.startCamera();// 打开后置摄像头开始预览，但是并未开始识别
        zxingview.startSpotAndShowRect(); // 显示扫描框，并开始识别
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        zxingview.onDestroy(); // 销毁二维码扫描控件
        super.onDestroyView();
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked(View view) {
        Navigation.findNavController(view).navigateUp();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) Objects.requireNonNull(getActivity()).getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Objects.requireNonNull(getActivity()).setTitle("扫描结果为：" + result);
        vibrate();
        zxingview.startSpot(); // 开始识别
        Bundle bundle = new Bundle();
        bundle.putString("title", "扫描货柜码");
        Navigation.findNavController(zxingview).navigate(R.id.action_scanFragment_to_againScanFragment, bundle);
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
// 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        String tipText = zxingview.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                zxingview.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                zxingview.getScanBoxView().setTipText(tipText);
            }
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
