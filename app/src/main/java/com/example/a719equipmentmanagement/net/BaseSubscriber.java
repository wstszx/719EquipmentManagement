package com.example.a719equipmentmanagement.net;

import android.content.Context;

import com.blankj.utilcode.util.NetworkUtils;

import java.net.ConnectException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class BaseSubscriber<T> implements SingleObserver<T> {

    private Context context;

    public BaseSubscriber(Context context) {
        this.context = context;
    }
    @Override
    public void onSubscribe(Disposable d) {
        boolean connected = NetworkUtils.isConnected();
        if (!connected) {
            this.onError(RetrofitException.retrofitException(new ConnectException()));
        }
    }

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(Throwable e) {
        NetworkError.error(context, e);
    }

//    private Context context;
//
//    public BaseSubscriber(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    public void onSubscribe(Disposable d) {
//        boolean connected = NetworkUtils.isConnected();
//        if (!connected) {
//            this.onError(RetrofitException.retrofitException(new ConnectException()));
//        }
//    }
//
//    @Override
//    public void onNext(T t) {
//
//    }
//
//    @Override
//    public void onError(Throwable e) {
//        NetworkError.error(context, e);
//    }
//
//    @Override
//    public void onComplete() {
//
//    }
}
