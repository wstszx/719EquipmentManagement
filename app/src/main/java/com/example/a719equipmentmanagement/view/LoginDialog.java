package com.example.a719equipmentmanagement.view;

import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.SPUtils;
import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.ui.LoginActivity;
import com.example.a719equipmentmanagement.ui.mine.ActivityCollector;
import com.example.a719equipmentmanagement.utils.SPUtil;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

public class LoginDialog {
    private static int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    public static void showDialog(Context context, String msg) {
        new QMUIDialog.MessageDialogBuilder(context)
                .setTitle("标题")
                .setMessage("您的账号已过期，请重新登录")
//                        .addAction("取消", new QMUIDialogAction.ActionListener() {
//                            @Override
//                            public void onClick(QMUIDialog dialog, int index) {
//                                dialog.dismiss();
//                            }
//                        })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        // 跳转到登陆页面
                        SPUtils.getInstance().put("main", false);
                        context.startActivity(new Intent(context, LoginActivity.class));
                        ActivityCollector.finishAll();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }
}
