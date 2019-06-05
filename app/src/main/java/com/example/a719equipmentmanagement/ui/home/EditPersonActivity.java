package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.clj.fastble.data.BleDevice;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.Person;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.utils.NumUtils;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditPersonActivity extends BaseActivity {

    private static final int OWNER_DEPT = 1;
    private String[] containerAttrs = {"用户名称：", "用户性别：", "归属部门：", "手机号码：", "邮箱：",
            "登录账号：", "用户状态：", "角色：", "备注："};
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.switchs)
    Switch switchs;
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
    @BindView(R.id.include_8)
    View include_8;
    @BindView(R.id.tv_title9)
    TextView tv_title9;
    @BindView(R.id.edittext9)
    EditText edittext9;
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
    private TextView textView8;
    private TextView tvResult8;
    private TextView tv_result1;
    private int userId;
    private int deptId;
    private String[] sexArray = {"男", "女", "未知"};
    private String[] roleArray = {"超级系统管理员", "普通用户", "实验室管理员"};
    private int id;
    private String name;
    private int roleId;
    private int sexTag;


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
        ButterKnife.bind(includedLayout, include);
        ButterKnife.bind(includedLayout3, include_3);
        ButterKnife.bind(includedLayout4, include_4);
        ButterKnife.bind(includedLayout5, include_5);
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
        include_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleBottomSheetList(sexArray, 1);
            }
        });
//        2
        textView2 = include_2.findViewById(R.id.tv_title);
        tvResult2 = include_2.findViewById(R.id.tv_result);
        textView2.setText(containerAttrs[2]);
        tvResult2.setText(deptName);
        include_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(EditPersonActivity.this, ChoiceDeptActivity.class);
                startActivityForResult(intent, OWNER_DEPT);
            }
        });
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

//        8
        textView8 = include_8.findViewById(R.id.tv_title);
        tvResult8 = include_8.findViewById(R.id.tv_result);
        textView8.setText(containerAttrs[7]);
        tvResult8.setText("");
        include_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleBottomSheetList(roleArray, 2);
            }
        });
//        9
        tv_title9.setText(containerAttrs[8]);
        edittext9.setText(remark);
    }

    private void showSimpleBottomSheetList(String[] array, int flag) {
        QMUIBottomSheet.BottomListSheetBuilder bottomListSheetBuilder = new QMUIBottomSheet.BottomListSheetBuilder(this);
        for (String s : array) {
            bottomListSheetBuilder.addItem(s != null ? s : "未知");
        }

        bottomListSheetBuilder.setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {

            @Override
            public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                roleId = position;
                switch (flag) {
                    case 1:
                        sexTag = position;
                        tv_result1.setText(tag);
                        break;
                    case 2:
                        tvResult8.setText(tag);
                        break;
                }
                dialog.dismiss();
            }
        })
                .build()
                .show();
    }

    private void initData() {
        Intent intent = getIntent();
        User.UsersBean listBean = (User.UsersBean) intent.getSerializableExtra("data");
        userId = listBean.getUserId();
        deptId = listBean.getDeptId();
//        roleId = listBean.getRoleIds();
        userName = listBean.getUserName();
        sex = listBean.getSex();
        deptName = listBean.getDept().getDeptName();
        phonenumber = listBean.getPhonenumber();
        email = listBean.getEmail();
        loginName = listBean.getLoginName();
        status = listBean.getStatus();
        remark = listBean.getRemark();
    }

    static class IncludedLayout {
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.edittext)
        EditText editText;
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        if (data != null) {
            id = data.getIntExtra("id", 0);
            name = data.getStringExtra("name");
            tvResult2.setText(name);
        }
        super.onActivityReenter(resultCode, data);
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
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
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
        String remark = edittext9.getText().toString();
        if (!RegexUtils.isEmail(email)) {
            ToastUtils.showShort("请填写正确的邮箱");
            return;
        }

        if (!RegexUtils.isMobileExact(phoneNum)) {
            ToastUtils.showShort("请填写正确的手机号");
            return;
        }

        try {
            map.put("userId", userId);
            map.put("userName", username);
            map.put("sex", sexTag);
            map.put("deptId", deptId);
            map.put("deptName", dept);
            map.put("phonenumber", phoneNum);
            map.put("email", email);
            map.put("loginName", loginName);
            map.put("status", switchs.isChecked() ? "0" : "1");
            map.put("role", roleId);
            map.put("remark", remark);
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
        setResult(RESULT_OK);
        finish();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, EditPersonActivity.class);
        context.startActivity(starter);
    }

}
