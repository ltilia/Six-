package com.mopub.mraid;

import android.support.annotation.NonNull;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;

public enum MraidJavascriptCommand {
    CLOSE(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE),
    EXPAND("expand") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return placementType == PlacementType.INLINE;
        }
    },
    USE_CUSTOM_CLOSE("usecustomclose"),
    OPEN(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_OPEN) {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return true;
        }
    },
    RESIZE("resize") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return true;
        }
    },
    SET_ORIENTATION_PROPERTIES("setOrientationProperties"),
    PLAY_VIDEO(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_PLAYVIDEO) {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return placementType == PlacementType.INLINE;
        }
    },
    STORE_PICTURE("storePicture") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return true;
        }
    },
    CREATE_CALENDAR_EVENT("createCalendarEvent") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return true;
        }
    },
    UNSPECIFIED(BuildConfig.FLAVOR);
    
    @NonNull
    private final String mJavascriptString;

    private MraidJavascriptCommand(@NonNull String javascriptString) {
        this.mJavascriptString = javascriptString;
    }

    static MraidJavascriptCommand fromJavascriptString(@NonNull String string) {
        for (MraidJavascriptCommand command : values()) {
            if (command.mJavascriptString.equals(string)) {
                return command;
            }
        }
        return UNSPECIFIED;
    }

    String toJavascriptString() {
        return this.mJavascriptString;
    }

    boolean requiresClick(@NonNull PlacementType placementType) {
        return false;
    }
}
