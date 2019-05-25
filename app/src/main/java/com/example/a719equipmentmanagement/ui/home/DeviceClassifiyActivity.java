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

import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.DeviceClassifiyAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.ContainerData;
import com.example.a719equipmentmanagement.entity.DeviceClassifiy;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.NetworkError;
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
import java.util.Objects;

import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceClassifiyActivity extends BaseActivity {

    private static final int EDIT_DEVICE_CLASSIFIY = 1;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.sticky_section_layout)
    QMUIStickySectionLayout stickySectionLayout;
    private QMUIListPopup mListPopup;
    private ArrayAdapter<String> adapter;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    String[] deletes = new String[]{
            "编辑",
            "删除"
    };
    private DeviceClassifiyAdapter adapter1;
    private int id;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initStickySectionLayout();
        initData();
    }

    private void initData() {
        RetrofitClient.getInstance().getService().findDeviceTypeData()
                .compose(CommonCompose.io2main(DeviceClassifiyActivity.this))
                .subscribe(new BaseSubscriber<List<DeviceClassifiy>>(DeviceClassifiyActivity.this) {
                    @Override
                    public void onSuccess(List<DeviceClassifiy> baseResponse) {
                        if (baseResponse != null && baseResponse.size() > 0) {
                            bindUi(baseResponse);
                        }
                    }
                });
    }

    private void bindUi(List<DeviceClassifiy> body) {
        ArrayList<QMUISection<SectionHeader<DeviceClassifiy>, SectionItem<DeviceClassifiy.ListBean>>> list = new ArrayList<>();
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
        adapter1.setCallback(new QMUIStickySectionAdapter.Callback<SectionHeader<DeviceClassifiy>, SectionItem<DeviceClassifiy.ListBean>>() {
            @Override
            public void loadMore(QMUISection<SectionHeader<DeviceClassifiy>, SectionItem<DeviceClassifiy.ListBean>> section, boolean loadMoreBefore) {

            }

            @Override
            public void onItemClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
                int itemViewType = holder.getItemViewType();
                if (itemViewType == 0) {
                    adapter1.toggleFold(position, false);
                }
            }

            @Override
            public boolean onItemLongClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
                int itemViewType = holder.getItemViewType();
                if (itemViewType == 0) {
                    SectionHeader<DeviceClassifiy> header = Objects.requireNonNull(adapter1.getSection(position)).getHeader();
                    id = header.getText().getId();
                    initListPopupIfNeed(deletes);
                    mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                    mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                    mListPopup.show(holder.itemView);
                }
                return true;
            }
        });
    }

    private QMUISection<SectionHeader<DeviceClassifiy>, SectionItem<DeviceClassifiy.ListBean>> createSection(DeviceClassifiy deviceClassifiy) {
        ArrayList<SectionItem<DeviceClassifiy.ListBean>> contents = new ArrayList<>();
        SectionHeader<DeviceClassifiy> header = new SectionHeader<>(deviceClassifiy);
        List<DeviceClassifiy.ListBean> list = deviceClassifiy.getList();
        if (list != null && list.size() > 0) {
            for (DeviceClassifiy.ListBean listBean : list) {
                contents.add(new SectionItem<>(listBean));
            }
        }
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
                    switch (s) {
                        case "编辑":
                            startActivityForResult(new Intent(DeviceClassifiyActivity.this, EditDeviceClassifiyActivity.class), EDIT_DEVICE_CLASSIFIY);
                            break;
                        case "删除":
                            delete();
                            break;
                    }
                    mListPopup.dismiss();
                }
            });
            mListPopup.setOnDismissListener(data::clear);
        }
    }

    private void showEditTextDialog() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("编辑设备分类名称")
                .setPlaceholder("请输入")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("确定", (dialog, index) -> {
                    CharSequence text = builder.getEditText().getText();
                    if (text != null && text.length() > 0) {
                        edit();
                        dialog.dismiss();
                    } else {
                        ToastUtils.showShort("输入不能为空");
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void delete() {
        RetrofitClient.getInstance().getService().deleteDeviceType(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(DeviceClassifiyActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        initData();
                    }
                });
    }

    private void edit() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pid", 0);
            jsonObject.put("name", "压力计");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        RetrofitClient.getInstance().getService().editDeviceType(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(DeviceClassifiyActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        initData();
                    }
                });

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
