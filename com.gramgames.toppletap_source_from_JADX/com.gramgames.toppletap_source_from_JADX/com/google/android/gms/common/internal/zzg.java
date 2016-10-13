package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.R;
import com.google.android.gms.internal.zzmu;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import org.json.simple.parser.Yytoken;

public final class zzg {
    public static String zzc(Context context, int i, String str) {
        Resources resources = context.getResources();
        switch (i) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                if (zzmu.zzb(resources)) {
                    return resources.getString(R.string.common_google_play_services_install_text_tablet, new Object[]{str});
                }
                return resources.getString(R.string.common_google_play_services_install_text_phone, new Object[]{str});
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return resources.getString(R.string.common_google_play_services_update_text, new Object[]{str});
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return resources.getString(R.string.common_google_play_services_enable_text, new Object[]{str});
            case Yytoken.TYPE_COMMA /*5*/:
                return resources.getString(R.string.common_google_play_services_invalid_account_text);
            case com.unity3d.ads.android.R.styleable.Toolbar_contentInsetLeft /*7*/:
                return resources.getString(R.string.common_google_play_services_network_error_text);
            case com.unity3d.ads.android.R.styleable.Toolbar_popupTheme /*9*/:
                return resources.getString(R.string.common_google_play_services_unsupported_text, new Object[]{str});
            case com.unity3d.ads.android.R.styleable.Toolbar_titleMarginBottom /*16*/:
                return resources.getString(R.string.common_google_play_services_api_unavailable_text, new Object[]{str});
            case com.unity3d.ads.android.R.styleable.Toolbar_maxButtonHeight /*17*/:
                return resources.getString(R.string.common_google_play_services_sign_in_failed_text);
            case com.unity3d.ads.android.R.styleable.Toolbar_collapseIcon /*18*/:
                return resources.getString(R.string.common_google_play_services_updating_text, new Object[]{str});
            case UnityAdsProperties.MAX_BUFFERING_WAIT_SECONDS /*20*/:
                return resources.getString(R.string.common_google_play_services_restricted_profile_text);
            case com.unity3d.ads.android.R.styleable.Theme_dialogTheme /*42*/:
                return resources.getString(R.string.common_google_play_services_wear_update_text);
            default:
                return resources.getString(R.string.common_google_play_services_unknown_issue, new Object[]{str});
        }
    }

    @Nullable
    public static final String zzg(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return resources.getString(R.string.common_google_play_services_install_title);
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
            case com.unity3d.ads.android.R.styleable.Theme_dialogTheme /*42*/:
                return resources.getString(R.string.common_google_play_services_update_title);
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return resources.getString(R.string.common_google_play_services_enable_title);
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
            case Yytoken.TYPE_COLON /*6*/:
                return null;
            case Yytoken.TYPE_COMMA /*5*/:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return resources.getString(R.string.common_google_play_services_invalid_account_title);
            case com.unity3d.ads.android.R.styleable.Toolbar_contentInsetLeft /*7*/:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return resources.getString(R.string.common_google_play_services_network_error_title);
            case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case com.unity3d.ads.android.R.styleable.Toolbar_popupTheme /*9*/:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return resources.getString(R.string.common_google_play_services_unsupported_title);
            case com.unity3d.ads.android.R.styleable.Toolbar_titleTextAppearance /*10*/:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case com.unity3d.ads.android.R.styleable.Toolbar_subtitleTextAppearance /*11*/:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case com.unity3d.ads.android.R.styleable.Toolbar_titleMarginBottom /*16*/:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case com.unity3d.ads.android.R.styleable.Toolbar_maxButtonHeight /*17*/:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return resources.getString(R.string.common_google_play_services_sign_in_failed_title);
            case com.unity3d.ads.android.R.styleable.Toolbar_collapseIcon /*18*/:
                return resources.getString(R.string.common_google_play_services_updating_title);
            case UnityAdsProperties.MAX_BUFFERING_WAIT_SECONDS /*20*/:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return resources.getString(R.string.common_google_play_services_restricted_profile_title);
            default:
                Log.e("GoogleApiAvailability", "Unexpected error code " + i);
                return null;
        }
    }

    public static String zzh(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return resources.getString(R.string.common_google_play_services_install_button);
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return resources.getString(R.string.common_google_play_services_update_button);
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return resources.getString(R.string.common_google_play_services_enable_button);
            default:
                return resources.getString(17039370);
        }
    }
}
