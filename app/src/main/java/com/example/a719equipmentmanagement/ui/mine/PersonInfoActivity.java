package com.example.a719equipmentmanagement.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonInfoActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    private QMUICommonListItemView listItemView;
    private String[] containerAttrs = {"姓名", "性别", "身份", "联系方式"};

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        initTopbar();
        initGroupListView();
        initShowInfo();
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

        QMUICommonListItemView item = groupListView.createItemView(
                null,
                containerAttrs[0],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(0);
        section.addItemView(item, onClickListener);

        QMUICommonListItemView item1 = groupListView.createItemView(
                null,
                containerAttrs[1],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(1);
        section.addItemView(item1, onClickListener);

        QMUICommonListItemView item2 = groupListView.createItemView(
                null,
                containerAttrs[2],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(2);
        section.addItemView(item2, onClickListener);

        QMUICommonListItemView item3 = groupListView.createItemView(
                null,
                containerAttrs[3],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(3);
        section.addItemView(item3, onClickListener);

        section.addTo(groupListView);
    }

    private void initShowInfo() {

    }

    private void initTopbar() {
        topbar.setTitle("个人信息");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_info;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, PersonInfoActivity.class);
        context.startActivity(starter);
    }

}
