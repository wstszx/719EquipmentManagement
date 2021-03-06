package com.example.a719equipmentmanagement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.clj.fastble.BleManager;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

public class App extends Application {
    protected static Context context;
    protected static int mainThreadId;
    protected static Handler handler;
    private AppCompatActivity curActivity;

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
//        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
//            @Override
//            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//            }
//
//            @Override
//            public void onActivityStarted(Activity activity) {
//            }
//
//            @Override
//            public void onActivityResumed(Activity activity) {
//                curActivity = (AppCompatActivity) activity;
//            }
//
//            @Override
//            public void onActivityPaused(Activity activity) {
//                curActivity = null;
//            }
//
//            @Override
//            public void onActivityStopped(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//
//            }
//
//            @Override
//            public void onActivityDestroyed(Activity activity) {
//
//            }
//        });
    }

    public static App getInstance() {
        return new App();
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
