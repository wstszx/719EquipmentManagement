package com.example.a719equipmentmanagement.ui.device;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.DeviceData2;
import com.example.a719equipmentmanagement.entity.DeviceScanData;
import com.example.a719equipmentmanagement.entity.DeptList;
import com.example.a719equipmentmanagement.entity.UserBean;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.home.ResultActivity;
import com.example.a719equipmentmanagement.utils.AboriginalDateSelect;
import com.example.a719equipmentmanagement.view.AuditDialog;
import com.example.a719equipmentmanagement.view.ReturnInspectionDialog;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DeviceDetailActivity extends BaseActivity {

    private static final int VALID_DATA = 1;
    private static final int LAST_DATA = 2;
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
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private String name;
    private String parameter;
    private String manufactuer;
    private String responsor;
    private String deptName;
    private String locationName;
    private String deviceStatus;

    private int status;
    private int equipId;
    private ArrayAdapter<String> adapter;
    private QMUIListPopup mListPopup;
    private List<String> opers;
    private ReturnInspectionDialog returnInspectionDialog;
    private String date;
    private String date1;
    private AuditDialog auditDialog;
    private int userId;
    private String techStateStr;


    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initView();
        initData();
    }

    private void initView() {
        returnInspectionDialog = new ReturnInspectionDialog(this);
        auditDialog = new AuditDialog(this);
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

    private void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        if (!StringUtils.isEmpty(id)) {
            getDeviceDetail(id);
        }
    }

    private void getDeviceDetail(String id) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("equipNo", id);
        Single<DeviceScanData> baseResponseSingle = RetrofitClient.getInstance().getService().getScanData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
//        Single<DeviceData2> deviceData2Single = RetrofitClient.getInstance().getService().findDeviceData(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
        baseResponseSingle.subscribe(new BaseSubscriber<DeviceScanData>(this) {
            @Override
            public void onSuccess(DeviceScanData deviceScanData) {
                DeviceScanData.DataBean data = deviceScanData.getData();
                if (data != null) {
                    setData(data);
                    List<String> opers = data.getOpers();
                    if (opers != null && opers.size() > 0) {
                        DeviceDetailActivity.this.opers = opers;
                        setMenu();
                    } else {
                        topbar.removeAllRightViews();
                    }
                }
            }
        });
