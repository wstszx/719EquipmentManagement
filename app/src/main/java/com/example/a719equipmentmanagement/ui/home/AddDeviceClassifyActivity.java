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
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDeviceClassifyActivity extends BaseActivity {

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private QMUICommonListItemView listItemView;
    private String[] containerAttrs = {"部门名称", "显示排序", "负责人", "联系电话", "邮箱", "部门状态"};
    private QMUICommonListItemView item;
    private QMUICommonListItemView item1;
    private QMUICommonListItemView item2;
    private QMUICommonListItemView item3;
    private QMUICommonListItemView item4;
    private QMUICommonListItemView item5;

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
            intent.setClass(this, BaseItemEditActivity.class);
            startActivityForResult(intent, tag);
        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);

        item = groupListView.createItemView(
                null,
                containerAttrs[0],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item.setTag(0);
        section.addItemView(item, onClickListener);
        item1 = groupListView.createItemView(
                null,
                containerAttrs[1],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item1.setTag(1);
        section.addItemView(item1, onClickListener);
        item2 = groupListView.createItemView(
                null,
                containerAttrs[2],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item2.setTag(2);
        section.addItemView(item2, onClickListener);
        item3 = groupListView.createItemView(
                null,
                containerAttrs[3],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item3.setTag(3);
        section.addItemView(item3, onClickListener);
        item4 = groupListView.createItemView(
                null,
                containerAttrs[4],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item4.setTag(4);
        section.addItemView(item4, onClickListener);
        item5 = groupListView.createItemView(
                null,
                containerAttrs[5],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item5.setTag(5);
        section.addItemView(item5, onClickListener);
        section.addTo(groupListView);
    }

    private void initTopbar() {
        topbar.setTitle("添加设备分类");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            getInputData();
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void getInputData() {
//        String input = item.getDetailText().toString();
//        String input1 = item1.getDetailText().toString();
//        String input2 = item2.getDetailText().toString();
//        String input3 = item3.getDetailText().toString();
//        String input4 = item4.getDetailText().toString();
//        String input5 = item5.getDetailText().toString();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pid", 0);
            jsonObject.put("name", "压力计");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        RetrofitClient.getInstance().getService().addDeviceType(requestBody).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

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
        return R.layout.activity_add_dept;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AddDeviceClassifyActivity.class);
        context.startActivity(starter);
    }
}
