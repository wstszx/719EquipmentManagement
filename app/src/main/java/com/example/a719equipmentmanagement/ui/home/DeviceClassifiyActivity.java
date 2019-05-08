package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.DeviceClassifiyAdapter;
import com.example.a719equipmentmanagement.adapter.PeopleManageAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.ContainerData;
import com.example.a719equipmentmanagement.entity.DeviceClassifiy;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceClassifiyActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.sticky_section_layout)
    QMUIStickySectionLayout stickySectionLayout;
    private QMUIListPopup mListPopup;
    private ArrayAdapter<String> adapter;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    String[] deletes = new String[]{
            "删除",
            "编辑"
    };
    private DeviceClassifiyAdapter adapter1;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initStickySectionLayout();
        initData();
    }

    private void initData() {
        RetrofitClient.getInstance().getService().findDeviceTypeData().enqueue(new Callback<List<DeviceClassifiy>>() {
            @Override
            public void onResponse(Call<List<DeviceClassifiy>> call, Response<List<DeviceClassifiy>> response) {
                List<DeviceClassifiy> body = response.body();
                if (body != null && body.size() > 0) {
                    bindUi(body);
                }
            }

            @Override
            public void onFailure(Call<List<DeviceClassifiy>> call, Throwable t) {

            }
        });
    }

    private void bindUi(List<DeviceClassifiy> body) {
        ArrayList<QMUISection<SectionHeader, SectionItem<DeviceClassifiy>>> list = new ArrayList<>();
        for (DeviceClassifiy deviceClassifiy : body) {
            list.add(createSection(deviceClassifiy));
        }
        adapter1.setData(list);
    }

    private void initStickySectionLayout() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        stickySectionLayout.setBackgroundColor(getResources().getColor(R.color.qmui_config_color_white));
        stickySectionLayout.setLayoutManager(manager);
        adapter1 = new DeviceClassifiyAdapter();
        stickySectionLayout.setAdapter(adapter1, true);
        adapter1.setCallback(new QMUIStickySectionAdapter.Callback<SectionHeader, SectionItem<DeviceClassifiy>>() {
            @Override
            public void loadMore(QMUISection<SectionHeader, SectionItem<DeviceClassifiy>> section, boolean loadMoreBefore) {

            }

            @Override
            public void onItemClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
                int itemViewType = holder.getItemViewType();
                switch (itemViewType) {
                    case 0:
                        adapter1.toggleFold(position, false);
                        break;
                    case 1:
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
                initListPopupIfNeed(deletes);
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                mListPopup.show(holder.itemView);
                return true;
            }
        });
    }

    private QMUISection<SectionHeader, SectionItem<DeviceClassifiy>> createSection(DeviceClassifiy deviceClassifiy) {
        String name = deviceClassifiy.getName();
        SectionHeader header = new SectionHeader(name);
        ArrayList<SectionItem<DeviceClassifiy>> contents = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            contents.add(new SectionItem(new ContainerData.ListBean()));
//        }
        // if test load more, you can open the code
//        section.setExistAfterDataToLoad(true);
//        section.setExistBeforeDataToLoad(true);
        return new QMUISection<>(header, contents, true);
    }

    private void initTopbar() {
        topbar.setTitle("设备分类");
        topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> {
            AddDeviceClassifyActivity.start(DeviceClassifiyActivity.this);
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

//    /**
//     * 弹出带输入框的dialog
//     */
//    private void showEditTextDialog() {
//        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
//        builder.setTitle("添加设备分类")
//                .setPlaceholder("请输入")
//                .setInputType(InputType.TYPE_CLASS_TEXT)
//                .addAction("取消", (dialog, index) -> dialog.dismiss())
//                .addAction("确定", (dialog, index) -> {
//                    CharSequence text1 = builder.getEditText().getText();
//                    if (text1 != null && text1.length() > 0) {
//                        Toast.makeText(DeviceClassifiyActivity.this, "成功添加设备分类" + ":" + text1, Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    } else {
//                        Toast.makeText(DeviceClassifiyActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .create(mCurrentDialogStyle).show();
//    }

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_classifiy;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, DeviceClassifiyActivity.class);
        context.startActivity(starter);
    }

}
