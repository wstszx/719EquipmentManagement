package com.example.a719equipmentmanagement.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.DeviceClassifiy;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUILoadingView;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class AddDeptActivity extends BaseActivity {

    private static final int ADD_DEPT = 1;
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private QMUICommonListItemView listItemView;
    private String[] containerAttrs = {"部门名称", "所属部门", "显示排序", "负责人", "联系电话", "邮箱", "部门状态"};
    private QMUICommonListItemView item;
    private QMUICommonListItemView item1;
    private QMUICommonListItemView item2;
    private QMUICommonListItemView item3;
    private QMUICommonListItemView item4;
    private QMUICommonListItemView item5;
    private QMUICommonListItemView item6;
    private String name;
    private int id;

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
            switch (tag) {
                case 1:
                    Intent intent1 = new Intent();
                    intent1.setClass(AddDeptActivity.this, ChoiceDeptActivity.class);
                    startActivityForResult(intent1, ADD_DEPT);
                    break;
                default:
//                    Intent intent = new Intent();
//                    intent.putExtra("text", listItemView.getDetailText().toString());
//                    intent.setClass(this, BaseItemEditActivity.class);
//                    startActivityForResult(intent, tag);
                    break;
            }
        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);

        item = groupListView.createItemView(containerAttrs[0]);
        item.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        EditText editText = new EditText(this);
        editText.setMaxEms(20);
        editText.setSingleLine();
        item.addAccessoryCustomView(editText);
        section.addItemView(item, onClickListener);

        item1 = groupListView.createItemView(
                null,
                containerAttrs[1],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item1.setTag(1);
        section.addItemView(item1, onClickListener);

        item2 = groupListView.createItemView(containerAttrs[2]);
        item2.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        EditText editText2 = new EditText(this);
        editText2.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText2.setMaxEms(3);
        editText2.setSingleLine();
        item2.addAccessoryCustomView(editText2);
        section.addItemView(item2, onClickListener);

        item3 = groupListView.createItemView(containerAttrs[3]);
        item3.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        EditText editText3 = new EditText(this);
        editText3.setMaxEms(20);
        editText3.setSingleLine();
        item3.addAccessoryCustomView(editText3);
        section.addItemView(item3, onClickListener);


        item4 = groupListView.createItemView(containerAttrs[4]);
        item4.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        EditText editText4 = new EditText(this);
        editText4.setInputType(InputType.TYPE_CLASS_PHONE);
        editText4.setMaxEms(11);
        editText4.setSingleLine();
        item2.addAccessoryCustomView(editText4);
        section.addItemView(item4, onClickListener);

        item5 = groupListView.createItemView(containerAttrs[5]);
        item5.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        EditText editText5 = new EditText(this);
        editText5.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        editText5.setMaxEms(20);
        editText5.setSingleLine();
        item5.addAccessoryCustomView(editText5);
        section.addItemView(item5, onClickListener);

        item6 = groupListView.createItemView("部门状态");
        item6.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        item6.getSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        section.addItemView(item6, onClickListener);
        section.addTo(groupListView);
    }

    private void initTopbar() {
        topbar.setTitle("添加部门");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            getInputData();
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void getInputData() {
        EditText editText = (EditText) item.getAccessoryContainerView().getChildAt(0);
        EditText editText1 = (EditText) item1.getAccessoryContainerView().getChildAt(0);
        EditText editText2 = (EditText) item2.getAccessoryContainerView().getChildAt(0);
        EditText editText3 = (EditText) item3.getAccessoryContainerView().getChildAt(0);
        EditText editText4 = (EditText) item4.getAccessoryContainerView().getChildAt(0);
        EditText editText5 = (EditText) item5.getAccessoryContainerView().getChildAt(0);
        String input = editText.getText().toString();
        String input1 = editText1.getText().toString();
        String input2 = editText2.getText().toString();
        String input3 = editText3.getText().toString();
        String input4 = editText4.getText().toString();
        String input5 = editText5.getText().toString();
        boolean checked = item6.getSwitch().isChecked();
        Map<String, Object> map = new HashMap<>();

        try {
            map.put("deptName", input);
            map.put("parentId", id);
            map.put("leader", input3);
            map.put("phone", input4);
            map.put("email", input5);
            map.put("status", checked ? "0" : "1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        RetrofitClient.getInstance().getService().addDept(map)
                .compose(CommonCompose.io2main(AddDeptActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(AddDeptActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        setResult(RESULT_OK, new Intent());
                        finish();
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ADD_DEPT:
                Intent intent = getIntent();
                name = intent.getStringExtra("name");
                id = intent.getIntExtra("id", 0);
                break;
        }
//        if (data != null) {
//            String text = data.getStringExtra("text");
//            TextView detailTextView = listItemView.getDetailTextView();
//            detailTextView.setSingleLine(true);
//            detailTextView.setMaxEms(8);
//            detailTextView.setEllipsize(TextUtils.TruncateAt.END);
//            detailTextView.setText(text);
//        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_dept;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AddDeptActivity.class);
        context.startActivity(starter);
    }

}
