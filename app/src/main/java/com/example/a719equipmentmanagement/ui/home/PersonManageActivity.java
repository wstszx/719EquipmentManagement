package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.PersonManageAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.view.CustomInputDialog;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PersonManageActivity extends BaseActivity {

    private static final int EDIT_USER = 1;
    private static final int ADD_PERSON = 2;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private PersonManageAdapter adapter;
    private int userId;
    private String loginName;
    private Switch aSwitch;
    private boolean isCheck;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    private CustomInputDialog customDialogBuilder;
    private String userName;
    private String password;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
        initData();
    }

    private void initAdapter() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        //直接传入dp值，比如10dp，就传入10，由SpaceItemDecoration负责像素转换
//        SpaceItemDecoration decoration = new SpaceItemDecoration(10);
//        recyclerview.addItemDecoration(decoration);
        adapter = new PersonManageAdapter(R.layout.person_item);
        adapter.bindToRecyclerView(recyclerview);
        adapter.setEnableLoadMore(true);
        adapter.setUpFetchEnable(true);
        adapter.disableLoadMoreIfNotFullPage();
        recyclerview.setAdapter(adapter);


        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            User.UsersBean listBean = (User.UsersBean) adapter.getData().get(position);
            userId = listBean.getUserId();
            loginName = listBean.getLoginName();
            userName = listBean.getUserName();
            password = listBean.getPassword();
            switch (view.getId()) {
                case R.id.tv_edit:
                    Intent intent = new Intent();
                    intent.putExtra("listBean", listBean);
                    intent.setClass(PersonManageActivity.this, EditPersonActivity.class);
                    startActivityForResult(intent, EDIT_USER);
                    break;
                case R.id.tv_delete:
                    showDeleteDialog();
                    break;
                case R.id.tv_reset:
                    showResetDialog();
                    break;
            }
        });
    }

    /**
     * 显示重置对话框
     */
    private void showResetDialog() {
        customDialogBuilder = new CustomInputDialog(this);
//        customDialogBuilder.getEditText().setText(userName);
//        customDialogBuilder.getEditText1().setText(password);
        customDialogBuilder.setTitle("重置密码")
                .setPlaceholder("用户名")
                .setPlaceholder1("密码")
                .setDefaultText(userName)
                .setDefaultText1(password)
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .setInputType1(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("确定", (dialog, index) -> {
                    dialog.dismiss();
                    resetUser();
                })
                .create(mCurrentDialogStyle).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        initData();
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showDeleteDialog() {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("提示")
                .setMessage("确定删除该人员？")
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("确认", (dialog, index) -> {
                    dialog.dismiss();
                    deleteUser();
                })
                .show();
    }


    /**
     * 重置用户
     */
    private void resetUser() {
        String username = customDialogBuilder.getEditText().getText().toString();
        String password = customDialogBuilder.getEditText1().getText().toString();
        if (StringUtils.isEmpty(username)) {
            ToastUtils.showShort("用户名称不能为空");
            return;
        }else if (StringUtils.isEmpty(password)) {
            ToastUtils.showShort("密码不能为空");
            return;
        }
        RetrofitClient.getInstance().getService().resetPwd(userId, username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(PersonManageActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        if (baseResponse != null) {
                            int code = baseResponse.getCode();
                            if (code == 0) {
                                ToastUtils.showShort("重置成功");
                                initData();
                            }
                        }
                    }
                });
    }


    /**
     * 删除用户
     */
    private void deleteUser() {
        RetrofitClient.getInstance().getService().deleteUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(PersonManageActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        initData();
                    }
                });
    }

    private void initData() {
        RetrofitClient.getInstance().getService().getUser()
                .compose(CommonCompose.io2main(PersonManageActivity.this))
                .subscribe(new BaseSubscriber<List<User>>(PersonManageActivity.this) {
                    @Override
                    public void onSuccess(List<User> users) {
                        if (users != null && users.size() > 0) {
                            List<User.UsersBean> listBeans = transformData(users);
                            adapter.setNewData(listBeans);
                        }
                    }
                });
    }

    private List<User.UsersBean> transformData(List<User> users) {
        List<User.UsersBean> listBeans = new ArrayList<>();
        for (User user : users) {
            List<User.UsersBean> list = user.getUsers();
            listBeans.addAll(list);
        }
        return listBeans;
    }

    private void initTopbar() {
        topbar.setTitle("人员管理");
        topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(PersonManageActivity.this, AddPersonActivity.class);
            startActivityForResult(intent, ADD_PERSON);
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
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
}
