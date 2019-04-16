package com.example.a719equipmentmanagement;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.ui.device.DeviceFragment;
import com.example.a719equipmentmanagement.ui.home.HomeFragment;
import com.example.a719equipmentmanagement.ui.mine.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private int currentIndex;
    private String bottomIndex = "Bottom_Index";


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchContent(homeFragment, "homeFragment");
                    return true;
                case R.id.navigation_device:
                    switchContent(deviceFragment, "deviceFragment");
                    return true;
                case R.id.navigation_mine:
                    switchContent(mineFragment, "mineFragment");
                    return true;
            }
            return false;
        }
    };
    private Fragment homeFragment;
    private Fragment mineFragment;
    private Fragment deviceFragment;
    private Fragment currentFragment;

    private void initFragment(Bundle savedInstanceState) {
//        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState != null) {
            homeFragment = getSupportFragmentManager().getFragment(savedInstanceState, HomeFragment.class.getSimpleName());
            deviceFragment = getSupportFragmentManager().getFragment(savedInstanceState, DeviceFragment.class.getSimpleName());
            mineFragment = getSupportFragmentManager().getFragment(savedInstanceState, MineFragment.class.getSimpleName());
            currentIndex = savedInstanceState.getInt(bottomIndex);
        } else {
            homeFragment = HomeFragment.newInstance();
            deviceFragment = DeviceFragment.newInstance();
            mineFragment = MineFragment.newInstance();
        }
        currentFragment = homeFragment;
        //默认显示第一个页面
        try {
            getSupportFragmentManager().beginTransaction().add(R.id.framelayout, homeFragment, "msgFragment").commit();
        } catch (Exception e) {

        }

    }

    private void switchContent(Fragment fragment, String tag) {
        if (!fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().hide(currentFragment).add(R.id.framelayout, fragment, tag).commit();
        } else {
            getSupportFragmentManager().beginTransaction().hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (homeFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, HomeFragment.class.getSimpleName(), homeFragment);
        }
        if (deviceFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, DeviceFragment.class.getSimpleName(), deviceFragment);
        }
        if (mineFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, MineFragment.class.getSimpleName(), mineFragment);
        }
        outState.putInt(bottomIndex, currentIndex);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initFragment(savedInstanceState);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}
