package com.amazon.device.ads;

import android.util.Log;

/* compiled from: Logger */
class LogcatLogger implements Logger {
    private String logTag;

    LogcatLogger() {
    }

    public LogcatLogger withLogTag(String logTag) {
        this.logTag = logTag;
        return this;
    }

    public void i(String message) {
        Log.i(this.logTag, message);
    }

    public void v(String message) {
        Log.v(this.logTag, message);
    }

    public void d(String message) {
        Log.d(this.logTag, message);
    }

    public void w(String message) {
        Log.w(this.logTag, message);
    }

    public void e(String message) {
        Log.e(this.logTag, message);
    }
}
