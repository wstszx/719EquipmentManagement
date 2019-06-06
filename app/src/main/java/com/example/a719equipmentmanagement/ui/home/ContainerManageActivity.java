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

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ContainerManageAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.Container;
import com.example.a719equipmentmanagement.entity.ContainerData;
import com.example.a719equipmentmanagement.entity.ContainerOne;
import com.example.a719equipmentmanagement.entity.ContainerTwo;
import com.example.a719equipmentmanagement.entity.PersonOne;
import com.example.a719equipmentmanagement.entity.PersonTwo;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.NetworkError;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.device.AddDeviceActivity;
import com.example.a719equipmentmanagement.view.CustomInputDialog;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
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
import androidx.navigation.Navigation;
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

/**
 * 货柜管理
 */
public class ContainerManageActivity extends BaseActivity {

    private static final int EDIT_CONTAINER = 1;
    private static final int ADD_CONTAINER = 2;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private ArrayAdapter<String> adapter;
    private QMUIListPopup mListPopup;
    String[] itemDeletes = new String[]{
            "编辑",
            "删除"
    };
    private ContainerManageAdapter adapter1;
    private int id;
    private ContainerData containerData;
    private int itemViewType;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initData() {
        RetrofitClient.getInstance().getService().findContainerData()
                .compose(CommonCompose.io2main(ContainerManageActivity.this))
                .subscribe(new BaseSubscriber<List<ContainerData>>(ContainerManageActivity.this) {
                    @Override
                    public void onSuccess(List<ContainerData> baseResponse) {
                        if (baseResponse != null && baseResponse.size() > 0) {
                            bindUi(baseResponse);
                        }
                    }
                });
    }

    private void bindUi(List<ContainerData> body) {
        List<MultiItemEntity> list = new ArrayList<>();
        for (ContainerData containerData : body) {
            ContainerOne containerOne = new ContainerOne(containerData);
            List<ContainerData.ContainersBean> containers = containerData.getContainers();
            for (ContainerData.ContainersBean container : containers) {
                ContainerTwo containerTwo = new ContainerTwo(container);
                containerOne.addSubItem(containerTwo);
            }
            list.add(containerOne);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter1 = new ContainerManageAdapter(list);
        adapter1.bindToRecyclerView(recyclerView);
        adapter1.setEmptyView(R.layout.empty);
        recyclerView.setAdapter(adapter1);
        adapter1.setOnItemClickListener((adapter, view, position) -> {
            int itemViewType = adapter.getItemViewType(position);
            if (itemViewType == 0) {
                ContainerOne containerOne = (ContainerOne) adapter.getData().get(position);
                ImageView imageView = (ImageView) adapter.getViewByPosition(position, R.id.iv_right);
                if (containerOne.isExpanded()) {
                    adapter1.collapse(position, true);
                    Objects.requireNonNull(imageView).setImageResource(R.mipmap.shangla);
                } else {
                    adapter1.expand(position, true);
                    Objects.requireNonNull(imageView).setImageResource(R.mipmap.xiala);
                }
                containerData = containerOne.getData();
            }
        });

        adapter1.setOnItemLongClickListener((adapter, view, position) -> {
            itemViewType = adapter.getItemViewType(position);
            switch (itemViewType) {
                case 0:
                    ContainerOne containerOne = (ContainerOne) adapter.getData().get(position);
                    ContainerData data = containerOne.getData();
                    String name = data.getName();
                    int deptId = data.getDeptId();
                    break;
                case 1:
                    ContainerTwo containerTwo = (ContainerTwo) adapter.getData().get(position);
                    ContainerData.ContainersBean containerTwoData = containerTwo.getData();
                    String name1 = containerTwoData.getName();
                    int id = containerTwoData.getId();
                    break;
            }
            initListPopupIfNeed(itemDeletes);
            mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
            mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
            mListPopup.show(view);
            return false;
        });
    }

    private void initTopbar() {
        topbar.setTitle("货柜管理");
        topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v ->
                startActivityForResult(new Intent(this, AddContainerActivity.class), ADD_CONTAINER));
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
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
            mListPopup.create(QMUIDisplayHelper.dp2px(this, 250), QMUIDisplayHelper.dp2px(this, 200), (adapterView, view, i, l) -> {
                mListPopup.dismiss();
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
            });
            mListPopup.setOnDismissListener(data::clear);
        }
    }

    private void edit() {
        switch (itemViewType) {
            case 0:
                startActivityForResult(new Intent(ContainerManageActivity.this, EditContainerActivity.class), EDIT_CONTAINER);
                break;
            case 1:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null && resultCode == RESULT_OK) {
            if (requestCode == EDIT_CONTAINER) {
                initData();
            } else if (requestCode == ADD_CONTAINER) {
                initData();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void delete() {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), id + "");
        RetrofitClient.getInstance().getService().deleteContainer(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(ContainerManageActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        initData();
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_container_manage;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ContainerManageActivity.class);
        context.startActivity(starter);
    }

    /**
     * 弹出带输入框的dialog
     */
//    private void showEditTextDialog() {
//        CustomInputDialog customDialogBuilder = new CustomInputDialog(this);
//        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
//        customDialogBuilder.setTitle("添加货柜")
//                .setPlaceholder("请输入货柜名")
//                .setPlaceholder1("请输入层数")
//                .setInputType(InputType.TYPE_CLASS_TEXT)
//                .setInputType1(InputType.TYPE_CLASS_NUMBER)
//                .addAction("取消", (dialog, index) -> dialog.dismiss())
//                .addAction("确定", (dialog, index) -> {
//                    CharSequence text = customDialogBuilder.getEditText().getText();
//                    CharSequence text1 = customDialogBuilder.getEditText1().getText();
//                    dialog.dismiss();
////                    if (text1 != null && text1.length() > 0) {
////                        Toast.makeText(ContainerManageActivity.this, "成功" + "添加货柜" + ":" + text + text1, Toast.LENGTH_SHORT).show();
////                        dialog.dismiss();
////                    } else {
////                        Toast.makeText(ContainerManageActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
////                    }
//                })
//                .create(mCurrentDialogStyle).show();
//    }


}
