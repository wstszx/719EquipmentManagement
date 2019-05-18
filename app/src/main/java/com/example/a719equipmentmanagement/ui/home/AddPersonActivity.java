package com.example.a719equipmentmanagement.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.TreeData;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class AddPersonActivity extends BaseActivity {
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private QMUICommonListItemView listItemView;

    private String[] containerAttrs = {"用户名称", "归属部门", "手机号码", "邮箱", "登录账号", "登录密码", "用户性别", "用户状态", "岗位", "角色", "备注"};
    private QMUICommonListItemView item7;
    private QMUIListPopup mListPopup;
    private ArrayAdapter<String> adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initGroupListView();
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
            if (((QMUICommonListItemView) v).getAccessoryType() == QMUICommonListItemView.ACCESSORY_TYPE_SWITCH) {
                ((QMUICommonListItemView) v).getSwitch().toggle();
            }
            listItemView = (QMUICommonListItemView) v;
            int tag = (int) listItemView.getTag();
            clickItem(v, tag);

        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
        QMUICommonListItemView item = groupListView.createItemView(
                null,
                containerAttrs[0],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(0);
        section.addItemView(item, onClickListener);

        QMUICommonListItemView item1 = groupListView.createItemView(
                null,
                containerAttrs[1],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item1.setTag(1);
        section.addItemView(item1, onClickListener);

        QMUICommonListItemView item2 = groupListView.createItemView(
                null,
                containerAttrs[2],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item2.setTag(2);
        section.addItemView(item2, onClickListener);

        QMUICommonListItemView item3 = groupListView.createItemView(
                null,
                containerAttrs[3],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item3.setTag(3);
        section.addItemView(item3, onClickListener);

        QMUICommonListItemView item4 = groupListView.createItemView(
                null,
                containerAttrs[4],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item4.setTag(4);
        section.addItemView(item4, onClickListener);

        QMUICommonListItemView item5 = groupListView.createItemView(
                null,
                containerAttrs[5],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item5.setTag(5);
        section.addItemView(item5, onClickListener);

        QMUICommonListItemView item6 = groupListView.createItemView(
                null,
                containerAttrs[6],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item6.setTag(6);
        section.addItemView(item6, onClickListener);

        item7 = groupListView.createItemView(containerAttrs[7]);
        item7.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        item7.getSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        section.addItemView(item7, onClickListener);

        QMUICommonListItemView item8 = groupListView.createItemView(
                null,
                containerAttrs[8],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item8.setTag(8);
        section.addItemView(item8, onClickListener);

        QMUICommonListItemView item9 = groupListView.createItemView(
                null,
                containerAttrs[9],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item9.setTag(9);
        section.addItemView(item9, onClickListener);

        QMUICommonListItemView item10 = groupListView.createItemView(
                null,
                containerAttrs[10],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item10.setTag(10);
        section.addItemView(item10, onClickListener);

        section.addTo(groupListView);

    }

    private void clickItem(View view, int tag) {
        switch (tag) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
            case 10:
                Intent intent = new Intent();
                intent.putExtra("text", listItemView.getDetailText().toString());
                intent.setClass(this, BaseItemEditActivity.class);
                startActivityForResult(intent, tag);
                break;
            case 1:
                getTreeData(view);
                break;
        }
    }

    private void getTreeData(View view) {
        RetrofitClient.getInstance().getService().getTreeData()
                .compose(CommonCompose.io2main(AddPersonActivity.this))
                .subscribe(new BaseSubscriber<List<TreeData>>(AddPersonActivity.this) {
                    @Override
                    public void onSuccess(List<TreeData> treeData) {

//                        initListPopupIfNeed();
                        mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                        mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                        mListPopup.show(view);
                    }
                });
    }

    private void initTopbar() {
        topbar.setTitle("添加人员");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            addPerson();
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void addPerson() {
        RetrofitClient.getInstance().getService().addUser()
                .compose(CommonCompose.io2main(AddPersonActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(AddPersonActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {

                    }
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
        return R.layout.activity_add_person;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AddPersonActivity.class);
        context.startActivity(starter);
    }

    private void initListPopupIfNeed(String[] listItems) {

        List<String> data = new ArrayList<>();

        Collections.addAll(data, listItems);
        if (adapter == null) {
            adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, data);
        } else {
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
        }
        if (mListPopup == null) {
            mListPopup = new QMUIListPopup(this, QMUIPopup.DIRECTION_NONE, adapter);
            mListPopup.create(QMUIDisplayHelper.dp2px(this, 250), QMUIDisplayHelper.dp2px(this, 200), new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView textView = (TextView) view;
                    String s = textView.getText().toString();
                    mListPopup.dismiss();
                }
            });
            mListPopup.setOnDismissListener(data::clear);
        }
    }
}
