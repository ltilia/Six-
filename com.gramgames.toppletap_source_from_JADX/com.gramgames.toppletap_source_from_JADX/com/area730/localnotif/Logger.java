package com.area730.localnotif;

import android.util.Log;

public class Logger {
    private static final String DEBUG_TAG = "Area730Log";
    private static final String ERROR_TAG = "ERROR";
    private static final String INFO_TAG = "INFO";
    private static final String WARNING_TAG = "WARNING";

    public static void Log(String text) {
        Log.d(DEBUG_TAG, text);
    }

    public static void LogError(String text) {
        Log("ERROR: " + text);
    }

    public static void LogWarning(String text) {
        Log("WARNING: " + text);
    }

    public static void LogInfo(String text) {
        Log("INFO: " + text);
    }
}
