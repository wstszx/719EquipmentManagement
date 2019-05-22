package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.Person;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditPersonActivity extends BaseActivity {

    private String[] containerAttrs = {"用户名称：", "用户性别：", "归属部门：", "手机号码：", "邮箱：",
            "登录账号：", "用户状态：", "岗位：", "角色：", "备注："};
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.switchs)
    Switch switchs;
//    @BindView(R.id.tv_result)
//    TextView tvResult;
    @BindView(R.id.include)
    View include;
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
    @BindView(R.id.include_6)
    View include_6;
    @BindView(R.id.include_7)
    View include_7;
    @BindView(R.id.include_8)
    View include_8;
    @BindView(R.id.include_9)
    View include_9;
    private String userName;
    private String sex;
    private String deptName;
    private String phonenumber;
    private String email;
    private String loginName;
    private String status;
    private String remark;
    private EditDeptActivity.IncludedLayout includedLayout1;
    private EditDeptActivity.IncludedLayout includedLayout2;
    private EditDeptActivity.IncludedLayout includedLayout3;
    private EditDeptActivity.IncludedLayout includedLayout4;
    private EditDeptActivity.IncludedLayout includedLayout;
    private EditDeptActivity.IncludedLayout includedLayout5;
    private TextView textView2;
    private TextView tvResult2;
    private EditDeptActivity.IncludedLayout includedLayout6;
    private EditDeptActivity.IncludedLayout includedLayout7;
    private EditDeptActivity.IncludedLayout includedLayout9;
    private TextView textView8;
    private TextView tvResult8;
    private TextView tv_result1;
    private int id;
    private int deptId;

    @Override
    protected void init(Bundle savedInstanceState) {
        initData();
        initView();
        initTopbar();
    }

    private void initView() {
        includedLayout = new EditDeptActivity.IncludedLayout();
        includedLayout3 = new EditDeptActivity.IncludedLayout();
        includedLayout4 = new EditDeptActivity.IncludedLayout();
        includedLayout5 = new EditDeptActivity.IncludedLayout();
        includedLayout7 = new EditDeptActivity.IncludedLayout();
        includedLayout9 = new EditDeptActivity.IncludedLayout();
        ButterKnife.bind(includedLayout, include);
        ButterKnife.bind(includedLayout3, include_3);
        ButterKnife.bind(includedLayout4, include_4);
        ButterKnife.bind(includedLayout5, include_5);
        ButterKnife.bind(includedLayout7, include_7);
        ButterKnife.bind(includedLayout9, include_9);
//        0
        includedLayout.tv_title.setText(containerAttrs[0]);
        includedLayout.editText.setText(userName);
//        1
        TextView textView1 = include_1.findViewById(R.id.tv_title);
        tv_result1 = include_1.findViewById(R.id.tv_result);
        textView1.setText(containerAttrs[1]);
        switch (sex) {
            case "0":
                tv_result1.setText("男");
                break;
            case "1":
                tv_result1.setText("女");
                break;
            case "2":
                tv_result1.setText("未知");
                break;
        }
//        2
        textView2 = include_2.findViewById(R.id.tv_title);
        tvResult2 = include_2.findViewById(R.id.tv_result);
        textView2.setText(containerAttrs[2]);
        tvResult2.setText(deptName);
//        3
        includedLayout3.tv_title.setText(containerAttrs[3]);
        includedLayout3.editText.setText(phonenumber);
//        4
        includedLayout4.tv_title.setText(containerAttrs[4]);
        includedLayout4.editText.setText(email);
//        5
        includedLayout5.tv_title.setText(containerAttrs[5]);
        includedLayout5.editText.setText(loginName);
//        6
        TextView textView6 = include_6.findViewById(R.id.tv_title);
        textView6.setText(containerAttrs[6]);
        switch (status) {
            case "0":
                switchs.setChecked(true);
                break;
            case "1":
                switchs.setChecked(false);
                break;
        }
//        7
        includedLayout7.tv_title.setText(containerAttrs[7]);
        includedLayout7.editText.setText("");
//        8
        textView8 = include_8.findViewById(R.id.tv_title);
        tvResult8 = include_8.findViewById(R.id.tv_result);
        textView8.setText(containerAttrs[8]);
        tvResult8.setText(deptName);
//        9
        includedLayout9.tv_title.setText(containerAttrs[9]);
        includedLayout9.editText.setText(remark);
    }

    private void initData() {
        Intent intent = getIntent();
        User.ListBean listBean = (User.ListBean) intent.getSerializableExtra("listBean");
        id = listBean.getId();
        deptId = listBean.getDeptId();
        userName = listBean.getUserName();
        sex = listBean.getSex();
        deptName = listBean.getDept().getDeptName();
        phonenumber = listBean.getPhonenumber();
        email = listBean.getEmail();
        loginName = listBean.getLoginName();
        status = listBean.getStatus();
        Object roleIds = listBean.getRoleIds();
        remark = listBean.getRemark();
    }

    static class IncludedLayout {
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.edittext)
        EditText editText;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_person;
    }

    private void initTopbar() {
        topbar.setTitle("编辑人员");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            editPerson();

        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void editPerson() {
        Map<String, Object> map = new HashMap<>();
        String username = includedLayout.editText.getText().toString();
        String dept = tvResult2.getText().toString();
        String phoneNum = includedLayout3.editText.getText().toString();
        String email = includedLayout4.editText.getText().toString();
        String loginName = includedLayout5.editText.getText().toString();
        String postion = includedLayout7.editText.getText().toString();
        try {
            map.put("userName", username);
            map.put("userId", id);
            map.put("deptId", deptId);
            map.put("deptName", dept);
            map.put("loginName", loginName);
            map.put("phone", phoneNum);
            map.put("email", email);
            map.put("status", switchs.isChecked() ? "0" : "1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        RetrofitClient.getInstance().getService().editUser(map)
                .compose(CommonCompose.io2main(EditPersonActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(EditPersonActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                    }
                });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, EditPersonActivity.class);
        context.startActivity(starter);
    }

}
