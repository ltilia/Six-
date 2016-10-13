package com.facebook.ads.internal.server;

import com.facebook.ads.internal.util.s;
import com.facebook.internal.AnalyticsEvents;
import com.mopub.common.AdType;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Locale;

public enum AdPlacementType {
    UNKNOWN(UnityAdsConstants.UNITY_ADS_DEVICEID_UNKNOWN),
    BANNER("banner"),
    INTERSTITIAL(AdType.INTERSTITIAL),
    NATIVE(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
    
    private String a;

    private AdPlacementType(String str) {
        this.a = str;
    }

    public static AdPlacementType fromString(String str) {
        if (s.a(str)) {
            return UNKNOWN;
        }
        try {
            return valueOf(str.toUpperCase(Locale.US));
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

    public String toString() {
        return this.a;
    }
}
