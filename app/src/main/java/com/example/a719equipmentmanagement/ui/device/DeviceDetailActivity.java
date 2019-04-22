package com.example.a719equipmentmanagement.ui.device;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseEditActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;

public class DeviceDetailActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;

    private String[] containerAttrs = {"货柜名称", "所属科室", "购置时间", "长度", "宽度", "高度",
            "层数", "最大承重", "当前存放设备数量"};
    private String[] containerAttrValue = {"货柜05", "三科室", "2019-7-9", "200cm", "60cm", "200cm",
            "3", "200kg", "100个"};

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initGroupListView();
    }

    private void initTopbar() {
        topbar.setTitle("设备详情");
        topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
//            listItemView = (QMUICommonListItemView) v;
//            int tag = (int) listItemView.getTag();
//            Intent intent = new Intent();
//            intent.putExtra("text", listItemView.getDetailText().toString());
//            intent.setClass(this, BaseEditActivity.class);
//            startActivityForResult(intent, tag);
        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
        for (int i = 0; i < containerAttrs.length; i++) {
            QMUICommonListItemView item = groupListView.createItemView(
                    null,
                    containerAttrs[i],
                    containerAttrValue[i],
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
