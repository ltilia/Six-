package com.applovin.impl.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.applovin.sdk.AppLovinLogger;
import com.google.android.exoplayer.C;
import com.mopub.common.GpsHelper;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

class r {
    private final AppLovinSdkImpl a;
    private final Context b;
    private final AppLovinLogger c;
    private final Map d;

    r(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = appLovinSdkImpl;
        this.c = appLovinSdkImpl.getLogger();
        this.b = appLovinSdkImpl.getApplicationContext();
        this.d = Collections.synchronizedMap(new HashMap());
    }

    static boolean a(String str, Context context) {
        if (str == null) {
            throw new IllegalArgumentException("No permission name specified");
        } else if (context != null) {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } else {
            throw new IllegalArgumentException("No context specified");
        }
    }

    private String d() {
        String str = UnityAdsConstants.UNITY_ADS_WEBVIEW_VIEWTYPE_NONE;
        try {
            int i = this.b.getResources().getConfiguration().orientation;
            return i == 1 ? DeviceInfo.ORIENTATION_PORTRAIT : i == 2 ? DeviceInfo.ORIENTATION_LANDSCAPE : str;
        } catch (Throwable th) {
            this.a.getLogger().e("DataCollector", "Encountered error while attempting to collect application orientation", th);
            return str;
        }
    }

    private double e() {
        return ((double) Math.round((((double) TimeZone.getDefault().getOffset(new Date().getTime())) * 10.0d) / 3600000.0d)) / 10.0d;
    }

    u a() {
        Object obj = this.d.get(u.class);
        if (obj != null) {
            return (u) obj;
        }
        u uVar = new u();
        uVar.h = Locale.getDefault();
        uVar.a = Build.MODEL;
        uVar.b = VERSION.RELEASE;
        uVar.c = Build.MANUFACTURER;
        uVar.e = VERSION.SDK_INT;
        uVar.d = Build.DEVICE;
        uVar.i = d();
        uVar.j = e();
        if (a("android.permission.READ_PHONE_STATE")) {
            TelephonyManager telephonyManager = (TelephonyManager) this.b.getSystemService("phone");
            if (telephonyManager != null) {
                uVar.f = telephonyManager.getSimCountryIso().toUpperCase(Locale.ENGLISH);
                String networkOperatorName = telephonyManager.getNetworkOperatorName();
                try {
                    uVar.g = URLEncoder.encode(networkOperatorName, C.UTF8_NAME);
                } catch (UnsupportedEncodingException e) {
                    uVar.g = networkOperatorName;
                }
            }
        }
        try {
            uVar.k = this.b.getPackageManager().getPackageInfo((String) this.a.getSettingsManager().a(cb.bu), 0).versionCode;
        } catch (Throwable th) {
        }
        this.d.put(u.class, uVar);
        return uVar;
    }

    boolean a(String str) {
        return a(str, this.b);
    }

    t b() {
        Object obj = this.d.get(t.class);
        if (obj != null) {
            return (t) obj;
        }
        ApplicationInfo applicationInfo = this.b.getApplicationInfo();
        long lastModified = new File(applicationInfo.sourceDir).lastModified();
        PackageManager packageManager = this.b.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(this.b.getPackageName(), 0);
        } catch (NameNotFoundException e) {
        }
        t tVar = new t();
        tVar.c = applicationInfo.packageName;
        tVar.d = lastModified;
        tVar.a = String.valueOf(packageManager.getApplicationLabel(applicationInfo));
        tVar.b = packageInfo != null ? packageInfo.versionName : BuildConfig.FLAVOR;
        this.d.put(t.class, tVar);
        return tVar;
    }

    s c() {
        try {
            Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            if (cls != null) {
                Object invoke = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.b});
                if (invoke != null) {
                    Class cls2 = invoke.getClass();
                    Object invoke2 = cls2.getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, null).invoke(invoke, null);
                    invoke = cls2.getMethod("getId", null).invoke(invoke, null);
                    s sVar = new s();
                    String str = (String) invoke;
                    String str2 = str == null ? BuildConfig.FLAVOR : str;
                    sVar.a = ((Boolean) invoke2).booleanValue();
                    sVar.b = str2;
                    return sVar;
                }
            }
        } catch (Throwable e) {
            this.c.userError("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", e);
        } catch (Throwable e2) {
            this.c.e("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", e2);
        }
        s sVar2 = new s();
        sVar2.b = BuildConfig.FLAVOR;
        sVar2.a = false;
        return sVar2;
    }
}
