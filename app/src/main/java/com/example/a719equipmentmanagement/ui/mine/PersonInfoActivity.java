package com.example.a719equipmentmanagement.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.Me;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
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
    private Me baseResponse;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        initTopbar();
        initData();

    }

    private void initData() {
        RetrofitClient.getInstance().getService().getMe()
                .compose(CommonCompose.io2main(PersonInfoActivity.this))
                .subscribe(new BaseSubscriber<Me>(PersonInfoActivity.this) {

                    @Override
                    public void onSuccess(Me baseResponse) {
                        if (baseResponse != null) {
                            PersonInfoActivity.this.baseResponse = baseResponse;
                            initGroupListView();
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

        QMUICommonListItemView item = groupListView.createItemView(
                null,
                containerAttrs[0],
                baseResponse.getUser().getUserName(),
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(0);
        section.addItemView(item, onClickListener);

        QMUICommonListItemView item1 = groupListView.createItemView(
                null,
                containerAttrs[1],
                baseResponse.getUser().getSex(),
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(1);
        section.addItemView(item1, onClickListener);

        QMUICommonListItemView item2 = groupListView.createItemView(
                null,
                containerAttrs[2],
                baseResponse.getUser().getRoles().get(0).getRoleName(),
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(2);
        section.addItemView(item2, onClickListener);

        QMUICommonListItemView item3 = groupListView.createItemView(
                null,
                containerAttrs[3],
                baseResponse.getUser().getPhonenumber(),
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(3);
        section.addItemView(item3, onClickListener);

        section.addTo(groupListView);
    }


    private void initTopbar() {
        topbar.setTitle("个人信息");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
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
