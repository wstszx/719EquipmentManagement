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
import com.example.a719equipmentmanagement.base.BaseEditActivity;
import com.example.a719equipmentmanagement.base.BaseFragment;
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

public class MineFragment extends BaseFragment {


    private static MineFragment fragment;
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private QMUICommonListItemView listItemView;
    private String[] containerAttrs = {"借还记录", "盘点记录", "送检记录", "报废记录", "退出登录"};
    private LogoutBroadcast localReceiver;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initGroupListView();
        initBroadcast();
        List<Activity> actList = ActivityCollector.getActList();
        Log.i("mylog","actList.size()=="+actList.size());
    }

    private void initBroadcast() {
        localBroadcastManager = LocalBroadcastManager.getInstance(Objects.requireNonNull(App.getContext()));

        //初始化广播接收者，设置过滤器
        localReceiver = new LogoutBroadcast();
        intentFilter = new IntentFilter();
        intentFilter.addAction("quit_login");
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);

    }

    private void initTopbar() {
        topbar.setTitle("我的");
    }
    private void ShowMessageLogout(){

        new QMUIDialog.MessageDialogBuilder(getActivity())
                .setTitle("提示")
                .setMessage("您确定要退出登录吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener(){
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                } )
                .addAction("确认", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        Intent intent = new Intent("quit_login");
                        localBroadcastManager.sendBroadcast(intent);
                    }
                })
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
              listItemView = (QMUICommonListItemView) v;
              int tag = (int) listItemView.getTag();
              switch (tag){
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
//            Intent intent = new Intent();
//            intent.putExtra("text", listItemView.getDetailText().toString());
//            intent.setClass(this, BaseEditActivity.class);
//            startActivityForResult(intent, tag);


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
