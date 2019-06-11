package com.example.a719equipmentmanagement.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EditContainerActivity extends BaseActivity {

    private static final int EDIT_DEPT = 1;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.tv_result1)
    TextView tvResult1;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.round_button)
    QMUIRoundButton roundButton;
    private String name;
    private int id;
    private int pid;
    private int deptId;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
//        pid = intent.getIntExtra("pid", 0);
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        deptId = intent.getIntExtra("deptId", 0);
        if (!StringUtils.isEmpty(name)) {
            edittext.setText(name);
        }
    }

    private void initTopbar() {
        topbar.setTitle("编辑货柜");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            topbar.removeAllRightViews();
            editContainer();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void editContainer() {
        String containerName = edittext.getText().toString();
        if (StringUtils.isEmpty(containerName)) {
            ToastUtils.showShort("货柜名不能为空");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("deptId", deptId);
//            jsonObject.put("pid", pid);
            jsonObject.put("name", containerName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        RetrofitClient.getInstance().getService().editContainer(requestBody)
                .compose(CommonCompose.io2main(EditContainerActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(EditContainerActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        if (baseResponse.getCode() == 0) {
                            ToastUtils.showShort("编辑货柜成功");
                            roundButton.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == EDIT_DEPT) {
            if (data != null) {
                name = data.getStringExtra("name");
                deptId = data.getIntExtra("id", 0);
                tvResult1.setText(name);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.constraint1, R.id.round_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.constraint1:
                startActivityForResult(new Intent(EditContainerActivity.this, ChoiceDeptActivity.class), EDIT_DEPT);
                break;
            case R.id.round_button:
                GenerateContainerCodeActivity.start(this, "");
                finish();
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_container;
    }
}
