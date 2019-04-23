package com.example.a719equipmentmanagement.ui.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckcountActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    private String[] date = new String[]{"2019年4月1日", "2019年3月1日", "2019年5月1日"};
    private String[] location = new String[]{"1号货架第1层盘点完成", "1号货架第2层盘点完成", "1号货架第3层盘点完成"};
    private String[] state = new String[]{"完全吻合", "盘盈", "，盘亏"};

    @Override
    protected void init(Bundle savedInstanceState) {
        // int id = getIntent().getIntExtra("id", -1);
        initView();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_checkcount;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CheckcountActivity.class);
        //starter.putExtra("id", id);
        context.startActivity(starter);
    }

    private void initView() {
        initTopbar();
        initGroupListView();
    }

    private void initTopbar() {
        topbar.setTitle("盘点记录");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void initGroupListView() {
        String time = "时间";
        String result = "，盘点结果：";
        View.OnClickListener onClickListener = v -> {
        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
        for (int i = 0; i < date.length; i++) {
            QMUICommonListItemView item = groupListView.createItemView(
                    null,
                    time + date[i],
                    location[i] + result + state[i],
                    QMUICommonListItemView.VERTICAL, 0);
            section.addItemView(item, onClickListener);
        }
        section.addTo(groupListView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
