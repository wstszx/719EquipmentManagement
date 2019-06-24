package com.example.a719equipmentmanagement.ui.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.DeviceDetailData;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.home.ChoiceContainerActivity;
import com.example.a719equipmentmanagement.ui.home.ChoiceDeptActivity;
import com.example.a719equipmentmanagement.ui.home.ChoiceDeviceClassifiyActivity;
import com.example.a719equipmentmanagement.utils.AboriginalDateSelect;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DeviceDetailActivity2 extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.constraint)
    ConstraintLayout constraint;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.constraint1)
    ConstraintLayout constraint1;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    @BindView(R.id.constraint2)
    ConstraintLayout constraint2;
    @BindView(R.id.tv_title3)
    TextView tvTitle3;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.constraint3)
    ConstraintLayout constraint3;
    @BindView(R.id.tv_title4)
    TextView tvTitle4;
    @BindView(R.id.constraint4)
    ConstraintLayout constraint4;
    @BindView(R.id.tv_title5)
    TextView tvTitle5;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.constraint5)
    ConstraintLayout constraint5;
    @BindView(R.id.tv_title6)
    TextView tvTitle6;
    @BindView(R.id.constraint6)
    ConstraintLayout constraint6;
    @BindView(R.id.constraint7)
    ConstraintLayout constraint7;
    @BindView(R.id.tv_title7)
    TextView tvTitle7;
    @BindView(R.id.edittext8)
    EditText edittext8;
    @BindView(R.id.tv_result9)
    TextView tvResult9;
    @BindView(R.id.edittext11)
    EditText edittext11;
    @BindView(R.id.tv_result12)
    TextView tvResult12;
    @BindView(R.id.tv_result13)
    TextView tvResult13;
    @BindView(R.id.constraint9)
    ConstraintLayout constraint9;
    @BindView(R.id.constraint12)
    ConstraintLayout constraint12;
    @BindView(R.id.constraint13)
    ConstraintLayout constraint13;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.tv_result1)
    TextView tvResult1;
    @BindView(R.id.edittext2)
    EditText edittext2;
    @BindView(R.id.tv_result3)
    TextView tvResult3;
    @BindView(R.id.edittext4)
    EditText edittext4;
    @BindView(R.id.tv_result5)
    TextView tvResult5;
    @BindView(R.id.edittext6)
    EditText edittext6;
    @BindView(R.id.tv_result7)
    TextView tvResult7;
    @BindView(R.id.tv_title8)
    TextView tvTitle8;
    @BindView(R.id.tv_title9)
    TextView tvTitle9;
    @BindView(R.id.tv_title11)
    TextView tvTitle11;
    @BindView(R.id.tv_title12)
    TextView tvTitle12;
    @BindView(R.id.tv_title13)
    TextView tvTitle13;
    private String[] options = {"可用", "借用", "送检占用", "送检", "报废占用", "报废", "封存", "解封占用", "过期", "外借", "不限"};
    private String[] technical_status = {"合格", "不合格"};
    private static final int DEVICE_TYPE = 1;
    private static final int DEPT_TYPE = 2;
    private static final int CONTAINER_TYPE = 3;
    private static final int DATE_ONE = 4;
    private static final int DATE_TWO = 5;
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
    private QMUIListPopup mListPopup;
    private ArrayAdapter<String> adapter;
    private int categoryId;
    private int deptId;
    private int locationId;
    private String techStateStr = "";
    private int tech_statu;
    private boolean isManager;

    @Override
    protected void init(Bundle savedInstanceState) {
        isManager = SPUtils.getInstance().getBoolean("isManager", false);
        initTopbar();
        initView();
        initData();
        AboriginalDateSelect.getInstance().setListener((position, date) -> {
            switch (position) {
                case DATE_ONE:
                    String time = TimeUtils.date2String(date);
                    tvResult12.setText(time);
                    break;
                case DATE_TWO:
                    String time1 = TimeUtils.date2String(date);
                    tvResult13.setText(time1);
                    break;
            }
        });
    }

    private void initView() {
        tvTitle.setEnabled(false);
        tvTitle1.setEnabled(false);
        tvTitle2.setEnabled(false);
        tvTitle3.setEnabled(false);
        tvTitle4.setEnabled(false);
        tvTitle5.setEnabled(false);
        tvTitle6.setEnabled(false);
        tvTitle7.setEnabled(false);
        tvTitle8.setEnabled(false);
        tvTitle9.setEnabled(false);
        tvTitle11.setEnabled(false);
        tvTitle12.setEnabled(false);
        tvTitle13.setEnabled(false);

        edittext.setFocusable(false);
        tvResult1.setEnabled(false);
        edittext2.setFocusable(false);
        tvResult3.setEnabled(false);
        edittext4.setFocusable(false);
        tvResult5.setEnabled(false);
        edittext6.setFocusable(false);
        tvResult7.setEnabled(false);
        edittext8.setFocusable(false);
        tvResult9.setEnabled(false);
        edittext11.setFocusable(false);
        tvResult12.setEnabled(false);
        tvResult13.setEnabled(false);
        edittext.setEnabled(false);
        edittext2.setEnabled(false);
        edittext4.setEnabled(false);
        edittext6.setEnabled(false);
        edittext8.setEnabled(false);
        edittext11.setEnabled(false);
        constraint.setEnabled(false);
        constraint1.setEnabled(false);
        constraint2.setEnabled(false);
        constraint3.setEnabled(false);
        constraint4.setEnabled(false);
        constraint5.setEnabled(false);
        constraint6.setEnabled(false);
        constraint7.setEnabled(false);
        constraint9.setEnabled(false);
        constraint12.setEnabled(false);
        constraint13.setEnabled(false);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_detail2;
    }

    public static void start(Context context, String deviceId) {
        Intent starter = new Intent(context, DeviceDetailActivity2.class);
        starter.putExtra("deviceId", deviceId);
        context.startActivity(starter);
    }


    private void initTopbar() {
        topbar.setTitle("设备详情");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
//        if (isManager) {
//            topbar.addRightTextButton(R.string.save, R.id.save).setOnClickListener(v -> updateDevice());
//        }
    }

    private void updateDevice() {
        String name = edittext.getText().toString();
        String equipNo = edittext2.getText().toString();
        String dept = tvResult3.getText().toString();
        String parameter = edittext4.getText().toString();
        String location = tvResult5.getText().toString();
        String manufactuer = edittext6.getText().toString();
        String equipStatus = tvResult7.getText().toString();
        String responsor = edittext8.getText().toString();
        String techStatus = tvResult9.getText().toString();
        String verifyPeriod = edittext11.getText().toString();
        String latestVerifyDate = tvResult12.getText().toString();
        String validDate = tvResult13.getText().toString();
        if (StringUtils.isEmpty(name)) {
            ToastUtils.showShort("设备名称不能为空");
            return;
        }
        if (StringUtils.isEmpty(equipNo)) {
            ToastUtils.showShort("设备编号不能为空");
            return;
        }
        if (StringUtils.isEmpty(dept)) {
            ToastUtils.showShort("归属部门不能为空");
            return;
        }
        if (StringUtils.isEmpty(parameter)) {
            ToastUtils.showShort("技术参数不能为空");
            return;
        }
        if (StringUtils.isEmpty(location)) {
            ToastUtils.showShort("所在位置不能为空");
            return;
        }
        if (StringUtils.isEmpty(manufactuer)) {
            ToastUtils.showShort("厂家不能为空");
            return;
        }
        if (StringUtils.isEmpty(equipStatus)) {
            ToastUtils.showShort("仪器状态不能为空");
            return;
        }
        if (StringUtils.isEmpty(responsor)) {
            ToastUtils.showShort("负责人不能为空");
            return;
        }
        if (StringUtils.isEmpty(techStatus)) {
            ToastUtils.showShort("技术状态不能为空");
            return;
        }
        if (StringUtils.isEmpty(verifyPeriod)) {
            ToastUtils.showShort("检定周期不能为空");
            return;
        }
        if (StringUtils.isEmpty(latestVerifyDate)) {
            ToastUtils.showShort("最近检定日期不能为空");
            return;
        }
        if (StringUtils.isEmpty(validDate)) {
            ToastUtils.showShort("有效期不能为空");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("categoryId", categoryId);
            jsonObject.put("sn", equipNo);
            jsonObject.put("deptId", deptId);
            jsonObject.put("parameter", parameter);
            jsonObject.put("locationId", locationId);
            jsonObject.put("manufactuer", manufactuer);
            jsonObject.put("status", status);
            jsonObject.put("responsor", responsor);
            jsonObject.put("techState", tech_statu);
            jsonObject.put("verifyPeriod", verifyPeriod);
            jsonObject.put("latestVerifyDate", latestVerifyDate);
            jsonObject.put("validDate", validDate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        RetrofitClient.getInstance().getService().updateDevice(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(DeviceDetailActivity2.this) {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        if (response != null && response.getCode() == 0) {
                            ToastUtils.showShort("保存成功");
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case DEVICE_TYPE:
                    if (data != null) {
                        String name = data.getStringExtra("name");
                        categoryId = data.getIntExtra("id", 0);
                        tvResult1.setText(name);
                    }
                    break;
                case DEPT_TYPE:
                    if (data != null) {
                        String name = data.getStringExtra("name");
                        deptId = data.getIntExtra("id", 0);
                        tvResult3.setText(name);
                    }
                    break;
                case CONTAINER_TYPE:
                    if (data != null) {
                        String name = data.getStringExtra("name");
                        locationId = data.getIntExtra("id", 0);
                        tvResult5.setText(name);
                    }
                    break;
            }
        }
    }

    private void initData() {
        Intent intent = getIntent();
        String deviceId = intent.getStringExtra("deviceId");
        getDetail(deviceId);
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
        String equipNo = data.getEquipNo();
        parameter = data.getParameter();
        manufactuer = data.getManufactuer();
        int techState = data.getTechState();
        responsor = data.getResponsor();
        int verifyPeriod = data.getVerifyPeriod();
        String latestVerifyDate = data.getLatestVerifyDate();
        String validDate = data.getValidDate();
        DeviceDetailData.DataBean.DeptBean dept1 = data.getDept();
        deptName = dept1.getDeptName();
        DeviceDetailData.DataBean.LocationBean location1 = data.getLocation();
        locationName = location1.getName();
        status = data.getStatus();
        opers = data.getOpers();
        switch (techState) {
            case 0:
                techStateStr = "合格";
                break;
            case 1:
                techStateStr = "不合格";
                break;
        }
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
        if (opers != null && opers.size() > 0) {
            topbar.addRightImageButton(R.mipmap.menu, R.id.menu).setOnClickListener(v -> {
                initListPopupIfNeed();
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                mListPopup.show(v);
            });
        }
        edittext.setText(deptName);
        edittext2.setText(equipNo);
        edittext4.setText(parameter);
        tvResult5.setText(locationName);
        edittext6.setText(manufactuer);
        tvResult7.setText(deviceStatus);
        edittext8.setText(responsor);
        tvResult9.setText(techStateStr);
        edittext11.setText(String.valueOf(verifyPeriod));
        tvResult12.setText(latestVerifyDate);
        tvResult13.setText(validDate);
    }


    @OnClick({R.id.constraint1, R.id.constraint3, R.id.constraint5, R.id.constraint7,
            R.id.constraint9, R.id.constraint12, R.id.constraint13})
    public void onViewClicked(View view) {
        if (isManager) {
            switch (view.getId()) {
                case R.id.constraint1:
                    startActivityForResult(new Intent(DeviceDetailActivity2.this, ChoiceDeviceClassifiyActivity.class), DEVICE_TYPE);
                    break;
                case R.id.constraint3:
                    startActivityForResult(new Intent(DeviceDetailActivity2.this, ChoiceDeptActivity.class), DEPT_TYPE);
                    break;
                case R.id.constraint5:
                    startActivityForResult(new Intent(DeviceDetailActivity2.this, ChoiceContainerActivity.class), CONTAINER_TYPE);
                    break;
                case R.id.constraint7:
                    showSimpleBottomSheetList(options, 1);
                    break;
                case R.id.constraint9:
                    showSimpleBottomSheetList(technical_status, 2);
                    break;
//            case R.id.constraint10:
//                showSimpleBottomSheetList(classification, 3);
//                break;
                case R.id.constraint12:
                    AboriginalDateSelect.getInstance().showDateTime(this, DATE_ONE);
                    break;
                case R.id.constraint13:
                    AboriginalDateSelect.getInstance().showDateTime(this, DATE_TWO);
                    break;
            }
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

    private void showSimpleBottomSheetList(String[] array, int flag) {
        QMUIBottomSheet.BottomListSheetBuilder bottomListSheetBuilder = new QMUIBottomSheet.BottomListSheetBuilder(this);
        for (String s : array) {
            bottomListSheetBuilder.addItem(s != null ? s : "未知");
        }

        bottomListSheetBuilder.setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
            dialog.dismiss();
            switch (flag) {
                case 1:
                    status = position;
                    tvResult7.setText(tag);
                    break;
                case 2:
                    tech_statu = position;
                    tvResult9.setText(tag);
                    break;
                case 3:
                    categoryId = position;
                    break;
            }
        })
                .build()
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
