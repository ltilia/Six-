package com.unity3d.ads.android.properties;

import android.app.Activity;
import android.content.Context;
import com.facebook.internal.ServerProtocol;
import com.google.android.exoplayer.C;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import com.unity3d.ads.android.data.UnityAdsDevice;
import com.unity3d.ads.android.view.UnityAdsFullscreenActivity;
import gs.gram.mopub.BuildConfig;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;

public class UnityAdsProperties {
    public static String ANALYTICS_BASE_URL = null;
    public static String APPFILTER_LIST = null;
    public static Context APPLICATION_CONTEXT = null;
    public static WeakReference BASE_ACTIVITY = null;
    public static long CACHING_SPEED = 0;
    public static String CAMPAIGN_DATA_URL = null;
    public static int CAMPAIGN_REFRESH_SECONDS = 0;
    public static int CAMPAIGN_REFRESH_VIEWS_COUNT = 0;
    public static int CAMPAIGN_REFRESH_VIEWS_MAX = 0;
    public static WeakReference CURRENT_ACTIVITY = null;
    public static String INSTALLED_APPS_URL = null;
    public static final int MAX_BUFFERING_WAIT_SECONDS = 20;
    public static final int MAX_NUMBER_OF_ANALYTICS_RETRIES = 5;
    public static Boolean RUN_WEBVIEW_TESTS;
    public static UnityAdsCampaign SELECTED_CAMPAIGN;
    public static Boolean SELECTED_CAMPAIGN_CACHED;
    private static Boolean SEND_INTERNAL_DETAILS;
    public static Boolean TESTMODE_ENABLED;
    public static String TEST_DATA;
    public static String TEST_DEVELOPER_ID;
    private static Map TEST_EXTRA_PARAMS;
    public static String TEST_JAVASCRIPT;
    public static String TEST_OPTIONS_ID;
    public static String UNITY_ADS_BASE_URL;
    public static String UNITY_ADS_GAMER_ID;
    public static String UNITY_ADS_GAME_ID;
    public static Boolean UNITY_ADS_READY_SENT;
    public static Boolean UNITY_DEVELOPER_INTERNAL_TEST;
    public static String UNITY_VERSION;
    public static String WEBVIEW_BASE_URL;
    private static String _campaignQueryString;

    static {
        CAMPAIGN_DATA_URL = "https://impact.applifier.com/mobile/campaigns";
        WEBVIEW_BASE_URL = null;
        ANALYTICS_BASE_URL = null;
        UNITY_ADS_BASE_URL = null;
        UNITY_ADS_GAME_ID = null;
        UNITY_ADS_GAMER_ID = null;
        APPFILTER_LIST = null;
        INSTALLED_APPS_URL = null;
        TESTMODE_ENABLED = Boolean.valueOf(false);
        BASE_ACTIVITY = null;
        APPLICATION_CONTEXT = null;
        CURRENT_ACTIVITY = null;
        SELECTED_CAMPAIGN = null;
        SELECTED_CAMPAIGN_CACHED = Boolean.valueOf(false);
        CAMPAIGN_REFRESH_VIEWS_COUNT = 0;
        CAMPAIGN_REFRESH_VIEWS_MAX = 0;
        CAMPAIGN_REFRESH_SECONDS = 0;
        CACHING_SPEED = 0;
        UNITY_VERSION = null;
        TEST_DATA = null;
        TEST_JAVASCRIPT = null;
        RUN_WEBVIEW_TESTS = Boolean.valueOf(false);
        UNITY_DEVELOPER_INTERNAL_TEST = Boolean.valueOf(false);
        TEST_DEVELOPER_ID = null;
        TEST_OPTIONS_ID = null;
        TEST_EXTRA_PARAMS = null;
        UNITY_ADS_READY_SENT = Boolean.valueOf(false);
        SEND_INTERNAL_DETAILS = Boolean.valueOf(false);
        _campaignQueryString = null;
    }

