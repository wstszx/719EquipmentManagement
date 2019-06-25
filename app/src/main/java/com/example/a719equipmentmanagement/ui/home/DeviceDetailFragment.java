package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.DeviceData2;
import com.example.a719equipmentmanagement.entity.DeviceDetailData;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.view.AuditDialog;
import com.example.a719equipmentmanagement.view.ReturnInspectionDialog;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeviceDetailFragment extends BaseFragment {
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
    private String categoryName;
    private int locationId;
    private int deptId;
    private int categoryId;
    private int inventoryId;
    private String equipNo;
    private String deviceName;


    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initView();
        initData();
    }

    private void initTopbar() {
        topbar.setTitle("设备详情");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            equipId = bundle.getInt("equipId");
            inventoryId = bundle.getInt("inventoryId");

            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonArray.put(jsonObject);
                jsonObject.put("inventoryId", inventoryId);
                jsonObject.put("equipId", equipId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonArray.toString());
            Single<BaseResponse> baseResponseSingle = RetrofitClient.getInstance().getService().saveInventoryDevice(requestBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            Single<DeviceDetailData> deviceDetailDataSingle = RetrofitClient.getInstance().getService().getDeviceDetail(equipId + "")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            Single.zip(baseResponseSingle, deviceDetailDataSingle, (response, detailData) -> {
                if (detailData != null) {
                    setData(detailData);
                }
                if (response.getCode() == 0) {
                    ToastUtils.showShort("保存盘点设备");
                }
                return new Object();
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseSubscriber<Object>(getContext()) {

                    });
        }
    }

    private void setData(DeviceDetailData deviceDetailData) {
        DeviceDetailData.DataBean data = deviceDetailData.getData();
        if (data != null) {
            deviceName = data.getName();
            equipId = data.getId();
            String equipNo = data.getEquipNo();
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

            edittext.setText(deviceName);
            tvResult1.setText(categoryName);
            edittext2.setText(equipNo);
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

    private void initView() {
//        returnInspectionDialog = new ReturnInspectionDialog(getContext());
//        auditDialog = new AuditDialog(getContext());
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
        return R.layout.fragment_device_detail;
    }


    @OnClick({R.id.tv_cancel, R.id.tv_complete, R.id.tv_continue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                cancelInventory(view);
                break;
            case R.id.tv_complete:
                completeInventory(view);
                break;
            case R.id.tv_continue:
                Bundle bundle = new Bundle();
                bundle.putInt("inventoryId", inventoryId);
                NavHostFragment.findNavController(DeviceDetailFragment.this).navigate(R.id.scanFragment, bundle);
                break;
        }
    }


    /**
     * 完成盘点
     */
    private void completeInventory(View view) {
        RetrofitClient.getInstance().getService().setEndInventory(inventoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(getContext()) {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        if (response.getCode() == 0) {
                            Bundle bundle = new Bundle();
                            bundle.putString("title", "盘点完成");
                            Navigation.findNavController(view).navigate(R.id.resultFragment, bundle);
                        }
                    }
                });
    }

    /**
     * 取消盘点
     */
    private void cancelInventory(View view) {
        RetrofitClient.getInstance().getService().cancelInventoryTask(inventoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(getContext()) {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        if (response.getCode() == 0) {
                            Bundle bundle = new Bundle();
                            bundle.putString("title", "盘点取消成功");
                            Navigation.findNavController(view).navigate(R.id.resultFragment, bundle);
                        }
                    }
                });
    }
}
