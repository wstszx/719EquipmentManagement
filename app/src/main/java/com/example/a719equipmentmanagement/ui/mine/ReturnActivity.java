package com.example.a719equipmentmanagement.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReturnActivity extends BaseActivity {


    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    private String[] date = new String[]{"2019年4月1日", "2019年3月1日", "2019年5月1日"};
    private String[] details = new String[]{"温度计，型号参数，20100401", "压力表，型号参数，20100301", "传感器，型号参数，20100024"};
    private String[] action = new String[]{"，归还", "，借用", "，归还"};

    @Override
    protected void init(Bundle savedInstanceState) {
       // int id = getIntent().getIntExtra("id", -1);
        initView();
        initData();
    }

    private void initData() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_return;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ReturnActivity.class);
        //starter.putExtra("id", id);
        context.startActivity(starter);
    }

    private void initView() {
        initTopbar();
        initGroupListView();
    }

    private void initTopbar() {
        topbar.setTitle("借还记录");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void initGroupListView() {
        String time = "时间";
        View.OnClickListener onClickListener = v -> {};
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
        for (int i = 0; i < date.length; i++) {
            QMUICommonListItemView item = groupListView.createItemView(
                    null,
                    time +date[i],
                    details[i]+action[i],
                    QMUICommonListItemView.VERTICAL, 0);
            section.addItemView(item, onClickListener);
        }
        section.addTo(groupListView);
    }

}


