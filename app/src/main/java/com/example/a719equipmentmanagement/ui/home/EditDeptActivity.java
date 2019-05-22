package com.example.a719equipmentmanagement.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.utils.NumUtils;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditDeptActivity extends BaseActivity {

    private static final int EDIT_DEPT = 1;
    private String[] containerAttrs = {"部门名称:", "所属部门:", "显示排序:",
            "负责人:", "联系电话:", "邮箱:", "部门状态:"};
    @BindView(R.id.switchs)
    Switch switchs;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.tv_result)
    TextView tvResult;
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
    private IncludedLayout includedLayout1;
    private IncludedLayout includedLayout2;
    private IncludedLayout includedLayout3;
    private IncludedLayout includedLayout4;
    private IncludedLayout includedLayout5;
    private TextView tv_result;
    private String name;
    private int id;
    private String parentTitle;
    private String deptName;
    private String orderNum;
    private String leader;
    private String phone;
    private String email;
    private String status;
    private int id1;

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
        includedLayout5 = new IncludedLayout();
        ButterKnife.bind(includedLayout1, include_1);
        ButterKnife.bind(includedLayout2, include_3);
        ButterKnife.bind(includedLayout3, include_4);
        ButterKnife.bind(includedLayout4, include_5);
        ButterKnife.bind(includedLayout5, include_6);
        includedLayout1.tv_title.setText(containerAttrs[0]);
        TextView textView2 = include_2.findViewById(R.id.tv_title);
        tv_result = include_2.findViewById(R.id.tv_result);
        textView2.setText(containerAttrs[1]);
        include_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(EditDeptActivity.this, ChoiceDeptActivity.class), EDIT_DEPT);
            }
        });
        includedLayout2.tv_title.setText(containerAttrs[2]);
        includedLayout3.tv_title.setText(containerAttrs[3]);
        includedLayout4.tv_title.setText(containerAttrs[4]);
        includedLayout5.tv_title.setText(containerAttrs[5]);
        includedLayout2.editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        includedLayout4.editText.setInputType(InputType.TYPE_CLASS_PHONE);
        includedLayout5.editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        TextView textView7 = include_7.findViewById(R.id.tv_title);
        textView7.setText(containerAttrs[6]);
        includedLayout1.editText.setText(deptName);
        tvResult.setText(parentTitle);
        includedLayout2.editText.setText(orderNum);
        includedLayout3.editText.setText(leader);
        includedLayout4.editText.setText(phone);
        includedLayout5.editText.setText(email);
        switch (status) {
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
        parentTitle = intent.getStringExtra("parentTitle");
        User data = (User) intent.getSerializableExtra("data");
        id1 = data.getId();
        deptName = data.getDeptName();
        orderNum = data.getOrderNum();
        leader = data.getLeader();
        phone = data.getPhone();
        email = data.getEmail();
        status = data.getStatus();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_dept;
    }

    private void initTopbar() {
        topbar.setTitle("编辑部门");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            editDept();

        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void editDept() {
        Map<String, Object> map = new HashMap<>();
        String deptName = includedLayout1.editText.getText().toString();
        String orderNum = includedLayout2.editText.getText().toString();
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

        if (!NumUtils.isInteger(orderNum)) {
            ToastUtils.showShort("请填写正确的显示排序");
            return;
        }
        try {
            map.put("deptId", id1);
            map.put("deptName", deptName);
            map.put("parentId", id);
            map.put("leader", leader);
            map.put("orderNum", orderNum);
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
                        setResult(RESULT_OK, new Intent());
                        finish();
                    }
                });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, EditDeptActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case EDIT_DEPT:
                if (data != null) {
                    name = data.getStringExtra("name");
                    id = data.getIntExtra("id", 0);
                    tv_result.setText(name);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
