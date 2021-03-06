package com.facebook.ads;

import com.facebook.ads.internal.util.s;
import com.google.android.gms.games.GamesStatusCodes;

public class AdError {
    public static final AdError INTERNAL_ERROR;
    public static final int INTERNAL_ERROR_CODE = 2001;
    public static final AdError LOAD_TOO_FREQUENTLY;
    public static final int LOAD_TOO_FREQUENTLY_ERROR_CODE = 1002;
    @Deprecated
    public static final AdError MISSING_PROPERTIES;
    public static final AdError NETWORK_ERROR;
    public static final int NETWORK_ERROR_CODE = 1000;
    public static final AdError NO_FILL;
    public static final int NO_FILL_ERROR_CODE = 1001;
    public static final AdError SERVER_ERROR;
    public static final int SERVER_ERROR_CODE = 2000;
    private final int a;
    private final String b;

    static {
        NETWORK_ERROR = new AdError(NETWORK_ERROR_CODE, "Network Error");
        NO_FILL = new AdError(NO_FILL_ERROR_CODE, "No Fill");
        LOAD_TOO_FREQUENTLY = new AdError(LOAD_TOO_FREQUENTLY_ERROR_CODE, "Ad was re-loaded too frequently");
        SERVER_ERROR = new AdError(SERVER_ERROR_CODE, "Server Error");
        INTERNAL_ERROR = new AdError(INTERNAL_ERROR_CODE, "Internal Error");
        MISSING_PROPERTIES = new AdError(GamesStatusCodes.STATUS_REQUEST_TOO_MANY_RECIPIENTS, "Native ad failed to load due to missing properties");
    }

    public AdError(int i, String str) {
        if (s.a(str)) {
            str = "unknown error";
        }
        this.a = i;
        this.b = str;
    }

    public int getErrorCode() {
        return this.a;
    }

    public String getErrorMessage() {
        return this.b;
    }
}
