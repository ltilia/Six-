package com.prime31;

import android.util.Log;
import gs.gram.mopub.BuildConfig;

public class IABConstants {
    public static boolean DEBUG = false;
    private static final String TAG = "Prime31-HD";

    static {
        DEBUG = false;
    }

    public static void logDebug(String log) {
        if (DEBUG) {
            Log.i(TAG, log);
        }
    }

    public static void logEntering(String klass, String method) {
        if (DEBUG) {
            Log.i(TAG, new StringBuilder(String.valueOf(klass)).append(".").append(method).append("()").toString());
        }
    }

    public static void logEntering(String klass, String method, Object param) {
        if (DEBUG) {
            Log.i(TAG, new StringBuilder(String.valueOf(klass)).append(".").append(method).append("( ").append(param).append(" )").toString());
        }
    }

    public static void logEntering(String klass, String method, Object[] params) {
        if (DEBUG) {
            String prefix = BuildConfig.FLAVOR;
            StringBuilder b = new StringBuilder();
            for (Object p : params) {
                b.append(prefix);
                b.append(p);
                prefix = ", ";
            }
            Log.i(TAG, new StringBuilder(String.valueOf(klass)).append(".").append(method).append("( ").append(b.toString()).append(" )").toString());
        }
    }
}
