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
import com.example.a719equipmentmanagement.base.BaseEditActivity;
import com.example.a719equipmentmanagement.ui.home.GenarateQRActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;

public class AddDeviceActivity extends BaseActivity {


    private String[] containerAttrs = {"设备名称", "技术指标", "设备编号", "所属部门", "存放位置", "属性1",
            "属性2", "属性3", "属性4"};
    private String[] containerAttrValue = {"05", "5MPa", "2019009", "三科室", "3#3", "001",
            "3", "10", "12"};

    @BindView(R.id.groupListView_addDevice)
    QMUIGroupListView groupListView_addDevice;

    @BindView(R.id.topbar_addDevice)
    QMUITopBarLayout topbar_addDevice;

    private QMUICommonListItemView listItemView;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initGroupListView();
    }

    private void initTopbar() {
        topbar_addDevice.setTitle("添加设备");
//        topbar_addDevice.addRightImageButton(R.mipmap.qrcode, R.id.qrcode).setOnClickListener(v -> {
//            GenarateQRActivity.start(this);
//        });

        topbar_addDevice.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });

        topbar_addDevice.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
//            Intent intent = new Intent();
//            intent.putExtra("text", edittext.getText().toString());
//            setResult(RESULT_OK, intent);
            finish();
        });
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
            QMUICommonListItemView item = groupListView_addDevice.createItemView(
                    null,
                    containerAttrs[i],
                    containerAttrValue[i],
                    QMUICommonListItemView.HORIZONTAL,
                    QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
            item.setTag(i);
            section.addItemView(item, onClickListener);
        }
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
