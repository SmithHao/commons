package com.step.exception;
/**
 * http://git.oschina.net/tangbuzhi
 *
 * @author Donfelix
 * @version V1.0
 * @Package
 * @Description: ${TODO}
 * @date: 2017/8/14
 */
public class ApiException extends BaseException {
    public ApiException(int code, String display_message) {
        super(code, display_message);
    }

    public ApiException(int resultCode) {
        super(resultCode);
    }
}
