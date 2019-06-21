package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.view.View;

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
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

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
    private String[] containerAttrs = {"设备名称", "技术指标", "生产厂家", "责任人", "所属部门", "位置", "状态"};

    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    private QMUICommonListItemView listItemView;
    private QMUICommonListItemView item0;
    private QMUICommonListItemView item1;
    private QMUICommonListItemView item2;
    private QMUICommonListItemView item3;
    private QMUICommonListItemView item4;
    private QMUICommonListItemView item5;
    private QMUICommonListItemView item6;
    private int status;
    private String locationName;
    private String deptName;
    private String responsor;
    private String manufactuer;
    private String parameter;
    private String name;
    private String deviceStatus;
    private int equipId;
    private int inventoryId;
    private String equipNo;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
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

    private void setData(DeviceDetailData detailData) {
        DeviceDetailData.DataBean data = detailData.getData();

        DeviceDetailData.DataBean.DeptBean dept = data.getDept();

        DeviceDetailData.DataBean.LocationBean location = data.getLocation();
        equipNo = data.getEquipNo();
        name = data.getName();
        parameter = data.getParameter();
        manufactuer = data.getManufactuer();
        responsor = data.getResponsor();
        deptName = dept == null ? "无部门信息" : dept.getDeptName();
        locationName = location == null ? "无位置信息" : location.getName();
        status = data.getStatus();
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
        initGroupListView();

    }

    private void initView() {
        topbar.setTitle("设备详情");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {

        };

        View.OnClickListener onClickListener4 = v -> {
            listItemView = (QMUICommonListItemView) v;
        };
        View.OnClickListener onClickListener5 = v -> {
            listItemView = (QMUICommonListItemView) v;
        };

        QMUIGroupListView.Section section = QMUIGroupListView.newSection(getContext());


        item0 = groupListView.createItemView(
                null,
                containerAttrs[0],
                name,
                QMUICommonListItemView.HORIZONTAL,
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
