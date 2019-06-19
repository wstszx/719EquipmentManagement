package com.example.a719equipmentmanagement.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
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
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EditDeviceClassifiyActivity extends BaseActivity {
    @BindView(R.id.edittext)
    EditText edittext;
    //    @BindView(R.id.edittext1)
//    EditText edittext1;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private int id;
    private String parentClassifiy;
    private String name;
    private int pid;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        pid = intent.getIntExtra("pid", 0);
        parentClassifiy = intent.getStringExtra("parentClassifiy");
        name = intent.getStringExtra("name");
        edittext.setText(name);
    }

    private void initTopbar() {
        topbar.setTitle("编辑设备分类");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            getInputData();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void getInputData() {
        String deviceClassifyName = edittext.getText().toString();
        if (StringUtils.isEmpty(deviceClassifyName)) {
            ToastUtils.showShort("设备分类没有变化");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("name", deviceClassifyName);
            jsonObject.put("pid", pid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        RetrofitClient.getInstance().getService().updataDeviceType(requestBody)
                .compose(CommonCompose.io2main(EditDeviceClassifiyActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(EditDeviceClassifiyActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        if (baseResponse != null && baseResponse.getCode() == 0) {
                            ToastUtils.showShort("修改成功");
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_device_classifiy;
    }

    public static void start(Context context, int id) {
        Intent starter = new Intent(context, EditDeviceClassifiyActivity.class);
        starter.putExtra("id", id);
        context.startActivity(starter);
    }
}
