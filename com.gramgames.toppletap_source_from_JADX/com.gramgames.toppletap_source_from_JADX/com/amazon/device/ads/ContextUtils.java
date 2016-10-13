package com.amazon.device.ads;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

class ContextUtils {
    ContextUtils() {
    }

    public static Activity getContextAsActivity(Context context) {
        while (context != null) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            if (!(context instanceof ContextWrapper)) {
                return null;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
}
