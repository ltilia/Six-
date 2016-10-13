package com.chartboost.sdk.Model;

import org.json.simple.parser.Yytoken;

public final class CBError {
    private a a;
    private String b;
    private boolean c;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[a.values().length];
            try {
                a[a.MISCELLANEOUS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.UNEXPECTED_RESPONSE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.PUBLIC_KEY_MISSING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.INTERNET_UNAVAILABLE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[a.HTTP_NOT_FOUND.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[a.NETWORK_FAILURE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[a.INVALID_RESPONSE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public enum CBClickError {
        URI_INVALID,
        URI_UNRECOGNIZED,
        AGE_GATE_FAILURE,
        NO_HOST_ACTIVITY,
        INTERNAL
    }

    public enum CBImpressionError {
        INTERNAL,
        INTERNET_UNAVAILABLE,
        TOO_MANY_CONNECTIONS,
        WRONG_ORIENTATION,
        FIRST_SESSION_INTERSTITIALS_DISABLED,
        NETWORK_FAILURE,
        NO_AD_FOUND,
        SESSION_NOT_STARTED,
        IMPRESSION_ALREADY_VISIBLE,
        NO_HOST_ACTIVITY,
        USER_CANCELLATION,
        INVALID_LOCATION,
        VIDEO_UNAVAILABLE,
        VIDEO_ID_MISSING,
        ERROR_PLAYING_VIDEO,
        INVALID_RESPONSE,
        ASSETS_DOWNLOAD_FAILURE,
        ERROR_CREATING_VIEW,
        ERROR_DISPLAYING_VIEW,
        INCOMPATIBLE_API_VERSION,
        ERROR_LOADING_WEB_VIEW,
        ASSET_PREFETCH_IN_PROGRESS,
        EMPTY_LOCAL_AD_LIST,
        ACTIVITY_MISSING_IN_MANIFEST,
        EMPTY_LOCAL_VIDEO_LIST,
        END_POINT_DISABLED
    }

    public enum a {
        MISCELLANEOUS,
        INTERNET_UNAVAILABLE,
        INVALID_RESPONSE,
        UNEXPECTED_RESPONSE,
        NETWORK_FAILURE,
        PUBLIC_KEY_MISSING,
        HTTP_NOT_FOUND
    }

    public CBError(a error, String errorDesc) {
        this.a = error;
        this.b = errorDesc;
        this.c = true;
    }

    public a a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public CBImpressionError c() {
        switch (1.a[this.a.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return CBImpressionError.INTERNAL;
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                return CBImpressionError.INTERNET_UNAVAILABLE;
            case Yytoken.TYPE_COMMA /*5*/:
                return CBImpressionError.NO_AD_FOUND;
            default:
                return CBImpressionError.NETWORK_FAILURE;
        }
    }
}
