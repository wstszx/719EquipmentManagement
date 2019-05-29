package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;

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
    private List<JSONObject> jsonObjects = new ArrayList<>();
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
        topbar.setTitle("新增建账入库");
        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
            accounting();
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void accounting() {
        ArrayList<Integer> setups = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        for (JSONObject jsonObject : jsonObjects) {
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
                        // TODO: 2019/5/29 修改生成二维码
                        GenarateQRActivity.start(AccountingListActivity.this, setups);
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
                        JSONObject jsonObject = (JSONObject) data.getSerializableExtra("accounting");
                        if (jsonObject != null) {
                            jsonObjects.add(jsonObject);
                            adapter.addData(jsonObjects);
                        }
                    }
                    break;
            }
        }
    }

    @OnClick(R.id.constraint)
    public void onViewClicked() {
//        AccountingActivity.start(this);
        startActivityForResult(new Intent(AccountingListActivity.this, AccountingActivity.class), ACCOUNTING);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AccountingListActivity.class);
        context.startActivity(starter);
    }
}
