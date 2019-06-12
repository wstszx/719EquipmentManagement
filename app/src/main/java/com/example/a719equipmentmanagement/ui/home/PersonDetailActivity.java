package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.DeptList;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.io.Serializable;

import butterknife.BindView;

public class PersonDetailActivity extends BaseActivity {

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private String[] containerAttrs = {"上级部门", "部门名称", "显示排序", "负责人", "联系电话", "邮箱", "部门状态"};
    private String[] containerAttrValue = {"张三", "三科室", "普通用户"};
    private QMUICommonListItemView listItemView;
    private String heightDept = "";
    private String deptName;
    private String orderNum;
    private String leader;
    private String phone;
    private String email;
    private String status;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
        initGroupListView();
    }

    private void initData() {
        Intent intent = getIntent();
        DeptList deptList = (DeptList) intent.getSerializableExtra("serializable");
        deptName = deptList.getDeptName();
        orderNum = deptList.getOrderNum();
        leader = deptList.getLeader();
        phone = deptList.getPhone();
        email = deptList.getEmail();
        status = deptList.getStatus();
        switch (status) {
            case "0":
                status = "正常";
                break;
            case "1":
                status = "停用";
                break;
        }


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_detail;
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
            if (((QMUICommonListItemView) v).getAccessoryType() == QMUICommonListItemView.ACCESSORY_TYPE_SWITCH) {
                ((QMUICommonListItemView) v).getSwitch().toggle();
            }
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
                heightDept != null ? heightDept : " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(0);
        section.addItemView(item, onClickListener);

        QMUICommonListItemView item1 = groupListView.createItemView(
                null,
                containerAttrs[1],
                deptName != null ? deptName : " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(1);
        section.addItemView(item1, onClickListener);

        QMUICommonListItemView item2 = groupListView.createItemView(
                null,
                containerAttrs[2],
                orderNum != null ? orderNum : " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(2);
        section.addItemView(item2, onClickListener);

        QMUICommonListItemView item3 = groupListView.createItemView(
                null,
                containerAttrs[3],
                leader != null ? leader : " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(3);
        section.addItemView(item3, onClickListener);

        QMUICommonListItemView item4 = groupListView.createItemView(
                null,
                containerAttrs[4],
                phone != null ? phone : " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(4);
        section.addItemView(item4, onClickListener);

        QMUICommonListItemView item5 = groupListView.createItemView(
                null,
                containerAttrs[5],
                email != null ? email : " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(5);
        section.addItemView(item5, onClickListener);

        QMUICommonListItemView item6 = groupListView.createItemView("部门状态");
        item6.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        item6.getSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        section.addItemView(item6, onClickListener);
        section.addTo(groupListView);

    }

    private void initTopbar() {
        topbar.setTitle("人员详情");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
        Button rightTextButton = topbar.addRightTextButton("保存", R.id.save);
        rightTextButton.setTextColor(getResources().getColor(R.color.blue));
        rightTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePersonDetail();
                finish();
            }
        });
    }

    /**
     * 更新用户详情
     */
    private void updatePersonDetail() {
//        RetrofitClient.getInstance().getService().editUser()
//                .compose(CommonCompose.io2main(PersonDetailActivity.this))
//                .subscribe(new BaseSubscriber<BaseResponse>(PersonDetailActivity.this) {
//                    @Override
//                    public void onSuccess(BaseResponse baseResponse) {
//                        ToastUtils.showShort("更新成功");
//                    }
//                });
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

    public static void start(Context context, Serializable serializable) {
        Intent starter = new Intent(context, PersonDetailActivity.class);
        starter.putExtra("serializable", serializable);
        context.startActivity(starter);
    }
}
