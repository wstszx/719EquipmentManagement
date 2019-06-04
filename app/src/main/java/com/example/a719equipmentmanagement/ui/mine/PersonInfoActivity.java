package com.example.a719equipmentmanagement.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    private Me baseResponse;
    private int id;
    private String[] sexArray = {"男", "女", "未知"};
    private String[] roleArray = {"超级系统管理员", "普通用户", "实验室管理员"};
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
                    public void onSuccess(Me baseResponse) {
                        if (baseResponse != null) {
                            PersonInfoActivity.this.baseResponse = baseResponse;
                            id = baseResponse.getUser().getId();
                            String userName = baseResponse.getUser().getUserName();
                            String sex = baseResponse.getUser().getSex();
                            int roleId = baseResponse.getUser().getRoles().get(0).getRoleId();
                            String phonenumber = baseResponse.getUser().getPhonenumber();
                            edittext.setText(userName);
                            tvResult1.setText(sex);
                            switch (roleId) {
                                case 1:
                                    tvResult2.setText("超级系统管理员");
                                    break;
                                case 2:
                                    tvResult2.setText("普通用户");
                                    break;
                                case 3:
                                    tvResult2.setText("实验室管理员");
                                    break;
                            }
                            edittext3.setText(phonenumber);
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
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            savePersonInfo();
            finish();
        });
    }

    private void savePersonInfo() {
        String userName = edittext.getText().toString();
        String phonenumber = edittext3.getText().toString();
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("userName", userName);
        map.put("sex", sexId);
        map.put("roleId", roleId);
        map.put("phonenumber", phonenumber);
        RetrofitClient.getInstance().getService().updataUserData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(PersonInfoActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse response) {

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


    @OnClick({R.id.constraint1, R.id.constraint2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.constraint1:
                showSimpleBottomSheetList(sexArray, 1);
                break;
            case R.id.constraint2:
                showSimpleBottomSheetList(roleArray, 2);
                break;
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
