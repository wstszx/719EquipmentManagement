package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPersonActivity extends BaseActivity {
    private static final int OWNER_DEPT = 1;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.tv_result1)
    TextView tvResult1;
    @BindView(R.id.tv_result2)
    TextView tvResult2;
    @BindView(R.id.edittext3)
    EditText edittext3;
    @BindView(R.id.edittext4)
    EditText edittext4;
    @BindView(R.id.edittext5)
    EditText edittext5;
    @BindView(R.id.switchs)
    Switch switchs;
    @BindView(R.id.tv_result7)
    TextView tvResult7;
    @BindView(R.id.edittext8)
    EditText edittext8;
    private String[] sexArray = {"男", "女", "未知"};
    private String[] roleArray = {"超级系统管理员", "普通用户", "实验室管理员"};
    private QMUIListPopup mListPopup;
    private ArrayAdapter<String> adapter;
    private int deptId;
    private int roleId;
    private QMUIBottomSheet build;
    private int sexTag;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
    }


    private void showSimpleBottomSheetList(String[] array, int flag) {
        QMUIBottomSheet.BottomListSheetBuilder bottomListSheetBuilder;
        switch (flag) {
            case 1:
                bottomListSheetBuilder = new QMUIBottomSheet.BottomListSheetBuilder(this);
                bottomListSheetBuilder.setTitle(R.string.sex);
                break;
            case 2:
                bottomListSheetBuilder = new QMUIBottomSheet.BottomListSheetBuilder(this, true);
                bottomListSheetBuilder.setTitle(R.string.role);
//                View view = LayoutInflater.from(this).inflate(R.layout.dialog_header, null);
//                TextView tv_cancel = view.findViewById(R.id.tv_cancel);
//                TextView tv_confirm = view.findViewById(R.id.tv_confirm);
//                tv_cancel.setOnClickListener(v -> build.dismiss());
//                tv_confirm.setOnClickListener(v -> {
//                    build.dismiss();
//
//                });
//                bottomListSheetBuilder.addHeaderView(view);
                break;
            default:
                bottomListSheetBuilder = new QMUIBottomSheet.BottomListSheetBuilder(this);
                break;
        }

        for (String s : array) {
            bottomListSheetBuilder.addItem(s != null ? s : "未知");
        }
        build = bottomListSheetBuilder.setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {

            @Override
            public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                roleId = position;
                dialog.dismiss();
                switch (flag) {
                    case 1:
                        sexTag = position;
                        tvResult1.setText(tag);
                        break;
                    case 2:
                        tvResult7.setText(tag);
                        break;
                }
            }
        })
                .build();
        build.show();
    }


//    private void getTreeData(View view) {
//        RetrofitClient.getInstance().getService().getTreeData()
//                .compose(CommonCompose.io2main(AddPersonActivity.this))
//                .subscribe(new BaseSubscriber<List<TreeData>>(AddPersonActivity.this) {
//                    @Override
//                    public void onSuccess(List<TreeData> treeData) {
//
////                        initListPopupIfNeed();
//                        mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
//                        mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
//                        mListPopup.show(view);
//                    }
//                });
//    }

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
        Map<String, Object> map = new HashMap<>();
        String username = edittext.getText().toString();
        String dept = tvResult2.getText().toString();
        String phoneNum = edittext3.getText().toString();
        String email = edittext4.getText().toString();
        String loginName = edittext5.getText().toString();
        String remark = edittext8.getText().toString();
        if (!RegexUtils.isEmail(email)) {
            ToastUtils.showShort("请填写正确的邮箱");
            return;
        }

        if (!RegexUtils.isMobileExact(phoneNum)) {
            ToastUtils.showShort("请填写正确的手机号");
            return;
        }
        try {
            map.put("userName", username);
            map.put("sex", sexTag);
            map.put("deptId", deptId);
            map.put("deptName", dept);
            map.put("phone", phoneNum);
            map.put("email", email);
            map.put("loginName", loginName);
            map.put("status", switchs.isChecked() ? "0" : "1");
            map.put("roleIds", roleId);
            map.put("remark", remark);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RetrofitClient.getInstance().getService().addUser(map)
                .compose(CommonCompose.io2main(AddPersonActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(AddPersonActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {

                    }
                });
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {
            String name = data.getStringExtra("name");
            deptId = data.getIntExtra("id", 0);
            tvResult2.setText(name);
        }
        super.onActivityResult(requestCode, resultCode, data);
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


    @OnClick({R.id.constraint1, R.id.constraint2, R.id.constraint7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.constraint1:
                showSimpleBottomSheetList(sexArray, 1);
                break;
            case R.id.constraint2:
                Intent intent = new Intent();
                intent.setClass(AddPersonActivity.this, ChoiceDeptActivity.class);
                startActivityForResult(intent, OWNER_DEPT);
                break;
            case R.id.constraint7:
                showSimpleBottomSheetList(roleArray, 2);
                break;
        }
    }
}
