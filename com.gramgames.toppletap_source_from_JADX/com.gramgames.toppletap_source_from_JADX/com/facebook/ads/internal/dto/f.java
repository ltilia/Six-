package com.facebook.ads.internal.dto;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.facebook.ads.internal.util.b;
import com.facebook.ads.internal.util.c;
import com.facebook.ads.internal.util.g;
import com.facebook.ads.internal.util.g.a;
import com.mopub.common.GpsHelper;
import gs.gram.mopub.BuildConfig;

public class f {
    public static final String a;
    public static String b;
    public static String c;
    public static String d;
    public static String e;
    public static String f;
    public static int g;
    public static String h;
    public static int i;
    public static String j;
    public static int k;
    public static String l;
    public static String m;
    public static String n;
    public static boolean o;
    private static boolean p;

    static {
        p = false;
        a = VERSION.RELEASE;
        b = BuildConfig.FLAVOR;
        c = BuildConfig.FLAVOR;
        d = BuildConfig.FLAVOR;
        e = BuildConfig.FLAVOR;
        f = BuildConfig.FLAVOR;
        g = 0;
        h = BuildConfig.FLAVOR;
        i = 0;
        j = BuildConfig.FLAVOR;
        k = 0;
        l = BuildConfig.FLAVOR;
        m = BuildConfig.FLAVOR;
        n = BuildConfig.FLAVOR;
        o = false;
    }

    public static synchronized void a(Context context) {
        synchronized (f.class) {
            if (!p) {
                p = true;
                c(context);
            }
            d(context);
        }
    }

    public static void b(Context context) {
        if (p) {
            try {
                a a;
                com.facebook.ads.internal.f a2;
                SharedPreferences sharedPreferences = context.getSharedPreferences("SDKIDFA", 0);
                if (sharedPreferences.contains("attributionId")) {
                    m = sharedPreferences.getString("attributionId", BuildConfig.FLAVOR);
                }
                if (sharedPreferences.contains(GpsHelper.ADVERTISING_ID_KEY)) {
                    n = sharedPreferences.getString(GpsHelper.ADVERTISING_ID_KEY, BuildConfig.FLAVOR);
                    o = sharedPreferences.getBoolean("limitAdTracking", o);
                }
                try {
                    a = g.a(context.getContentResolver());
                } catch (Throwable e) {
                    c.a(b.a(e, "Error retrieving attribution id from fb4a"));
                    a = null;
                }
                if (a != null) {
                    String str = a.a;
                    if (str != null) {
                        m = str;
                    }
                }
                try {
                    a2 = com.facebook.ads.internal.f.a(context, a);
                } catch (Throwable e2) {
                    c.a(b.a(e2, "Error retrieving advertising id from Google Play Services"));
                    a2 = null;
                }
                if (a2 != null) {
                    String a3 = a2.a();
                    Boolean valueOf = Boolean.valueOf(a2.b());
                    if (a3 != null) {
                        n = a3;
                        o = valueOf.booleanValue();
                    }
                }
                Editor edit = sharedPreferences.edit();
                edit.putString("attributionId", m);
                edit.putString(GpsHelper.ADVERTISING_ID_KEY, n);
                edit.putBoolean("limitAdTracking", o);
                edit.apply();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    private static void c(Context context) {
        String networkOperatorName;
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            d = packageInfo.packageName;
            f = packageInfo.versionName;
            g = packageInfo.versionCode;
        } catch (NameNotFoundException e) {
        }
        try {
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
            if (applicationLabel != null && applicationLabel.length() > 0) {
                e = applicationLabel.toString();
            }
        } catch (NameNotFoundException e2) {
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            networkOperatorName = telephonyManager.getNetworkOperatorName();
            if (networkOperatorName != null && networkOperatorName.length() > 0) {
                h = networkOperatorName;
            }
        }
        networkOperatorName = Build.MANUFACTURER;
        if (networkOperatorName != null && networkOperatorName.length() > 0) {
            b = networkOperatorName;
        }
        networkOperatorName = Build.MODEL;
        if (networkOperatorName != null && networkOperatorName.length() > 0) {
            c = Build.MODEL;
        }
    }

    private static void d(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                i = activeNetworkInfo.getType();
                j = activeNetworkInfo.getTypeName();
                k = activeNetworkInfo.getSubtype();
                l = activeNetworkInfo.getSubtypeName();
            }
        } catch (Exception e) {
        }
    }
}
