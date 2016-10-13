package com.unity3d.ads.android;

import gs.gram.mopub.BuildConfig;

class UnityAdsDeviceLogEntry {
    private UnityAdsDeviceLogLevel _logLevel;
    private String _originalMessage;
    private StackTraceElement _stackTraceElement;

    public UnityAdsDeviceLogEntry(UnityAdsDeviceLogLevel unityAdsDeviceLogLevel, String str, StackTraceElement stackTraceElement) {
        this._logLevel = null;
        this._originalMessage = null;
        this._stackTraceElement = null;
        this._logLevel = unityAdsDeviceLogLevel;
        this._originalMessage = str;
        this._stackTraceElement = stackTraceElement;
    }

    public UnityAdsDeviceLogLevel getLogLevel() {
        return this._logLevel;
    }

    public String getParsedMessage() {
        String str = this._originalMessage;
        String str2 = "UnknownClass";
        String str3 = "unknownMethod";
        int i = -1;
        if (this._stackTraceElement != null) {
            str2 = this._stackTraceElement.getClassName();
            str3 = this._stackTraceElement.getMethodName();
            i = this._stackTraceElement.getLineNumber();
        }
        if (str != null && str.length() > 0) {
            str = " :: " + str;
        }
        if (str == null) {
            str = BuildConfig.FLAVOR;
        }
        return str2 + "." + str3 + "()" + (" (line:" + i + ")") + str;
    }
}
