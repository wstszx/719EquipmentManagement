package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.DeptList;
import com.example.a719equipmentmanagement.entity.PersonOne;
import com.example.a719equipmentmanagement.entity.PersonTwo;
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
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DeptManageActivity extends BaseActivity {

    private static final int EDIT_DEPT = 1;
    private static final int ADD_PERSON = 2;
    private static final int EDIT_PERSON = 3;
    private static final int ADD_DEPT = 4;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private QMUIListPopup mListPopup;
    String[] addTypes = new String[]{
            "添加部门",
            "添加人员"
    };
    String[] parentdeletes = new String[]{
            "编辑",
            "删除"
    };
    private List<DeptList> deptLists;
    private ArrayAdapter<String> adapter;
    private int itemViewType = -1;
    private String parentTitle;
    private DeptList deptList;
    private DeptManageAdapter adapter1;
    private PersonOne personOne;
    private PersonTwo personTwo;
    private int deptId;
    private DeptList.UsersBean usersBean;
    private int userId;
    private boolean isManager;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
        initData();
    }

    private void initAdapter() {
        adapter1 = new DeptManageAdapter(this, null);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter1);
        adapter1.bindToRecyclerView(recyclerview);

    }

    private void initData() {
        Intent intent = getIntent();
        isManager = intent.getBooleanExtra("isManager", false);
        if (isManager) {
            topbar.removeAllRightViews();
            topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> {
                initListPopupIfNeed(addTypes);
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                mListPopup.show(v);
            });
        }
        RetrofitClient.getInstance().getService().getDeptList()
                .compose(CommonCompose.io2main(DeptManageActivity.this))
                .subscribe(new BaseSubscriber<List<DeptList>>(DeptManageActivity.this) {
                    @Override
                    public void onSuccess(List<DeptList> deptLists) {
                        DeptManageActivity.this.deptLists = deptLists;
                        if (deptLists != null && deptLists.size() > 0) {
                            createSection(deptLists);
                        } else {
                            adapter1.setEmptyView(R.layout.empty);
                        }
                    }
                });
    }


    private void createSection(List<DeptList> deptLists) {
        List<MultiItemEntity> list = new ArrayList<>();

        for (DeptList deptList : deptLists) {
            PersonOne personOne = new PersonOne(deptList);
            List<DeptList.UsersBean> users1 = deptList.getUsers();
            for (DeptList.UsersBean listBean : users1) {
                PersonTwo personTwo = new PersonTwo(listBean);
                personOne.addSubItem(personTwo);
            }
            list.add(personOne);
        }
        adapter1.setNewData(list);
        adapter1.setOnItemClickListener((adapter, view, position) -> {
            itemViewType = adapter.getItemViewType(position);
            ImageView imageView = (ImageView) adapter.getViewByPosition(position, R.id.iv_right);
            if (itemViewType == 0) {
                personOne = (PersonOne) adapter.getData().get(position);
                if (personOne.isExpanded()) {
                    adapter1.collapse(position, true);
                    Objects.requireNonNull(imageView).setImageResource(R.mipmap.shangla);
                } else {
                    adapter1.expand(position, true);
                    Objects.requireNonNull(imageView).setImageResource(R.mipmap.xiala);
                }
                deptList = personOne.getDeptList();
            }
        });
        if (isManager) {
            adapter1.setOnItemLongClickListener((adapter, v, position) -> {
                itemViewType = adapter.getItemViewType(position);
                switch (itemViewType) {
                    case 0:
                        personOne = (PersonOne) adapter.getData().get(position);
                        parentTitle = personOne.getParentTitle();
                        deptList = personOne.getDeptList();
                        deptId = deptList.getDeptId();
                        break;
                    case 1:
                        personTwo = (PersonTwo) adapter.getData().get(position);
                        parentTitle = personTwo.getParentTitle();
                        usersBean = personTwo.getDeptList();
                        userId = personTwo.getDeptList().getUserId();
                        deptId = usersBean.getDeptId();
                        break;
                }
                initListPopupIfNeed(parentdeletes);
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                mListPopup.show(v);
                return false;
            });
        }
    }

    private void initTopbar() {
        topbar.setTitle("组织管理");

        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dept_manage;
    }

    public static void start(Context context, boolean isManager) {
        Intent starter = new Intent(context, DeptManageActivity.class);
        starter.putExtra("isManager", isManager);
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
                    switch (s) {
                        case "删除":
                            delete();
                            break;
                        case "编辑":
                            edit();
                            break;
                        case "添加部门":
                            Intent addDeptIntent = new Intent();
                            addDeptIntent.setClass(DeptManageActivity.this, AddDeptActivity.class);
                            startActivityForResult(addDeptIntent, ADD_DEPT);
                            break;
                        case "添加人员":
                            Intent addPersonIntent = new Intent();
                            addPersonIntent.setClass(DeptManageActivity.this, AddPersonActivity.class);
                            startActivityForResult(addPersonIntent, ADD_PERSON);
                            break;
                    }
                    mListPopup.dismiss();
                }
            });
            mListPopup.setOnDismissListener(data::clear);
        }
    }

    private void edit() {
        switch (itemViewType) {
            case 0:
                Intent intent = new Intent();
                intent.putExtra("parentTitle", parentTitle);
                intent.putExtra("data", deptList);
                intent.setClass(DeptManageActivity.this, EditDeptActivity.class);
                startActivityForResult(intent, EDIT_DEPT);
                break;
            case 1:
                Intent intent1 = new Intent();
                intent1.putExtra("parentTitle", parentTitle);
                intent1.putExtra("data", usersBean);
                intent1.setClass(DeptManageActivity.this, EditPersonActivity.class);
                startActivityForResult(intent1, EDIT_PERSON);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            initData();
        }
    }

    private void delete() {
        Single<List<DeptList>> user = RetrofitClient.getInstance().getService().getDeptList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        switch (itemViewType) {
            case 0:
                RetrofitClient.getInstance().getService().delete(deptId)
                        .flatMap((Function<BaseResponse, SingleSource<List<DeptList>>>) baseResponse -> user)
                        .compose(CommonCompose.io2main(DeptManageActivity.this))
                        .subscribe(new BaseSubscriber<List<DeptList>>(DeptManageActivity.this) {
                            @Override
                            public void onSuccess(List<DeptList> deptLists) {
                                if (deptLists != null && deptLists.size() > 0) {
                                    createSection(deptLists);
                                } else {
                                    adapter1.setEmptyView(R.layout.empty);
                                }
                            }
                        });
                break;
            case 1:
                RetrofitClient.getInstance().getService().deleteUser(userId)
                        .flatMap((Function<BaseResponse, SingleSource<List<DeptList>>>) baseResponse -> user)
                        .compose(CommonCompose.io2main(DeptManageActivity.this))
                        .subscribe(new BaseSubscriber<List<DeptList>>(DeptManageActivity.this) {
                            @Override
                            public void onSuccess(List<DeptList> deptLists) {
                                if (deptLists != null && deptLists.size() > 0) {
                                    createSection(deptLists);
                                }
                            }
                        });
//                RetrofitClient.getInstance().getService().deleteUser(userId)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new BaseSubscriber<BaseResponse>(DeptManageActivity.this) {
//                            @Override
//                            public void onSuccess(BaseResponse baseResponse) {
//                                initData();
//                            }
//                        });
                break;
        }


    }


/**
 * 弹出带输入框的dialog
 */
//    private void showEditTextDialog(String text) {
//        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
//        builder.setTitle(text)
//                .setPlaceholder("请输入")
//                .setInputType(InputType.TYPE_CLASS_TEXT)
//                .addAction("取消", (dialog, index) -> dialog.dismiss())
//                .addAction("确定", (dialog, index) -> {
//                    CharSequence text1 = builder.getEditText().getText();
//                    if (text1 != null && text1.length() > 0) {
//                        Bundle bundle = new Bundle();
//                        bundle.putStringArray("titleArray", personTitles);
//                        BaseEditActivity.start(DeptManageActivity.this, text, bundle);
////                        Toast.makeText(DeptManageActivity.this, "成功" + text + ":" + text1, Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    } else {
//                        Toast.makeText(DeptManageActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .create(mCurrentDialogStyle).show();
//    }

}
