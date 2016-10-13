package com.unity3d.ads.android.data;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@TargetApi(9)
public class UnityAdsDevice {
    public static String getSoftwareVersion() {
        return VERSION.SDK_INT;
    }

    public static String getHardwareVersion() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    public static int getDeviceType() {
        return UnityAdsProperties.APPLICATION_CONTEXT.getResources().getConfiguration().screenLayout;
    }

    @SuppressLint({"DefaultLocale"})
    public static String getAndroidId(boolean z) {
        try {
            String string = Secure.getString(UnityAdsProperties.APPLICATION_CONTEXT.getContentResolver(), "android_id");
            if (z) {
                return UnityAdsUtils.Md5(string).toLowerCase(Locale.US);
            }
            return string;
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Problems fetching androidId: " + e.getMessage());
            return UnityAdsConstants.UNITY_ADS_DEVICEID_UNKNOWN;
        }
    }

    public static String getAdvertisingTrackingId() {
        return UnityAdsAdvertisingId.getAdvertisingTrackingId();
    }

    public static boolean isLimitAdTrackingEnabled() {
        return UnityAdsAdvertisingId.getLimitedAdTracking();
    }

    public static String getConnectionType() {
        if (isUsingWifi()) {
            return "wifi";
        }
        return "cellular";
    }

    public static boolean isUsingWifi() {
        ConnectivityManager connectivityManager = (ConnectivityManager) UnityAdsProperties.APPLICATION_CONTEXT.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        TelephonyManager telephonyManager = (TelephonyManager) UnityAdsProperties.APPLICATION_CONTEXT.getSystemService("phone");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !connectivityManager.getBackgroundDataSetting() || !connectivityManager.getActiveNetworkInfo().isConnected() || telephonyManager == null) {
            return false;
        }
        return activeNetworkInfo.getType() == 1 && activeNetworkInfo.isConnected();
    }

    public static int getNetworkType() {
        return ((TelephonyManager) UnityAdsProperties.APPLICATION_CONTEXT.getSystemService("phone")).getNetworkType();
    }

    public static int getScreenDensity() {
        return UnityAdsProperties.APPLICATION_CONTEXT.getResources().getDisplayMetrics().densityDpi;
    }

    public static int getScreenSize() {
        return getDeviceType();
    }

    private static JSONArray getPackageJsonArray(Map map) {
        JSONArray jSONArray = null;
        if (!(map == null || map.size() == 0)) {
            for (PackageInfo packageInfo : UnityAdsProperties.APPLICATION_CONTEXT.getPackageManager().getInstalledPackages(0)) {
                try {
                    if (packageInfo.packageName != null && packageInfo.packageName.length() > 0) {
                        String Md5 = UnityAdsUtils.Md5(packageInfo.packageName);
                        if (map.containsKey(Md5)) {
                            map.get(Md5);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, map.get(Md5));
                            if (packageInfo.firstInstallTime > 0) {
                                jSONObject.put("timestamp", packageInfo.firstInstallTime);
                            }
                            if (jSONArray == null) {
                                jSONArray = new JSONArray();
                            }
                            jSONArray.put(jSONObject);
                        }
                    }
                    jSONArray = jSONArray;
                } catch (Exception e) {
                    UnityAdsDeviceLog.debug("Exception when processing package " + packageInfo.packageName + " " + e);
                }
            }
        }
        return jSONArray;
    }

    public static String getPackageDataJson(Map map) {
        String str = null;
        JSONArray packageJsonArray = getPackageJsonArray(map);
        if (packageJsonArray != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("games", packageJsonArray);
                str = jSONObject.toString();
            } catch (Exception e) {
                UnityAdsDeviceLog.debug("Exception in getPackageDataJson" + e);
            }
        }
        return str;
    }

    public static boolean isActiveNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) UnityAdsProperties.APPLICATION_CONTEXT.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }
}
