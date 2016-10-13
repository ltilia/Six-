package com.mopub.common.util;

import com.adjust.sdk.Constants;
import com.google.android.exoplayer.C;
import gs.gram.mopub.BuildConfig;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

public class Utils {
    private static final AtomicLong sNextGeneratedId;

    static {
        sNextGeneratedId = new AtomicLong(1);
    }

    public static String sha1(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance(Constants.SHA1);
            byte[] bytes = string.getBytes(C.UTF8_NAME);
            digest.update(bytes, 0, bytes.length);
            int length = digest.digest().length;
            for (int i = 0; i < length; i++) {
                stringBuilder.append(String.format("%02X", new Object[]{Byte.valueOf(bytes[i])}));
            }
            return stringBuilder.toString().toLowerCase(Locale.US);
        } catch (Exception e) {
            return BuildConfig.FLAVOR;
        }
    }

    public static long generateUniqueId() {
        long result;
        long newValue;
        do {
            result = sNextGeneratedId.get();
            newValue = result + 1;
            if (newValue > 9223372036854775806L) {
                newValue = 1;
            }
        } while (!sNextGeneratedId.compareAndSet(result, newValue));
        return result;
    }

    public static boolean bitMaskContainsFlag(int bitMask, int flag) {
        return (bitMask & flag) != 0;
    }
}
