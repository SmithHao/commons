package com.step.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.step.Constant;


/**
 * http://git.oschina.net/tangbuzhi
 *
 * @author Donfelix
 * @version V1.0
 * @Package
 * @Description: ${TODO}
 * @date: 2017/8/14
 */
public class ToastUtil {

    private ToastUtil() {
    }

    private static Toast toast = null;

    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, Constant.EMPTY, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.setGravity(Gravity.CENTER, 0, 500);
        toast.show();
    }
}
