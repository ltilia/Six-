package com.vungle.log;

import android.text.TextUtils;
import android.util.Log;

/* compiled from: vungle */
public class Logger {
    public static final String AD_TAG = "VungleAd";
    public static final String ASYNC_TAG = "VungleAsync";
    public static final String CONFIG_TAG = "VungleConfig";
    public static final String DATABASE_DUMP_TAG = "VungleDumpDatabase";
    public static final String DATABASE_TAG = "VungleDatabase";
    public static final String DATA_TAG = "VungleData";
    public static final int DEBUG_LOGGING_LEVEL = 3;
    public static final String DEVICE_TAG = "VungleDevice";
    public static final int ERROR_LOGGING_LEVEL = 5;
    public static final String EVENT_TAG = "VungleEvent";
    public static final String FILE_TAG = "VungleFile";
    public static final int INFO_LOGGING_LEVEL = 4;
    public static final String INJECT_TAG = "VungleInject";
    public static final String LOCATION_TAG = "VungleLocation";
    public static final String NETWORK_TAG = "VungleNetwork";
    public static final String PREPARE_TAG = "VunglePrepare";
    public static final String PROTOCOL_TAG = "VungleProtocol";
    public static final String REPORT_TAG = "VungleReport";
    public static final int VERBOSE_LOGGING_LEVEL = 2;
    public static final String VUNGLE_TAG = "Vungle";
    public static final int WARN_LOGGING_LEVEL = 5;

    public static void v(String tag, String message) {
        a(VERBOSE_LOGGING_LEVEL, tag, message, null);
    }

    public static void v(String tag, Throwable throwable) {
        a(VERBOSE_LOGGING_LEVEL, tag, null, throwable);
    }

    public static void v(String tag, String message, Throwable throwable) {
        a(VERBOSE_LOGGING_LEVEL, tag, message, throwable);
    }

    public static void d(String tag, String message) {
        a(DEBUG_LOGGING_LEVEL, tag, message, null);
    }

    public static void d(String tag, Throwable throwable) {
        a(DEBUG_LOGGING_LEVEL, tag, null, throwable);
    }

    public static void d(String tag, String message, Throwable throwable) {
        a(DEBUG_LOGGING_LEVEL, tag, message, throwable);
    }

    public static void i(String tag, String message) {
        a(INFO_LOGGING_LEVEL, tag, message, null);
    }

    public static void i(String tag, Throwable throwable) {
        a(INFO_LOGGING_LEVEL, tag, null, throwable);
    }

    public static void i(String tag, String message, Throwable throwable) {
        a(INFO_LOGGING_LEVEL, tag, message, throwable);
    }

    public static void w(String tag, String message) {
        a(WARN_LOGGING_LEVEL, tag, message, null);
    }

    public static void w(String tag, Throwable throwable) {
        a(WARN_LOGGING_LEVEL, tag, null, throwable);
    }

    public static void w(String tag, String message, Throwable throwable) {
        a(WARN_LOGGING_LEVEL, tag, message, throwable);
    }

    public static void e(String tag, String message) {
        a(6, tag, message, null);
    }

    public static void e(String tag, Throwable throwable) {
        a(6, tag, null, throwable);
    }

    public static void e(String tag, String message, Throwable throwable) {
        a(6, tag, message, throwable);
    }

    private static void a(int i, String str, String str2, Throwable th) {
        Object obj = 1;
        boolean isLoggable = Log.isLoggable("VungleDebug", DEBUG_LOGGING_LEVEL);
        if ((!isLoggable && i >= WARN_LOGGING_LEVEL) || (isLoggable && isLoggable(i))) {
            Object obj2 = !TextUtils.isEmpty(str2) ? 1 : null;
            if (th == null) {
                obj = null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (obj2 != null) {
                stringBuilder.append(str2);
            }
            if (!(obj2 == null || obj == null)) {
                stringBuilder.append("\n");
            }
            if (obj != null) {
                stringBuilder.append(Log.getStackTraceString(th));
            }
            Log.println(i, str, stringBuilder.toString());
        }
    }

    public static boolean isLoggable(int level) {
        return Log.isLoggable(VUNGLE_TAG, level);
    }

    private Logger() {
    }
}