    private static void createCampaignQueryString() {
        String advertisingTrackingId;
        Locale locale;
        String toLowerCase;
        Exception e;
        Object[] objArr;
        Locale locale2;
        String str;
        Object[] objArr2;
        String str2 = "?";
        try {
            str2 = String.format(Locale.US, "%s%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_PLATFORM_KEY, "android"});
            advertisingTrackingId = UnityAdsDevice.getAdvertisingTrackingId();
            if (advertisingTrackingId != null) {
                locale = Locale.US;
                String str3 = "%s&%s=%d";
                Object[] objArr3 = new Object[3];
                objArr3[0] = str2;
                objArr3[1] = UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_TRACKINGENABLED_KEY;
                objArr3[2] = Integer.valueOf(UnityAdsDevice.isLimitAdTrackingEnabled() ? 0 : 1);
                str2 = String.format(locale, str3, objArr3);
                toLowerCase = UnityAdsUtils.Md5(advertisingTrackingId).toLowerCase(Locale.US);
                str2 = String.format(Locale.US, "%s&%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_ADVERTISINGTRACKINGID_KEY, URLEncoder.encode(toLowerCase, C.UTF8_NAME)});
                str2 = String.format(Locale.US, "%s&%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_RAWADVERTISINGTRACKINGID_KEY, URLEncoder.encode(advertisingTrackingId, C.UTF8_NAME)});
            } else if (!UnityAdsDevice.getAndroidId(false).equals(UnityAdsConstants.UNITY_ADS_DEVICEID_UNKNOWN)) {
                str2 = String.format(Locale.US, "%s&%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_ANDROIDID_KEY, URLEncoder.encode(UnityAdsDevice.getAndroidId(true), C.UTF8_NAME)});
                str2 = String.format(Locale.US, "%s&%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_RAWANDROIDID_KEY, URLEncoder.encode(UnityAdsDevice.getAndroidId(false), C.UTF8_NAME)});
            }
            str2 = String.format(Locale.US, "%s&%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_GAMEID_KEY, URLEncoder.encode(UNITY_ADS_GAME_ID, C.UTF8_NAME)});
            str2 = String.format(Locale.US, "%s&%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SDKVERSION_KEY, URLEncoder.encode(UnityAdsConstants.UNITY_ADS_VERSION, C.UTF8_NAME)});
            str2 = String.format(Locale.US, "%s&%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SOFTWAREVERSION_KEY, URLEncoder.encode(UnityAdsDevice.getSoftwareVersion(), C.UTF8_NAME)});
            str2 = String.format(Locale.US, "%s&%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_HARDWAREVERSION_KEY, URLEncoder.encode(UnityAdsDevice.getHardwareVersion(), C.UTF8_NAME)});
            str2 = String.format(Locale.US, "%s&%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_DEVICETYPE_KEY, Integer.valueOf(UnityAdsDevice.getDeviceType())});
            str2 = String.format(Locale.US, "%s&%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_CONNECTIONTYPE_KEY, URLEncoder.encode(UnityAdsDevice.getConnectionType(), C.UTF8_NAME)});
            if (UNITY_VERSION != null && UNITY_VERSION.length() > 0) {
                str2 = String.format(Locale.US, "%s&%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_UNITYVERSION_KEY, URLEncoder.encode(UNITY_VERSION, C.UTF8_NAME)});
            }
            if (!UnityAdsDevice.isUsingWifi()) {
                str2 = String.format(Locale.US, "%s&%s=%d", new Object[]{str2, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_ANDROIDNETWORKTYPE_KEY, Integer.valueOf(UnityAdsDevice.getNetworkType())});
            }
            if (CACHING_SPEED > 0) {
                str2 = String.format(Locale.US, "%s&%s=%d", new Object[]{str2, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_CACHINGSPEED_KEY, Long.valueOf(CACHING_SPEED)});
            }
            str2 = String.format(Locale.US, "%s&%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SCREENSIZE_KEY, Integer.valueOf(UnityAdsDevice.getScreenSize())});
            toLowerCase = String.format(Locale.US, "%s&%s=%s", new Object[]{str2, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SCREENDENSITY_KEY, Integer.valueOf(UnityAdsDevice.getScreenDensity())});
            try {
                if (APPFILTER_LIST != null) {
                    toLowerCase = String.format(Locale.US, "%s&%s=%s", new Object[]{toLowerCase, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_APPFILTER_KEY, APPFILTER_LIST});
                    APPFILTER_LIST = null;
                }
            } catch (Exception e2) {
                e = e2;
                UnityAdsDeviceLog.error("Problems creating campaigns query: " + e.getMessage());
                if (TESTMODE_ENABLED.booleanValue()) {
                    locale = Locale.US;
                    advertisingTrackingId = "%s&%s=%s";
                    objArr = new Object[3];
                    objArr[0] = toLowerCase;
                    objArr[1] = UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_ENCRYPTED_KEY;
                    if (UnityAdsUtils.isDebuggable()) {
                        toLowerCase = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
                        locale2 = locale;
                        str = advertisingTrackingId;
                        objArr2 = objArr;
                    } else {
                        toLowerCase = "false";
                        locale2 = locale;
                        str = advertisingTrackingId;
                        objArr2 = objArr;
                    }
                } else {
                    toLowerCase = String.format(Locale.US, "%s&%s=%s", new Object[]{toLowerCase, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_TEST_KEY, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE});
                    toLowerCase = String.format(Locale.US, "%s&%s=%s", new Object[]{toLowerCase, "optionsId", TEST_OPTIONS_ID});
                    locale = Locale.US;
                    objArr = new Object[3];
                    objArr[0] = toLowerCase;
                    objArr[1] = "developerId";
                    toLowerCase = TEST_DEVELOPER_ID;
                    locale2 = locale;
                    str = "%s&%s=%s";
                    objArr2 = objArr;
                }
                objArr[2] = toLowerCase;
                toLowerCase = String.format(locale2, str, objArr2);
                if (SEND_INTERNAL_DETAILS.booleanValue()) {
                    toLowerCase = String.format(Locale.US, "%s&%s=%s", new Object[]{toLowerCase, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_SENDINTERNALDETAILS_KEY, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE});
                    SEND_INTERNAL_DETAILS = Boolean.valueOf(false);
                }
                _campaignQueryString = toLowerCase;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            toLowerCase = str2;
            e = exception;
            UnityAdsDeviceLog.error("Problems creating campaigns query: " + e.getMessage());
            if (TESTMODE_ENABLED.booleanValue()) {
                toLowerCase = String.format(Locale.US, "%s&%s=%s", new Object[]{toLowerCase, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_TEST_KEY, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE});
                toLowerCase = String.format(Locale.US, "%s&%s=%s", new Object[]{toLowerCase, "optionsId", TEST_OPTIONS_ID});
                locale = Locale.US;
                objArr = new Object[3];
                objArr[0] = toLowerCase;
                objArr[1] = "developerId";
                toLowerCase = TEST_DEVELOPER_ID;
                locale2 = locale;
                str = "%s&%s=%s";
                objArr2 = objArr;
            } else {
                locale = Locale.US;
                advertisingTrackingId = "%s&%s=%s";
                objArr = new Object[3];
                objArr[0] = toLowerCase;
                objArr[1] = UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_ENCRYPTED_KEY;
                if (UnityAdsUtils.isDebuggable()) {
                    toLowerCase = "false";
                    locale2 = locale;
                    str = advertisingTrackingId;
                    objArr2 = objArr;
                } else {
                    toLowerCase = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
                    locale2 = locale;
                    str = advertisingTrackingId;
                    objArr2 = objArr;
                }
            }
            objArr[2] = toLowerCase;
            toLowerCase = String.format(locale2, str, objArr2);
            if (SEND_INTERNAL_DETAILS.booleanValue()) {
                toLowerCase = String.format(Locale.US, "%s&%s=%s", new Object[]{toLowerCase, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_SENDINTERNALDETAILS_KEY, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE});
                SEND_INTERNAL_DETAILS = Boolean.valueOf(false);
            }
            _campaignQueryString = toLowerCase;
        }
        if (TESTMODE_ENABLED.booleanValue()) {
            toLowerCase = String.format(Locale.US, "%s&%s=%s", new Object[]{toLowerCase, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_TEST_KEY, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE});
            if (TEST_OPTIONS_ID != null && TEST_OPTIONS_ID.length() > 0) {
                toLowerCase = String.format(Locale.US, "%s&%s=%s", new Object[]{toLowerCase, "optionsId", TEST_OPTIONS_ID});
            }
            if (TEST_DEVELOPER_ID != null && TEST_DEVELOPER_ID.length() > 0) {
                locale = Locale.US;
                objArr = new Object[3];
                objArr[0] = toLowerCase;
                objArr[1] = "developerId";
                toLowerCase = TEST_DEVELOPER_ID;
                locale2 = locale;
                str = "%s&%s=%s";
                objArr2 = objArr;
            }
            if (SEND_INTERNAL_DETAILS.booleanValue()) {
                toLowerCase = String.format(Locale.US, "%s&%s=%s", new Object[]{toLowerCase, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_SENDINTERNALDETAILS_KEY, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE});
                SEND_INTERNAL_DETAILS = Boolean.valueOf(false);
            }
            _campaignQueryString = toLowerCase;
        }
        locale = Locale.US;
        advertisingTrackingId = "%s&%s=%s";
        objArr = new Object[3];
        objArr[0] = toLowerCase;
        objArr[1] = UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_ENCRYPTED_KEY;
        if (UnityAdsUtils.isDebuggable()) {
            toLowerCase = "false";
            locale2 = locale;
            str = advertisingTrackingId;
            objArr2 = objArr;
        } else {
            toLowerCase = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
            locale2 = locale;
            str = advertisingTrackingId;
            objArr2 = objArr;
        }
        objArr[2] = toLowerCase;
        toLowerCase = String.format(locale2, str, objArr2);
        if (SEND_INTERNAL_DETAILS.booleanValue()) {
            toLowerCase = String.format(Locale.US, "%s&%s=%s", new Object[]{toLowerCase, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_SENDINTERNALDETAILS_KEY, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE});
            SEND_INTERNAL_DETAILS = Boolean.valueOf(false);
        }
        _campaignQueryString = toLowerCase;
    }

    public static String getCampaignQueryUrl() {
        createCampaignQueryString();
        String str = CAMPAIGN_DATA_URL;
        return String.format(Locale.US, "%s%s", new Object[]{str, _campaignQueryString});
    }

    public static String getCampaignQueryArguments() {
        if (_campaignQueryString == null || _campaignQueryString.length() <= 2) {
            return BuildConfig.FLAVOR;
        }
        return _campaignQueryString.substring(1);
    }

    public static Activity getBaseActivity() {
        if (BASE_ACTIVITY == null || BASE_ACTIVITY.get() == null || ((Activity) BASE_ACTIVITY.get()).isFinishing() || isActivityDestroyed((Activity) BASE_ACTIVITY.get())) {
            return null;
        }
        return (Activity) BASE_ACTIVITY.get();
    }

    public static Activity getCurrentActivity() {
        if (CURRENT_ACTIVITY == null || CURRENT_ACTIVITY.get() == null || ((Activity) CURRENT_ACTIVITY.get()).isFinishing() || isActivityDestroyed((Activity) CURRENT_ACTIVITY.get())) {
            return getBaseActivity();
        }
        return (Activity) CURRENT_ACTIVITY.get();
    }

    private static boolean isActivityDestroyed(Activity activity) {
        Method method = null;
        try {
            method = Activity.class.getMethod("isDestroyed", new Class[0]);
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Couldn't get isDestroyed -method");
        }
        if (!(method == null || activity == null)) {
            try {
                return ((Boolean) method.invoke(activity, new Object[0])).booleanValue();
            } catch (Exception e2) {
                UnityAdsDeviceLog.error("Error running isDestroyed -method");
            }
        }
        return false;
    }

    public static boolean isAdsReadySent() {
        return UNITY_ADS_READY_SENT.booleanValue();
    }

    public static void setAdsReadySent(boolean z) {
        UNITY_ADS_READY_SENT = Boolean.valueOf(z);
    }

    public static boolean isShowingAds() {
        return getCurrentActivity() instanceof UnityAdsFullscreenActivity;
    }
}
