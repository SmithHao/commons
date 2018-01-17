package com.step.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.step.Constant;
import com.step.steplibrary.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http://git.oschina.net/tangbuzhi
 *
 * @author Donfelix
 * @version V1.0
 * @Package
 * @Description: ${TODO}
 * @date: 2017/8/14
 */
public class StringUtil {

    private StringUtil() {
    }

    public static boolean isTrue(String value) {
        if (value != null && !TextUtils.isEmpty(value) && Constant.STATUS_SUCCESS.equals(value)) {
            return true;
        }
        return false;
    }

    public static String correctValue(String value) {
        if (value != null && !TextUtils.isEmpty(value) && !TextUtils.isEmpty(value.trim())) {
            return value;
        }
        return Constant.EMPTY;
    }

    public static boolean isValidValue(String value) {
        if (value != null && !value.isEmpty() && !value.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isValidMobile(String zone, String mobiles) {
        String pp = "^1[3|4|5|7|8][0-9]{9}$";
        Pattern p;
        Matcher m;
        if (!isValidValue(zone) || !isValidValue(mobiles)) {
            return false;
        }
        p = Pattern.compile(pp);
        m = p.matcher(mobiles);
        return m.matches();
    }

    private static boolean isValidUsername(String username) {
        String pp = "^[a-zA-Z0-9\\u4e00-\\u9fa5-_]{1,15}$";
        Pattern p;
        Matcher m;
        if (!isValidValue(username)) {
            return false;
        }
        p = Pattern.compile(pp);
        m = p.matcher(username);
        return m.matches();
    }

    public static boolean isValidPassword(String psw) {
        if (!isValidValue(psw)) {
            return false;
        }
        return psw.trim().length() >= 6 && psw.trim().length() <= 12;
    }

    public static String imei(Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return manager.getDeviceId();
        } catch (Exception e) {
            return Constant.EMPTY;
        }
    }

    public static String imsi(Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return manager.getSubscriberId();
        } catch (Exception e) {
            return Constant.EMPTY;
        }
    }

    public static String appver(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo pi = manager.getPackageInfo(context.getPackageName(), 0);
            return String.valueOf(pi.versionName);
        } catch (PackageManager.NameNotFoundException ignored) {
            return Constant.EMPTY;
        }
    }

    public static boolean isSdcardMounted() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    public static String dayOfWeek(Context context, int value) {
        if (value > 6 || value < 0) {
            return null;
        }
        switch (value) {
            case 0:
                return context.getResources().getString(R.string.sunday);
            case 1:
                return context.getResources().getString(R.string.monday);
            case 2:
                return context.getResources().getString(R.string.tuesday);
            case 3:
                return context.getResources().getString(R.string.wendnesday);
            case 4:
                return context.getResources().getString(R.string.thursday);
            case 5:
                return context.getResources().getString(R.string.friday);
            case 6:
                return context.getResources().getString(R.string.satueday);
            default:
                return null;
        }
    }

    public static boolean isValidConfirmNum(String number) {
        if (!isValidValue(number)) {
            return false;
        }
        String numRex = "^[0-9]*$";
        Pattern pp = Pattern.compile(numRex);
        Matcher mm = pp.matcher(number);
        return mm.matches();
    }
}
