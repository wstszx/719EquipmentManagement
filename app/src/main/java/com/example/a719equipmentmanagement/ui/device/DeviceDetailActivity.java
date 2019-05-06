package com.example.a719equipmentmanagement.ui.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.ui.home.GenarateQRActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;

public class DeviceDetailActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;

    private QMUICommonListItemView listItemView;

    private String[] containerAttrs = {"属性", "属性", "属性", "属性", "属性", "属性", "属性", "属性", "属性"};
    private String[] containerAttrValue = {"数据", "数据", "数据", "数据", "数据", "数据",
            "数据", "数据", "数据"};


    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initGroupListView();
    }

    private void initTopbar() {
        topbar.setTitle("设备详情");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            //                    ?????????
//            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
        topbar.addRightImageButton(R.mipmap.qrcode, R.id.qrcode).setOnClickListener(v -> {
            GenarateQRActivity.start(this);
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
        for (int i = 0; i < containerAttrs.length; i++) {
            QMUICommonListItemView item = groupListView.createItemView(
                    null,
                    containerAttrs[i]+(i+1),
                    containerAttrValue[i]+(i+1),
                    QMUICommonListItemView.HORIZONTAL,
                    QMUICommonListItemView.ACCESSORY_TYPE_NONE);
            item.setTag(i);
            section.addItemView(item, onClickListener);
        }
        section.addTo(groupListView);

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, DeviceDetailActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_detail;
    }
}
