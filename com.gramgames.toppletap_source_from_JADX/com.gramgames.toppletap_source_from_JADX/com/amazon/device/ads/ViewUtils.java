package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class ViewUtils {
    private final AndroidBuildInfo androidBuildInfo;

    public ViewUtils() {
        this(new AndroidBuildInfo());
    }

    ViewUtils(AndroidBuildInfo androidBuildInfo) {
        this.androidBuildInfo = androidBuildInfo;
    }

    @SuppressLint({"NewApi"})
    public boolean removeOnGlobalLayoutListener(ViewTreeObserver vto, OnGlobalLayoutListener onGlobalLayoutListener) {
        if (!vto.isAlive()) {
            return false;
        }
        if (AndroidTargetUtils.isAtLeastAndroidAPI(this.androidBuildInfo, 16)) {
            vto.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        } else {
            vto.removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
        return true;
    }
}
