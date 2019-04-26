package com.example.a719equipmentmanagement.ui.device;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.a719equipmentmanagement.R;
import com.qmuiteam.qmui.widget.QMUITopBar;

public class SearchActivity extends AppCompatActivity {

    private QMUITopBar topBar;
    private TextView mKeShi,mLeibie,mZhuangTai;
    private FragmentTransaction transaction;
    private SearchFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        fragment = SearchFragment.getInstance();
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.search_fragment, fragment);
        topBar=findViewById(R.id.topbar1);
        topBar.setTitle("设备搜索");
        topBar.addLeftImageButton(R.mipmap.back,R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mKeShi=findViewById(R.id.tv_KeShi);
        mLeibie=findViewById(R.id.tv_LeiBie);
        mZhuangTai=findViewById(R.id.tv_ZhuangTai);

        mKeShi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.show(getSupportFragmentManager(),"search");
            }
        });

    }
}
