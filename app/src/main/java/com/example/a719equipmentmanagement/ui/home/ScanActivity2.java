package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.device.DeviceDetailActivity;
import com.example.a719equipmentmanagement.view.OperaDialog;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ScanActivity2 extends BaseActivity implements QRCodeView.Delegate {
    private static final String TAG = ScanActivity2.class.getSimpleName();
    @BindView(R.id.zbarview)
    ZBarView zbarview;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private String id;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    private int equipId;
    private String responsor;
    private String date;
    private String date1;


    @Override
    protected void init(Bundle savedInstanceState) {
        Intent intent = getIntent();
        equipId = intent.getIntExtra("equipId", 0);
        responsor = intent.getStringExtra("responsor");
        date = intent.getStringExtra("date");
        date1 = intent.getStringExtra("date1");
        zbarview.setDelegate(this);
    }

    @Override
    protected void onStart() {
        zbarview.startCamera();// 打开后置摄像头开始预览，但是并未开始识别
        zbarview.startSpotAndShowRect(); // 显示扫描框，并开始识别
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        zbarview.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan;
    }

    public static void start(Context context, int equipId, String responsor, String date, String date1) {
        Intent starter = new Intent(context, ScanActivity2.class);
        starter.putExtra("equipId", equipId);
        starter.putExtra("responsor", responsor);
        starter.putExtra("date", date);
        starter.putExtra("date1", date1);
        context.startActivity(starter);
    }


    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    private boolean isEquipScaned;
    private boolean isContainerScaned;

    @Override
    public void onScanQRCodeSuccess(String result) {
//        setTitle("扫描结果为：" + result);
        vibrate();
//        zbarview.startSpot(); // 开始识别
        if (!StringUtils.isEmpty(result)) {
            String[] split = result.split("\\|");
            if (split.length > 0) {
                String no = split[0];
                String id = split[1];
//                if (StringUtils.equals("E", no)) {
//                    id = split[1];
//                    Log.d(TAG, "onScanQRCodeSuccess: E==" + id);
//                    isEquipScaned = true;
//                } else if (StringUtils.equals("C", no)) {
//                    isContainerScaned = true;
//                }
//                if (!isEquipScaned) {
//                    zbarview.startSpot();
//                    ToastUtils.showShort("请继续扫描设备二维码");
//                    return;
//                }
//                if (!isContainerScaned) {
//                    zbarview.startSpot();
//                    ToastUtils.showShort("请继续扫描货柜二维码");
//                    return;
//                }
//                DeviceDetailActivity.start(this, id);
//                finish();
                if (StringUtils.equals("C", no)) {
                    showOperaDialog(2, "归还");
                } else {
                    zbarview.startSpot();
                    ToastUtils.showShort("请扫描货柜二维码");
                }
            }
//            else if (StringUtils.equals("C", no)) {
//                ContainerDetailActivity.start(this, userId);
//            }

        }
    }

    private void showOperaDialog(int operType, String s) {
        OperaDialog operaDialog = new OperaDialog(this);

        operaDialog.setTitle(s)
                .setPlaceholder("备注")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("确定", (dialog, index) -> {
                    dialog.dismiss();
                    String msg = operaDialog.getEditText().getText().toString();
                    operatingEquip(operType, s, msg);
                })
                .create(mCurrentDialogStyle).show();
    }

    /**
     * 操作设备
     *
     * @param operType
     * @param title
     */
    private void operatingEquip(int operType, String title, String msg) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("equipId", equipId);
        map.put("operType", operType);
        map.put("msg", StringUtils.isEmpty(msg) ? "" : msg);
        map.put("dealer", responsor);
        RetrofitClient.getInstance().getService().operatingEquip(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(ScanActivity2.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        int code = baseResponse.getCode();
                        if (code == 0) {
                            ResultActivity.start(ScanActivity2.this, title + "成功");
                        }
                    }
                });
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
// 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        String tipText = zbarview.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                zbarview.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                zbarview.getScanBoxView().setTipText(tipText);
            }
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked(View view) {
        if (view.getId() == R.id.iv_back) {
            finish();
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        zbarview.startSpotAndShowRect(); // 显示扫描框，并开始识别
//
//        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
//            final String picturePath = BGAPhotoPickerActivity.getSelectedPhotos(data).get(0);
//            // 本来就用到 QRCodeView 时可直接调 QRCodeView 的方法，走通用的回调
//            zbarview.decodeQRCode(picturePath);
//
//        }
//    }
}
