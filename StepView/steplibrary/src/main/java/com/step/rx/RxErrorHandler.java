package com.step.rx;

import android.content.Context;

import com.step.exception.ApiException;
import com.step.exception.BaseException;
import com.step.exception.ErrorMessageFactory;
import com.step.util.ToastUtil;

import org.json.JSONException;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * Created by WangHao
 *
 * @ 创建时间 2017/10/13  11:16
 */
public class RxErrorHandler {
    private Context context;

    public RxErrorHandler(Context context) {
        this.context = context;
    }

    public BaseException onErrorHandle(Throwable t) {
        BaseException exception = new BaseException();
        if (t instanceof ApiException) {
            exception.setCode(((ApiException) t).getCode());
        } else if (t instanceof HttpException) {
            exception.setCode(((HttpException) t).code());
        } else if (t instanceof SocketException) {
            exception.setCode(BaseException.SOCKET_ERROR);
        } else if (t instanceof SocketTimeoutException) {
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        } else if (t instanceof JSONException) {
            exception.setCode(BaseException.JSON_ERROR);
        } else {
            exception.setCode(BaseException.UNKNOWN_ERROR);
        }
        exception.setDisplay_message(ErrorMessageFactory.create(context, exception.getCode()));
        return exception;
    }

    public void showErrorMessage(BaseException e) {
        ToastUtil.showToast(context, e.getDisplay_message());
    }
}
