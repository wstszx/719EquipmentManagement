package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.InRecordData;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.utils.AboriginalDateSelect;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 建账入库
 */
public class AccountingActivity extends BaseActivity {

    private static final int DEVICE_TYPE = 1;
    private static final int DEPT_TYPE = 2;
    private static final int CONTAINER_TYPE = 3;
    private static final int DATE_ONE = 4;
    private static final int DATE_TWO = 5;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.constraint)
    ConstraintLayout constraint;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.constraint1)
    ConstraintLayout constraint1;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    @BindView(R.id.constraint2)
    ConstraintLayout constraint2;
    @BindView(R.id.tv_title3)
    TextView tvTitle3;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.constraint3)
    ConstraintLayout constraint3;
    @BindView(R.id.tv_title4)
    TextView tvTitle4;
    @BindView(R.id.constraint4)
    ConstraintLayout constraint4;
    @BindView(R.id.tv_title5)
    TextView tvTitle5;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.constraint5)
    ConstraintLayout constraint5;
    @BindView(R.id.tv_title6)
    TextView tvTitle6;
    @BindView(R.id.constraint6)
    ConstraintLayout constraint6;
    @BindView(R.id.tv_title7)
    TextView tvTitle7;
    @BindView(R.id.edittext8)
    EditText edittext8;
    @BindView(R.id.tv_result9)
    TextView tvResult9;
    @BindView(R.id.tv_result10)
    TextView tvResult10;
    @BindView(R.id.edittext11)
    EditText edittext11;
    @BindView(R.id.tv_result12)
    TextView tvResult12;
    @BindView(R.id.tv_result13)
    TextView tvResult13;
    private String[] options = {"可用", "借用", "送检占用", "送检", "报废占用", "报废", "封存", "解封占用", "过期", "外借", "不限"};
    private String[] classification = {"A", "B", "C"};
    private String[] technical_status = {"合格", "不合格"};
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.tv_result1)
    TextView tvResult1;
    @BindView(R.id.edittext2)
    EditText edittext2;
    @BindView(R.id.tv_result3)
    TextView tvResult3;
    @BindView(R.id.edittext4)
    EditText edittext4;
    @BindView(R.id.tv_result5)
    TextView tvResult5;
    @BindView(R.id.edittext6)
    EditText edittext6;
    @BindView(R.id.tv_result7)
    TextView tvResult7;
    private int locationId;
    private int categoryId;
    private int deptId;
    private int status;
    private Calendar calendar = Calendar.getInstance(Locale.CHINA);
    private int tech_statu;
    private Integer id;


    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        AboriginalDateSelect.getInstance().setListener((position, dateFormat) -> {
            switch (position) {
                case DATE_ONE:
                    tvResult12.setText(dateFormat.format(calendar.getTime()));
                    break;
                case DATE_TWO:
                    tvResult13.setText(dateFormat.format(calendar.getTime()));
                    break;
            }
        });
        initData();
    }

    private void initData() {
        RetrofitClient.getInstance().getService().getNextId()
                .compose(CommonCompose.io2main(this))
                .subscribe(new BaseSubscriber<Integer>(AccountingActivity.this) {
                    @Override
                    public void onSuccess(Integer integer) {
                        id = integer;
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case DEVICE_TYPE:
                    if (data != null) {
                        String name = data.getStringExtra("name");
                        categoryId = data.getIntExtra("id", 0);
                        tvResult1.setText(name);
                    }
                    break;
                case DEPT_TYPE:
                    if (data != null) {
                        String name = data.getStringExtra("name");
                        deptId = data.getIntExtra("id", 0);
                        tvResult3.setText(name);
                    }
                    break;
                case CONTAINER_TYPE:
                    if (data != null) {
                        String name = data.getStringExtra("name");
                        locationId = data.getIntExtra("id", 0);
                        tvResult5.setText(name);
                    }
                    break;
            }
        }
    }

    private void showSimpleBottomSheetList(String[] array, int flag) {
        QMUIBottomSheet.BottomListSheetBuilder bottomListSheetBuilder = new QMUIBottomSheet.BottomListSheetBuilder(this);
        for (String s : array) {
            bottomListSheetBuilder.addItem(s != null ? s : "未知");
        }

        bottomListSheetBuilder.setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
            dialog.dismiss();
            switch (flag) {
                case 1:
                    status = position;
                    tvResult7.setText(tag);
                    break;
                case 2:
                    tech_statu = position;
                    tvResult9.setText(tag);
                    break;
                case 3:
                    categoryId = position;
                    tvResult10.setText(tag);
                    break;
            }
        })
                .build()
                .show();
    }

    private void initTopbar() {
        topbar.setTitle("建账入库");
        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
            JSONObject jsonObject = accounting();
            Intent intent = new Intent();
            intent.putExtra("accounting", (Serializable) jsonObject);
            setResult(RESULT_OK, intent);
//            accounting();
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private JSONObject accounting() {
        String name = edittext.getText().toString();
        String equipNo = edittext2.getText().toString();
        String parameter = edittext4.getText().toString();
        String manufactuer = edittext6.getText().toString();
        String responsor = edittext8.getText().toString();
        String verifyPeriod = edittext11.getText().toString();
        String latestVerifyDate = tvResult12.getText().toString();
        String validDate = tvResult13.getText().toString();
//        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
//        jsonArray.put(jsonObject);
        try {
            jsonObject.put("equip", jsonObject1);
            jsonObject.put("setupId", id);
            jsonObject1.put("name", name);
            jsonObject1.put("categoryId", categoryId);
            jsonObject1.put("equipNo", equipNo);
            jsonObject1.put("deptId", deptId);
            jsonObject1.put("parameter", parameter);
            jsonObject1.put("locationId", locationId);
            jsonObject1.put("manufactuer", manufactuer);
            jsonObject1.put("status", status);
            jsonObject1.put("responsor", responsor);
            jsonObject1.put("techState", tech_statu);
            jsonObject1.put("verifyPeriod", verifyPeriod);
            jsonObject1.put("latestVerifyDate", latestVerifyDate);
            jsonObject1.put("validDate", validDate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonArray.toString());
//        RetrofitClient.getInstance().getService().addInRecord(requestBody)
//                .compose(CommonCompose.io2main(AccountingActivity.this))
//                .subscribe(new BaseSubscriber<BaseResponse>(AccountingActivity.this) {
//                    @Override
//                    public void onSuccess(BaseResponse baseResponse) {
//                        // TODO: 2019/5/29 修改生成二维码
//                        GenarateQRActivity.start(AccountingActivity.this, 0);
//                    }
//                });
        return jsonObject;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_accounting;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AccountingActivity.class);
        context.startActivity(starter);
    }

    @OnClick({R.id.constraint1, R.id.constraint3, R.id.constraint5, R.id.constraint7,
            R.id.constraint9, R.id.constraint10, R.id.constraint12, R.id.constraint13})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.constraint1:
                startActivityForResult(new Intent(AccountingActivity.this, ChoiceDeviceClassifiyActivity.class), DEVICE_TYPE);
                break;
            case R.id.constraint3:
                startActivityForResult(new Intent(AccountingActivity.this, ChoiceDeptActivity.class), DEPT_TYPE);
                break;
            case R.id.constraint5:
                startActivityForResult(new Intent(AccountingActivity.this, ChoiceContainerActivity.class), CONTAINER_TYPE);
                break;
            case R.id.constraint7:
                showSimpleBottomSheetList(options, 1);
                break;
            case R.id.constraint9:
                showSimpleBottomSheetList(technical_status, 2);
                break;
            case R.id.constraint10:
                showSimpleBottomSheetList(classification, 3);
                break;
            case R.id.constraint12:
                AboriginalDateSelect.getInstance().showDateTime(this, DATE_ONE);
                break;
            case R.id.constraint13:
                AboriginalDateSelect.getInstance().showDateTime(this, DATE_TWO);
                break;
        }
    }

}
