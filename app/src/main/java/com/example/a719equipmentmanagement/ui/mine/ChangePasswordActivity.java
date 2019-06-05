package com.example.a719equipmentmanagement.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.HandleHistory;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.edittext1)
    EditText edittext1;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
    }

    private void initTopbar() {
        topbar.setTitle("修改密码");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            changePassword();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void changePassword() {
        String oldPassword = edittext.getText().toString();
        String newPassword = edittext1.getText().toString();
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
            ToastUtils.showShort("原密码或新密码不能为空");
            return;
        }
        RetrofitClient.getInstance().getService().editPassword(oldPassword, newPassword)
                .compose(CommonCompose.io2main(ChangePasswordActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(ChangePasswordActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        ToastUtils.showShort("修改成功");
                        finish();
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_password;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ChangePasswordActivity.class);
        context.startActivity(starter);
    }

}
