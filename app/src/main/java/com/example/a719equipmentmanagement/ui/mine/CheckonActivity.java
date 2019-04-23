package com.example.a719equipmentmanagement.ui.mine;

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

public class CheckonActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    private String[] date = new String[]{"2019年4月1日", "2019年3月1日", "2019年5月1日"};
    private String[] details = new String[]{"温度计，型号参数，20100401", "压力表，型号参数，20100301", "传感器，型号参数，20100024"};
    private String[] result = new String[]{"，点检合格", "，点检不合格，请用户申请报废", "，点检合格"};

    @Override
    protected void init(Bundle savedInstanceState) {
        // int id = getIntent().getIntExtra("id", -1);
        initView();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_checkon;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CheckonActivity.class);
        //starter.putExtra("id", id);
        context.startActivity(starter);
    }

    private void initView() {
        initTopbar();
        initGroupListView();
    }

    private void initTopbar() {
        topbar.setTitle("送检记录");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void initGroupListView() {
        String time = "时间";
        View.OnClickListener onClickListener = v -> {
        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
        for (int i = 0; i < date.length; i++) {
            QMUICommonListItemView item = groupListView.createItemView(
                    null,
                    time + date[i],
                    details[i] + result[i],
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
