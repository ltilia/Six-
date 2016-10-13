package com.mopub.common.util;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.AdError;
import com.google.android.gms.nearby.connection.Connections;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

public class Strings {
    private static Pattern absolutePattern;
    private static Pattern percentagePattern;

    static {
        percentagePattern = Pattern.compile("((\\d{1,2})|(100))%");
        absolutePattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}(.\\d{3})?");
    }

    public static String fromStream(InputStream inputStream) throws IOException {
        int numberBytesRead = 0;
        StringBuilder out = new StringBuilder();
        byte[] bytes = new byte[Connections.MAX_RELIABLE_MESSAGE_LEN];
        while (numberBytesRead != -1) {
            out.append(new String(bytes, 0, numberBytesRead));
            numberBytesRead = inputStream.read(bytes);
        }
        inputStream.close();
        return out.toString();
    }

    public static boolean isPercentageTracker(String progressValue) {
        return !TextUtils.isEmpty(progressValue) && percentagePattern.matcher(progressValue).matches();
    }

    public static boolean isAbsoluteTracker(String progressValue) {
        return !TextUtils.isEmpty(progressValue) && absolutePattern.matcher(progressValue).matches();
    }

    @Nullable
    public static Integer parseAbsoluteOffset(@Nullable String progressValue) {
        if (progressValue == null) {
            return null;
        }
        String[] split = progressValue.split(":");
        if (split.length == 3) {
            return Integer.valueOf(((((Integer.parseInt(split[0]) * 60) * 60) * AdError.NETWORK_ERROR_CODE) + ((Integer.parseInt(split[1]) * 60) * AdError.NETWORK_ERROR_CODE)) + ((int) (Float.parseFloat(split[2]) * 1000.0f)));
        }
        return null;
    }
}
