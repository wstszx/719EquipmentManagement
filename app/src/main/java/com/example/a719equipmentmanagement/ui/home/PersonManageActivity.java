package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.PeopleManageAdapter;
import com.example.a719equipmentmanagement.adapter.PersonManageAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.PersonOne;
import com.example.a719equipmentmanagement.entity.PersonThree;
import com.example.a719equipmentmanagement.entity.PersonTwo;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import butterknife.BindView;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonManageActivity extends BaseActivity {


    private static final int EDIT_DEPT = 1;
    private static final int EDIT_CHILD_DEPT = 2;
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
    String[] deletes = new String[]{
            "删除"
    };
    private List<User> users;
    private ArrayAdapter<String> adapter;
    private int itemViewType = -1;
    private String parentTitle;
    private String parentDept;
    private User user;
    private PersonManageAdapter adapter1;
    private PersonOne personOne;
    private PersonTwo personTwo;
    private int deptId;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        initTopbar();
        initData();
    }

    private void initData() {
        RetrofitClient.getInstance().getService().getUser()
                .compose(CommonCompose.io2main(PersonManageActivity.this))
                .subscribe(new BaseSubscriber<List<User>>(PersonManageActivity.this) {
                    @Override
                    public void onSuccess(List<User> users) {
                        PersonManageActivity.this.users = users;
                        if (users != null && users.size() > 0) {
                            createSection(users);
                        }
                    }
                });

    }


    private void createSection(List<User> users) {
        List<MultiItemEntity> list = new ArrayList<>();
        for (User user : users) {
            int id = user.getId();
            if (100 == id) {
                int deptId = user.getDeptId();
                PersonOne personOne = new PersonOne(user);

                for (User user1 : users) {
                    int parentId1 = user1.getParentId();

                    if (deptId == parentId1) {
                        int deptId1 = user1.getDeptId();

                        PersonTwo personTwo = new PersonTwo(user1);
                        for (User user2 : users) {
                            int parentId2 = user2.getParentId();
                            if (deptId1 == parentId2) {
                                PersonThree personThree = new PersonThree(user2);
                                personTwo.addSubItem(personThree);
                            }
                        }
                        personOne.addSubItem(personTwo);
                    }
                }
                list.add(personOne);
            }
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter1 = new PersonManageAdapter(this, list);
        adapter1.bindToRecyclerView(recyclerview);
        adapter1.setEmptyView(R.layout.empty);
        recyclerview.setAdapter(adapter1);
        adapter1.setOnItemClickListener((adapter, view, position) -> {
            itemViewType = adapter.getItemViewType(position);
            ImageView imageView = (ImageView) adapter.getViewByPosition(position, R.id.iv_right);
            switch (itemViewType) {
                case 0:
                    personOne = (PersonOne) adapter.getData().get(position);
                    if (personOne.isExpanded()) {
                        adapter1.collapse(position, true);
                        Objects.requireNonNull(imageView).setImageResource(R.mipmap.shangla);
                    } else {
                        adapter1.expand(position, true);
                        Objects.requireNonNull(imageView).setImageResource(R.mipmap.xiala);
                    }
                    user = personOne.getUser();
                    break;
                case 1:
                    personTwo = (PersonTwo) adapter.getData().get(position);
                    if (personTwo.isExpanded()) {
                        adapter1.collapse(position, true);
                        Objects.requireNonNull(imageView).setImageResource(R.mipmap.shangla);
                    } else {
                        adapter1.expand(position, true);
                        Objects.requireNonNull(imageView).setImageResource(R.mipmap.xiala);
                    }
                    user = personTwo.getUser();
                    break;
                case 2:
                    PersonThree personThree = (PersonThree) adapter.getData().get(position);
                    user = personThree.getUser();
                    break;
            }
        });
        adapter1.setOnItemLongClickListener((adapter, v, position) -> {
            itemViewType = adapter.getItemViewType(position);
            switch (itemViewType) {
                case 0:
                    personOne = (PersonOne) adapter.getData().get(position);
                    user = personOne.getUser();
                    deptId = user.getDeptId();
                    break;
                case 1:
                    personTwo = (PersonTwo) adapter.getData().get(position);
                    user = personTwo.getUser();
                    deptId = user.getDeptId();
                    break;
                case 2:
                    PersonThree personThree = (PersonThree) adapter.getData().get(position);
                    user = personThree.getUser();
                    deptId = user.getDeptId();
                    break;
            }
            initListPopupIfNeed(parentdeletes);
            mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
            mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
            mListPopup.show(v);
            return false;
        });
    }

    private void initTopbar() {
        topbar.setTitle("人员管理");
        topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> {
            initListPopupIfNeed(addTypes);
            mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
            mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
            mListPopup.show(v);
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_manage;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, PersonManageActivity.class);
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
                        case "添加部门":
                            Intent addDeptIntent = new Intent();
                            addDeptIntent.setClass(PersonManageActivity.this, AddDeptActivity.class);
                            startActivityForResult(addDeptIntent, ADD_DEPT);
                            break;
                        case "添加人员":
                            AddPersonActivity.start(PersonManageActivity.this);
                            break;
                        case "删除":
                            delete();
                            break;
                        case "编辑":
                            edit();
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
                parentTitle = "无";
                break;
            case 1:
                parentTitle = personOne.getUser().getDeptName();
                break;
            case 2:
                parentTitle = personTwo.getUser().getDeptName();
                break;
        }
        Intent intent = new Intent();
        intent.putExtra("parentTitle", parentTitle);
        intent.putExtra("data", user);
        intent.setClass(PersonManageActivity.this, EditDeptActivity.class);
        startActivity(intent);
//        switch (itemViewType) {
//            case 0:
//                Intent intent = new Intent();
//                intent.putExtra("parentTitle", parentTitle);
//                intent.putExtra("data", user);
//                intent.setClass(PersonManageActivity.this, EditDeptActivity.class);
//                startActivityForResult(intent, EDIT_DEPT);
//                break;
//            case 1:
//                Intent intent1 = new Intent();
//                intent1.putExtra("parentTitle", parentTitle);
//                intent1.putExtra("data", user);
//                intent1.setClass(PersonManageActivity.this, EditDeptActivity.class);
//                startActivityForResult(intent1, EDIT_CHILD_DEPT);
//                break;
//            case 2:
//                Intent intent2 = new Intent();
//                intent2.putExtra("parentTitle", parentTitle);
//                intent2.putExtra("data", user);
//                intent2.setClass(PersonManageActivity.this, EditDeptActivity.class);
//                startActivityForResult(intent2, EDIT_PERSON);
//                break;
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case EDIT_DEPT:
                RetrofitClient.getInstance().getService().editDept()
                        .compose(CommonCompose.io2main(PersonManageActivity.this))
                        .subscribe(new BaseSubscriber<BaseResponse>(PersonManageActivity.this) {
                            @Override
                            public void onSuccess(BaseResponse baseResponse) {
                                initData();
                            }
                        });
                break;
            case EDIT_CHILD_DEPT:
                RetrofitClient.getInstance().getService().editDept()
                        .compose(CommonCompose.io2main(PersonManageActivity.this))
                        .subscribe(new BaseSubscriber<BaseResponse>(PersonManageActivity.this) {
                            @Override
                            public void onSuccess(BaseResponse baseResponse) {
                                initData();
                            }
                        });
                break;
            case EDIT_PERSON:
                RetrofitClient.getInstance().getService().editUser()
                        .compose(CommonCompose.io2main(PersonManageActivity.this))
                        .subscribe(new BaseSubscriber<BaseResponse>(PersonManageActivity.this) {
                            @Override
                            public void onSuccess(BaseResponse baseResponse) {
                                initData();
                            }
                        });
                break;
            case ADD_DEPT:
                initData();
                break;

        }
    }

    private void delete() {
        switch (itemViewType) {
            case 0:
            case 1:
                RetrofitClient.getInstance().getService().delete(deptId)
                        .flatMap(new Function<BaseResponse, SingleSource<List<User>>>() {
                            @Override
                            public SingleSource<List<User>> apply(BaseResponse baseResponse) throws Exception {
                                return RetrofitClient.getInstance().getService().getUser();
                            }
                        })
                        .compose(CommonCompose.io2main(PersonManageActivity.this))
                        .subscribe(new BaseSubscriber<List<User>>(PersonManageActivity.this) {
                            @Override
                            public void onSuccess(List<User> users) {
                                if (users != null && users.size() > 0) {
                                    createSection(users);
                                }
                            }
                        });
//                        .subscribe(new BaseSubscriber<BaseResponse>(PersonManageActivity.this) {
//                            @Override
//                            public void onSuccess(BaseResponse baseResponse) {
//                                initData();
//                            }
//                        });
                break;
            case 2:
                RetrofitClient.getInstance().getService().deleteUser("")
                        .compose(CommonCompose.io2main(PersonManageActivity.this))
                        .subscribe(new BaseSubscriber<BaseResponse>(PersonManageActivity.this) {
                            @Override
                            public void onSuccess(BaseResponse baseResponse) {
                                initData();
                            }
                        });
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
//                        BaseEditActivity.start(PersonManageActivity.this, text, bundle);
////                        Toast.makeText(PersonManageActivity.this, "成功" + text + ":" + text1, Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    } else {
//                        Toast.makeText(PersonManageActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .create(mCurrentDialogStyle).show();
//    }

}
