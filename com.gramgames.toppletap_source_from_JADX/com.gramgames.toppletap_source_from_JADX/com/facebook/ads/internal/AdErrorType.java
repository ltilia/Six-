package com.facebook.ads.internal;

import com.amazon.device.ads.AdProperties;
import com.facebook.ads.AdError;
import com.google.android.gms.games.GamesStatusCodes;

public enum AdErrorType {
    UNKNOWN_ERROR(-1, "unknown error", false),
    NETWORK_ERROR(AdError.NETWORK_ERROR_CODE, "Network Error", true),
    NO_FILL(AdError.NO_FILL_ERROR_CODE, "No Fill", true),
    LOAD_TOO_FREQUENTLY(AdError.LOAD_TOO_FREQUENTLY_ERROR_CODE, "Ad was re-loaded too frequently", true),
    INVALID_PARAMETERS(AdProperties.CAN_EXPAND1, "Ad was requested with invalid parameters", true),
    SERVER_ERROR(GamesStatusCodes.STATUS_REQUEST_UPDATE_PARTIAL_SUCCESS, "Server Error", true),
    INTERNAL_ERROR(GamesStatusCodes.STATUS_REQUEST_UPDATE_TOTAL_FAILURE, "Internal Error", true),
    START_BEFORE_INIT(2004, "initAd must be called before startAd", true),
    AD_REQUEST_FAILED(1111, "Facebook Ads SDK request for ads failed", false),
    AD_REQUEST_TIMEOUT(1112, "Facebook Ads SDK request for ads timed out", false),
    PARSER_FAILURE(1201, "Failed to parse Facebook Ads SDK delivery response", false),
    UNKNOWN_RESPONSE(1202, "Unknown Facebook Ads SDK delivery response type", false),
    ERROR_MESSAGE(1203, "Facebook Ads SDK delivery response Error message", true),
    NO_AD_PLACEMENT(1302, "Facebook Ads SDK returned no ad placements", false);
    
    private final int a;
    private final String b;
    private final boolean c;

    private AdErrorType(int i, String str, boolean z) {
        this.a = i;
        this.b = str;
        this.c = z;
    }

    public static AdErrorType adErrorTypeFromCode(int i) {
        for (AdErrorType adErrorType : values()) {
            if (adErrorType.getErrorCode() == i) {
                return adErrorType;
            }
        }
        return UNKNOWN_ERROR;
    }

    boolean a() {
        return this.c;
    }

    public AdError getAdError(String str) {
        return new b(this, str).b();
    }

    public b getAdErrorWrapper(String str) {
        return new b(this, str);
    }

    public String getDefaultErrorMessage() {
        return this.b;
    }

    public int getErrorCode() {
        return this.a;
    }
}
