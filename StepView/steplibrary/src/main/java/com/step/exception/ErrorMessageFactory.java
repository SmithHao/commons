package com.step.exception;

import android.content.Context;

import com.step.steplibrary.R;


/**
 * http://git.oschina.net/tangbuzhi
 *
 * @author Donfelix
 * @version V1.0
 * @Package
 * @Description: ${TODO}
 * @date: 2017/8/14
 */
public class ErrorMessageFactory {

    public static String create(Context context, int code) {
        String errorMsg;
        switch (code) {
            case BaseException.HTTP_ERROR:
                errorMsg = context.getResources().getString(R.string.error_http);
                break;
            case BaseException.SOCKET_TIMEOUT_ERROR:
                errorMsg = context.getResources().getString(R.string.error_socket_timeout);
                break;
            case BaseException.SOCKET_ERROR:
                errorMsg = context.getResources().getString(R.string.error_socket_unreachable);
                break;
            case BaseException.ERROR_HTTP_400:
                errorMsg = context.getResources().getString(R.string.error_http_400);
                break;
            case BaseException.ERROR_HTTP_404:
                errorMsg = context.getResources().getString(R.string.error_http_404);
                break;
            case BaseException.ERROR_HTTP_405:
                errorMsg = context.getResources().getString(R.string.error_http_405);
                break;
            case BaseException.ERROR_OLD_PWD_409:
                errorMsg = context.getResources().getString(R.string.error_old_pwd_409);
                break;
            case BaseException.ERROR_HTTP_500:
                errorMsg = context.getResources().getString(R.string.error_http_500);
                break;
            case BaseException.ERROR_API_SYSTEM:
                errorMsg = context.getResources().getString(R.string.error_system);
                break;
            case BaseException.ERROR_API_LOGIN:
                errorMsg = context.getResources().getString(R.string.error_login);
                break;
            case BaseException.ERROR_TOKEN:
                errorMsg = context.getResources().getString(R.string.error_token);
                break;
            case BaseException.ERROR_ALREADY_REGISTER_407:
                errorMsg = context.getString(R.string.error_http_already_exist_407);
            default:
                errorMsg = context.getResources().getString(R.string.net_error);
                break;
        }
        return errorMsg;
    }
}
