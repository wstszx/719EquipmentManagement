package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.StringUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.AccountingListAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AccountingListActivity extends BaseActivity {


    private static final int ACCOUNTING = 1;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private AccountingListAdapter adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
    }

    private void initAdapter() {
        adapter = new AccountingListAdapter(R.layout.accounting_item);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
    }

    private void initTopbar() {
        topbar.setTitle("建账入库");
        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
            accounting();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void accounting() {
        ArrayList<Integer> setups = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        List<JSONObject> data = adapter.getData();
        for (JSONObject jsonObject : data) {
            try {
                int setupId = jsonObject.getInt("setupId");
                setups.add(setupId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonArray.toString());
        RetrofitClient.getInstance().getService().addInRecord(requestBody)
                .compose(CommonCompose.io2main(AccountingListActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(AccountingListActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        String msg = baseResponse.getMsg();
//                        字符串提取数字
                        String regEx = "[^0-9]+";
                        Pattern pattern = Pattern.compile(regEx);
                        //用定义好的正则表达式拆分字符串，把字符串中的数字留出来
                        String[] cs = pattern.split(msg);
                        for (String c : cs) {
                            setups.add(Integer.parseInt(c));
                        }
                        GenarateQRActivity.start(AccountingListActivity.this, setups);
                        finish();
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_accounting_list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ACCOUNTING:
                    if (data != null) {
                        String accounting = data.getStringExtra("accounting");
                        if (accounting != null) {
                            JSONObject jsonObject;
                            try {
                                jsonObject = new JSONObject(accounting);
                                adapter.addData(jsonObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
            }
        }
    }

    @OnClick(R.id.constraint)
    public void onViewClicked() {
        startActivityForResult(new Intent(AccountingListActivity.this, AccountingActivity.class), ACCOUNTING);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AccountingListActivity.class);
        context.startActivity(starter);
    }
}
