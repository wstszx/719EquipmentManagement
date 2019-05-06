package com.example.a719equipmentmanagement.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ContainerData;
import com.example.a719equipmentmanagement.ui.home.GenarateQRActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;

public class BaseEditActivity extends BaseActivity {

    List<String> containerAttrs = new ArrayList<>();
    List<String> containerAttrValue = new ArrayList<>();
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private QMUICommonListItemView listItemView;
    private String title;
    private String[] titleArrays;


    @Override
    protected void init(Bundle savedInstanceState) {
        initData();
        initTopbar();
        initGroupListView();
    }

    private void initData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        titleArrays = intent.getStringArrayExtra("titleArray");
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
            listItemView = (QMUICommonListItemView) v;
            int tag = (int) listItemView.getTag();
            Intent intent = new Intent();
            intent.putExtra("text", listItemView.getDetailText().toString());
            intent.setClass(this, BaseItemEditActivity.class);
            startActivityForResult(intent, tag);
        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
        for (int i = 0; i < containerAttrs.size(); i++) {
            QMUICommonListItemView item = groupListView.createItemView(
                    null,
                    titleArrays[i],
                    null,
                    QMUICommonListItemView.HORIZONTAL,
                    QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
            item.setTag(i);
            section.addItemView(item, onClickListener);
        }
        section.addTo(groupListView);

    }

    private void initTopbar() {
        topbar.setTitle(title == null ? "" : title);
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {

        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_container_detail;
    }

    public static void start(Context context, String title, String[] titleArray) {
        Intent starter = new Intent(context, BaseEditActivity.class);
        starter.putExtra("titleArray", titleArray);
        starter.putExtra("title", title);
        context.startActivity(starter);
    }

}
