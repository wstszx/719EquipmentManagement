package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.DeviceAdapter;
import com.example.a719equipmentmanagement.adapter.PeopleManageAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.device.DeviceDetailActivity;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MsgActivity extends BaseActivity {


    @BindView(R.id.topbar)
    QMUITopBar topbar;

    @BindView(R.id.sticky_section_layout)
    QMUIStickySectionLayout stickySectionLayout;

    private ArrayList<QMUISection<SectionHeader, SectionItem<User.ListBean>>> list;
    private PeopleManageAdapter adapter1;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        initTopbar();
        initStickySectionLayout();
        initData();
        initAdapter();
    }

    private void initAdapter() {
//        adapter = new DeviceAdapter(R.layout.base_device02);
//
//        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerview.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
////        recyclerview.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()),DividerItemDecoration.VERTICAL,10,getResources().getColor(R.color.app_color_blue)));
//        recyclerview.setAdapter(adapter);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(getContext(),"当前点击条目为"+(position+1),Toast.LENGTH_SHORT).show();
//                DeviceDetailActivity.start(getActivity());
//            }
//        });
    }

    private void initTopbar() {
        topbar.setTitle("消息");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void initStickySectionLayout() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        stickySectionLayout.setBackgroundColor(getResources().getColor(R.color.qmui_config_color_white));
        stickySectionLayout.setLayoutManager(manager);
        adapter1 = new PeopleManageAdapter();
        stickySectionLayout.setAdapter(adapter1, true);
        list = new ArrayList<>();

    }

    private void initData() {

    }




    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_manage;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MsgActivity.class);
        context.startActivity(starter);
    }
}
