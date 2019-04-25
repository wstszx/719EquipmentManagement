package com.example.a719equipmentmanagement.ui.mine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.a719equipmentmanagement.MainActivity;
import com.example.a719equipmentmanagement.ui.LoginActivity;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class LogoutBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("quit_login".equals(intent.getAction())) {
            ActivityCollector.finishAll();
            intent.setClass(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
