package com.example.a719equipmentmanagement.base;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.ui.mine.ActivityCollector;
import com.example.a719equipmentmanagement.ui.mine.QuitLogiinBroadcast;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Objects;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    public LocalBroadcastManager localBroadcastManager;
    private QuitLogiinBroadcast localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
//         沉浸式状态栏
//        QMUIStatusBarHelper.translucent(this);
        ActivityCollector.addActivity(this);
        ButterKnife.bind(this);
        init(savedInstanceState);
        initBroadcast();
    }

    private void initBroadcast() {
        localBroadcastManager = LocalBroadcastManager.getInstance(Objects.requireNonNull(App.getContext()));

        //初始化广播接收者，设置过滤器
        localReceiver = new QuitLogiinBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("quit_login");
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
        // 销毁活动时，将其从管理器中移除
        ActivityCollector.removeActivity(this);
    }

    /**
     * 初始化view，
     *
     * @param savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 设置当前页面布局
     *
     * @return
     */
    protected abstract int getLayoutId();

}
