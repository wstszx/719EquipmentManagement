package com.example.a719equipmentmanagement.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.MainActivity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_username_phone_email)
    EditText etUsernamePhoneEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.email_sign_in_button)
    Button emailSignInButton;

    @Override
    protected void init(Bundle savedInstanceState) {
    }

    @Override
    protected int getLayoutId() {
        boolean main = SPUtils.getBoolean(getApplicationContext(), "main", false);
        if (main) {
            MainActivity.start(this);
            LoginActivity.this.finish();
        }
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

        RetrofitClient.getInstance().getService().login(username, password)
                .compose(CommonCompose.io2main(LoginActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(LoginActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        int code = baseResponse.getCode();
                        if (code == 0) {
                            String token = baseResponse.getMsg();
                            SPUtils.putString(App.getContext(), "token", token);
                            SPUtils.putBoolean(App.getContext(), "main", true);
                            MainActivity.start(LoginActivity.this);
                            finish();
                        }
                    }
                });


//        IdentityHashMap<String, String> map = new IdentityHashMap<>();
//        map.put("locationIds", "gdgdfg");
//        map.put(new String("locationIds"), "gdgfdggdfg");
//        map.put("name", "gegeg");
//        RetrofitClient.getInstance().getService().test(map).enqueue(new Callback<BaseResponse>() {
//            @Override
//            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse> call, Throwable t) {
//
//            }
//        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }
}
