package com.example.a719equipmentmanagement.ui.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.DeviceData2;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.home.ScanActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DeviceDetailActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.bt)
    QMUIRoundButton bt;

    private QMUICommonListItemView listItemView;
    private QMUICommonListItemView item0;
    private QMUICommonListItemView item1;
    private QMUICommonListItemView item2;
    private QMUICommonListItemView item3;
    private QMUICommonListItemView item4;
    private QMUICommonListItemView item5;
    private QMUICommonListItemView item6;
    private QMUICommonListItemView item7;

    private String[] containerAttrs = {"设备名称", "技术指标", "生产厂家", "责任人", "所属部门", "位置", "状态"};
    private String name;
    private String parameter;
    private String manufactuer;
    private String responsor;
    private String deptName;
    private String locationName;
    private String deviceStatus;

    private int mStyle = R.style.QMUI_Dialog;
    private int status;
    private String equipNo;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
        initGroupListView();
    }

    private void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        if (!StringUtils.isEmpty(id)) {
            getDeviceDetail(id);
            bt.setVisibility(View.VISIBLE);
        } else {
            DeviceData2.RowsBean rowsBean = (DeviceData2.RowsBean) intent.getSerializableExtra("serializable");
            if (rowsBean != null) {
                bt.setVisibility(View.GONE);
                setData(rowsBean);
//            DeviceData2.RowsBean.DeptBean deptBean = rowsBean.getDept();
//
//            DeviceData2.RowsBean.LocationBean locationBean = rowsBean.getLocation();
//            name = rowsBean.getName();
//            parameter = rowsBean.getParameter();
//            manufactuer = rowsBean.getManufactuer();
//            responsor = rowsBean.getResponsor();
//            deptName = deptBean == null ? "无部门信息" : deptBean.getDeptName();
//            locationName = locationBean == null ? "无位置信息" : locationBean.getName();
//            int status = rowsBean.getStatus();
//            switch (status) {
//                case 0:
//                    deviceStatus = "可用";
//                    break;
//                case 1:
//                    deviceStatus = "借用";
//                    break;
//                case 2:
//                    deviceStatus = "送检占用";
//                    break;
//                case 3:
//                    deviceStatus = "送检";
//                    break;
//                case 4:
//                    deviceStatus = "报废占用";
//                    break;
//                case 5:
//                    deviceStatus = "报废";
//                    break;
//                case 6:
//                    deviceStatus = "封存";
//                    break;
//                case 7:
//                    deviceStatus = "解封占用";
//                    break;
//                case 8:
//                    deviceStatus = "过期";
//                    break;
//                case 9:
//                    deviceStatus = "外借";
//                    break;
//                default:
//                    deviceStatus = "无状态信息";
//                    break;
//            }
            }
        }
    }

    private void getDeviceDetail(String id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("equipNo", id);
        RetrofitClient.getInstance().getService().findDeviceData(map)
                .compose(CommonCompose.io2main(DeviceDetailActivity.this))
                .subscribe(new BaseSubscriber<DeviceData2>(DeviceDetailActivity.this) {
                    @Override
                    public void onSuccess(DeviceData2 deviceData2) {
                        if (deviceData2 != null) {
                            List<DeviceData2.RowsBean> rows = deviceData2.getRows();
                            if (rows != null && rows.size() > 0) {
                                DeviceData2.RowsBean rowsBean = rows.get(0);
                                setData(rowsBean);
                            }
                        }
                    }
                });
    }

    private void setData(DeviceData2.RowsBean rowsBean) {
        DeviceData2.RowsBean.DeptBean deptBean = rowsBean.getDept();
        DeviceData2.RowsBean.LocationBean locationBean = rowsBean.getLocation();
        equipNo = rowsBean.getEquipNo();
        name = rowsBean.getName();
        parameter = rowsBean.getParameter();
        manufactuer = rowsBean.getManufactuer();
        responsor = rowsBean.getResponsor();
        deptName = deptBean == null ? "无部门信息" : deptBean.getDeptName();
        locationName = locationBean == null ? "无位置信息" : locationBean.getName();
        status = rowsBean.getStatus();
        switch (status) {
            case 0:
                bt.setText("借用");
                deviceStatus = "可用";
                break;
            case 1:
                bt.setText("归还");
                deviceStatus = "借用";
                break;
            case 2:
                bt.setText("归还");
                deviceStatus = "送检占用";
                break;
            case 3:
                bt.setText("送检");
                deviceStatus = "送检";
                break;
            case 4:
                bt.setText("无法使用");
                deviceStatus = "报废占用";
                break;
            case 5:
                bt.setText("无法使用");
                deviceStatus = "报废";
                break;
            case 6:
                bt.setText("解封");
                deviceStatus = "封存";
                break;
            case 7:
                bt.setText("无法使用");
                deviceStatus = "解封占用";
                break;
            case 8:
                bt.setText("送检");
                deviceStatus = "过期";
                break;
            case 9:
                bt.setText("无法使用");
                deviceStatus = "外借";
                break;
            default:
                bt.setText("无法使用");
                deviceStatus = "无状态信息";
                break;
        }
    }

    private void initTopbar() {
        topbar.setTitle("设备详情");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
//        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
//            GenarateQRActivity.start(this);
//        });

//        RetrofitClient.getInstance().getService().findDeviceData()
//                .compose(CommonCompose.io2main(this))
//                .subscribe(new BaseSubscriber<DeviceData2>(this) {
//                    @Override
//                    public void onSuccess(DeviceData2 deviceData) {
//                        if (deviceData != null) {
//                            List<DeviceData2.RowsBean> rows = deviceData.getRows();
//                            if (rows != null && rows.size() > 0) {
////                                adapter.setNewData(rows);
//
//                            }
//                        }
//                    }
//                });

    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
//            listItemView = (QMUICommonListItemView) v;
//            int tag = (int) listItemView.getTag();
//            Intent intent = new Intent();
//            intent.putExtra("text", listItemView.getDetailText().toString());
//            intent.setClass(this, BaseItemEditActivity.class);
//            startActivityForResult(intent, tag);
        };

        View.OnClickListener onClickListener4 = v -> {
            listItemView = (QMUICommonListItemView) v;
//            int tag = (int) listItemView.getTag();
//            if(tag==4){
//                showSingleChoiceDialog();
//            }
        };
        View.OnClickListener onClickListener5 = v -> {
            listItemView = (QMUICommonListItemView) v;
//            int tag = (int) listItemView.getTag();
//            if(tag==5){
//                showChoiceDialog();
//            }
        };

        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);

        item0 = groupListView.createItemView(
                null,
                containerAttrs[0],
                name,
                QMUICommonListItemView.HORIZONTAL,
//                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        item0.setTag(0);
        section.addItemView(item0, onClickListener);
        item1 = groupListView.createItemView(
                null,
                containerAttrs[1],
                parameter,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        item1.setTag(1);
        section.addItemView(item1, onClickListener);
        item2 = groupListView.createItemView(
                null,
                containerAttrs[2],
                manufactuer,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        item2.setTag(2);
        section.addItemView(item2, onClickListener);
        item3 = groupListView.createItemView(
                null,
                containerAttrs[3],
                responsor,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        item3.setTag(3);
        section.addItemView(item3, onClickListener);
        item4 = groupListView.createItemView(
                null,
                containerAttrs[4],
                deptName,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        item4.setTag(4);
        section.addItemView(item4, onClickListener4);
        item5 = groupListView.createItemView(
                null,
                containerAttrs[5],
                locationName,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        item5.setTag(5);
        section.addItemView(item5, onClickListener5);
        item6 = groupListView.createItemView(
                null,
                containerAttrs[6],
                deviceStatus,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        item6.setTag(6);
        section.addItemView(item6, onClickListener);

//        //测试部分item7,8
//        item7 = groupListView.createItemView(
//                null,
//                containerAttrs[7],
//                "测试条目",
//                QMUICommonListItemView.HORIZONTAL,
//                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//        item7.setTag(7);
//        section.addItemView(item7, onClickListener7);
//        QMUICommonListItemView item8 = groupListView.createItemView(
//                null,
//                containerAttrs[7],
//                "测试弹窗",
//                QMUICommonListItemView.HORIZONTAL,
//                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//        item8.setTag(8);
//        section.addItemView(item8, onClickListener8);

        section.addTo(groupListView);

    }

    //列表选择对话框
    private void showSingleChoiceDialog() {
        final String[] items = new String[]{"1组", "2组", "3组"};
        new QMUIDialog.CheckableDialogBuilder(DeviceDetailActivity.this)
                .setCheckedIndex(0).addItems(items, ((dialog, which) -> {
            dialog.dismiss();
            item4.setDetailText(items[which]);
        })).create(mStyle).show();
    }

    //提示对话框
    private void showChoiceDialog() {
        new QMUIDialog.MessageDialogBuilder(this).setTitle("提示").setMessage("重新选择设备所在位置？？？")
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("确定", ((dialog, index) -> {
                    dialog.dismiss();
                    ScanActivity.start(this);
                })).create(mStyle).show();
    }

    public static void start(Context context, Serializable serializable) {
        Intent starter = new Intent(context, DeviceDetailActivity.class);
        starter.putExtra("serializable", serializable);
        context.startActivity(starter);

    }

    public static void start(Context context, String id) {
        Intent starter = new Intent(context, DeviceDetailActivity.class);
        starter.putExtra("id", id);
        context.startActivity(starter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String text = data.getStringExtra("text");
            TextView detailTextView = listItemView.getDetailTextView();
            detailTextView.setSingleLine(true);
            detailTextView.setMaxEms(8);
            detailTextView.setEllipsize(TextUtils.TruncateAt.END);
            detailTextView.setText(text);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_detail;
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
        switch (status) {
            case 0:
                bt.setText("借用");
//                deviceStatus = "可用";
                operatingEquip(0,0);
                break;
            case 1:
                bt.setText("归还");
//                deviceStatus = "借用";
                operatingEquip(1, 1);
                break;
            case 2:
                bt.setText("归还");
//                deviceStatus = "送检占用";
                operatingEquip(1,2);
                break;
            case 3:
                bt.setText("送检");
//                deviceStatus = "送检";
                operatingEquip(3, 3);
                break;
            case 4:
                bt.setText("无法使用");
//                deviceStatus = "报废占用";
                break;
            case 5:
                bt.setText("无法使用");
//                deviceStatus = "报废";
                break;
            case 6:
                bt.setText("解封");
//                deviceStatus = "封存";
                operatingEquip(6, 6);
                break;
            case 7:
                bt.setText("无法使用");
//                deviceStatus = "解封占用";
                break;
            case 8:
                bt.setText("送检");
//                deviceStatus = "过期";
                operatingEquip(8, 8);
                break;
            case 9:
                bt.setText("无法使用");
//                deviceStatus = "外借";
                break;
            default:
                bt.setText("无法使用");
//                deviceStatus = "无状态信息";
                break;
        }

    }

    private void operatingEquip(int operType, int operState) {
        String nowString = TimeUtils.getNowString();
        HashMap<String, Object> map = new HashMap<>();
        map.put("equipId", equipNo);
        map.put("operType", operType);
        map.put("msg", "");
        map.put("operState", operState);
        map.put("dealer", responsor);
        map.put("validDate", nowString);
        map.put("latestVerifyDate", nowString);
        RetrofitClient.getInstance().getService().operatingEquip(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(DeviceDetailActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {

                    }
                });
    }
}
