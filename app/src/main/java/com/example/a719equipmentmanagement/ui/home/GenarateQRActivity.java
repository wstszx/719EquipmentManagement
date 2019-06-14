package com.example.a719equipmentmanagement.ui.home;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.example.a719equipmentmanagement.MainActivity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.utils.DeviceConnFactoryManager;
import com.example.a719equipmentmanagement.utils.PrinterCommand;
import com.example.a719equipmentmanagement.utils.ThreadPool;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.tools.command.EscCommand;
import com.tools.command.LabelCommand;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;

import static android.hardware.usb.UsbManager.ACTION_USB_DEVICE_ATTACHED;
import static android.hardware.usb.UsbManager.ACTION_USB_DEVICE_DETACHED;
import static com.example.a719equipmentmanagement.utils.DeviceConnFactoryManager.ACTION_QUERY_PRINTER_STATE;
import static com.example.a719equipmentmanagement.utils.DeviceConnFactoryManager.CONN_STATE_FAILED;

public class GenarateQRActivity extends BaseActivity {

    private static final int BLUETOOTH_REQUEST_CODE = 1;
    /**
     * 连接状态断开
     */
    private static final int CONN_STATE_DISCONN = 0x007;
    /**
     * 使用打印机指令错误
     */
    private static final int PRINTER_COMMAND_ERROR = 0x008;
    private static final int CONN_PRINTER = 0x12;
    public static final int MESSAGE_UPDATE_PARAMETER = 0x009;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.emptyView)
    QMUIEmptyView emptyView;
    @BindView(R.id.bt_connect)
    QMUIRoundButton btConnect;
    @BindView(R.id.bt_print)
    QMUIRoundButton btPrint;
    @BindView(R.id.tv_connect_status)
    TextView tv_connect_status;
    private ThreadPool threadPool;
    private int id = 0;
    private int containerId;
    ArrayList<String> per = new ArrayList<>();
    private String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.BLUETOOTH
    };
    private static final int REQUEST_CODE = 0x004;
    private ArrayList<Integer> ids;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        checkPermission();
        requestPermission();
        initData();
//        createChineseQRCode();
    }

    private void checkPermission() {
        for (String permission : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, permission)) {
                per.add(permission);
            }
        }
    }


    private void requestPermission() {
        if (per.size() > 0) {
            String[] p = new String[per.size()];
            ActivityCompat.requestPermissions(this, per.toArray(p), REQUEST_CODE);
        }
    }

    private void initData() {
        Intent intent = getIntent();
        ids = intent.getIntegerArrayListExtra("id");
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_QUERY_PRINTER_STATE);
        filter.addAction(DeviceConnFactoryManager.ACTION_CONN_STATE);
        registerReceiver(receiver, filter);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                if (DeviceConnFactoryManager.ACTION_CONN_STATE.equals(action)) {
                    int state = intent.getIntExtra(DeviceConnFactoryManager.STATE, -1);
                    int deviceId = intent.getIntExtra(DeviceConnFactoryManager.DEVICE_ID, -1);
                    switch (state) {
                        case DeviceConnFactoryManager.CONN_STATE_DISCONNECT:
                            if (id == deviceId) {
                                tv_connect_status.setText(getString(R.string.str_conn_state_disconnect));
                            }
                            break;
                        case DeviceConnFactoryManager.CONN_STATE_CONNECTING:
                            tv_connect_status.setText(getString(R.string.str_conn_state_connecting));
                            break;
                        case DeviceConnFactoryManager.CONN_STATE_CONNECTED:
                            tv_connect_status.setText(getString(R.string.str_conn_state_connected) + "\n" + getConnDeviceInfo());
                            /*
                             *                            if(DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].connMethod== DeviceConnFactoryManager.CONN_METHOD.WIFI){
                             *                                wificonn=true;
                             *                                if(keepConn==null) {
                             *                                    keepConn = new KeepConn();
                             *                                    keepConn.start();
                             *                                }
                             *                            }
                             */
                            break;
                        case CONN_STATE_FAILED:
                            /* wificonn=false; */
                            tv_connect_status.setText(getString(R.string.str_conn_state_disconnect));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    };

    private String getConnDeviceInfo() {
        String str = "";
        DeviceConnFactoryManager deviceConnFactoryManager = DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id];
        if (deviceConnFactoryManager != null
                && deviceConnFactoryManager.getConnState()) {
            switch (deviceConnFactoryManager.getConnMethod().toString()) {
                case "USB":
                    str += "USB\n";
                    str += "USB Name: " + deviceConnFactoryManager.usbDevice().getDeviceName();
                    break;
                case "WIFI":
                    str += "WIFI\n";
                    str += "IP: " + deviceConnFactoryManager.getIp() + "\t";
                    str += "Port: " + deviceConnFactoryManager.getPort();
                    break;
                case "BLUETOOTH":
                    str += "BLUETOOTH\n";
                    str += "MacAddress: " + deviceConnFactoryManager.getMacAddress();
                    break;
                case "SERIAL_PORT":
                    str += "SERIAL_PORT\n";
                    str += "Path: " + deviceConnFactoryManager.getSerialPortPath() + "\t";
                    str += "Baudrate: " + deviceConnFactoryManager.getBaudrate();
                    break;
            }
        }
        return (str);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        DeviceConnFactoryManager.closeAllPort();
        if (threadPool != null) {
            threadPool.stopThreadPool();
        }
    }


    /**
     * 初始化蓝牙
     */
