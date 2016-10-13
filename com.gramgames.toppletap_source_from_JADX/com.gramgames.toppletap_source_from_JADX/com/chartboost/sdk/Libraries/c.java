package com.chartboost.sdk.Libraries;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.util.Base64;
import com.chartboost.sdk.impl.aw;
import com.chartboost.sdk.impl.az;
import gs.gram.mopub.BuildConfig;
import java.util.UUID;
import org.json.JSONObject;

public final class c {
    private static String a;
    private static String b;
    private static a c;
    private static String d;

    static class 1 implements Runnable {
        1() {
        }

        public void run() {
            try {
                c.b(d.a());
                az.b();
            } catch (VerifyError e) {
                c.h();
            }
        }
    }

    public enum a {
        PRELOAD(-1),
        LOADING(-1),
        UNKNOWN(-1),
        TRACKING_ENABLED(0),
        TRACKING_DISABLED(1);
        
        private int f;

        private a(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean b() {
            return this.f != -1;
        }
    }

    static {
        a = null;
        b = null;
        c = a.PRELOAD;
        d = null;
    }

    private c() {
    }

    public static void a() {
        synchronized (d.class) {
            if (c() != a.PRELOAD) {
                return;
            }
            a(a.LOADING);
            Class cls = null;
            try {
                cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            } catch (ClassNotFoundException e) {
            }
            if (cls == null) {
                try {
                    throw new ClassNotFoundException("Google play services library is missing. Unable to find class com.google.android.gms.ads.identifier.AdvertisingIdClient");
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                    h();
                    return;
                }
            }
            aw.a().execute(new 1());
        }
    }

    private static void h() {
        CBLogging.b("CBIdentity", "WARNING: It looks like you've forgotten to include the Google Play Services library in your project. Please review the SDK documentation for more details.");
        a(a.UNKNOWN);
        az.b();
    }

    public static String b() {
        if (a == null) {
            a = i();
        }
        return a;
    }

    public static synchronized a c() {
        a aVar;
        synchronized (c.class) {
            aVar = c;
        }
        return aVar;
    }

    protected static synchronized void a(a aVar) {
        synchronized (c.class) {
            c = aVar;
        }
    }

    public static synchronized String d() {
        String str;
        synchronized (c.class) {
            str = b;
        }
        return str;
    }

    private static synchronized void b(String str) {
        synchronized (c.class) {
            b = str;
        }
    }

    private static String i() {
        Object e = e();
        if (e == null || "9774d56d682e549c".equals(e)) {
            e = j();
        }
        String f = f();
        String d = d();
        com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
        a.a("uuid", e);
        a.a("macid", f);
        a.a("gaid", d);
        JSONObject e2 = a.e();
        if (e2 == null) {
            e2 = new JSONObject();
        }
        return Base64.encodeToString(e2.toString().getBytes(), 0);
    }

    public static String e() {
        return Secure.getString(com.chartboost.sdk.c.y().getContentResolver(), "android_id");
    }

    private static String j() {
        if (d == null) {
            SharedPreferences a = CBUtility.a();
            d = a.getString("cbUUID", null);
            if (d == null) {
                d = UUID.randomUUID().toString();
                Editor edit = a.edit();
                edit.putString("cbUUID", d);
                edit.commit();
            }
        }
        return d;
    }

    public static String f() {
        return b.b(b.a(k()));
    }

    private static byte[] k() {
        try {
            String macAddress = ((WifiManager) com.chartboost.sdk.c.y().getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress == null || macAddress.equals(BuildConfig.FLAVOR)) {
                return null;
            }
            String[] split = macAddress.split(":");
            byte[] bArr = new byte[6];
            for (int i = 0; i < split.length; i++) {
                bArr[i] = Integer.valueOf(Integer.parseInt(split[i], 16)).byteValue();
            }
            return bArr;
        } catch (Exception e) {
            return null;
        }
    }
}
