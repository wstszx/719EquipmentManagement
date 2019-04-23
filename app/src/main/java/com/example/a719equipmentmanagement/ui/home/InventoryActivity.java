package com.example.a719equipmentmanagement.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;

public class InventoryActivity extends BaseActivity {

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inventory;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, InventoryActivity.class);
        context.startActivity(starter);
    }
}
