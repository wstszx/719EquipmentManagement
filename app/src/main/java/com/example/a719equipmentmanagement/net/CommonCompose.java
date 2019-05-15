package com.example.a719equipmentmanagement.net;

import android.content.Context;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CommonCompose {

    private static QMUITipDialog tipDialog;

    public static <T> SingleTransformer<T, T> io2main(Context context) {
        tipDialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        return upstream -> upstream.subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    if (!tipDialog.isShowing()) {
                        tipDialog.show();
                    }
                })
                .doFinally((Action) () -> {
                    if (tipDialog.isShowing()) {
                        tipDialog.dismiss();
                    }
                });

    }
}