//        Single.zip(baseResponseSingle, deviceData2Single, (response, deviceData2) -> {
//            if (deviceData2 != null) {
//                List<DeviceData2.RowsBean> rows = deviceData2.getRows();
//                if (rows != null && rows.size() > 0) {
//                    DeviceData2.RowsBean rowsBean = rows.get(0);
//                    setData(rowsBean);
//                }
//            }
//            if (response != null) {
//                DeviceScanData.DataBean data = response.getData();
//                if (data != null) {
//                    List<String> opers = data.getOpers();
//                    if (opers != null && opers.size() > 0) {
//                        DeviceDetailActivity.this.opers = opers;
//                        setMenu();
//                    } else {
//                        topbar.removeAllRightViews();
//                    }
//                }
//            }
//            return new Object();
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseSubscriber<Object>(this) {
//
//                });
//        RetrofitClient.getInstance().getService().findDeviceData(map)
//                .compose(CommonCompose.io2main(DeviceDetailActivity.this))
//                .subscribe(new BaseSubscriber<DeviceData2>(DeviceDetailActivity.this) {
//                    @Override
//                    public void onSuccess(DeviceData2 deviceData2) {
//                        if (deviceData2 != null) {
//                            List<DeviceData2.RowsBean> rows = deviceData2.getRows();
//                            if (rows != null && rows.size() > 0) {
//                                DeviceData2.RowsBean rowsBean = rows.get(0);
//                                setData(rowsBean);
//                            }
//                        }
//                    }
//                });
    }

    private void setMenu() {
        topbar.addRightImageButton(R.mipmap.menu, R.id.menu).setOnClickListener(v -> {
            initListPopupIfNeed(opers);
            mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
            mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
            mListPopup.show(v);
        });
    }

    private void setData(DeviceScanData.DataBean dataBean) {
        DeviceScanData.DataBean.DeptBean dept = dataBean.getDept();
        DeviceScanData.DataBean.LocationBean location = dataBean.getLocation();
        String equipNo = dataBean.getEquipNo();
        int techState = dataBean.getTechState();
        equipId = dataBean.getId();
        name = dataBean.getName();
        int verifyPeriod = dataBean.getVerifyPeriod();
        String latestVerifyDate = dataBean.getLatestVerifyDate();
        String validDate = dataBean.getValidDate();
        parameter = dataBean.getParameter();
        manufactuer = dataBean.getManufactuer();
        responsor = dataBean.getResponsor();
        deptName = dept == null ? "无部门信息" : dept.getDeptName();
        locationName = location == null ? "无位置信息" : location.getName();
        status = dataBean.getStatus();
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

    private void initTopbar() {
        topbar.setTitle("设备详情");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void initListPopupIfNeed(List<String> listItems) {

        List<String> data = new ArrayList<>();
        data.addAll(listItems);
//        Collections.addAll(data, listItems);
        if (adapter == null) {
            adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, data);
        } else {
            adapter.addAll(listItems);
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
                        case "借用":
                            operatingEquip(1, s);
                            break;
                        case "归还":
                            operatingEquip(2, s);
                            break;
                        case "送检申请":
                            operatingEquip(3, s);
                            break;
                        case "审核送检":
                            showAuditDialog(4, s);
                            break;
                        case "送检借出":
                            operatingEquip(5, s);
                            break;
                        case "送检归还":
                            showReturnInspectionDialog(s);
                            break;
                        case "报废申请":
                            operatingEquip(7, s);
                            break;
                        case "审核报废":
                            showAuditDialog(8, s);
                            break;
                        case "报废处理":
                            operatingEquip(9, s);
                            break;
                        case "封存":
                            operatingEquip(10, s);
                            break;
                        case "解封申请":
                            operatingEquip(11, s);
                            break;
                        case "审核解封":
                            showAuditDialog(12, s);
                            break;
                        case "解封":
                            operatingEquip(13, s);
                            break;
                        case "编辑":
                            operatingEquip(14, s);
                            break;
                        case "删除":
                            operatingEquip(15, s);
                            break;
                    }
                    mListPopup.dismiss();
                }
            });
            mListPopup.setOnDismissListener(data::clear);
        }
    }

    private void showReturnInspectionDialog(String s) {
        returnInspectionDialog.setTitle(s)
                .setPlaceholder("有效期")
                .setPlaceholder1("最后检验日期")
                .setInputType(InputType.TYPE_CLASS_DATETIME)
                .setInputType1(InputType.TYPE_CLASS_DATETIME)
                .addAction("取消", (dialog, index) -> dialog.dismiss())

                .addAction("确定", (dialog, index) -> {
                    dialog.dismiss();
                    operatingEquip(6, s);
                })
                .create(mCurrentDialogStyle).show();
        returnInspectionDialog.getRightImageView().setOnClickListener(v -> AboriginalDateSelect.getInstance().showDateTime(DeviceDetailActivity.this, VALID_DATA));
        returnInspectionDialog.getRightImageView1().setOnClickListener(v -> AboriginalDateSelect.getInstance().showDateTime(DeviceDetailActivity.this, LAST_DATA));
        AboriginalDateSelect.getInstance().setListener((position, dateFormat) -> {
            switch (position) {
                case VALID_DATA:
                    date = TimeUtils.date2String(dateFormat);
                    returnInspectionDialog.getEditText().setText(date);
                    break;
                case LAST_DATA:
                    date1 = TimeUtils.date2String(dateFormat);
                    returnInspectionDialog.getEditText1().setText(date1);
                    break;
            }
        });
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

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_detail;
    }


    /**
     * 操作设备
     *
     * @param operType
     * @param text
     */
    private void operatingEquip(int operType, String text) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("equipId", equipId);
        map.put("operType", operType);
        map.put("msg", "");
        map.put("dealer", responsor);
        if (operType == 6) {
            map.put("validDate", date);
            map.put("latestVerifyDate", date1);
        }
        RetrofitClient.getInstance().getService().operatingEquip(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(DeviceDetailActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        int code = baseResponse.getCode();
                        if (code == 0) {
                            ResultActivity.start(DeviceDetailActivity.this, text + "成功");
                        }
                    }
                });
    }

    /**
     * 审核设备
     *
     * @param operType
     * @param text
     */
    private void auditEquip(int operType, int operState, String text) {
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
                .subscribe(new BaseSubscriber<BaseResponse>(DeviceDetailActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        int code = baseResponse.getCode();
                        if (code == 0) {
                            ToastUtils.showShort(text + "成功");
                        }
                    }
                });
    }

    private List<UserBean> userBeanList = new ArrayList<>();

    private void showAuditDialog(int operType, String s) {
        userBeanList.clear();
        auditDialog.setTitle(s)
                .setPlaceholder1("请输入理由")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("不通过", (dialog, index) -> {
                    dialog.dismiss();
                    auditEquip(operType, 1, s);

                })

                .addAction("通过", (dialog, index) -> {
                    dialog.dismiss();
                    auditEquip(operType, 2, s);
                })
                .create(mCurrentDialogStyle).show();
        auditDialog.getRightImageView().setOnClickListener(v -> {
            RetrofitClient.getInstance().getService().getDeptList()
                    .compose(CommonCompose.io2main(DeviceDetailActivity.this))
                    .subscribe(new BaseSubscriber<List<DeptList>>(DeviceDetailActivity.this) {
                        @Override
                        public void onSuccess(List<DeptList> users) {
                            if (users != null && users.size() > 0) {
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
                                showSingleChoiceDialog();
                            }
                        }
                    });
        });
    }

    private void showSingleChoiceDialog() {
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
}