//    private void initBle() {
//        if (BleManager.getInstance().isSupportBle()) {
//            if (!BleManager.getInstance().isBlueEnable()) {
//                BleManager.getInstance().enableBluetooth();
//            }
////            initScanRule();
////            scanBlueDevice();
//            connectBlueDevice();
//        } else {
//            Toast.makeText(GenarateQRActivity.this, "您的设备不支持蓝牙", Toast.LENGTH_SHORT).show();
//        }
//    }

//    private void connectBlueDevice() {
//        String mAddress = "DC:0D:30:3C:C1:93";
//        BleManager.getInstance().connect(mAddress, new BleGattCallback() {
//            @Override
//            public void onStartConnect() {
//                emptyView.show(true);
//            }
//
//            @Override
//            public void onConnectFail(BleDevice bleDevice, BleException exception) {
//                Toast.makeText(GenarateQRActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
//                emptyView.show(false);
//                Toast.makeText(GenarateQRActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDisConnected(boolean isActiveDisConnected, BleDevice device, BluetoothGatt gatt, int status) {
//                emptyView.show(false);
//            }
//        });
//    }
    private void initTopbar() {
        topbar.setTitle("二维码");
//        topbar.addRightTextButton(R.string.print, R.id.print).setOnClickListener(v -> {
//            initBle();
//        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

//    private void initScanRule() {
//        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
////                .setServiceUuids(serviceUuids)      // 只扫描指定的服务的设备，可选
////                .setDeviceName(true, names)         // 只扫描指定广播名的设备，可选
////                .setDeviceMac(mac)                  // 只扫描指定mac的设备，可选
//                .setAutoConnect(true)      // 连接时的autoConnect参数，可选，默认false
//                .setScanTimeOut(10000)              // 扫描超时时间，可选，默认10秒；小于等于0表示不限制扫描时间
//                .build();
//        BleManager.getInstance().initScanRule(scanRuleConfig);
//    }

    /**
     * 扫描蓝牙设备
     */
//    private void scanBlueDevice() {
//        BleManager.getInstance().scan(new BleScanCallback() {
//            @Override
//            public void onScanStarted(boolean success) {
////                emptyView.setVisibility(View.VISIBLE);
//                emptyView.show(true);
//                // 开始扫描（主线程）
//                LogUtils.i("开始扫描" + success);
//            }
//
//            @Override
//            public void onScanning(BleDevice bleDevice) {
//                // 扫描到一个符合扫描规则的BLE设备（主线程）
//                BluetoothDevice device = bleDevice.getDevice();
//                String address = device.getAddress();
//                LogUtils.i("扫描到一个符合扫描规则的BLE设备" + bleDevice.getName());
//            }
//
//            @Override
//            public void onScanFinished(List<BleDevice> scanResultList) {
////                emptyView.setVisibility(View.GONE);
//                emptyView.show(false);
//                // 扫描结束，列出所有扫描到的符合扫描规则的BLE设备（主线程）
//                if (scanResultList != null && scanResultList.size() > 0) {
//                    List<BleDevice> bleDevices = scanResultList.subList(0, 3);
//                    showSimpleBottomSheetList(bleDevices);
////                    for (BleDevice bleDevice : scanResultList) {
////                        LogUtils.i("扫描结束，列出所有扫描到的符合扫描规则的BLE设备（主线程）" + bleDevice.getName());
////                    }
//                }
//
//            }
//        });
//    }

//    private void showSimpleBottomSheetList(List<BleDevice> bleDevices) {
//        new QMUIBottomSheet.BottomListSheetBuilder(this)
//                .addItem(bleDevices.get(0).getName() != null ? bleDevices.get(0).getName() : "未知")
//                .addItem(bleDevices.get(1).getName() != null ? bleDevices.get(1).getName() : "未知")
//                .addItem(bleDevices.get(2).getName() != null ? bleDevices.get(2).getName() : "未知")
//                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
//                    @Override
//                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
//                        dialog.dismiss();
//                        BleDevice bleDevice = bleDevices.get(position);
//                    }
//                })
//                .build()
//                .show();
//    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_genarate_qr;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            /*蓝牙连接*/
            /*获取蓝牙mac地址*/
            /* 初始化话DeviceConnFactoryManager */
            /* 打开端口 */
            if (requestCode == BLUETOOTH_REQUEST_CODE) {
                closeport();
                String macAddress = null;
                if (data != null) {
                    macAddress = data.getStringExtra(BluetoothDeviceList.EXTRA_DEVICE_ADDRESS);
                }
                new DeviceConnFactoryManager.Build()
                        .setId(id)
                        /* 设置连接方式 */
                        .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.BLUETOOTH)
                        /* 设置连接的蓝牙mac地址 */
                        .setMacAddress(macAddress)
                        .build();
                threadPool = ThreadPool.getInstantiation();
                threadPool.addTask(() -> DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort());
            }
        }
    }

    /**
     * 重新连接回收上次连接的对象，避免内存泄漏
     */
    private void closeport() {
        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null && DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].mPort != null) {
//            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].reader.cancel();
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].mPort.closePort();
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].mPort = null;
        }
    }

