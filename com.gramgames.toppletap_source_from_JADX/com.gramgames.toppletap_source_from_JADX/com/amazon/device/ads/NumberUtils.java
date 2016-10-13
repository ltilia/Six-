package com.amazon.device.ads;

import com.google.android.exoplayer.C;

class NumberUtils {
    private NumberUtils() {
    }

    public static int parseInt(String string, int defaultValue) {
        int value = defaultValue;
        try {
            value = Integer.parseInt(string);
        } catch (NumberFormatException e) {
        }
        return value;
    }

    public static final long convertToMillisecondsFromNanoseconds(long nanoseconds) {
        return nanoseconds / C.MICROS_PER_SECOND;
    }

    public static final long convertToMillisecondsFromSeconds(long seconds) {
        return 1000 * seconds;
    }
}
