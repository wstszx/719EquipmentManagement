package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddContainerActivity extends BaseActivity {

    private static final int ADD_DEPT = 1;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.tv_result1)
    TextView tvResult1;
    @BindView(R.id.edittext2)
    EditText edittext2;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.round_button)
    QMUIRoundButton roundButton;
    private String name;
    private int id;
    private int deptId;
    private String containerId;
    private String containerNum;
    private int pid;
    private String msg;
    private ArrayList<Integer> qrList;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
    }

    private void initTopbar() {
        topbar.setTitle("添加货柜");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            topbar.removeAllRightViews();
            addContainer();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void addContainer() {
        String containerName = edittext.getText().toString();
        containerNum = edittext2.getText().toString();
        String ownerDept = tvResult1.getText().toString();
        if (StringUtils.isEmpty(containerName)) {
            ToastUtils.showShort("货柜名称不能为空");
            return;
        } else if (StringUtils.isEmpty(containerNum)) {
            ToastUtils.showShort("货柜层数不能为空");
            return;
        } else if (StringUtils.isEmpty(ownerDept)) {
            ToastUtils.showShort("归属部门不能为空");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("deptId", deptId);
            jsonObject.put("name", containerName);
            jsonObject.put("pid", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        RetrofitClient.getInstance().getService().addContainer(requestBody)
                .flatMap((Function<BaseResponse, SingleSource<BaseResponse>>) response -> {
                    String msg = response.getMsg();
                    int containNum = Integer.parseInt(containerNum);
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < containNum; i++) {
                        JSONObject jsonObject1 = new JSONObject();
                        try {
                            jsonObject1.put("deptId", deptId);
                            jsonObject1.put("name", containerName + "--" + i);
                            jsonObject1.put("pid", msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        jsonArray.put(jsonObject1);
                    }
                    RequestBody requestBody1 = RequestBody.create(MediaType.parse("application/json"), jsonArray.toString());
                    return RetrofitClient.getInstance().getService().addBatchContainer(requestBody1);
                })
                .compose(CommonCompose.io2main(AddContainerActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(AddContainerActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        if (response != null && response.getCode() == 0) {
                            msg = response.getMsg();
                            qrList = new ArrayList<>();
                            String regEx = "[^0-9]+";
                            Pattern pattern = Pattern.compile(regEx);
                            //用定义好的正则表达式拆分字符串，把字符串中的数字留出来
                            String[] cs = pattern.split(msg);
                            for (String c : cs) {
                                qrList.add(Integer.parseInt(c));
                            }
                            ToastUtils.showShort("添加货柜成功");
                            roundButton.setVisibility(View.VISIBLE);
                        }
//                        setResult(RESULT_OK);
//                        finish();
                    }
                });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_container;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_DEPT) {
            if (data != null) {
                pid = data.getIntExtra("pid", 0);
                name = data.getStringExtra("name");
                deptId = data.getIntExtra("deptId", 0);
                tvResult1.setText(name);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.constraint1, R.id.round_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.constraint1:
                startActivityForResult(new Intent(AddContainerActivity.this, ChoiceDeptActivity.class), ADD_DEPT);
                break;
            case R.id.round_button:
                GenarateQRActivity.start(this, qrList);
                finish();
                break;
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AddContainerActivity.class);
        context.startActivity(starter);
    }

}
