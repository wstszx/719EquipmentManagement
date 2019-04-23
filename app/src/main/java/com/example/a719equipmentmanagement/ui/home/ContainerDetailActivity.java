package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseEditActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContainerDetailActivity extends BaseActivity {

    private String[] containerAttrs = {"货柜名称", "所属科室", "购置时间", "长度", "宽度", "高度",
            "层数", "最大承重", "当前存放设备数量"};
    private String[] containerAttrValue = {"货柜05", "三科室", "2019-7-9", "200cm", "60cm", "200cm",
            "3", "200kg", "100个"};
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private QMUICommonListItemView listItemView;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initGroupListView();
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
            listItemView = (QMUICommonListItemView) v;
            int tag = (int) listItemView.getTag();
            Intent intent = new Intent();
            intent.putExtra("text", listItemView.getDetailText().toString());
            intent.setClass(this, BaseEditActivity.class);
            startActivityForResult(intent, tag);
        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
        for (int i = 0; i < containerAttrs.length; i++) {
            QMUICommonListItemView item = groupListView.createItemView(
                    null,
                    containerAttrs[i],
                    containerAttrValue[i],
                    QMUICommonListItemView.HORIZONTAL,
                    QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
            item.setTag(i);
            section.addItemView(item, onClickListener);
        }
        section.addTo(groupListView);

    }

    private void initTopbar() {
        topbar.setTitle("货柜详情");
        topbar.addRightImageButton(R.mipmap.qrcode, R.id.qrcode).setOnClickListener(v -> {
            GenarateQRActivity.start(this);
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
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
        return R.layout.activity_container_detail;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ContainerDetailActivity.class);
        context.startActivity(starter);
    }

}