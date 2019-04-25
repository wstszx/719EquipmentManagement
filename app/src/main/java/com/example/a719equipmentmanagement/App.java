package com.example.a719equipmentmanagement;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.clj.fastble.BleManager;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

public class App extends Application {
    protected static Context context;
    protected static int mainThreadId;
    protected static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        BleManager.getInstance().init(this);
        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(1, 5000)
                .setSplitWriteNum(20)
                .setConnectOverTime(10000)
                .setOperateTimeout(5000);
        mainThreadId = android.os.Process.myTid();
        handler = new Handler();
        QMUISwipeBackActivityManager.init(this);
    }

    /**
     * 获取上下文对象
     *
     * @return context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }

}
