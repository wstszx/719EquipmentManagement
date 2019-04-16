package com.example.a719equipmentmanagement.base;

import android.os.Bundle;
import android.view.WindowManager;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        // 沉浸式状态栏
//        QMUIStatusBarHelper.translucent(this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init(savedInstanceState);
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
