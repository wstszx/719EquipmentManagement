package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.PersonManageAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.Person;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.utils.SPUtils;
import com.example.a719equipmentmanagement.view.SpaceItemDecoration;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonManageActivity extends BaseActivity {

    private static final int EDIT_USER = 1;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private PersonManageAdapter adapter;
    private int userId;
    private String loginName;
    private Switch aSwitch;
    private boolean isCheck;

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

//        adapter.setListener((aSwitch, isCheck) -> {
//            PersonManageActivity.this.isCheck = isCheck;
//            PersonManageActivity.this.aSwitch = aSwitch;
//            if (isCheck) {
//                showDialog("确定暂停该人员？", 2);
//            } else {
//                showDialog("确定恢复该人员？", 3);
//            }
//        });

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            User.ListBean listBean = (User.ListBean) adapter.getData().get(position);
            userId = listBean.getUserId();
            loginName = listBean.getLoginName();
            switch (view.getId()) {
                case R.id.tv_edit:
                    Intent intent = new Intent();
                    intent.putExtra("listBean", listBean);
                    intent.setClass(PersonManageActivity.this, EditPersonActivity.class);
                    startActivityForResult(intent, EDIT_USER);
                    break;
                case R.id.tv_delete:
                    showDialog("确定删除该人员？", 0);
                    break;
                case R.id.tv_reset:
                    showDialog("确定重置该人员？", 1);
                    break;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        initData();
//        switch (requestCode) {
//            case EDIT_USER:
//                initData();
//                break;
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showDialog(String msg, int tag) {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("提示")
                .setMessage(msg)
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("确认", (dialog, index) -> {
                    switch (tag) {
                        case 0:
                            delete();
                            break;
                        case 1:
                            resetPwd();
                            break;
                        case 2:
                            changeStatus(1);
                            break;
                        case 3:
                            changeStatus(0);
                            break;
                    }
                })
                .show();
    }

    private void changeStatus(int status) {
        RetrofitClient.getInstance().getService().changeStatus(userId, status)
                .compose(CommonCompose.io2main(PersonManageActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(PersonManageActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        if (isCheck) {
                            aSwitch.setChecked(false);
                        } else {
                            aSwitch.setChecked(true);
                        }
                    }
                });
    }

    private void resetPwd() {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("提示")
                .setMessage("您确定要重置？")
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("确认", (dialog, index) -> {
                    dialog.dismiss();
                    resetUser();
                })
                .show();

    }

    /**
     * 重置用户
     */
    private void resetUser() {
        RetrofitClient.getInstance().getService().resetPwd(userId, loginName, "123456")
                .compose(CommonCompose.io2main(PersonManageActivity.this))
                .subscribe(new BaseSubscriber<BaseResponse>(PersonManageActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        initData();
                    }
                });
    }

    private void delete() {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("提示")
                .setMessage("您确定要删除？")
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("确认", (dialog, index) -> {
                    dialog.dismiss();
                    deleteUser();
                })
                .show();

    }

    /**
     * 删除用户
     */
    private void deleteUser() {
        RetrofitClient.getInstance().getService().deleteUser(userId)
                .compose(CommonCompose.io2main(PersonManageActivity.this))
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
                            List<User.ListBean> listBeans = transformData(users);
                            adapter.setNewData(listBeans);
                        }
//                        if (users != null) {
//                            if (users.size() > 0) {
//                                List<User.ListBean> listBeans = transformData(users);
////                                if (page == 1) {
//                                    adapter.setNewData(listBeans);
////                                } else {
////                                    adapter.addData(listBeans);
////                                }
//                            } else {
//                                adapter.loadMoreEnd(true);
//                            }
//                        }
                    }
                });
    }

    private List<User.ListBean> transformData(List<User> users) {
        List<User.ListBean> listBeans = new ArrayList<>();
        for (User user : users) {
            List<User.ListBean> list = user.getList();
            listBeans.addAll(list);
        }
        return listBeans;
    }

    private void initTopbar() {
        topbar.setTitle("人员管理");
        topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> {
            AddPersonActivity.start(PersonManageActivity.this);
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
}