//    private void createChineseQRCode() {
//        new MyTask(GenarateQRActivity.this, imageView).execute();
//    }

    @OnClick({R.id.bt_connect, R.id.bt_print})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_connect:
                startActivityForResult(new Intent(this, BluetoothDeviceList.class), BLUETOOTH_REQUEST_CODE);
                break;
            case R.id.bt_print:
                print();
                break;
        }
    }

    private void print() {
        threadPool = ThreadPool.getInstantiation();
        threadPool.addTask(() -> {
            if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] == null ||
                    !DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
                mHandler.obtainMessage(CONN_PRINTER).sendToTarget();
                return;
            }
            if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getCurrentPrinterCommand() == PrinterCommand.TSC) {
                sendLabel();
            } else {
                mHandler.obtainMessage(PRINTER_COMMAND_ERROR).sendToTarget();
            }
        });
    }

    /**
     * 发送标签
     */
    void sendLabel() {
        LabelCommand tsc = new LabelCommand();
        /* 设置标签尺寸，按照实际尺寸设置 */
        tsc.addSize(30, 30);
        /* 设置标签间隙，按照实际尺寸设置，如果为无间隙纸则设置为0 */
        tsc.addGap(0);
        /* 设置打印方向 */
        tsc.addDirection(LabelCommand.DIRECTION.BACKWARD, LabelCommand.MIRROR.NORMAL);
        /* 开启带Response的打印，用于连续打印 */
        tsc.addQueryPrinterStatus(LabelCommand.RESPONSE_MODE.ON);
        /* 设置原点坐标 */
        tsc.addReference(0, 0);
        /* 撕纸模式开启 */
        tsc.addTear(EscCommand.ENABLE.ON);
        /* 清除打印缓冲区 */
        tsc.addCls();
        /* 绘制简体中文 */
        tsc.addText(50, 200, LabelCommand.FONTTYPE.SIMPLIFIED_CHINESE, LabelCommand.ROTATION.ROTATION_0, LabelCommand.FONTMUL.MUL_1, LabelCommand.FONTMUL.MUL_1,
                containerId + "");
        /* 绘制图片 */
//        Drawable drawable = imageView.getDrawable();
//        Bitmap b = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//        Bitmap b = BitmapFactory.decodeResource(getResources(), drawable);
//        tsc.addBitmap(0, 0, LabelCommand.BITMAP_MODE.OVERWRITE, 150, b);
        for (Integer integer : ids) {
            tsc.addQRCode(60, 60, LabelCommand.EEC.LEVEL_M, 5, LabelCommand.ROTATION.ROTATION_0, integer + "");
        }
        /* 绘制一维条码 */
//        tsc.add1DBarcode(10, 450, LabelCommand.BARCODETYPE.CODE128, 100, LabelCommand.READABEL.EANBEL, LabelCommand.ROTATION.ROTATION_0, "SMARNET");
        /* 打印标签 */
        tsc.addPrint(1, 1);
        /* 打印标签后 蜂鸣器响 */

        tsc.addSound(2, 100);
        tsc.addCashdrwer(LabelCommand.FOOT.F5, 255, 255);
        Vector<Byte> datas = tsc.getCommand();
        /* 发送数据 */
        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] == null) {
            return;
        }
        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CONN_STATE_DISCONN:
                    if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null || !DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].closePort(id);
                        ToastUtils.showShort(R.string.str_disconnect_success);
                    }
                    break;
                case PRINTER_COMMAND_ERROR:
                    ToastUtils.showShort(R.string.str_choice_printer_command);
                    break;
                case CONN_PRINTER:
                    ToastUtils.showShort(R.string.str_cann_printer);
                    break;
                case MESSAGE_UPDATE_PARAMETER:
                    String strIp = msg.getData().getString("Ip");
                    String strPort = msg.getData().getString("Port");
                    /* 初始化端口信息 */
                    new DeviceConnFactoryManager.Build()
                            /* 设置端口连接方式 */
                            .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.WIFI)
                            /* 设置端口IP地址 */
                            .setIp(strIp)
                            /* 设置端口ID（主要用于连接多设备） */
                            .setId(id)
                            /* 设置连接的热点端口号 */
                            .setPort(Integer.parseInt(strPort))
                            .build();
                    threadPool = ThreadPool.getInstantiation();
                    threadPool.addTask(new Runnable() {
                        @Override
                        public void run() {
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
                        }
                    });
                    break;
                default:
                    new DeviceConnFactoryManager.Build()
                            /* 设置端口连接方式 */
                            .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.WIFI)
                            /* 设置端口IP地址 */
                            .setIp("192.168.2.227")
                            /* 设置端口ID（主要用于连接多设备） */
                            .setId(id)
                            /* 设置连接的热点端口号 */
                            .setPort(9100)
                            .build();
                    threadPool.addTask(new Runnable() {
                        @Override
                        public void run() {
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
                        }
                    });
                    break;
            }
        }
    };

