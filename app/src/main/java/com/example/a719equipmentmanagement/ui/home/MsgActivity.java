package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.DeviceAdapter;
import com.example.a719equipmentmanagement.adapter.MsgAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.MsgData;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.device.DeviceDetailActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.section.QMUISection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class MsgActivity extends BaseActivity {


    @BindView(R.id.topbar)
    QMUITopBar topbar;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private MsgAdapter adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        initTopbar();
        initData();
        initAdapter();
    }



    private void initTopbar() {
        topbar.setTitle("消息");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }



    private void initData() {
        RetrofitClient.getInstance().getService().findMsgData()
                .compose(CommonCompose.io2main(MsgActivity.this))
                .subscribe(new BaseSubscriber<MsgData>(MsgActivity.this){
                    @Override
                    public void onSuccess(MsgData baseResponse) {
                        if(baseResponse!=null){

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    private void initAdapter() {
        adapter = new MsgAdapter(R.layout.base_msg);

//        recyclerView.setLayoutManager(new LinearLayoutManager());
//        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
//        recyclerview.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()),DividerItemDecoration.VERTICAL,10,getResources().getColor(R.color.app_color_blue)));
        recyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////                Toast.makeText(getContext(),"当前点击条目为"+(position+1),Toast.LENGTH_SHORT).show();
////                DeviceDetailActivity.start(getActivity());
//            }
//        });
    }




    @Override
    protected int getLayoutId() {
        return R.layout.activity_msg;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MsgActivity.class);
        context.startActivity(starter);
    }
}
