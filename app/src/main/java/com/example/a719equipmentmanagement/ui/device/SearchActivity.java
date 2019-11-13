package com.example.a719equipmentmanagement.ui.device;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.DeviceAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.DeviceData2;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends BaseActivity {


    private static final int DEVICE_DETAIL = 1;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_sn)
    EditText et_sn;
    @BindView(R.id.bt_search)
    TextView bt_search;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private List<DeviceData2.RowsBean> rows;
    private DeviceAdapter adapter;
    private int pageNum = 1;
    private Map<String, Object> map = new HashMap<>();


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void getDeviceData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().findDeviceData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<DeviceData2>(this) {
                    @Override
                    public void onSuccess(DeviceData2 deviceData2) {
                        if (deviceData2 != null) {
                            rows = deviceData2.getRows();
                            if (rows != null && rows.size() > 0) {
                                adapter.setNewData(rows);
                            } else {
                                adapter.setNewData(null);
                            }
                        }
                    }
                });
    }

    private void initView() {
        if (adapter == null) {
            adapter = new DeviceAdapter(R.layout.base_device02);
        }
        adapter.bindToRecyclerView(recyclerView);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            DeviceData2.RowsBean currentItemData = (DeviceData2.RowsBean) adapter.getData().get(position);
            int deviceId = currentItemData.getId();
            String sn = currentItemData.getSn();
            Intent intent = new Intent();
            intent.putExtra("deviceId", deviceId);
            intent.putExtra("sn", sn);
            intent.setClass(this, DeviceDetailActivity2.class);
            startActivityForResult(intent, DEVICE_DETAIL);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        refreshlayout.setOnRefreshListener(refreshLayout1 -> {
            refreshLayout1.finishRefresh();
            pageNum = 1;
            map.put("pageNum", 1);
            map.put("pageSize", 10);
            getDeviceData(map);
        });
        refreshlayout.setOnLoadMoreListener(refreshLayout -> {
            refreshLayout.finishLoadMore();
            pageNum++;
            map.put("pageNum", pageNum);
            map.put("pageSize", 10);
            refreshDeviceData(map);
        });
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isEmpty(s)) {
                    et_sn.setText("");
                }
            }
        });
        et_sn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isEmpty(s)) {
                    et_name.setText("");
                }
            }
        });

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String sn = et_sn.getText().toString();
                if (!StringUtils.isEmpty(name)) {
                    map.put("name", name);
                }
                if (!StringUtils.isEmpty(sn)) {
                    map.put("sn", sn);
                }
                getDeviceData(map);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == DEVICE_DETAIL) {
                pageNum = 1;
                map.put("pageNum", 1);
                getDeviceData(map);
            }
        }
    }

    private void refreshDeviceData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().findDeviceData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<DeviceData2>(this) {
                    @Override
                    public void onSuccess(DeviceData2 deviceData2) {
                        if (deviceData2 != null) {
                            rows = deviceData2.getRows();
                            if (rows != null) {
                                adapter.addData(rows);
                                if (rows.size() < 10) {
                                    refreshlayout.finishLoadMoreWithNoMoreData();
                                }
                            }
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SearchActivity.class);
        context.startActivity(starter);
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
        overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
