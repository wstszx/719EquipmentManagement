package com.example.a719equipmentmanagement.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.ui.mine.ActivityCollector;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.et_username_phone_email)
    EditText etUsernamePhoneEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.email_sign_in_button)
    Button emailSignInButton;

    @Override
    protected void init(Bundle savedInstanceState) {
        List<Activity> actList = ActivityCollector.getActList();
        Log.i("mylog", "actList.size()==" + actList.size());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @OnClick(R.id.email_sign_in_button)
    public void onViewClicked() {
    }
}
