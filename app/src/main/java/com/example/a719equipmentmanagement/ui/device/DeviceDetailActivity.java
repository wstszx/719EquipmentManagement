package com.example.a719equipmentmanagement.ui.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.TimeUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.DeviceDetailData;
import com.example.a719equipmentmanagement.entity.DeviceData2;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.home.GenarateQRActivity;
import com.example.a719equipmentmanagement.ui.home.ScanActivity;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DeviceDetailActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.bt1)
    QMUIRoundButton bt1;
    @BindView(R.id.bt2)
    QMUIRoundButton bt2;

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
    private String deviceName;
    private String parameter;
    private String manufactuer;
    private String responsor;
    private String deptName;
    private String locationName;
    private String deviceStatus;
    private int deviceId;
    private List<String> opers;

    private int mStyle = R.style.QMUI_Dialog;
    private int status;
    private QMUIListPopup mListPopup;
    private ArrayAdapter<String> adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initTopbar() {
        topbar.setTitle("设备详情");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
        bt1.setOnClickListener(v -> {
            ArrayList<Integer> idList = new ArrayList<>();
            idList.add(deviceId);
            GenarateQRActivity.start(DeviceDetailActivity.this, idList);
        });
        bt2.setOnClickListener(v -> {

        });
    }

    private void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        getDetail(id);
        deviceId = Integer.parseInt(id);
    }

    private void getDetail(String id) {
        RetrofitClient.getInstance().getService().getDeviceDetail(id)
                .compose(CommonCompose.io2main(DeviceDetailActivity.this))
                .subscribe(new BaseSubscriber<DeviceDetailData>(DeviceDetailActivity.this) {
                    @Override
                    public void onSuccess(DeviceDetailData deviceDetailData) {
                        if (deviceDetailData != null) {
                            setData(deviceDetailData);
                        }
                    }
                });

    }

    private void setData(DeviceDetailData deviceDetailData) {
//        DeviceDetailData.DeptBean dept = deviceDetailData.getDept();
//        DeviceDetailData.LocationBean location = deviceDetailData.getLocation();
//        deviceName = deviceDetailData.getName();
//        parameter = deviceDetailData.getParameter();
//        manufactuer = deviceDetailData.getManufactuer();
//        responsor = deviceDetailData.getResponsor();
//        status = deviceDetailData.getStatus();
//        deptName = dept.getDeptName();
//        locationName = location.getName();
//        opers = deviceDetailData.getOpers();
        switch (status) {
            case 0:
                deviceStatus = "可用";
                break;
            case 1:
                deviceStatus = "借用";
                break;
            case 2:
                deviceStatus = "送检占用";
                break;
            case 3:
                deviceStatus = "送检";
                break;
            case 4:
                deviceStatus = "报废占用";
                break;
            case 5:
                deviceStatus = "报废";
                break;
            case 6:
                deviceStatus = "封存";
                break;
            case 7:
                deviceStatus = "解封占用";
                break;
            case 8:
                deviceStatus = "过期";
                break;
            case 9:
                deviceStatus = "外借";
                break;
            default:
                deviceStatus = "无状态信息";
                break;
        }
        if(opers.size()>0){
            topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> {
                initListPopupIfNeed();
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                mListPopup.show(v);
            });
        }
        initGroupListView();
    }

    private void initListPopupIfNeed() {
        int size = opers.size();
        String[] handleTypes = new String[size];
        for (int i = 0; i < size; i++) {
            String handleEnglish = opers.get(i);
            switch (handleEnglish) {
                case "Edit":
                    handleTypes[i] = "编辑";
                    break;
                case "Del":
                    handleTypes[i] = "删除";
                    break;
            }
        }
        List<String> data = new ArrayList<>();
        Collections.addAll(data, handleTypes);
        if (adapter == null) {
            adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, data);
        } else {
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
        }
        if (mListPopup == null) {
            mListPopup = new QMUIListPopup(this, QMUIPopup.DIRECTION_NONE, adapter);
            mListPopup.create(QMUIDisplayHelper.dp2px(this, 250), QMUIDisplayHelper.dp2px(this, 200), new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView textView = (TextView) view;
                    String s = textView.getText().toString();
                    switch (s) {
                        case "处理1":
//                            Intent addDeptIntent = new Intent();
//                            addDeptIntent.setClass(DeptManageActivity.this, AddDeptActivity.class);
//                            startActivityForResult(addDeptIntent, ADD_DEPT);
                            break;
                        case "处理2":
//                            Intent addPersonIntent = new Intent();
//                            addPersonIntent.setClass(DeptManageActivity.this, AddPersonActivity.class);
//                            startActivityForResult(addPersonIntent, ADD_PERSON);
                            break;
                    }
                    mListPopup.dismiss();
                }
            });
            mListPopup.setOnDismissListener(data::clear);
        }
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
            listItemView = (QMUICommonListItemView) v;
            int tag = (int) listItemView.getTag();
            if (tag >= 0 && tag <= 3) {
                Intent intent = new Intent();
                intent.putExtra("text", listItemView.getDetailText().toString());
                intent.setClass(this, BaseItemEditActivity.class);
                startActivityForResult(intent, tag);
            }
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
                deviceName,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//                QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        item0.setTag(0);
        section.addItemView(item0, onClickListener);
        item1 = groupListView.createItemView(
                null,
                containerAttrs[1],
                parameter,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item1.setTag(1);
        section.addItemView(item1, onClickListener);
        item2 = groupListView.createItemView(
                null,
                containerAttrs[2],
                manufactuer,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item2.setTag(2);
        section.addItemView(item2, onClickListener);
        item3 = groupListView.createItemView(
                null,
                containerAttrs[3],
                responsor,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
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

//    public static void start(Context context, Serializable serializable) {
//        Intent starter = new Intent(context, DeviceDetailActivity.class);
//        starter.putExtra("serializable", serializable);
//        context.startActivity(starter);
//    }

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

//    @OnClick(R.id.bt1)
//    public void onViewClicked() {
//        switch (status) {
//            case 0:
//                bt.setText("借用");
////                deviceStatus = "可用";
//                operatingEquip(0,0);
//                break;
//            case 1:
//                bt.setText("归还");
////                deviceStatus = "借用";
//                operatingEquip(1, 1);
//                break;
//            case 2:
//                bt.setText("归还");
////                deviceStatus = "送检占用";
//                operatingEquip(1,2);
//                break;
//            case 3:
//                bt.setText("送检");
////                deviceStatus = "送检";
//                operatingEquip(3, 3);
//                break;
//            case 4:
//                bt.setText("无法使用");
////                deviceStatus = "报废占用";
//                break;
//            case 5:
//                bt.setText("无法使用");
////                deviceStatus = "报废";
//                break;
//            case 6:
//                bt.setText("解封");
////                deviceStatus = "封存";
//                operatingEquip(6, 6);
//                break;
//            case 7:
//                bt.setText("无法使用");
////                deviceStatus = "解封占用";
//                break;
//            case 8:
//                bt.setText("送检");
////                deviceStatus = "过期";
//                operatingEquip(8, 8);
//                break;
//            case 9:
//                bt.setText("无法使用");
////                deviceStatus = "外借";
//                break;
//            default:
//                bt.setText("无法使用");
////                deviceStatus = "无状态信息";
//                break;
//        }
//
//    }

//    private void operatingEquip(int operType, int operState) {
//        String nowString = TimeUtils.getNowString();
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("equipId", equipNo);
//        map.put("operType", operType);
//        map.put("msg", "");
//        map.put("operState", operState);
//        map.put("dealer", responsor);
//        map.put("validDate", nowString);
//        map.put("latestVerifyDate", nowString);
//        RetrofitClient.getInstance().getService().operatingEquip(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseSubscriber<BaseResponse>(DeviceDetailActivity.this) {
//                    @Override
//                    public void onSuccess(BaseResponse baseResponse) {
//
//                    }
//                });
//    }
}
