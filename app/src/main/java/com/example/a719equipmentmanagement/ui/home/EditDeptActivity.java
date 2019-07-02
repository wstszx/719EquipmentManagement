package com.example.a719equipmentmanagement.ui.home;

import androidx.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.DeptList;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditDeptActivity extends BaseActivity {

    private String[] containerAttrs = {"部门名称:", "负责人:", "联系电话:", "邮箱:", "部门状态:"};
    @BindView(R.id.switchs)
    Switch switchs;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.include_1)
    View include_1;
    @BindView(R.id.include_2)
    View include_2;
    @BindView(R.id.include_3)
    View include_3;
    @BindView(R.id.include_4)
    View include_4;
    @BindView(R.id.include_5)
    View include_5;
    private IncludedLayout includedLayout1;
    private IncludedLayout includedLayout2;
    private IncludedLayout includedLayout3;
    private IncludedLayout includedLayout4;
    private String parentTitle;
    private String deptName;
    private String orderNum;
    private String leader;
    private String phone;
    private String email;
    private String status;
    private int id1;
    private int deptId;

    @Override
    protected void init(Bundle savedInstanceState) {
        initData();
        initView();
        initTopbar();
    }

    static class IncludedLayout {
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.edittext)
        EditText editText;
    }

    private void initView() {
        includedLayout1 = new IncludedLayout();
        includedLayout2 = new IncludedLayout();
        includedLayout3 = new IncludedLayout();
        includedLayout4 = new IncludedLayout();
        ButterKnife.bind(includedLayout1, include_1);
        ButterKnife.bind(includedLayout2, include_2);
        ButterKnife.bind(includedLayout3, include_3);
        ButterKnife.bind(includedLayout4, include_4);
        includedLayout1.tv_title.setText(containerAttrs[0]);
        includedLayout2.tv_title.setText(containerAttrs[1]);
        includedLayout3.tv_title.setText(containerAttrs[2]);
        includedLayout4.tv_title.setText(containerAttrs[3]);
        includedLayout3.editText.setInputType(InputType.TYPE_CLASS_PHONE);
        includedLayout4.editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        TextView textView5 = include_5.findViewById(R.id.tv_title);
        textView5.setText(containerAttrs[4]);
        includedLayout1.editText.setText(deptName);
        includedLayout2.editText.setText(leader);
        includedLayout3.editText.setText(phone);
        includedLayout4.editText.setText(email);
        switch (StringUtils.isEmpty(status) ? "" : status) {
            case "0":
                switchs.setChecked(true);
                break;
            case "1":
                switchs.setChecked(false);
                break;
        }
    }

    private void initData() {
        Intent intent = getIntent();
//        parentTitle = intent.getStringExtra("parentTitle");
        DeptList data = (DeptList) intent.getSerializableExtra("data");
        if (data != null) {
            id1 = data.getId();
            deptName = data.getDeptName();
            orderNum = data.getOrderNum();
            leader = data.getLeader();
            phone = data.getPhone();
            email = data.getEmail();
            status = data.getStatus();
            deptId = data.getDeptId();
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_dept;
    }

    private void initTopbar() {
        topbar.setTitle("编辑部门");
        topbar.addRightTextButton(R.string.save, R.id.save).setOnClickListener(v -> {
            editDept();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void editDept() {
        Map<String, Object> map = new HashMap<>();
        String deptName = includedLayout1.editText.getText().toString();
        String leader = includedLayout2.editText.getText().toString();
        String phone = includedLayout3.editText.getText().toString();
        String email = includedLayout4.editText.getText().toString();

        if (StringUtils.isEmpty(deptName)) {
            ToastUtils.showShort("部门名称不能为空");
            return;
        } else if (StringUtils.isEmpty(leader)) {
            ToastUtils.showShort("负责人不能为空");
            return;
        } else if (!RegexUtils.isEmail(email)) {
            ToastUtils.showShort("请填写正确的邮箱");
            return;
        } else if (!RegexUtils.isMobileExact(phone)) {
            ToastUtils.showShort("请填写正确的手机号");
            return;
        }

        try {
            map.put("deptId", deptId);
            map.put("deptName", deptName);
            map.put("leader", leader);
            map.put("phone", phone);
            map.put("email", email);
            map.put("status", switchs.isChecked() ? "0" : "1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        RetrofitClient.getInstance().getService().editDept(map)
                .compose(CommonCompose.io2main(EditDeptActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(EditDeptActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        int code = baseResponse.getCode();
                        if (code == 0) {
                            ToastUtils.showShort("编辑成功");
                        }
                    }
                });

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, EditDeptActivity.class);
        context.startActivity(starter);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        switch (requestCode) {
//            case EDIT_DEPT:
//                if (data != null) {
//                    name = data.getStringExtra("name");
//                    deptId = data.getIntExtra("id", 0);
//                }
//                break;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
