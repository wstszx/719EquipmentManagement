package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddDeviceClassifyActivity extends BaseActivity {


    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.edittext1)
    EditText edittext1;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
    }


    private void initTopbar() {
        topbar.setTitle("添加设备分类");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            getInputData();
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void getInputData() {
        String deviceClassifyName = edittext.getText().toString();
        String ownerDeviceClassify = edittext1.getText().toString();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", deviceClassifyName);
            jsonObject.put("ownername", ownerDeviceClassify);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        RetrofitClient.getInstance().getService().addDeviceType(requestBody)
                .compose(CommonCompose.io2main(AddDeviceClassifyActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(AddDeviceClassifyActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        setResult(RESULT_OK);
                        finish();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_device_classify;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AddDeviceClassifyActivity.class);
        context.startActivity(starter);
    }

}
