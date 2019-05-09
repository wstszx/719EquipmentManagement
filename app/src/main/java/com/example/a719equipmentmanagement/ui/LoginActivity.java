package com.example.a719equipmentmanagement.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.MainActivity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.LoginBean;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.NetworkError;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.mine.ActivityCollector;
import com.example.a719equipmentmanagement.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

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
        RetrofitClient.getInstance().getService().login(username, password).enqueue(new Callback<BaseResponse<LoginBean>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginBean>> call, Response<BaseResponse<LoginBean>> response) {
                boolean ok = false;
                if (response.body() != null) {
                    ok = response.body().isOk(App.getContext());
                    if (ok) {
                        String token = response.body().getMsg();
                        SPUtils.putString(App.getContext(), "token", token);
                        SPUtils.putBoolean(App.getContext(), "main", true);
                        MainActivity.start(LoginActivity.this);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<LoginBean>> call, Throwable t) {
                NetworkError.error(App.getContext(), t);
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
}
