package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.AdFormat;
import com.mopub.common.AdType;
import com.mopub.common.util.ResponseHeader;
import com.mopub.network.HeaderUtils;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.util.Map;

public class AdTypeTranslator {
    public static final String BANNER_SUFFIX = "_banner";
    public static final String INTERSTITIAL_SUFFIX = "_interstitial";

    public enum CustomEventType {
        GOOGLE_PLAY_SERVICES_BANNER("admob_native_banner", "com.mopub.mobileads.GooglePlayServicesBanner"),
        GOOGLE_PLAY_SERVICES_INTERSTITIAL("admob_full_interstitial", "com.mopub.mobileads.GooglePlayServicesInterstitial"),
        MILLENNIAL_BANNER("millennial_native_banner", "com.mopub.mobileads.MillennialBanner"),
        MILLENNIAL_INTERSTITIAL("millennial_full_interstitial", "com.mopub.mobileads.MillennialInterstitial"),
        MRAID_BANNER("mraid_banner", "com.mopub.mraid.MraidBanner"),
        MRAID_INTERSTITIAL("mraid_interstitial", "com.mopub.mraid.MraidInterstitial"),
        HTML_BANNER("html_banner", "com.mopub.mobileads.HtmlBanner"),
        HTML_INTERSTITIAL("html_interstitial", "com.mopub.mobileads.HtmlInterstitial"),
        VAST_VIDEO_INTERSTITIAL("vast_interstitial", "com.mopub.mobileads.VastVideoInterstitial"),
        MOPUB_NATIVE("mopub_native", "com.mopub.nativeads.MoPubCustomEventNative"),
        MOPUB_VIDEO_NATIVE("mopub_video_native", "com.mopub.nativeads.MoPubCustomEventVideoNative"),
        MOPUB_REWARDED_VIDEO(AdType.REWARDED_VIDEO, "com.mopub.mobileads.MoPubRewardedVideo"),
        UNSPECIFIED(BuildConfig.FLAVOR, null);
        
        private final String mClassName;
        private final String mKey;

        private CustomEventType(String key, String className) {
            this.mKey = key;
            this.mClassName = className;
        }

        private static CustomEventType fromString(String key) {
            for (CustomEventType customEventType : values()) {
                if (customEventType.mKey.equals(key)) {
                    return customEventType;
                }
            }
            return UNSPECIFIED;
        }

        public String toString() {
            return this.mClassName;
        }
    }

    static String getAdNetworkType(String adType, String fullAdType) {
        String adNetworkType;
        if (AdType.INTERSTITIAL.equals(adType)) {
            adNetworkType = fullAdType;
        } else {
            adNetworkType = adType;
        }
        return adNetworkType != null ? adNetworkType : UnityAdsConstants.UNITY_ADS_DEVICEID_UNKNOWN;
    }

    public static String getCustomEventName(@NonNull AdFormat adFormat, @NonNull String adType, @Nullable String fullAdType, @NonNull Map<String, String> headers) {
        if (AdType.CUSTOM.equalsIgnoreCase(adType)) {
            return HeaderUtils.extractHeader((Map) headers, ResponseHeader.CUSTOM_EVENT_NAME);
        }
        if (AdType.STATIC_NATIVE.equalsIgnoreCase(adType)) {
            return CustomEventType.MOPUB_NATIVE.toString();
        }
        if (AdType.VIDEO_NATIVE.equalsIgnoreCase(adType)) {
            return CustomEventType.MOPUB_VIDEO_NATIVE.toString();
        }
        if (AdType.REWARDED_VIDEO.equalsIgnoreCase(adType)) {
            return CustomEventType.MOPUB_REWARDED_VIDEO.toString();
        }
        if (AdType.HTML.equalsIgnoreCase(adType) || AdType.MRAID.equalsIgnoreCase(adType)) {
            CustomEventType access$000;
            if (AdFormat.INTERSTITIAL.equals(adFormat)) {
                access$000 = CustomEventType.fromString(adType + INTERSTITIAL_SUFFIX);
            } else {
                access$000 = CustomEventType.fromString(adType + BANNER_SUFFIX);
            }
            return access$000.toString();
        } else if (AdType.INTERSTITIAL.equalsIgnoreCase(adType)) {
            return CustomEventType.fromString(fullAdType + INTERSTITIAL_SUFFIX).toString();
        } else {
            return CustomEventType.fromString(adType + BANNER_SUFFIX).toString();
        }
    }
}
