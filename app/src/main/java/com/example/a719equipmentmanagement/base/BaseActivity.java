package com.example.a719equipmentmanagement.base;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.a719equipmentmanagement.ui.mine.ActivityCollector;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
//         沉浸式状态栏
//        QMUIStatusBarHelper.translucent(this);
        ActivityCollector.addActivity(this);
        ButterKnife.bind(this);
        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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
