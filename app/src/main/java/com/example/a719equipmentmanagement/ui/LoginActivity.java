package com.example.a719equipmentmanagement.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.ui.mine.ActivityCollector;

import java.util.List;

public class LoginActivity extends BaseActivity {


    @Override
    protected void init(Bundle savedInstanceState) {
        List<Activity> actList = ActivityCollector.getActList();
        Log.i("mylog","actList.size()=="+actList.size());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}
