package com.step.imageloader;

import android.graphics.Bitmap;

/**
 * http://git.oschina.net/tangbuzhi
 *
 * @author Donfelix
 * @version V1.0
 * @Package
 * @Description: ${TODO}
 * @date: 2017/8/14
 */
public interface BitmapLoadingListener {
    void onSuccess(Bitmap b);

    void onError();
}
