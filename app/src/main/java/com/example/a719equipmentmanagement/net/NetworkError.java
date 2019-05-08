package com.example.a719equipmentmanagement.net;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.a719equipmentmanagement.ui.LoginActivity;
import com.example.a719equipmentmanagement.ui.mine.ActivityCollector;

public class NetworkError {
    public static void error(Context context, Throwable throwable) {
        RetrofitException.ResponeThrowable responeThrowable = RetrofitException.retrofitException(throwable);
        // 此处可以通过判断错误代码来实现根据不同的错误代码做出相应的反应
        switch (responeThrowable.code) {
            case RetrofitException.ERROR.UNKNOWN:
            case RetrofitException.ERROR.PARSE_ERROR:
            case RetrofitException.ERROR.NETWORD_ERROR:
            case RetrofitException.ERROR.HTTP_ERROR:
            case RetrofitException.ERROR.SSL_ERROR:
                Toast.makeText(context, responeThrowable.message, Toast.LENGTH_SHORT).show();
                break;
            case -1:
                // 跳转到登陆页面
                context.startActivity(new Intent(context, LoginActivity.class));
                ActivityCollector.finishAll();
                break;
            default:
                Toast.makeText(context, responeThrowable.message, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
