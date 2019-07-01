package com.example.a719equipmentmanagement.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.Me;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PersonInfoActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.tv_result1)
    TextView tvResult1;
    @BindView(R.id.tv_result2)
    TextView tvResult2;
    @BindView(R.id.edittext3)
    EditText edittext3;
    @BindView(R.id.tv_result4)
    TextView tvResult4;
    @BindView(R.id.tv_result5)
    TextView tvResult5;
    @BindView(R.id.edittext6)
    EditText edittext6;
    private Me me;
    private int userId;
    private String[] sexArray = {"男", "女", "未知"};
    private int sexId;
    private int roleId;

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
                    public void onSuccess(Me me) {
                        if (me != null) {
                            PersonInfoActivity.this.me = me;
                            Me.UserBean user = me.getUser();
                            if (user != null) {
                                userId = user.getUserId();
                                String userName = user.getUserName();
                                String loginName = user.getLoginName();
                                String email = user.getEmail();
                                Me.UserBean.DeptBean dept = user.getDept();
                                if (dept != null) {
                                    String deptName = dept.getDeptName();
                                    tvResult4.setText(deptName);
                                }
                                String sex = user.getSex();
                                edittext6.setText(email);
                                List<Me.UserBean.RolesBean> roles = user.getRoles();
                                if (roles != null && roles.size() > 0) {
                                    roleId = user.getRoles().get(0).getRoleId();
                                }
                                String phonenumber = user.getPhonenumber();
                                edittext.setText(userName);
                                tvResult1.setText(sex);
                                tvResult5.setText(loginName);
                                switch (roleId) {
                                    case 1:
                                        tvResult2.setText("超级系统管理员");
                                        break;
                                    case 2:
                                        tvResult2.setText("实验室管理员");
                                        break;
                                    case 3:
                                        tvResult2.setText("普通用户");
                                        break;
                                }
                                edittext3.setText(phonenumber);
                            }
                        }
                    }
                });
    }

    private void initTopbar() {
        topbar.setTitle("个人信息");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
        topbar.addRightTextButton(R.string.save, R.id.save).setOnClickListener(v -> {
            savePersonInfo();
            finish();
        });
    }

    private void savePersonInfo() {
        String userName = edittext.getText().toString();
        String phonenumber = edittext3.getText().toString();
        String email = edittext6.getText().toString();
        if (!RegexUtils.isMobileExact(phonenumber)) {
            ToastUtils.showShort("请填写正确的手机号");
            return;
        }

        if (StringUtils.isEmpty(userName)) {
            ToastUtils.showShort("请填写用户名称");
            return;
        }

        if (StringUtils.isEmpty(email)) {
            ToastUtils.showShort("请填写邮箱");
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("sex", sexId);
        map.put("phonenumber", phonenumber);
        map.put("email", phonenumber);
        RetrofitClient.getInstance().getService().updataUserData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(PersonInfoActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        if (response != null) {
                            int code = response.getCode();
                            if (code == 0) {
                                ToastUtils.showShort("保存成功");
                            }
                        }
                    }
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


    @OnClick({R.id.constraint1})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.constraint1) {
            showSimpleBottomSheetList(sexArray, 1);
        }
    }

    private void showSimpleBottomSheetList(String[] array, int flag) {
        QMUIBottomSheet.BottomListSheetBuilder bottomListSheetBuilder = new QMUIBottomSheet.BottomListSheetBuilder(this);
        for (String s : array) {
            bottomListSheetBuilder.addItem(s != null ? s : "未知");
        }

        bottomListSheetBuilder.setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
            switch (flag) {
                case 1:
                    sexId = position;
                    tvResult1.setText(tag);
                    break;
                case 2:
                    roleId = position;
                    tvResult2.setText(tag);
                    break;
            }
            dialog.dismiss();
        })
                .build()
                .show();
    }

}
