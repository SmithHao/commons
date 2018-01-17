package com.step.util;

import android.content.Context;
import android.text.TextUtils;

import com.step.Constant;

import java.util.Properties;

/**
 * http://git.oschina.net/tangbuzhi
 *
 * @author Donfelix
 * @version V1.0
 * @Package
 * @Description: ${TODO}
 * @date: 2017/8/14
 */
public class PropertyUtil {

    private PropertyUtil() {
    }

    public static boolean isLogAvailable(Context context) {
        if (context == null) {
            return false;
        } else {
            try {
                Properties pro = new Properties();
                pro.load(context.getAssets().open(Constant.BOERMAN_PROPERTIES));
                String logAvailable = pro.getProperty(Constant.TOGGLE_LOG_VISIBLE);
                if (!TextUtils.isEmpty(logAvailable)) {
                    return Boolean.parseBoolean(logAvailable);
                }
                return false;
            } catch (Exception ignored) {
                return false;
            }
        }
    }
}
