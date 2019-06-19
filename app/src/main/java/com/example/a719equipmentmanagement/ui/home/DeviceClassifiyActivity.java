package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ContainerManageAdapter;
import com.example.a719equipmentmanagement.adapter.DeviceClassifiyAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.ContainerData;
import com.example.a719equipmentmanagement.entity.ContainerOne;
import com.example.a719equipmentmanagement.entity.ContainerTwo;
import com.example.a719equipmentmanagement.entity.DeviceClassifiy;
import com.example.a719equipmentmanagement.entity.DeviceTypeData;
import com.example.a719equipmentmanagement.entity.DeviceTypeOne;
import com.example.a719equipmentmanagement.entity.DeviceTypeTwo;
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

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private static final int ADD_DEVICE_CLASSIFY = 2;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private QMUIListPopup mListPopup;
    private ArrayAdapter<String> adapter;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    String[] deletes = new String[]{
            "编辑",
            "删除"
    };
    private DeviceClassifiyAdapter adapter1;
    private int id;
    private String parentClassifiy;
    private String name;
    private int pid;
    private DeviceTypeOne deviceTypeOne;
    private DeviceClassifiy deviceTypeData;
    private int itemViewType;
    private boolean isManager;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        isManager = intent.getBooleanExtra("isManager", false);
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
        List<MultiItemEntity> list = new ArrayList<>();
        for (DeviceClassifiy deviceClassifiy : body) {
            DeviceTypeOne deviceTypeOne = new DeviceTypeOne(deviceClassifiy);
            List<DeviceClassifiy.CategorysBean> categorys = deviceClassifiy.getCategorys();
            if (categorys != null && categorys.size() > 0) {
                for (DeviceClassifiy.CategorysBean category : categorys) {
                    DeviceTypeTwo deviceTypeTwo = new DeviceTypeTwo(category);
                    deviceTypeOne.addSubItem(deviceTypeTwo);
                }
            }
            list.add(deviceTypeOne);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter1 = new DeviceClassifiyAdapter(list);
        adapter1.bindToRecyclerView(recyclerView);
        adapter1.setEmptyView(R.layout.empty);
        recyclerView.setAdapter(adapter1);
        adapter1.setOnItemClickListener((adapter, view, position) -> {
            int itemViewType = adapter.getItemViewType(position);
            if (itemViewType == 0) {
                deviceTypeOne = (DeviceTypeOne) adapter.getData().get(position);
                ImageView imageView = (ImageView) adapter.getViewByPosition(position, R.id.iv_right);
                if (deviceTypeOne.isExpanded()) {
                    adapter1.collapse(position, true);
                    Objects.requireNonNull(imageView).setImageResource(R.mipmap.shangla);
                } else {
                    adapter1.expand(position, true);
                    Objects.requireNonNull(imageView).setImageResource(R.mipmap.xiala);
                }
                deviceTypeData = deviceTypeOne.getData();
            }
        });
        if (isManager) {
            topbar.removeAllRightViews();
            topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> startActivityForResult(new Intent(DeviceClassifiyActivity.this, AddDeviceClassifyActivity.class), ADD_DEVICE_CLASSIFY));
            adapter1.setOnItemLongClickListener((adapter, view, position) -> {
                itemViewType = adapter.getItemViewType(position);
                switch (itemViewType) {
                    case 0:
                        DeviceTypeOne deviceTypeOne = (DeviceTypeOne) adapter.getData().get(position);
                        DeviceClassifiy data = deviceTypeOne.getData();
                        id = data.getId();
                        name = data.getName();
                        pid = 0;
                        break;
                    case 1:
                        DeviceTypeTwo deviceTypeTwo = (DeviceTypeTwo) adapter.getData().get(position);
                        int parentPosition = adapter1.getParentPosition(deviceTypeTwo);
                        DeviceTypeOne deviceTypeOne1 = (DeviceTypeOne) adapter.getData().get(parentPosition);
                        pid = deviceTypeOne1.getData().getId();
                        DeviceClassifiy.CategorysBean deviceTypeTwoData = deviceTypeTwo.getData();
                        name = deviceTypeTwoData.getName();
                        this.id = deviceTypeTwoData.getId();
                        break;
                }
                initListPopupIfNeed(deletes);
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                mListPopup.show(view);
                return false;
            });
        }
    }


    private void initTopbar() {
        topbar.setTitle("设备分类");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
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
            mListPopup.create(QMUIDisplayHelper.dp2px(this, 250), QMUIDisplayHelper.dp2px(this, 200), (adapterView, view, i, l) -> {
                TextView textView = (TextView) view;
                String s = textView.getText().toString();
                switch (s) {
                    case "编辑":
                        edit();
                        break;
                    case "删除":
                        delete();
                        break;
                }
                mListPopup.dismiss();
            });
            mListPopup.setOnDismissListener(data::clear);
        }
    }

    private void edit() {
        Intent intent = new Intent(DeviceClassifiyActivity.this, EditDeviceClassifiyActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
//        intent.putExtra("parentClassifiy", parentClassifiy);
        intent.putExtra("pid", pid);
        startActivityForResult(intent, EDIT_DEVICE_CLASSIFIY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            initData();
        }
        super.onActivityResult(requestCode, resultCode, data);
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
                        dialog.dismiss();
                    } else {
                        ToastUtils.showShort("输入不能为空");
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void delete() {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("id", id);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_classifiy;
    }

    public static void start(Context context, boolean isManager) {
        Intent starter = new Intent(context, DeviceClassifiyActivity.class);
        starter.putExtra("isManager", isManager);
        context.startActivity(starter);
    }

}
