package com.example.a719equipmentmanagement.ui.home;

import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.GenerateContainerCodeAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.ContainerItem;
import com.example.a719equipmentmanagement.view.SpaceItemDecoration;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenerateContainerCodeActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<ContainerItem> qrList = new ArrayList<>();

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        GenerateContainerCodeAdapter adapter = new GenerateContainerCodeAdapter(R.layout.container_code_item, qrList, App.getContext());
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new SpaceItemDecoration(30));
        recyclerview.setAdapter(adapter);
    }

    private void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        ContainerItem item1 = new ContainerItem();
        ContainerItem item2 = new ContainerItem();
        ContainerItem item3 = new ContainerItem();
        item1.setId("1");
        item2.setId("2");
        item3.setId("3");
        item1.setName("货柜第一层");
        item2.setName("货柜第二层");
        item3.setName("货柜第三层");
        qrList.add(item1);
        qrList.add(item2);
        qrList.add(item3);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_generate_container_code;
    }

    private void initTopbar() {
        topbar.setTitle("货柜码");
        topbar.addRightTextButton(R.string.print, R.id.print).setOnClickListener(v -> {
            initBle();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
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
            ToastUtils.showShort("您的设备不支持蓝牙");
        }
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
    private void connectBlueDevice() {
        String mAddress = "DC:0D:30:3C:C1:93";
        BleManager.getInstance().connect(mAddress, new BleGattCallback() {
            @Override
            public void onStartConnect() {
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {
                ToastUtils.showShort("连接失败");
            }

            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                ToastUtils.showShort("连接成功");

            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice device, BluetoothGatt gatt, int status) {
            }
        });
    }

    public static void start(Context context, int id) {
        Intent starter = new Intent(context, GenerateContainerCodeActivity.class);
        starter.putExtra("id", id);
        context.startActivity(starter);
    }

}
