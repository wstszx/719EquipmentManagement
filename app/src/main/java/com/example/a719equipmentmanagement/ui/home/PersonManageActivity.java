package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.PeopleManageAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.NetworkError;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;


import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonManageActivity extends BaseActivity {


    private static final int EDIT_DEPT = 1;
    private static final int EDIT_PERSON = 2;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.sticky_section_layout)
    QMUIStickySectionLayout stickySectionLayout;
    private QMUIListPopup mListPopup;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    private String[] personTitles = {"用户名称", "归属部门", "手机号码", "邮箱", "登录账号", "登录密码", "用户性别", "岗位", "角色", "备注"};
    String[] addTypes = new String[]{
            "添加部门",
            "添加人员"
    };
    String[] deletes = new String[]{
            "删除",
            "编辑"
    };

    private ArrayAdapter<String> adapter;
    private ArrayList<QMUISection<SectionHeader, SectionItem<User.ListBean>>> list;
    private PeopleManageAdapter adapter1;
    private int itemViewType = -1;
    private String parentTitle;
    private String childTitle1;
    private String childTitle2;
    private String childTitle3;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        initTopbar();
        initStickySectionLayout();
        initData();
    }

    private void initData() {
//        RetrofitClient.getInstance().getService().getUser().enqueue(new Callback<BaseResponse<User>>() {
//            @Override
//            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
//                if (response.body() != null) {
//                    boolean ok = response.body().isOk(App.getContext());
//                    if (ok) {
//                        User body = response.body().getRes();
//                        list.add(createSection(body));
//                        adapter1.setData(list);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
//                NetworkError.error(App.getContext(), t);
//            }
//        });
        RetrofitClient.getInstance().getService().getUser().enqueue(new Callback<BaseResponse<List<User>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<User>>> call, Response<BaseResponse<List<User>>> response) {
                if (response.body() != null) {
//                    boolean ok = response.body().isOk(App.getContext());
//                    if (ok) {
//                        List<User> body = response.body().getRes();
//                        if (body != null && body.size() > 0) {
//                            for (User user : body) {
//                                list.add(createSection(user));
//                            }
//                            adapter1.setData(list);
//                        }
//                    }
                    List<User> body = response.body().getRes();
                    if (body != null && body.size() > 0) {
                        for (User user : body) {
                            list.add(createSection(user));
                        }
                        adapter1.setData(list);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<User>>> call, Throwable t) {
                NetworkError.error(App.getContext(), t);
            }
        });
    }

    private void initStickySectionLayout() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        stickySectionLayout.setBackgroundColor(getResources().getColor(R.color.qmui_config_color_white));
        stickySectionLayout.setLayoutManager(manager);
        adapter1 = new PeopleManageAdapter();
        stickySectionLayout.setAdapter(adapter1, true);
        list = new ArrayList<>();

        adapter1.setCallback(new QMUIStickySectionAdapter.Callback<SectionHeader, SectionItem<User.ListBean>>() {
            @Override
            public void loadMore(QMUISection<SectionHeader, SectionItem<User.ListBean>> section, boolean loadMoreBefore) {

            }

            @Override
            public void onItemClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
                int itemViewType = holder.getItemViewType();
                switch (itemViewType) {
                    case 0:
                        adapter1.toggleFold(position, false);
                        break;
                    case 1:
                        PersonDetailActivity.start(PersonManageActivity.this);
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
                itemViewType = holder.getItemViewType();
                View view = holder.itemView;
                switch (itemViewType) {
                    case 0:
                        TextView tvParent = view.findViewById(R.id.tv_parent);
                        parentTitle = tvParent.getText().toString();
                        break;
                    case 1:
                        TextView tv_1 = view.findViewById(R.id.tv_1);
                        TextView tv_2 = view.findViewById(R.id.tv_2);
                        TextView tv_3 = view.findViewById(R.id.tv_3);
                        childTitle1 = tv_1.getText().toString();
                        childTitle2 = tv_2.getText().toString();
                        childTitle3 = tv_3.getText().toString();
                        break;
                }
                initListPopupIfNeed(deletes);
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                mListPopup.show(holder.itemView);
                return true;
            }
        });
    }

    private QMUISection<SectionHeader, SectionItem<User.ListBean>> createSection(User user) {
        String deptName = user.getDeptName();
        SectionHeader header = new SectionHeader(deptName);
        ArrayList<SectionItem<User.ListBean>> contents = new ArrayList<>();
        List<User.ListBean> listBeans = user.getList();
        for (User.ListBean listBean : listBeans) {
            contents.add(new SectionItem<>(listBean));
        }
        // if test load more, you can open the code
//        section.setExistAfterDataToLoad(true);
//        section.setExistBeforeDataToLoad(true);
        return new QMUISection<>(header, contents, true);
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
                            AddDeptActivity.start(PersonManageActivity.this);
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
                                    intent.putExtra("text", parentTitle);
                                    intent.setClass(PersonManageActivity.this, BaseItemEditActivity.class);
                                    startActivityForResult(intent, EDIT_DEPT);
                                    break;
                                case 1:
                                    Intent intent1 = new Intent();
                                    intent1.putExtra("text", parentTitle);
                                    intent1.setClass(PersonManageActivity.this, BaseItemEditActivity.class);
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
                RetrofitClient.getInstance().getService().editDept().enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

                    }
                });
                break;
            case EDIT_PERSON:
                break;
        }
    }

    private void delete() {
        switch (itemViewType) {
            case 0:
                RetrofitClient.getInstance().getService().delete().enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

                    }
                });
                break;
            case 1:
                RetrofitClient.getInstance().getService().deleteUser("").enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

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
