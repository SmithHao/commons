package com.step.rx.observer;

import android.content.Context;

import com.step.exception.BaseException;
import com.step.rx.RxErrorHandler;
import com.step.util.LogUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * http://git.oschina.net/tangbuzhi
 *
 * @author Donfelix
 * @version V1.0
 * @Package
 * @Description: ${TODO}
 * @date: 2017/8/14
 */
public abstract class ErrorHandleObserver<T> extends DefaultObserver<T> {
    private   Context        context;
    protected RxErrorHandler rxErrorHandler;

    public ErrorHandleObserver(Context context) {
        this.context = context;
        this.rxErrorHandler = new RxErrorHandler(context);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onError(@NonNull Throwable e) {
        //e.printStackTrace();
        BaseException baseException = rxErrorHandler.onErrorHandle(e);
        if (baseException == null) {
            LogUtil.logger(e.getMessage());
        } else {
            rxErrorHandler.showErrorMessage(baseException);
        }
    }

    @Override
    public void onComplete() {
    }
}
