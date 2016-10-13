package com.facebook.ads.internal.dto;

import com.facebook.ads.internal.e;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import org.json.simple.parser.Yytoken;

public enum b {
    UNKNOWN,
    BANNER,
    INTERSTITIAL,
    NATIVE;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[e.values().length];
            try {
                a[e.NATIVE_UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[e.WEBVIEW_BANNER_50.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[e.WEBVIEW_BANNER_90.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[e.WEBVIEW_BANNER_LEGACY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[e.WEBVIEW_BANNER_250.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[e.WEBVIEW_INTERSTITIAL_HORIZONTAL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[e.WEBVIEW_INTERSTITIAL_VERTICAL.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[e.WEBVIEW_INTERSTITIAL_TABLET.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[e.WEBVIEW_INTERSTITIAL_UNKNOWN.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public static b a(e eVar) {
        switch (1.a[eVar.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return NATIVE;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
            case Yytoken.TYPE_COMMA /*5*/:
                return BANNER;
            case Yytoken.TYPE_COLON /*6*/:
            case R.styleable.Toolbar_contentInsetLeft /*7*/:
            case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
            case R.styleable.Toolbar_popupTheme /*9*/:
                return INTERSTITIAL;
            default:
                return UNKNOWN;
        }
    }
}
