package com.example.a719equipmentmanagement.ui.home;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.ui.device.DeviceDetailActivity;

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
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void init(Bundle savedInstanceState) {
        zxingview.setDelegate(this);
        initView();
        closeKeybord(Objects.requireNonNull(getActivity()));
    }

    /**
     * 自动关闭软键盘
     * @param activity
     */
    public static void closeKeybord(Activity activity) {
        InputMethodManager imm =  (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    private void initView() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String title = arguments.getString("title");
            tvTitle.setText(TextUtils.isEmpty(title) ? "" : title);
        }
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
//        zxingview.startSpot(); // 开始识别
        if (!StringUtils.isEmpty(result)) {
            String[] split = result.split("\\|");
            String no = split[0];
            String id = split[1];
            if (StringUtils.equals("E", no)) {
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                Navigation.findNavController(Objects.requireNonNull(getView())).navigate(R.id.deviceDetailFragment, bundle);
            } else if (StringUtils.equals("C", no)) {
                ToastUtils.showShort("这是货柜码");
            }
//            else if (StringUtils.equals("C", no)) {
//                ContainerDetailActivity.start(this, userId);
//            }

        }
//        Bundle bundle = new Bundle();
//        bundle.putString("result", "当前货柜：" + result);
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
