package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
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

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonManageActivity extends BaseActivity {


    private static final int EDIT_DEPT = 1;
    private static final int EDIT_PERSON = 2;
    private static final int ADD_DEPT = 3;
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

//    private void initStickySectionLayout() {
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        stickySectionLayout.setBackgroundColor(getResources().getColor(R.color.qmui_config_color_white));
//        stickySectionLayout.setLayoutManager(manager);
//        adapter1 = new PeopleManageAdapter();
//        stickySectionLayout.setAdapter(adapter1, true);
//        list = new ArrayList<>();
//
//        adapter1.setCallback(new QMUIStickySectionAdapter.Callback<SectionHeader<User>, SectionItem<User>>() {
//            @Override
//            public void loadMore(QMUISection<SectionHeader<User>, SectionItem<User>> section, boolean loadMoreBefore) {
//
//            }
//
//            @Override
//            public void onItemClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
//                int itemViewType = holder.getItemViewType();
//                switch (itemViewType) {
//                    case 0:
//                        adapter1.toggleFold(position, false);
//                        break;
//                    case 1:
//                        SectionItem<User> sectionItem = adapter1.getSectionItem(position);
//                        User listBean = sectionItem != null ? sectionItem.getListBean() : null;
//                        PersonDetailActivity.start(PersonManageActivity.this, listBean);
//                        break;
//                }
//            }
//
//            @Override
//            public boolean onItemLongClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
//                itemViewType = holder.getItemViewType();
//                View view = holder.itemView;
//                switch (itemViewType) {
//                    case 0:
//                        initListPopupIfNeed(parentdeletes);
//                        TextView tvParent = view.findViewById(R.id.tv_parent);
//                        parentTitle = tvParent.getText().toString();
//                        QMUISection<SectionHeader<User>, SectionItem<User>> section = adapter1.getSection(position);
//                        if (section != null) {
//                            SectionHeader<User> header = section.getHeader();
//                            user = header.getText();
//                        }
//                        break;
//                    case 1:
//                        initListPopupIfNeed(deletes);
//                        TextView tv_1 = view.findViewById(R.id.tv_1);
//                        TextView tv_2 = view.findViewById(R.id.tv_2);
//                        TextView tv_3 = view.findViewById(R.id.tv_3);
//                        childTitle1 = tv_1.getText().toString();
//                        childTitle2 = tv_2.getText().toString();
//                        childTitle3 = tv_3.getText().toString();
//                        SectionItem<User> sectionItem = adapter1.getSectionItem(position);
//                        if (sectionItem != null) {
//                            user = sectionItem.getListBean();
//                        }
//                        break;
//                }
//                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
//                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
//                mListPopup.show(holder.itemView);
//                return true;
//            }
//        });
//    }

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
        recyclerview.setAdapter(adapter1);
        adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        adapter1.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View v, int position) {
                initListPopupIfNeed(addTypes);
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                mListPopup.show(v);
                return false;
            }
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
                            switch (itemViewType) {
                                case 0:
                                    Intent intent = new Intent();
                                    intent.putExtra("parentDept", parentDept);
                                    intent.putExtra("data", user);
                                    intent.setClass(PersonManageActivity.this, EditDeptActivity.class);
                                    startActivityForResult(intent, EDIT_DEPT);
                                    break;
                                case 1:
                                    Intent intent1 = new Intent();
                                    intent1.putExtra("text", parentTitle);
                                    intent1.setClass(PersonManageActivity.this, EditPersonActivity.class);
                                    startActivityForResult(intent1, EDIT_PERSON);
                                    break;
                            }

                            break;
                    }
                    mListPopup.dismiss();
                }
            });
            mListPopup.setOnDismissListener(data::clear);
        }
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
                RetrofitClient.getInstance().getService().delete()
                        .compose(CommonCompose.io2main(PersonManageActivity.this))
                        .subscribe(new BaseSubscriber<BaseResponse>(PersonManageActivity.this) {
                            @Override
                            public void onSuccess(BaseResponse baseResponse) {
                                initData();
                            }
                        });
                break;
            case 1:
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
