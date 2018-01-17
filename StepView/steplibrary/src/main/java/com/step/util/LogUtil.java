package com.step.util;

import android.content.Context;
import android.util.Log;

/**
 * http://git.oschina.net/tangbuzhi
 *
 * @author Donfelix
 * @version V1.0
 * @Package
 * @Description: ${TODO}
 * @date: 2017/8/14
 */
public class LogUtil {
    private static String  TAG   = "logger";
    private static boolean DEBUG = true;

    private LogUtil() {
    }

    public static void logger(String msg) {
        if (DEBUG) {
            Log.w(TAG, msg);
        }
    }

    public static void setLogState(Context context) {
        DEBUG = PropertyUtil.isLogAvailable(context);
    }
}
