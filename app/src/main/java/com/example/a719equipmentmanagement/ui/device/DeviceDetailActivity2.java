package com.example.a719equipmentmanagement.ui.device;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.DeviceDetailData;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.home.ChoiceDeptActivity;
import com.example.a719equipmentmanagement.ui.home.EditDeptActivity;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceDetailActivity2 extends BaseActivity {

    //    @BindView(R.id.switchs)
//    Switch switchs;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    @BindView(R.id.include_1)
    View include_1;
    @BindView(R.id.include_2)
    View include_2;
    @BindView(R.id.include_3)
    View include_3;
    @BindView(R.id.include_4)
    View include_4;
    @BindView(R.id.include_5)
    View include_5;
    @BindView(R.id.include_6)
    View include_6;
    @BindView(R.id.include_7)
    View include_7;

    private static final int EDIT_DEPT = 1;
    private String[] deviceAttrs = {"设备名称", "技术指标", "生产厂家", "责任人", "所属部门", "位置", "状态"};
    private String deviceName;
    private String parameter;
    private String manufactuer;
    private String responsor;
    private String deptName;
    private String locationName;
    private int status;
    private String deviceStatus;
    private int deviceId;
    private List<String> opers;

    private IncludedLayout includedLayout1;
    private IncludedLayout includedLayout2;
    private IncludedLayout includedLayout3;
    private IncludedLayout includedLayout4;
    private IncludedLayout includedLayout5;
    private IncludedLayout includedLayout6;
    private IncludedLayout includedLayout7;

    private QMUIListPopup mListPopup;
    private ArrayAdapter<String> adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_detail2;
    }

    public static void start(Context context, String id) {
        Intent starter = new Intent(context, DeviceDetailActivity2.class);
        starter.putExtra("id", id);
        context.startActivity(starter);
    }

    static class IncludedLayout {
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.edittext)
        EditText editText;
    }

    private void initTopbar() {
        topbar.setTitle("设备详情");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
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
                .compose(CommonCompose.io2main(DeviceDetailActivity2.this))
                .subscribe(new BaseSubscriber<DeviceDetailData>(DeviceDetailActivity2.this) {
                    @Override
                    public void onSuccess(DeviceDetailData deviceDetailData) {
                        if (deviceDetailData != null) {
                            setData(deviceDetailData);
                        }
                    }
                });

    }

    private void setData(DeviceDetailData deviceDetailData) {
        DeviceDetailData.DataBean data = deviceDetailData.getData();
        deviceName = data.getName();
        parameter = data.getParameter();
        manufactuer = data.getManufactuer();
        responsor = data.getResponsor();
        DeviceDetailData.DataBean.DeptBean dept1 = data.getDept();
        deptName = dept1.getDeptName();
        DeviceDetailData.DataBean.LocationBean location1 = data.getLocation();
        locationName = location1.getName();
        status = data.getStatus();
        opers = data.getOpers();
        switch (this.status) {
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
        if (this.opers.size() > 0) {
            topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> {
                initListPopupIfNeed();
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                mListPopup.show(v);
            });
        }
        initGroupListView();
    }

    private void initGroupListView() {
        includedLayout1 = new IncludedLayout();
        includedLayout2 = new IncludedLayout();
        includedLayout3 = new IncludedLayout();
        includedLayout4 = new IncludedLayout();
//        includedLayout5=new IncludedLayout();
//        includedLayout6=new IncludedLayout();
        includedLayout7 = new IncludedLayout();
        ButterKnife.bind(includedLayout1, include_1);
        ButterKnife.bind(includedLayout2, include_2);
        ButterKnife.bind(includedLayout3, include_3);
        ButterKnife.bind(includedLayout4, include_4);
        ButterKnife.bind(includedLayout7, include_7);


        includedLayout1.tv_title.setText(deviceAttrs[0]);
        includedLayout2.tv_title.setText(deviceAttrs[1]);
        includedLayout3.tv_title.setText(deviceAttrs[2]);
        includedLayout4.tv_title.setText(deviceAttrs[3]);
        includedLayout7.tv_title.setText(deviceAttrs[6]);
        includedLayout1.editText.setText(deviceName);
        includedLayout2.editText.setText(parameter);
        includedLayout3.editText.setText(manufactuer);
        includedLayout4.editText.setText(responsor);
        includedLayout7.editText.setText(deviceStatus);
        TextView tv_title5 = include_5.findViewById(R.id.tv_title);
        TextView tv_result5 = include_5.findViewById(R.id.tv_result);
        TextView tv_title6 = include_6.findViewById(R.id.tv_title);
        TextView tv_result6 = include_6.findViewById(R.id.tv_result);

        tv_title5.setText(deviceAttrs[4]);
        tv_result5.setText(deptName);
        tv_title6.setText(deviceAttrs[4]);
        tv_result6.setText(locationName);
        include_5.setOnClickListener(v -> {
            startActivityForResult(new Intent(DeviceDetailActivity2.this, ChoiceDeptActivity.class), EDIT_DEPT);
        });
        include_6.setOnClickListener(v -> {

        });
        includedLayout7.editText.setEnabled(false);
        if (opers.contains("Edit")) {
            includedLayout1.editText.setEnabled(true);
            includedLayout2.editText.setEnabled(true);
            includedLayout3.editText.setEnabled(true);
            includedLayout4.editText.setEnabled(true);
            include_5.setEnabled(true);
            include_6.setEnabled(true);
        } else {
            includedLayout1.editText.setEnabled(false);
            includedLayout2.editText.setEnabled(false);
            includedLayout3.editText.setEnabled(false);
            includedLayout4.editText.setEnabled(false);
            include_5.setEnabled(false);
            include_6.setEnabled(false);
        }
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
}
