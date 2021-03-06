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
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ContainerManageAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.ContainerData;
import com.example.a719equipmentmanagement.entity.ContainerOne;
import com.example.a719equipmentmanagement.entity.ContainerTwo;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 货柜管理
 */
public class ContainerManageActivity extends BaseActivity {

    private static final int EDIT_CONTAINER = 1;
    private static final int ADD_CONTAINER = 2;
    private static final int EDIT_CONTAINER_LEVEL = 3;
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
    private int deptId;
    private String name;
    private boolean isManager;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        isManager = intent.getBooleanExtra("isManager", false);
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
            if (containers != null && containers.size() > 0) {
                for (ContainerData.ContainersBean container : containers) {
                    ContainerTwo containerTwo = new ContainerTwo(container);
                    containerOne.addSubItem(containerTwo);
                }
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
        if (isManager) {
            topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v ->
                    startActivityForResult(new Intent(this, AddContainerActivity.class), ADD_CONTAINER));

            adapter1.setOnItemLongClickListener((adapter, view, position) -> {
                itemViewType = adapter.getItemViewType(position);
                switch (itemViewType) {
                    case 0:
                        ContainerOne containerOne = (ContainerOne) adapter.getData().get(position);
                        ContainerData data = containerOne.getData();
                        id = data.getId();
                        name = data.getName();
                        deptId = data.getDeptId();
                        break;
                    case 1:
                        ContainerTwo containerTwo = (ContainerTwo) adapter.getData().get(position);
                        ContainerData.ContainersBean containerTwoData = containerTwo.getData();
                        name = containerTwoData.getName();
                        id = containerTwoData.getId();
                        break;
                }
                initListPopupIfNeed(itemDeletes);
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                mListPopup.show(view);
                return false;
            });
        }
    }

    private void initTopbar() {
        topbar.setTitle("货柜管理");

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
        Intent intent = new Intent();
        intent.putExtra("name", name);
        intent.putExtra("deptId", deptId);
        intent.putExtra("id", id);
        switch (itemViewType) {
            case 0:
                intent.setClass(ContainerManageActivity.this, EditContainerActivity.class);
                startActivityForResult(intent, EDIT_CONTAINER);
                break;
            case 1:
                intent.setClass(ContainerManageActivity.this, EditContainerLevelActivity.class);
                startActivityForResult(intent, EDIT_CONTAINER_LEVEL);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            initData();
//            if (requestCode == EDIT_CONTAINER) {
//                initData();
//            } else if (requestCode == ADD_CONTAINER) {
//                initData();
//            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void delete() {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), id + "");
        RetrofitClient.getInstance().getService().deleteContainer(id)
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

    public static void start(Context context, boolean isManager) {
        Intent starter = new Intent(context, ContainerManageActivity.class);
        starter.putExtra("isManager", isManager);
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
