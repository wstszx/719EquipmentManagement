package com.example.a719equipmentmanagement.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.MainActivity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.LoginBean;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.mine.ActivityCollector;
import com.example.a719equipmentmanagement.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        String username = etUsernamePhoneEmail.getText().toString();
        String password = etPassword.getText().toString();
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("username", username);
//            jsonObject.put("password", password);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        RetrofitClient.getInstance().getService().login(username, password).enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if (response.body() != null) {
                    int code = response.body().getCode();
                    if (0 == code) {
                        String token = response.body().getMsg();
                        SPUtils.putString(App.getContext(), "token", token);
                        MainActivity.start(LoginActivity.this);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                LogUtils.i(t.getMessage());
            }
        });


    }
}
