package com.example.a719equipmentmanagement.ui.device;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.a719equipmentmanagement.R;
import com.qmuiteam.qmui.widget.QMUITopBar;

public class SearchActivity extends AppCompatActivity {

    private QMUITopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        topBar=findViewById(R.id.topbar1);
        topBar.setTitle("设备搜索");
        topBar.addLeftImageButton(R.mipmap.back,R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
