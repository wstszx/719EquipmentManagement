package com.example.a719equipmentmanagement.ui.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
import com.example.a719equipmentmanagement.entity.DeptList;
import com.example.a719equipmentmanagement.entity.DeviceDetailData;
import com.example.a719equipmentmanagement.entity.UserBean;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.home.ChoiceContainerActivity;
import com.example.a719equipmentmanagement.ui.home.ChoiceDeptActivity;
import com.example.a719equipmentmanagement.ui.home.ChoiceDeviceClassifiyActivity;
import com.example.a719equipmentmanagement.ui.home.GenarateQRActivity;
import com.example.a719equipmentmanagement.ui.home.ResultActivity;
import com.example.a719equipmentmanagement.utils.AboriginalDateSelect;
import com.example.a719equipmentmanagement.view.AuditDialog;
import com.example.a719equipmentmanagement.view.OperaDialog;
import com.example.a719equipmentmanagement.view.ReturnInspectionDialog;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
    private static final int VALID_DATA = 1;
    private static final int LAST_DATA = 2;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    private String[] options = {"可用", "借用", "送检占用", "送检", "报废占用", "报废", "封存", "解封占用", "过期", "外借", "未送检", "不限"};
    private String[] technical_status = {"合格", "不合格", "限制使用"};
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
    private int equipId;
    private String date;
    private String date1;
    private int userId;
    private String categoryName;
    private ArrayList<String> snList;
    private ArrayList<String> deviceIdList;

    @Override
    protected void init(Bundle savedInstanceState) {
        isManager = SPUtils.getInstance().getBoolean("isManager", false);
        initTopbar();
        initView();
        initData();
        AboriginalDateSelect.getInstance().setListener((position, date) -> {
            switch (position) {
                case DATE_ONE:
                    tvResult12.setText(date);
                    break;
                case DATE_TWO:
                    tvResult13.setText(date);
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

        tvResult1.setEnabled(false);
        tvResult3.setEnabled(false);
        tvResult5.setEnabled(false);
        tvResult7.setEnabled(false);
        tvResult9.setEnabled(false);
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

//    public static void start(Context context, String deviceId) {
//        Intent starter = new Intent(context, DeviceDetailActivity2.class);
//        starter.putExtra("deviceId", deviceId);
//        context.startActivity(starter);
//    }


    private void initTopbar() {
        topbar.setTitle("设备详情");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void updateDevice() {
        String name = edittext.getText().toString();
        String sn = edittext2.getText().toString();
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
        if (StringUtils.isEmpty(sn)) {
            ToastUtils.showShort("厂家编号不能为空");
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
            jsonObject.put("id", equipId);
            jsonObject.put("name", name);
            jsonObject.put("categoryId", categoryId);
            jsonObject.put("sn", sn);
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
                            topbar.removeAllLeftViews();
                            topbar.removeAllRightViews();
                            initTopbar();
                            if (opers != null) {
                                topbar.addRightImageButton(R.mipmap.menu, R.id.menu).setOnClickListener(v1 -> {
                                    initListPopupIfNeed(opers);
                                    mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                                    mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                                    mListPopup.show(v1);
                                });
                            }
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
        snList = new ArrayList<>();
        deviceIdList = new ArrayList<>();
        Intent intent = getIntent();
        int deviceId = intent.getIntExtra("deviceId", 0);
        String sn = intent.getStringExtra("sn");
        snList.add(sn);
        deviceIdList.add("E|" + deviceId);
        getDetail(deviceId);
    }

    private void getDetail(int deviceId) {
        RetrofitClient.getInstance().getService().getDeviceDetail(deviceId)
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
        if (data != null) {
            deviceName = data.getName();
            equipId = data.getId();
            String sn = data.getSn();
            parameter = data.getParameter();
            manufactuer = data.getManufactuer();
            int techState = data.getTechState();
            responsor = data.getResponsor();
            int verifyPeriod = data.getVerifyPeriod();
            String latestVerifyDate = data.getLatestVerifyDate();
            String validDate = data.getValidDate();
            DeviceDetailData.DataBean.DeptBean dept1 = data.getDept();
            if (dept1 != null) {
                deptId = dept1.getDeptId();
                deptName = dept1.getDeptName();
            }
            DeviceDetailData.DataBean.CategoryBean category = data.getCategory();
            if (category != null) {
                categoryId = category.getId();
                categoryName = category.getName();
            }
            DeviceDetailData.DataBean.LocationBean location1 = data.getLocation();
            if (location1 != null) {
                locationId = location1.getId();
                locationName = location1.getName();
            }
            status = data.getStatus();
            opers = data.getOpers();
            opers.add(0, "打印二维码");
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
            if (opers != null) {
                topbar.addRightImageButton(R.mipmap.menu, R.id.menu).setOnClickListener(v -> {
                    initListPopupIfNeed(opers);
                    mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                    mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                    mListPopup.show(v);
                });
            }
            edittext.setText(deviceName);
            tvResult1.setText(categoryName);
            edittext2.setText(sn);
            tvResult3.setText(deptName);
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
                case R.id.constraint12:
                    AboriginalDateSelect.getInstance().showDate(this, DATE_ONE);
                    break;
                case R.id.constraint13:
                    AboriginalDateSelect.getInstance().showDate(this, DATE_TWO);
                    break;
            }
        }
    }

    private void initListPopupIfNeed(List<String> listItems) {
        List<String> data = new ArrayList<>(listItems);
        if (adapter == null) {
            adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, data);
        } else {
            adapter.addAll(listItems);
            adapter.notifyDataSetChanged();
        }
        if (mListPopup == null) {
            mListPopup = new QMUIListPopup(this, QMUIPopup.DIRECTION_NONE, adapter);
            mListPopup.create(QMUIDisplayHelper.dp2px(this, 250), QMUIDisplayHelper.dp2px(this, 200), (adapterView, view, i, l) -> {
                TextView textView = (TextView) view;
                String s = textView.getText().toString();
                switch (s) {
                    case "借用":
                        showOperaDialog(1, s);
                        break;
                    case "归还":
                        showOperaDialog(2, s);
                        break;
                    case "送检申请":
                        showOperaDialog(3, s);
                        break;
                    case "审核送检":
                        showAuditDialog(4, s);
                        break;
                    case "送检借出":
                        showOperaDialog(5, s);
                        break;
                    case "送检归还":
                        showReturnInspectionDialog(s);
                        break;
                    case "报废申请":
                        showOperaDialog(7, s);
                        break;
                    case "审核报废":
                        showAuditDialog(8, s);
                        break;
                    case "报废处理":
                        showOperaDialog(9, s);
                        break;
                    case "封存":
                        showOperaDialog(10, s);
                        break;
                    case "解封申请":
                        showOperaDialog(11, s);
                        break;
                    case "审核解封":
                        showAuditDialog(12, s);
                        break;
                    case "解封":
                        showOperaDialog(13, s);
                        break;
                    case "编辑":
                        setEditStatu();
                        break;
                    case "删除":
                        deleteDevice();
                        break;
                    case "打印二维码":
                        print();
                        break;
                }
                mListPopup.dismiss();
            });
            mListPopup.setOnDismissListener(data::clear);
        }
    }

    private void print() {
        if (snList != null && snList.size() > 0 && deviceIdList != null && deviceIdList.size() > 0) {
            GenarateQRActivity.start(this, deviceIdList, snList);
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
     * 删除设备
     */
    private void deleteDevice() {
        RetrofitClient.getInstance().getService().deleteDevice(String.valueOf(equipId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(DeviceDetailActivity2.this) {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        if (response != null) {
                            int code = response.getCode();
                            if (code == 0) {
                                setResult(RESULT_OK);
                                finish();
                            }
                        }
                    }
                });
    }

    /**
     * 设置设备详情可编辑
     */
    private void setEditStatu() {
        tvTitle.setEnabled(true);
        tvTitle1.setEnabled(true);
        tvTitle2.setEnabled(true);
        tvTitle3.setEnabled(true);
        tvTitle4.setEnabled(true);
        tvTitle5.setEnabled(true);
        tvTitle6.setEnabled(true);
        tvTitle7.setEnabled(true);
        tvTitle8.setEnabled(true);
        tvTitle9.setEnabled(true);
        tvTitle11.setEnabled(true);
        tvTitle12.setEnabled(true);
        tvTitle13.setEnabled(true);

        tvResult1.setEnabled(true);
        tvResult3.setEnabled(true);
        tvResult5.setEnabled(true);
        tvResult7.setEnabled(true);
        tvResult9.setEnabled(true);
        tvResult12.setEnabled(true);
        tvResult13.setEnabled(true);
        edittext.setEnabled(true);
        edittext2.setEnabled(true);
        edittext4.setEnabled(true);
        edittext6.setEnabled(true);
        edittext8.setEnabled(true);
        edittext11.setEnabled(true);
        constraint.setEnabled(true);
        constraint1.setEnabled(true);
        constraint2.setEnabled(true);
        constraint3.setEnabled(true);
        constraint4.setEnabled(true);
        constraint5.setEnabled(true);
        constraint6.setEnabled(true);
        constraint7.setEnabled(true);
        constraint9.setEnabled(true);
        constraint12.setEnabled(true);
        constraint13.setEnabled(true);
        topbar.removeAllLeftViews();
        topbar.removeAllRightViews();
        topbar.addLeftTextButton(R.string.canecl, R.id.canecl).setOnClickListener(v -> {
            topbar.removeAllLeftViews();
            topbar.removeAllRightViews();
            initTopbar();
            if (opers != null) {
                topbar.addRightImageButton(R.mipmap.menu, R.id.menu).setOnClickListener(v1 -> {
                    initListPopupIfNeed(opers);
                    mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                    mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                    mListPopup.show(v1);
                });
            }
        });
        topbar.addRightTextButton(R.string.save, R.id.save).setOnClickListener(v -> {
            updateDevice();
        });
    }

    private List<UserBean> userBeanList = new ArrayList<>();

    private void showAuditDialog(int operType, String s) {
        userBeanList.clear();
        AuditDialog auditDialog = new AuditDialog(this);

        auditDialog.setTitle(s)
                .setPlaceholder1("备注")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .setInputType1(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("不通过", (dialog, index) -> {
                    dialog.dismiss();
                    auditEquip(operType, 2, s, auditDialog);

                })
                .addAction("通过", (dialog, index) -> {
                    dialog.dismiss();
                    auditEquip(operType, 3, s, auditDialog);
                })
                .create(mCurrentDialogStyle).show();
        auditDialog.getRightImageView().setOnClickListener(v -> RetrofitClient.getInstance().getService().getDeptList()
                .compose(CommonCompose.io2main(DeviceDetailActivity2.this))
                .subscribe(new BaseSubscriber<List<DeptList>>(DeviceDetailActivity2.this) {
                    @Override
                    public void onSuccess(List<DeptList> users) {
                        if (users != null && users.size() > 0) {
                            userBeanList.clear();
                            for (DeptList user : users) {
                                List<DeptList.UsersBean> deptLists = user.getUsers();
                                if (deptLists != null && deptLists.size() > 0) {
                                    for (DeptList.UsersBean deptList : deptLists) {
                                        String userName = deptList.getUserName();
                                        int userId = deptList.getUserId();
                                        UserBean userBean = new UserBean();
                                        userBean.setUserId(userId);
                                        userBean.setUserName(userName);
                                        userBeanList.add(userBean);
                                    }
                                }
                            }
                            showSingleChoiceDialog(auditDialog);
                        }
                    }
                }));
    }

    private void showSingleChoiceDialog(AuditDialog auditDialog) {
        String[] userNameArray = new String[]{};
        List<String> userNameList = new ArrayList<>();
        for (UserBean userBean : userBeanList) {
            String userName = userBean.getUserName();
            userNameList.add(userName);
        }
        String[] strings = userNameList.toArray(userNameArray);
        if (strings != null && strings.length > 0) {
            final int checkedIndex = 1;
            new QMUIDialog.CheckableDialogBuilder(this)
                    .setCheckedIndex(checkedIndex)
                    .addItems(strings, (dialog, which) -> {
                        userId = userBeanList.get(which).getUserId();
                        auditDialog.getEditText().setText(strings[which]);
                        dialog.dismiss();
                    })
                    .create(mCurrentDialogStyle).show();
        }
    }

    /**
     * 审核设备
     *
     * @param operType
     * @param text
     */
    private void auditEquip(int operType, int operState, String text, AuditDialog auditDialog) {
        HashMap<String, Object> map = new HashMap<>();
        String msg = auditDialog.getEditText1().getText().toString();
        String userName = auditDialog.getEditText().getText().toString();
        if (StringUtils.isEmpty(userName)) {
            ToastUtils.showShort("请选择负责人");
            return;
        }
        map.put("equipId", equipId);
        map.put("operType", operType);
        map.put("msg", msg);
        map.put("operState", operState);
        map.put("dealer", userId);
        RetrofitClient.getInstance().getService().operatingEquip(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(DeviceDetailActivity2.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        int code = baseResponse.getCode();
                        if (code == 0) {
                            ToastUtils.showShort(text + "成功");
                        }
                    }
                });
    }

    private void showReturnInspectionDialog(String s) {
        ReturnInspectionDialog returnInspectionDialog = new ReturnInspectionDialog(this);

        returnInspectionDialog.setTitle(s)
                .setPlaceholder("有效期")
                .setPlaceholder1("最后检验日期")
                .setInputType(InputType.TYPE_CLASS_DATETIME)
                .setInputType1(InputType.TYPE_CLASS_DATETIME)
                .addAction("取消", (dialog, index) -> dialog.dismiss())

                .addAction("确定", (dialog, index) -> {
                    dialog.dismiss();
                    String msg = returnInspectionDialog.getEditText2().getText().toString();
                    operatingEquip(6, s, msg);
                })
                .create(mCurrentDialogStyle).show();
        returnInspectionDialog.getRightImageView().setOnClickListener(v -> AboriginalDateSelect.getInstance().showDate(DeviceDetailActivity2.this, VALID_DATA));
        returnInspectionDialog.getRightImageView1().setOnClickListener(v -> AboriginalDateSelect.getInstance().showDate(DeviceDetailActivity2.this, LAST_DATA));
        AboriginalDateSelect.getInstance().setListener((position, dateFormat) -> {
            switch (position) {
                case VALID_DATA:
                    date = dateFormat;
                    returnInspectionDialog.getEditText().setText(date);
                    break;
                case LAST_DATA:
                    date1 = dateFormat;
                    returnInspectionDialog.getEditText1().setText(date1);
                    break;
            }
        });
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
        if (operType == 6) {
            map.put("validDate", date);
            map.put("latestVerifyDate", date1);
        }
        RetrofitClient.getInstance().getService().operatingEquip(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(DeviceDetailActivity2.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        int code = baseResponse.getCode();
                        if (code == 0) {
                            ResultActivity.start(DeviceDetailActivity2.this, title + "成功");
                        }
                    }
                });
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
}
