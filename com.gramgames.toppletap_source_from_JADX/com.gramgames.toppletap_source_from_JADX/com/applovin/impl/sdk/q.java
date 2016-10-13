package com.applovin.impl.sdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.exoplayer.C;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.io.InputStream;
import java.util.Scanner;
import org.json.JSONObject;

class q {
    private static final int[] a;
    private static final int[] b;
    private static final int[] c;
    private static final String d;

    static {
        a = new int[]{7, 4, 2, 1, 11};
        b = new int[]{5, 6, 10, 3, 9, 8, 14};
        c = new int[]{15, 12, 13};
        d = q.class.getSimpleName();
    }

    q() {
    }

    static String a(AppLovinSdkImpl appLovinSdkImpl) {
        NetworkInfo b = b(appLovinSdkImpl.getApplicationContext());
        if (b == null) {
            return UnityAdsConstants.UNITY_ADS_DEVICEID_UNKNOWN;
        }
        int type = b.getType();
        int subtype = b.getSubtype();
        String str = type == 1 ? "wifi" : type == 0 ? a(subtype, a) ? "2g" : a(subtype, b) ? "3g" : a(subtype, c) ? "4g" : "mobile" : UnityAdsConstants.UNITY_ADS_DEVICEID_UNKNOWN;
        appLovinSdkImpl.getLogger().d(d, "Network " + type + "/" + subtype + " resolved to " + str);
        return str;
    }

    static String a(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        Scanner scanner = new Scanner(inputStream, C.UTF8_NAME);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        return stringBuilder.toString();
    }

    static String a(String str) {
        return str.startsWith("https://") ? str : str.replace("http://", "https://");
    }

    static String a(String str, AppLovinSdkImpl appLovinSdkImpl) {
        if (str == null) {
            throw new IllegalArgumentException("No endpoint specified");
        } else if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            String str2 = (String) appLovinSdkImpl.a(cb.e);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append((String) appLovinSdkImpl.a(cb.j));
            stringBuilder.append(str);
            if (str2 == null || str2.length() <= 0) {
                stringBuilder.append("?api_key=");
                stringBuilder.append(appLovinSdkImpl.getSdkKey());
            } else {
                stringBuilder.append("?device_token=");
                stringBuilder.append(str2);
            }
            return stringBuilder.toString();
        }
    }

    static JSONObject a(JSONObject jSONObject) {
        return (JSONObject) jSONObject.getJSONArray("results").get(0);
    }

    static void a(int i, AppLovinSdkImpl appLovinSdkImpl) {
        ce settingsManager = appLovinSdkImpl.getSettingsManager();
        if (i == 401) {
            settingsManager.a(cb.c, BuildConfig.FLAVOR);
            settingsManager.a(cb.e, BuildConfig.FLAVOR);
            settingsManager.b();
        } else if (i == 418) {
            settingsManager.a(cb.a, Boolean.valueOf(true));
            settingsManager.b();
        } else if (i >= 400 && i < 500) {
            appLovinSdkImpl.h();
        } else if (i == -1) {
            appLovinSdkImpl.h();
        }
    }

    static void a(JSONObject jSONObject, AppLovinSdkImpl appLovinSdkImpl) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No response specified");
        } else if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            try {
                if (jSONObject.has("settings")) {
                    ce settingsManager = appLovinSdkImpl.getSettingsManager();
                    if (!jSONObject.isNull("settings")) {
                        settingsManager.a(jSONObject.getJSONObject("settings"));
                        settingsManager.b();
                        appLovinSdkImpl.getLogger().d(d, "New settings processed");
                    }
                }
            } catch (Throwable e) {
                appLovinSdkImpl.getLogger().e(d, "Unable to parse settings out of API response", e);
            }
        }
    }

    private static boolean a(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    static boolean a(Context context) {
        NetworkInfo b = b(context);
        return b == null || b.isConnected();
    }

    private static NetworkInfo b(Context context) {
        if (r.a("android.permission.ACCESS_NETWORK_STATE", context)) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                return connectivityManager.getActiveNetworkInfo();
            }
        }
        return null;
    }

    static String b(String str, AppLovinSdkImpl appLovinSdkImpl) {
        if (str == null) {
            throw new IllegalArgumentException("No endpoint specified");
        } else if (appLovinSdkImpl != null) {
            return ((String) appLovinSdkImpl.a(cb.k)) + str;
        } else {
            throw new IllegalArgumentException("No sdk specified");
        }
    }

    static void b(int i, AppLovinSdkImpl appLovinSdkImpl) {
        if (i == 418) {
            ce settingsManager = appLovinSdkImpl.getSettingsManager();
            settingsManager.a(cb.a, Boolean.valueOf(true));
            settingsManager.b();
        }
    }
}