//    static class MyTask extends AsyncTask<Void, Void, Bitmap> {
//        // 弱引用允许Activity被垃圾收集器清理
//        private final WeakReference<GenarateQRActivity> weakActivity;
//        private final WeakReference<ImageView> weakimageview;
//
//        MyTask(GenarateQRActivity myActivity, ImageView imageView) {
//            this.weakActivity = new WeakReference<>(myActivity);
//            this.weakimageview = new WeakReference<>(imageView);
//        }
//
//        @Override
//        public Bitmap doInBackground(Void... params) {
//            return QRCodeEncoder.syncEncodeQRCode("生成的", BGAQRCodeUtil.dp2px(weakActivity.get().getApplicationContext(), 150));
//        }
//
//        @Override
//        public void onPostExecute(Bitmap bitmap) {
//            // 重新获取Actiity的强引用，并且判断是否存活
//            GenarateQRActivity activity = weakActivity.get();
//            if (activity == null
//                    || activity.isFinishing()
//                    || activity.isDestroyed()) {
//                // activity死亡了，不再做任何的事情
//                return;
//            }
//            if (bitmap != null) {
//                weakimageview.get().setImageBitmap(bitmap);
//            } else {
//                Toast.makeText(weakActivity.get().getApplicationContext(), "生成二维码失败", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    public static void start(Context context, ArrayList<Integer> ids) {
        Intent starter = new Intent(context, GenarateQRActivity.class);
        starter.putIntegerArrayListExtra("id", ids);
        context.startActivity(starter);
    }
}
