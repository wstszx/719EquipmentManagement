package com.example.a719equipmentmanagement.ui.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.DeviceData;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.home.GenarateQRActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

public class DeviceDetailActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    private String deviceStatus;

    private QMUICommonListItemView listItemView;
    private QMUICommonListItemView item0;
    private QMUICommonListItemView item1;
    private QMUICommonListItemView item2;
    private QMUICommonListItemView item3;
    private QMUICommonListItemView item4;
    private QMUICommonListItemView item5;
    private QMUICommonListItemView item6;

    private String[] containerAttrs = {"设备名称", "技术指标", "生产厂家", "责任人", "所属部门", "位置", "状态"};
    private String name;
    private String parameter;
    private String responsor;
    //    private String[] containerAttrValue = {"数据", "数据", "数据", "数据", "数据", "数据",
//            "数据", "数据", "数据"};


    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
        initGroupListView();
    }

    private void initData() {
        Intent intent = getIntent();
        DeviceData.RowsBean rowsBean = (DeviceData.RowsBean) intent.getSerializableExtra("serializable");
        name = rowsBean.getName();
        parameter = rowsBean.getParameter();
        responsor = rowsBean.getResponsor();
        int status = rowsBean.getStatus();
        switch (status) {
            case 0:
                deviceStatus = "可用";
                break;
            case 1:
                deviceStatus = "封存";
                break;
            case 2:
                deviceStatus = "占用";
                break;
            case 3:
                deviceStatus = "报废";
                break;
        }
    }

    private void initTopbar() {
        topbar.setTitle("设备详情");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            //                    ?????????
//            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
            GenarateQRActivity.start(this);
        });

        RetrofitClient.getInstance().getService().findDeviceData()
                .compose(CommonCompose.io2main(this))
                .subscribe(new BaseSubscriber<DeviceData>(this) {
                    @Override
                    public void onSuccess(DeviceData deviceData) {
                        if (deviceData != null) {
                            List<DeviceData.RowsBean> rows = deviceData.getRows();
                            if (rows != null && rows.size() > 0) {
//                                adapter.setNewData(rows);

                            }
                        }
                    }
                });

    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
            listItemView = (QMUICommonListItemView) v;
            int tag = (int) listItemView.getTag();
            Intent intent = new Intent();
            intent.putExtra("text", listItemView.getDetailText().toString());
            intent.setClass(this, BaseItemEditActivity.class);
            startActivityForResult(intent, tag);
        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);

        item0 = groupListView.createItemView(
                null,
                containerAttrs[0],
                name,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
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
                " ",
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
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item4.setTag(4);
        section.addItemView(item4, onClickListener);
        item5 = groupListView.createItemView(
                null,
                containerAttrs[5],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item5.setTag(5);
        section.addItemView(item5, onClickListener);
        item6 = groupListView.createItemView(
                null,
                containerAttrs[6],
                deviceStatus,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item6.setTag(6);
        section.addItemView(item6, onClickListener);

        section.addTo(groupListView);

//        for (int i = 0; i < containerAttrs.length; i++) {
//            QMUICommonListItemView item = groupListView.createItemView(
//                    null,
//                    containerAttrs[i],
//                    containerAttrValue[i]+(i+1),
//                    QMUICommonListItemView.HORIZONTAL,
//                    QMUICommonListItemView.ACCESSORY_TYPE_NONE);
//            item.setTag(i);
//            section.addItemView(item, onClickListener);
//        }
//        section.addTo(groupListView);

    }

    public static void start(Context context, Serializable serializable) {
        Intent starter = new Intent(context, DeviceDetailActivity.class);
        starter.putExtra("serializable", serializable);
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
}
