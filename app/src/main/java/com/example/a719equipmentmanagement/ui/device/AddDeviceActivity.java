package com.example.a719equipmentmanagement.ui.device;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.DeviceData;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.home.AddDeptActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDeviceActivity extends BaseActivity {


    private String[] containerAttrs = {"设备名称", "技术指标", "生产厂家", "责任人"};
//    private String[] containerAttrValue = {"05", "5MPa", "2019009", "三科室", "3#3", "001",
//            "3", "10", "12"};

    @BindView(R.id.groupListView_addDevice)
    QMUIGroupListView groupListView_addDevice;

    @BindView(R.id.topbar_addDevice)
    QMUITopBarLayout topbar_addDevice;

    private QMUICommonListItemView listItemView;

    private QMUICommonListItemView item0;
    private QMUICommonListItemView item1;
    private QMUICommonListItemView item2;
    private QMUICommonListItemView item3;
//    private QMUICommonListItemView item4;
//    private QMUICommonListItemView item5;

    private int rowCount;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initGroupListView();
    }

    private void initTopbar() {
        topbar_addDevice.setTitle("添加设备");

        topbar_addDevice.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });

        topbar_addDevice.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
            getInputData();
        });

//        RetrofitClient.getInstance().getService().findDeviceData()
//                .compose(CommonCompose.io2main(AddDeviceActivity.this))
//                .subscribe(new BaseSubscriber<DeviceData>(AddDeviceActivity.this) {
//                    @Override
//                    public void onSuccess(DeviceData baseResponse) {
//                        if (baseResponse != null ) {
//                            List<DeviceData.RowsBean> rows = baseResponse.getRows();
//                            rowCount=rows.size()+1;
//                        }
//                    }
//                });
    }

    private void getInputData() {
        String input0 = item0.getDetailText().toString();
        String input1 = item1.getDetailText().toString();
        String input2 = item2.getDetailText().toString();
        String input3 = item3.getDetailText().toString();
//        String input4 = item4.getDetailText().toString();

        JSONObject jsonObject = new JSONObject();
        try {
//            jsonObject.put("id",rowCount);
            jsonObject.put("equipNo",rowCount+"");
            jsonObject.put("status",1);  //添加设备时默认状态为“可用”
//                jsonObject.put("sn", 5);
//                jsonObject.put("category_id", 5);

            //此三行为目前展示需要，对应DeviceAdapter添加对应解析
            int i=rowCount%4;
            jsonObject.put("locationId", i);
            jsonObject.put("deptId", i);
            jsonObject.put("categoryId", i);

            jsonObject.put("name", input0);
            jsonObject.put("parameter", input1) ;
            jsonObject.put("manufactuer", input2);
            jsonObject.put("responsor", input3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        RetrofitClient.getInstance().getService().addDevice(requestBody)
                .compose(CommonCompose.io2main(AddDeviceActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(AddDeviceActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        setResult(RESULT_OK,new Intent());
                    }
                });

//            Intent intent = new Intent();
//            intent.putExtra("text", edittext.getText().toString());
//            setResult(RESULT_OK, intent);
        finish();
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
            if (((QMUICommonListItemView) v).getAccessoryType() == QMUICommonListItemView.ACCESSORY_TYPE_SWITCH) {
                ((QMUICommonListItemView) v).getSwitch().toggle();
            }

            listItemView = (QMUICommonListItemView) v;
            int tag = (int) listItemView.getTag();
            Intent intent = new Intent();
            intent.putExtra("text", listItemView.getDetailText().toString());
            intent.setClass(this, BaseItemEditActivity.class);
            startActivityForResult(intent, tag);
        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
        item0 = groupListView_addDevice.createItemView(
                null,
                containerAttrs[0],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item0.setTag(0);
        section.addItemView(item0, onClickListener);
        item1 = groupListView_addDevice.createItemView(
                null,
                containerAttrs[1],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item1.setTag(1);
        section.addItemView(item1, onClickListener);
        item2 = groupListView_addDevice.createItemView(
                null,
                containerAttrs[2],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item2.setTag(2);
        section.addItemView(item2, onClickListener);
        item3 = groupListView_addDevice.createItemView(
                null,
                containerAttrs[3],
                " ",
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item3.setTag(3);
        section.addItemView(item3, onClickListener);
//        item4 = groupListView_addDevice.createItemView(
//                null,
//                containerAttrs[4],
//                " ",
//                QMUICommonListItemView.HORIZONTAL,
//                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//        item4.setTag(4);
//        section.addItemView(item4, onClickListener);

//        for (int i = 0; i < containerAttrs.length; i++) {
//            QMUICommonListItemView item = groupListView_addDevice.createItemView(
//                    null,
//                    containerAttrs[i],
//                    containerAttrValue[i],
//                    QMUICommonListItemView.HORIZONTAL,
//                    QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//            item.setTag(i);
//            section.addItemView(item, onClickListener);
//        }

        section.addTo(groupListView_addDevice);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_device;
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

}
