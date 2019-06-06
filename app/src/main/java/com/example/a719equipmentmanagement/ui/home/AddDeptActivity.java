package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.utils.NumUtils;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeptActivity extends BaseActivity {

    private String[] containerAttrs = {"部门名称:", "显示排序:",
            "负责人:", "联系电话:", "邮箱:", "部门状态:"};
    @BindView(R.id.switchs)
    Switch switchs;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.include_1)
    View include_1;
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

    private void initView() {
        includedLayout1 = new IncludedLayout();
        includedLayout3 = new IncludedLayout();
        includedLayout4 = new IncludedLayout();
        includedLayout5 = new IncludedLayout();
        ButterKnife.bind(includedLayout1, include_1);
        ButterKnife.bind(includedLayout3, include_4);
        ButterKnife.bind(includedLayout4, include_5);
        ButterKnife.bind(includedLayout5, include_6);
        includedLayout1.tv_title.setText(containerAttrs[0]);
        includedLayout3.tv_title.setText(containerAttrs[2]);
        includedLayout4.tv_title.setText(containerAttrs[3]);
        includedLayout5.tv_title.setText(containerAttrs[4]);
        includedLayout4.editText.setInputType(InputType.TYPE_CLASS_PHONE);
        includedLayout5.editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        TextView textView7 = include_7.findViewById(R.id.tv_title);
        textView7.setText(containerAttrs[5]);
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

        if (!RegexUtils.isEmail(email)) {
            ToastUtils.showShort("请填写正确的邮箱");
            return;
        }

        if (!RegexUtils.isMobileExact(phone)) {
            ToastUtils.showShort("请填写正确的手机号");
            return;
        }

        try {
            map.put("deptName", deptName);
            int pid = 100;
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
