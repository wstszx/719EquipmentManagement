package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.ContainerData;
import com.example.a719equipmentmanagement.entity.UserData;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;

public class ContainerDetailActivity extends BaseActivity {

    private String[] containerAttrs = {"货柜名称", "购置时间"};
    private String[] containerAttrValue = new String[3];
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private QMUICommonListItemView listItemView;
    private int id;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
        initGroupListView();
    }

    private void initData() {
        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
//        getContainerData(userId);
//        String name = listBean.getName();
//        id = listBean.getId();
//        String dept = listBean.getDept();
//        String createTime = listBean.getCreateTime();
//        containerAttrValue[0] = name;
//        containerAttrValue[1] = dept;
//        containerAttrValue[1] = createTime;
    }

//    private void getContainerData(String userId) {
//        RetrofitClient.getInstance().getService().queryContainer(userId)
//                .compose(CommonCompose.io2main(this))
//                .subscribe(new BaseSubscriber<UserData>(this){
//                    @Override
//                    public void onSuccess(UserData userData) {
//
//                    }
//                });
//    }

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
        for (int i = 0; i < containerAttrs.length; i++) {
            QMUICommonListItemView item = groupListView.createItemView(
                    null,
                    containerAttrs[i],
                    containerAttrValue[i] == null ? " " : containerAttrValue[i],
                    QMUICommonListItemView.HORIZONTAL,
                    QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
            item.setTag(i);
            section.addItemView(item, onClickListener);
        }
        section.addTo(groupListView);

    }

    private void initTopbar() {
        topbar.setTitle("货柜详情");
        topbar.addRightImageButton(R.mipmap.qrcode, R.id.qrcode).setOnClickListener(v -> {
            ArrayList<Integer> ids = new ArrayList<>();
            ids.add(id);
            GenarateQRActivity.start(this, ids);
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
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

    public static void start(Context context, String userId) {
        Intent starter = new Intent(context, ContainerDetailActivity.class);
        starter.putExtra("userId", userId);
        context.startActivity(starter);
    }

}
