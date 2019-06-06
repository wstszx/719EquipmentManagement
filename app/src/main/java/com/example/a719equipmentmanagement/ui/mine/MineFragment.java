package com.example.a719equipmentmanagement.ui.mine;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.LoginActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment {


    private static MineFragment fragment;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private QMUICommonListItemView listItemView;
    private String[] containerAttrs = {"个人信息", "借用历史","申请历史", "处理历史","审核历史", "盘点历史"};

    private int roleId;

    @Override
    protected void init(Bundle savedInstanceState) {
        roleId = SPUtils.getInstance().getInt("roleId", 0);
        initTopbar();
        initGroupListView();
        initTextView();
    }


    private void initTopbar() {
        topbar.setTitle("我的");
    }

    private void initTextView() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new QMUIDialog.MessageDialogBuilder(getActivity())
                        .setTitle("提示")
                        .setMessage("您确定要退出登录吗？")
                        .addAction("取消", (dialog, index) -> dialog.dismiss())
                        .addAction("确认", (dialog, index) -> {
                            dialog.dismiss();
                            SPUtils.getInstance().put("main", false);
                            logout();
                        })
                        .show();
            }
        });
    }

    /**
     * 登出
     */
    private void logout() {
        RetrofitClient.getInstance().getService().loginout()
                .compose(CommonCompose.io2main(getContext()))
                .subscribe(new BaseSubscriber<BaseResponse>(getContext()) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        ActivityCollector.finishAll();
                        LoginActivity.start(getContext());
                    }
                });
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
            listItemView = (QMUICommonListItemView) v;
            String tag = (String) listItemView.getTag();
            switch (tag) {
                case "个人信息":
                    PersonInfoActivity.start(getContext());
                    break;
                case "申请历史":
                    ApplyHistoryActivity.start(getContext());
                    break;
                case "借用历史":
                    BorrowHistoryActivity.start(getContext());
                    break;
                case "盘点历史":
                    InventoryHistoryActivity.start(getContext());
                    break;
                case "处理历史":
                    HandleHistoryActivity.start(getContext());
                    break;
                case "审核历史":
                    ReviewHistoryActivity.start(getContext());
                    break;


            }
        };

        QMUIGroupListView.Section section = QMUIGroupListView.newSection(getContext());

                for (String containerAttr : containerAttrs) {
                    QMUICommonListItemView item = groupListView.createItemView(
                            null,
                            containerAttr,
                            null,
                            QMUICommonListItemView.HORIZONTAL,
                            QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
                    item.setTag(containerAttr);
                    section.addItemView(item, onClickListener);
                }


        section.addTo(groupListView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    public static MineFragment newInstance() {
        if (fragment == null) {
            fragment = new MineFragment();
        }
        return fragment;
    }

    @OnClick(R.id.tv_change_assword)
    public void onViewClicked() {
        ChangePasswordActivity.start(getContext());
    }
}
