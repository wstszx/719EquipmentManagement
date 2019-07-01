package com.example.a719equipmentmanagement.ui.home;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ToAuditAdapter;
import com.example.a719equipmentmanagement.adapter.ToAuditListAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.DeptList;
import com.example.a719equipmentmanagement.entity.ToAudit;
import com.example.a719equipmentmanagement.entity.UserBean;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.device.DeviceDetailActivity;
import com.example.a719equipmentmanagement.ui.device.DeviceDetailActivity2;
import com.example.a719equipmentmanagement.view.AuditDialog;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ToAuditActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private ToAuditListAdapter toAuditListAdapter;
    private int equipId;
    private String dealer;
    private int pageNum = 1;
    private Map<String, Object> map = new HashMap<>();
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    private int userId;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
        map.put("pageNum", 1);
        map.put("pageSize", 10);
        initData(map);
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(ToAuditActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        toAuditListAdapter = new ToAuditListAdapter(R.layout.to_audit_items);
        toAuditListAdapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(toAuditListAdapter);
        toAuditListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ToAudit.RowsBean rowsBean = toAuditListAdapter.getData().get(position);
            equipId = rowsBean.getEquipId();
            dealer = rowsBean.getDealer();
            int type = rowsBean.getType();
            showAuditDialog(type);
        });
        refreshlayout.setOnRefreshListener(refreshLayout1 -> {
            refreshLayout1.finishRefresh();
            LogUtils.i("我在调用下拉");
            pageNum = 1;
            map.put("pageNum", 1);
            map.put("pageSize", 10);
            initData(map);
        });
        refreshlayout.setOnLoadMoreListener(refreshLayout -> {
            refreshLayout.finishLoadMore();
            pageNum++;
            map.put("pageNum", pageNum);
            map.put("pageSize", 10);
            refreshData(map);
        });
    }

    private List<UserBean> userBeanList = new ArrayList<>();


    private void showAuditDialog(int operType) {
        userBeanList.clear();
        AuditDialog auditDialog = new AuditDialog(this);

        auditDialog.setTitle("审核")
                .setPlaceholder1("备注")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("不通过", (dialog, index) -> {
                    dialog.dismiss();
                    auditEquip(operType, 1, auditDialog);
                })
                .addAction("通过", (dialog, index) -> {
                    dialog.dismiss();
                    auditEquip(operType, 2, auditDialog);
                })
                .create(mCurrentDialogStyle).show();
        auditDialog.getRightImageView().setOnClickListener(v -> RetrofitClient.getInstance().getService().getDeptList()
                .compose(CommonCompose.io2main(ToAuditActivity.this))
                .subscribe(new BaseSubscriber<List<DeptList>>(ToAuditActivity.this) {
                    @Override
                    public void onSuccess(List<DeptList> users) {
                        if (users != null && users.size() > 0) {
                            for (DeptList user : users) {
                                List<DeptList.UsersBean> deptLists = user.getUsers();
                                if (deptLists != null && deptLists.size() > 0) {
                                    for (DeptList.UsersBean deptList : deptLists) {
                                        String userName = deptList.getUserName();
                                        int userId = deptList.getUserId();
                                        UserBean userBean = new UserBean();
                                        userBean.setUserId(userId);
                                        userBean.setUserName(userName);
                                        userBeanList.add(userBean);
                                    }
                                }
                            }
                            showSingleChoiceDialog(auditDialog);
                        }
                    }
                }));
    }

    /**
     * 审核设备
     *
     * @param operType
     */
    private void auditEquip(int operType, int operState, AuditDialog auditDialog) {
        HashMap<String, Object> map = new HashMap<>();
        String msg = auditDialog.getEditText1().getText().toString();
        String userName = auditDialog.getEditText().getText().toString();
        if (StringUtils.isEmpty(userName)) {
            ToastUtils.showShort("请选择负责人");
            return;
        }
        map.put("equipId", equipId);
        map.put("operType", operType);
        map.put("msg", msg);
        map.put("operState", operState);
        map.put("dealer", userId);
        RetrofitClient.getInstance().getService().operatingEquip(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(ToAuditActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        int code = baseResponse.getCode();
                        if (code == 0) {
                            initData(map);
                            ToastUtils.showShort("审核成功");
                        }
                    }
                });
    }

    private void showSingleChoiceDialog(AuditDialog auditDialog) {
        String[] userNameArray = new String[]{};
        List<String> userNameList = new ArrayList<>();
        for (UserBean userBean : userBeanList) {
            String userName = userBean.getUserName();
            userNameList.add(userName);
        }
        String[] strings = userNameList.toArray(userNameArray);
        if (strings != null && strings.length > 0) {
            final int checkedIndex = 1;
            new QMUIDialog.CheckableDialogBuilder(this)
                    .setCheckedIndex(checkedIndex)
                    .addItems(strings, (dialog, which) -> {
                        userId = userBeanList.get(which).getUserId();
                        auditDialog.getEditText().setText(strings[which]);
                        dialog.dismiss();
                    })
                    .create(mCurrentDialogStyle).show();
        }
    }

    private void initTopbar() {
        topBar.setTitle("我的待审任务");
        topBar.addLeftBackImageButton().setOnClickListener(v -> finish());
    }

    private void refreshData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().toAudit(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ToAudit>(ToAuditActivity.this) {
                    @Override
                    public void onSuccess(ToAudit toAudit) {
                        if (toAudit != null) {
                            List<ToAudit.RowsBean> rows = toAudit.getRows();
                            if (rows != null) {
                                toAuditListAdapter.addData(rows);
                                if (rows.size() < 10) {
                                    refreshlayout.finishLoadMoreWithNoMoreData();
                                }
                            }
                        }
                    }
                });
    }

    private void initData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().toAudit(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ToAudit>(ToAuditActivity.this) {
                    @Override
                    public void onSuccess(ToAudit toAudit) {
                        if (toAudit != null) {
                            List<ToAudit.RowsBean> rows = toAudit.getRows();
                            if (rows != null && rows.size() > 0) {
                                toAuditListAdapter.setNewData(rows);
                                if (rows.size() != 10) {
                                    refreshlayout.finishLoadMoreWithNoMoreData();
                                }
                            }else {
                                refreshlayout.finishLoadMoreWithNoMoreData();
                                toAuditListAdapter.setEmptyView(R.layout.empty);
                            }
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_to_audit;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ToAuditActivity.class);
        context.startActivity(starter);
    }
}
