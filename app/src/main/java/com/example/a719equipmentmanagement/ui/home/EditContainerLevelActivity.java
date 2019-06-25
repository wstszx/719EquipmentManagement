package com.example.a719equipmentmanagement.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EditContainerLevelActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.round_button)
    QMUIRoundButton roundButton;
    private int id;
    private String name;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        if (!StringUtils.isEmpty(name)) {
            edittext.setText(name);
        }
    }

    private void initTopbar() {
        topbar.setTitle("编辑货柜层");
        topbar.addRightTextButton(R.string.save, R.id.save).setOnClickListener(v -> {
            editContainer();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void editContainer() {
        name = edittext.getText().toString();
        if (StringUtils.isEmpty(name)) {
            ToastUtils.showShort("货柜名不能为空");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        RetrofitClient.getInstance().getService().editContainer(requestBody)
                .compose(CommonCompose.io2main(EditContainerLevelActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(EditContainerLevelActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        if (baseResponse.getCode() == 0) {
                            ToastUtils.showShort("编辑货柜成功");
                        }
                    }
                });
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_container_level;
    }

}
