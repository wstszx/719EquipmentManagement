package com.example.a719equipmentmanagement.base;

import android.os.Bundle;
import android.view.WindowManager;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<VDB extends ViewDataBinding> extends AppCompatActivity {

    protected VDB mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        // 沉浸式状态栏
//        QMUIStatusBarHelper.translucent(this);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
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
