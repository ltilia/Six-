package com.gramgames.utilitylibrary;

import android.content.Context;
import android.provider.Settings.Secure;

public class Utilities {
    public static String GetAndroidID(Context context) {
        try {
            String androidDeviceId = Secure.getString(context.getContentResolver(), "android_id");
            return (androidDeviceId == null || androidDeviceId.isEmpty()) ? null : androidDeviceId;
        } catch (Exception e) {
            return null;
        }
    }
}
