package com.example.a719equipmentmanagement.net;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.ui.LoginActivity;
import com.example.a719equipmentmanagement.ui.mine.ActivityCollector;
import com.example.a719equipmentmanagement.utils.SPUtils;
import com.example.a719equipmentmanagement.view.LoginDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

public class NetworkError {

    public static void error(Context context, Throwable throwable) {
        RetrofitException.ResponeThrowable responeThrowable = RetrofitException.retrofitException(throwable);
        // 此处可以通过判断错误代码来实现根据不同的错误代码做出相应的反应
        switch (responeThrowable.code) {
            case RetrofitException.ERROR.UNKNOWN:
            case RetrofitException.ERROR.PARSE_ERROR:
            case RetrofitException.ERROR.NETWORD_ERROR:
                Toast.makeText(context, responeThrowable.message, Toast.LENGTH_SHORT).show();
                break;
            case RetrofitException.ERROR.HTTP_ERROR:
            case RetrofitException.ERROR.SSL_ERROR:
                Toast.makeText(context, responeThrowable.message, Toast.LENGTH_SHORT).show();
                break;
            case -1:
                LoginDialog.showDialog(context, "您的账号已过期，请重新登录");
                break;
            case 1:
                Toast.makeText(context, responeThrowable.message, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, responeThrowable.message, Toast.LENGTH_SHORT).show();
                break;
        }


    }

//    private void showMessagePositiveDialog() {
//        new QMUIDialog.MessageDialogBuilder(context)
//                .setTitle("标题")
//                .setMessage("确定要发送吗？")
//                .addAction("取消", new QMUIDialogAction.ActionListener() {
//                    @Override
//                    public void onClick(QMUIDialog dialog, int index) {
//                        dialog.dismiss();
//                    }
//                })
//                .addAction("确定", new QMUIDialogAction.ActionListener() {
//                    @Override
//                    public void onClick(QMUIDialog dialog, int index) {
//                        dialog.dismiss();
//                        // 跳转到登陆页面
//                        SPUtils.putBoolean(App.getContext(), "main", false);
//                        context.startActivity(new Intent(context, LoginActivity.class));
//                        ActivityCollector.finishAll();
//                        Toast.makeText(context, "发送成功", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .create(mCurrentDialogStyle).show();
//    }
}
