package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseEditActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 建账入库
 */
public class AccountingActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    private QMUICommonListItemView listItemView;
    private String[] containerAttrs = {"设备名称", "所在位置", "厂家", "状态"};

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initGroupListView();
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
            listItemView = (QMUICommonListItemView) v;
            int tag = (int) listItemView.getTag();
            Intent intent = new Intent();
            intent.putExtra("text", listItemView.getDetailText().toString());
            intent.setClass(this, BaseEditActivity.class);
            startActivityForResult(intent, tag);
        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
        for (int i = 0; i < containerAttrs.length; i++) {
            QMUICommonListItemView item = groupListView.createItemView(
                    null,
                    containerAttrs[i],
                    " ",
                    QMUICommonListItemView.HORIZONTAL,
                    QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
            item.setTag(i);
            section.addItemView(item, onClickListener);
        }
        section.addTo(groupListView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String text = data.getStringExtra("text");
            TextView detailTextView = listItemView.getDetailTextView();
            detailTextView.setSingleLine(true);
            detailTextView.setMaxEms(8);
            detailTextView.setEllipsize(TextUtils.TruncateAt.END);
            detailTextView.setText(text);
        }
    }


    private void initTopbar() {
        topbar.setTitle("建账入库");
        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
//            Intent intent = new Intent();
//            intent.putExtra("text", edittext.getText().toString());
//            setResult(RESULT_OK, intent);
//            finish();
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_accounting;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AccountingActivity.class);
        context.startActivity(starter);
    }

}
