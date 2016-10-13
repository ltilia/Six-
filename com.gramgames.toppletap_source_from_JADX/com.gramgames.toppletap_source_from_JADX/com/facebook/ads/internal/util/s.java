package com.facebook.ads.internal.util;

import com.adjust.sdk.Constants;
import com.google.android.gms.drive.FileUploadPreferences;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import java.security.MessageDigest;

public class s {
    public static boolean a(String str) {
        return str == null || str.length() <= 0;
    }

    public static String b(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(Constants.MD5).digest(str.getBytes("utf-8"));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                stringBuilder.append(Integer.toString((b & RadialCountdown.PROGRESS_ALPHA) + FileUploadPreferences.BATTERY_USAGE_UNRESTRICTED, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
