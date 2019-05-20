package com.example.a719equipmentmanagement.ui.home;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleScanAndConnectCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class GenarateQRActivity extends BaseActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    private final String mAddress = "DC:0D:30:3C:C1:93";
    @BindView(R.id.emptyView)
    QMUIEmptyView emptyView;

    @Override
    protected void init(Bundle savedInstanceState) {
        initBottomMenu();
        initTopbar();
        createChineseQRCode();
    }

    private void initBottomMenu() {

    }


    /**
     * 初始化蓝牙
     */
    private void initBle() {
        if (BleManager.getInstance().isSupportBle()) {
            if (!BleManager.getInstance().isBlueEnable()) {
                BleManager.getInstance().enableBluetooth();
            }
            initScanRule();
//            scanBlueDevice();
            connectBlueDevice();
        } else {
            Toast.makeText(GenarateQRActivity.this, "您的设备不支持蓝牙", Toast.LENGTH_SHORT).show();
        }
    }

    private void connectBlueDevice() {
        BleManager.getInstance().connect(mAddress, new BleGattCallback() {
            @Override
            public void onStartConnect() {
                emptyView.show(true);
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {
                Toast.makeText(GenarateQRActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                emptyView.show(false);
                Toast.makeText(GenarateQRActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice device, BluetoothGatt gatt, int status) {
                emptyView.show(false);
            }
        });
    }

    private void initTopbar() {
        topbar.setTitle("二维码");
        topbar.addRightTextButton(R.string.print, R.id.print).setOnClickListener(v -> {
            initBle();
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void initScanRule() {
        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
//                .setServiceUuids(serviceUuids)      // 只扫描指定的服务的设备，可选
//                .setDeviceName(true, names)         // 只扫描指定广播名的设备，可选
//                .setDeviceMac(mac)                  // 只扫描指定mac的设备，可选
                .setAutoConnect(true)      // 连接时的autoConnect参数，可选，默认false
                .setScanTimeOut(10000)              // 扫描超时时间，可选，默认10秒；小于等于0表示不限制扫描时间
                .build();
        BleManager.getInstance().initScanRule(scanRuleConfig);
    }

    /**
     * 扫描蓝牙设备
     */
    private void scanBlueDevice() {
        BleManager.getInstance().scan(new BleScanCallback() {
            @Override
            public void onScanStarted(boolean success) {
//                emptyView.setVisibility(View.VISIBLE);
                emptyView.show(true);
                // 开始扫描（主线程）
                LogUtils.i("开始扫描" + success);
            }

            @Override
            public void onScanning(BleDevice bleDevice) {
                // 扫描到一个符合扫描规则的BLE设备（主线程）
                BluetoothDevice device = bleDevice.getDevice();
                String address = device.getAddress();
                LogUtils.i("扫描到一个符合扫描规则的BLE设备" + bleDevice.getName());
            }

            @Override
            public void onScanFinished(List<BleDevice> scanResultList) {
//                emptyView.setVisibility(View.GONE);
                emptyView.show(false);
                // 扫描结束，列出所有扫描到的符合扫描规则的BLE设备（主线程）
                if (scanResultList != null && scanResultList.size() > 0) {
                    List<BleDevice> bleDevices = scanResultList.subList(0, 3);
                    showSimpleBottomSheetList(bleDevices);
//                    for (BleDevice bleDevice : scanResultList) {
//                        LogUtils.i("扫描结束，列出所有扫描到的符合扫描规则的BLE设备（主线程）" + bleDevice.getName());
//                    }
                }

            }
        });
    }

    private void showSimpleBottomSheetList(List<BleDevice> bleDevices) {
        new QMUIBottomSheet.BottomListSheetBuilder(this)
                .addItem(bleDevices.get(0).getName() != null ? bleDevices.get(0).getName() : "未知")
                .addItem(bleDevices.get(1).getName() != null ? bleDevices.get(1).getName() : "未知")
                .addItem(bleDevices.get(2).getName() != null ? bleDevices.get(2).getName() : "未知")
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        BleDevice bleDevice = bleDevices.get(position);
                    }
                })
                .build()
                .show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_genarate_qr;
    }

    private void createChineseQRCode() {
        new MyTask(GenarateQRActivity.this, imageView).execute();

    }

    static class MyTask extends AsyncTask<Void, Void, Bitmap> {
        // 弱引用允许Activity被垃圾收集器清理
        private final WeakReference<GenarateQRActivity> weakActivity;
        private final WeakReference<ImageView> weakimageview;

        MyTask(GenarateQRActivity myActivity, ImageView imageView) {
            this.weakActivity = new WeakReference<>(myActivity);
            this.weakimageview = new WeakReference<>(imageView);
        }

        @Override
        public Bitmap doInBackground(Void... params) {
            return QRCodeEncoder.syncEncodeQRCode("生成的", BGAQRCodeUtil.dp2px(weakActivity.get().getApplicationContext(), 150));
        }

        @Override
        public void onPostExecute(Bitmap bitmap) {
            // 重新获取Actiity的强引用，并且判断是否存活
            GenarateQRActivity activity = weakActivity.get();
            if (activity == null
                    || activity.isFinishing()
                    || activity.isDestroyed()) {
                // activity死亡了，不再做任何的事情
                return;
            }
            if (bitmap != null) {
                weakimageview.get().setImageBitmap(bitmap);
            } else {
                Toast.makeText(weakActivity.get().getApplicationContext(), "生成二维码失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, GenarateQRActivity.class);
        context.startActivity(starter);
    }
}
