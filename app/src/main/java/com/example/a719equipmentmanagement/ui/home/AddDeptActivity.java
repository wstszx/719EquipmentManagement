package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.device.DeviceDetailActivity2;
import com.example.a719equipmentmanagement.utils.NumUtils;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeptActivity extends BaseActivity {

    private static final int DEPT_TYPE = 1;
    private String[] containerAttrs = {"部门名称:", "所属部门:",
            "负责人:", "联系电话:", "邮箱:", "部门状态:"};
    @BindView(R.id.switchs)
    Switch switchs;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.include_1)
    View include_1;
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
    private IncludedLayout includedLayout1;
    private IncludedLayout includedLayout3;
    private IncludedLayout includedLayout4;
    private IncludedLayout includedLayout5;
    private IncludedLayout1 includedLayout2;
    private int pid;
    private int deptId;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initTopbar();
    }


    static class IncludedLayout {
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.edittext)
        EditText editText;
    }

    static class IncludedLayout1 {
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_result)
        TextView tv_result;
    }

    private void initView() {
        includedLayout1 = new IncludedLayout();
        includedLayout2 = new IncludedLayout1();
        includedLayout3 = new IncludedLayout();
        includedLayout4 = new IncludedLayout();
        includedLayout5 = new IncludedLayout();
        ButterKnife.bind(includedLayout1, include_1);
        ButterKnife.bind(includedLayout2, include_3);
        ButterKnife.bind(includedLayout3, include_4);
        ButterKnife.bind(includedLayout4, include_5);
        ButterKnife.bind(includedLayout5, include_6);
        includedLayout1.tv_title.setText(containerAttrs[0]);
        includedLayout2.tv_title.setText(containerAttrs[1]);
        includedLayout3.tv_title.setText(containerAttrs[2]);
        includedLayout4.tv_title.setText(containerAttrs[3]);
        includedLayout5.tv_title.setText(containerAttrs[4]);
        includedLayout4.editText.setInputType(InputType.TYPE_CLASS_PHONE);
        includedLayout5.editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        TextView textView7 = include_7.findViewById(R.id.tv_title);
        textView7.setText(containerAttrs[5]);
        include_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AddDeptActivity.this, ChoiceDeptActivity2.class), DEPT_TYPE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
//                intent.putExtra("pid", pid);
//                intent.putExtra("name", name);
//                intent.putExtra("id", deptId);
                pid = data.getIntExtra("pid", 0);
                String name = data.getStringExtra("name");
                deptId = data.getIntExtra("id", 0);
                includedLayout2.tv_result.setText(StringUtils.isEmpty(name) ? "" : name);
            }
        }
    }

    private void initTopbar() {
        topbar.setTitle("添加部门");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            getInputData();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void getInputData() {
        Map<String, Object> map = new HashMap<>();
        String deptName = includedLayout1.editText.getText().toString();
        String leader = includedLayout3.editText.getText().toString();
        String email = includedLayout5.editText.getText().toString();
        String phone = includedLayout4.editText.getText().toString();
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
            map.put("deptName", deptName);
//            int pid = 100;
            map.put("parentId", pid);
            map.put("leader", leader);
            map.put("phone", phone);
            map.put("email", email);
            map.put("status", switchs.isChecked() ? "0" : "1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        RetrofitClient.getInstance().getService().addDept(map)
                .compose(CommonCompose.io2main(AddDeptActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(AddDeptActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        setResult(RESULT_OK, new Intent());
                        finish();
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_dept;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AddDeptActivity.class);
        context.startActivity(starter);
    }

}
