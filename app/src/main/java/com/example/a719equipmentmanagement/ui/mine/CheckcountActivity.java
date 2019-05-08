package com.example.a719equipmentmanagement.ui.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.InventoryData;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckcountActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    private List<InventoryData> body;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
//        initData();
    }

//    private void initData() {
//        RetrofitClient.getInstance().getService().findInventoryData().enqueue(new Callback<List<InventoryData>>() {
//            @Override
//            public void onResponse(Call<List<InventoryData>> call, Response<List<InventoryData>> response) {
//                body = response.body();
//                if (body != null && body.size() > 0) {
//                    initGroupListView();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<InventoryData>> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_checkcount;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CheckcountActivity.class);
        context.startActivity(starter);
    }

    private void initView() {
        initTopbar();
    }

    private void initTopbar() {
        topbar.setTitle("盘点记录");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

//    private void initGroupListView() {
//        String time = "时间";
//        String result = "盘点结果：";
//        View.OnClickListener onClickListener = v -> {
//        };
//        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
//            for (int i = 0; i < body.size(); i++) {
//                QMUICommonListItemView item = groupListView.createItemView(
//                        null,
//                        time + body.get(i).getCreateTime(),
//                        result+(body.get(i).getState()==0?"盘点完成":"盘点未完成"),
//                        QMUICommonListItemView.VERTICAL, 0);
//                section.addItemView(item, onClickListener);
//            }
//        section.addTo(groupListView);
//    }
}
