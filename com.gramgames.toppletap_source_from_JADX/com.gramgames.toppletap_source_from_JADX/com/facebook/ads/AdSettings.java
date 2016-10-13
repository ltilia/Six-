package com.facebook.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import com.facebook.ads.internal.util.g;
import com.facebook.ads.internal.util.g.a;
import com.facebook.ads.internal.util.s;
import com.facebook.internal.ServerProtocol;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class AdSettings {
    public static final boolean DEBUG = false;
    static volatile boolean a;
    private static final String b;
    private static final Collection<String> c;
    private static final Collection<String> d;
    private static String e;
    private static boolean f;
    private static String g;

    static {
        b = AdSettings.class.getSimpleName();
        e = null;
        f = false;
        g = null;
        c = new HashSet();
        d = new HashSet();
        d.add(ServerProtocol.DIALOG_PARAM_SDK_VERSION);
        d.add("google_sdk");
        d.add("vbox86p");
        d.add("vbox86tp");
        a = false;
    }

    private static void a(String str) {
        if (!a) {
            a = true;
            Log.d(b, "Test mode device hash: " + str);
            Log.d(b, "When testing your app with Facebook's ad units you must specify the device hashed ID to ensure the delivery of test ads, add the following code before loading an ad: AdSettings.addTestDevice(\"" + str + "\");");
        }
    }

    public static void addTestDevice(String str) {
        c.add(str);
    }

    public static void addTestDevices(Collection<String> collection) {
        c.addAll(collection);
    }

    public static void clearTestDevices() {
        c.clear();
    }

    public static String getUrlPrefix() {
        return e;
    }

    public static boolean isChildDirected() {
        return f;
    }

    public static boolean isTestMode(Context context) {
        if (d.contains(Build.PRODUCT)) {
            return true;
        }
        if (g == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("FBAdPrefs", 0);
            g = sharedPreferences.getString("deviceIdHash", null);
            if (s.a(g)) {
                a a = g.a(context.getContentResolver());
                if (!s.a(a.b)) {
                    g = s.b(a.b);
                } else if (s.a(a.a)) {
                    g = s.b(UUID.randomUUID().toString());
                } else {
                    g = s.b(a.a);
                }
                sharedPreferences.edit().putString("deviceIdHash", g).apply();
            }
        }
        if (c.contains(g)) {
            return true;
        }
        a(g);
        return false;
    }

    public static void setIsChildDirected(boolean z) {
        f = z;
    }

    public static void setUrlPrefix(String str) {
        e = str;
    }
}
