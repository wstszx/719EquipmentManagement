package com.example.a719equipmentmanagement.ui.mine;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.MainActivity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseFragment;

import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.LoginActivity;
import com.example.a719equipmentmanagement.ui.home.GenarateQRActivity;
import com.example.a719equipmentmanagement.ui.home.HomeFragment;


import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.util.List;
import java.util.Objects;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class MineFragment extends BaseFragment {


    private static MineFragment fragment;
    private IntentFilter intentFilter;
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private QMUICommonListItemView listItemView;
    private String[] containerAttrs = {"借还记录", "盘点记录", "送检记录", "报废记录", "退出登录"};

    @Override
    protected void init(Bundle savedInstanceState) {
        initData();
        initTopbar();
        initGroupListView();
        List<Activity> actList = ActivityCollector.getActList();
        Log.i("mylog", "actList.size()==" + actList.size());
    }

    private void initData() {
        RetrofitClient.getInstance().getService().getMe().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void initTopbar() {
        topbar.setTitle("我的");
    }

    private void ShowMessageLogout() {

        new QMUIDialog.MessageDialogBuilder(getActivity())
                .setTitle("提示")
                .setMessage("您确定要退出登录吗？")
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("确认", (dialog, index) -> {
                    dialog.dismiss();
                    Intent intent = new Intent("quit_login");
                    ((BaseActivity) Objects.requireNonNull(getActivity())).localBroadcastManager.sendBroadcast(intent);
                })
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
            listItemView = (QMUICommonListItemView) v;
            int tag = (int) listItemView.getTag();
            switch (tag) {
                case 0:
                    ReturnActivity.start(getContext());
                    break;
                case 1:
                    CheckcountActivity.start(getContext());
                    break;
                case 2:
                    CheckonActivity.start(getContext());
                    break;
                case 3:
                    DiscardActivity.start(getContext());
                    break;
                case 4:
                    ShowMessageLogout();
                    break;
            }
        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(getContext());
        for (int i = 0; i < containerAttrs.length; i++) {
            QMUICommonListItemView item = groupListView.createItemView(
                    null,
                    containerAttrs[i],
                    null,
                    QMUICommonListItemView.HORIZONTAL,
                    QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
            item.setTag(i);
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

}
