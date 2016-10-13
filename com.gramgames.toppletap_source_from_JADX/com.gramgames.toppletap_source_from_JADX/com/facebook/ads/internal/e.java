package com.facebook.ads.internal;

import com.unity3d.ads.android.R;

public enum e {
    UNKNOWN(0),
    WEBVIEW_BANNER_LEGACY(4),
    WEBVIEW_BANNER_50(5),
    WEBVIEW_BANNER_90(6),
    WEBVIEW_BANNER_250(7),
    WEBVIEW_INTERSTITIAL_UNKNOWN(100),
    WEBVIEW_INTERSTITIAL_HORIZONTAL(R.styleable.Theme_buttonStyleSmall),
    WEBVIEW_INTERSTITIAL_VERTICAL(R.styleable.Theme_checkboxStyle),
    WEBVIEW_INTERSTITIAL_TABLET(R.styleable.Theme_checkedTextViewStyle),
    NATIVE_UNKNOWN(200),
    NATIVE_250(201);
    
    private final int l;

    private e(int i) {
        this.l = i;
    }

    public int a() {
        return this.l;
    }
}
