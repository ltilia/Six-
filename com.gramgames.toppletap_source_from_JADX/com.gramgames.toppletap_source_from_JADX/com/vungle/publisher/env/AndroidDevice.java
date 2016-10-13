package com.vungle.publisher.env;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebView;
import com.google.android.exoplayer.ExoPlayer.Factory;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.vungle.log.Logger;
import com.vungle.publisher.device.AudioHelper;
import com.vungle.publisher.eh;
import com.vungle.publisher.ek;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.inject.annotations.EnvSharedPreferences;
import com.vungle.publisher.jh;
import com.vungle.publisher.ji;
import com.vungle.publisher.util.ViewUtils;
import gs.gram.mopub.BuildConfig;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AndroidDevice implements ek {
    static int a;
    final AtomicBoolean b;
    boolean c;
    String d;
    String e;
    String f;
    @Inject
    public AudioHelper g;
    @Inject
    public WindowManager h;
    @Inject
    public Context i;
    @Inject
    public EventBus j;
    @Inject
    @EnvSharedPreferences
    public SharedPreferences k;
    @Inject
    public DeviceIdStrategy l;
    private final String m;

    /* compiled from: vungle */
    public static abstract class DeviceIdStrategy {
        protected abstract void c(AndroidDevice androidDevice);
    }

    static {
        a = Factory.DEFAULT_MIN_REBUFFER_MS;
    }

    @Inject
    AndroidDevice() {
        this.m = VERSION.RELEASE;
        this.b = new AtomicBoolean();
        Injector.b().a(this);
    }

    public final String a() {
        s();
        return this.d;
    }

    final boolean b() {
        return !TextUtils.isEmpty(this.d);
    }

    public final String c() {
        s();
        String str = this.e;
        if (!a(str) || !b()) {
            return str;
        }
        Logger.w(Logger.DEVICE_TAG, "have advertising and Android ID");
        d();
        return null;
    }

    final void d() {
        Logger.i(Logger.DEVICE_TAG, "clearing Android ID");
        this.e = null;
    }

    static boolean a(String str) {
        return !TextUtils.isEmpty(str);
    }

    final void e() {
        if (!this.b.getAndSet(true)) {
            synchronized (this.b) {
                this.b.notifyAll();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void s() {
        /*
        r6 = this;
        r0 = r6.b;	 Catch:{ all -> 0x003f }
        r0 = r0.get();	 Catch:{ all -> 0x003f }
        if (r0 != 0) goto L_0x005e;
    L_0x0008:
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x003f }
        r2 = a;	 Catch:{ all -> 0x003f }
        r2 = (long) r2;	 Catch:{ all -> 0x003f }
        r0 = r0 + r2;
        r2 = r6.b;	 Catch:{ all -> 0x003f }
        monitor-enter(r2);	 Catch:{ all -> 0x003f }
        r3 = "VungleDevice";
        r4 = "waiting for device ID";
        com.vungle.log.Logger.d(r3, r4);	 Catch:{ all -> 0x003c }
    L_0x001a:
        r3 = r6.b;	 Catch:{ all -> 0x003c }
        r3 = r3.get();	 Catch:{ all -> 0x003c }
        if (r3 != 0) goto L_0x004e;
    L_0x0022:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x003c }
        r3 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r3 >= 0) goto L_0x004e;
    L_0x002a:
        r3 = r6.b;	 Catch:{ InterruptedException -> 0x0033 }
        r4 = a;	 Catch:{ InterruptedException -> 0x0033 }
        r4 = (long) r4;	 Catch:{ InterruptedException -> 0x0033 }
        r3.wait(r4);	 Catch:{ InterruptedException -> 0x0033 }
        goto L_0x001a;
    L_0x0033:
        r3 = move-exception;
        r3 = "VungleDevice";
        r4 = "interrupted while awaiting device ID";
        com.vungle.log.Logger.v(r3, r4);	 Catch:{ all -> 0x003c }
        goto L_0x001a;
    L_0x003c:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
        throw r0;	 Catch:{ all -> 0x003f }
    L_0x003f:
        r0 = move-exception;
        r1 = r6.f();
        if (r1 != 0) goto L_0x004d;
    L_0x0046:
        r1 = "VungleDevice";
        r2 = "no device ID available";
        com.vungle.log.Logger.w(r1, r2);
    L_0x004d:
        throw r0;
    L_0x004e:
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
        r0 = r6.b;	 Catch:{ all -> 0x003f }
        r0 = r0.get();	 Catch:{ all -> 0x003f }
        if (r0 == 0) goto L_0x006c;
    L_0x0057:
        r0 = "VungleDevice";
        r1 = "obtained device ID";
        com.vungle.log.Logger.d(r0, r1);	 Catch:{ all -> 0x003f }
    L_0x005e:
        r0 = r6.f();
        if (r0 != 0) goto L_0x006b;
    L_0x0064:
        r0 = "VungleDevice";
        r1 = "no device ID available";
        com.vungle.log.Logger.w(r0, r1);
    L_0x006b:
        return;
    L_0x006c:
        r0 = new com.vungle.publisher.el;	 Catch:{ all -> 0x003f }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x003f }
        r2 = "timeout after ";
        r1.<init>(r2);	 Catch:{ all -> 0x003f }
        r2 = a;	 Catch:{ all -> 0x003f }
        r1 = r1.append(r2);	 Catch:{ all -> 0x003f }
        r2 = " ms";
        r1 = r1.append(r2);	 Catch:{ all -> 0x003f }
        r1 = r1.toString();	 Catch:{ all -> 0x003f }
        r0.<init>(r1);	 Catch:{ all -> 0x003f }
        throw r0;	 Catch:{ all -> 0x003f }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.env.AndroidDevice.s():void");
    }

    final boolean f() {
        return b() || a(this.e) || l();
    }

    public final String g() {
        return this.m;
    }

    public final boolean a(eh ehVar) {
        return VERSION.SDK_INT >= ehVar.f;
    }

    public final DisplayMetrics h() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            this.h.getDefaultDisplay().getMetrics(displayMetrics);
        } catch (Throwable e) {
            Logger.d(Logger.DEVICE_TAG, "error getting display metrics", e);
        }
        return displayMetrics;
    }

    public final boolean i() {
        return this.c;
    }

    public final String j() {
        String str = this.f;
        if (!l() || !b()) {
            return str;
        }
        Logger.w(Logger.DEVICE_TAG, "have advertising and mac address");
        k();
        return null;
    }

    final void k() {
        Logger.i(Logger.DEVICE_TAG, "clearing MAC address");
        this.f = null;
    }

    final boolean l() {
        return !TextUtils.isEmpty(this.f);
    }

    public final String m() {
        try {
            return (Build.MANUFACTURER == null ? BuildConfig.FLAVOR : Build.MANUFACTURER) + "," + (Build.MODEL == null ? BuildConfig.FLAVOR : Build.MODEL);
        } catch (Throwable e) {
            Logger.d(Logger.DEVICE_TAG, "error getting device model", e);
            return null;
        }
    }

    public final Float n() {
        Float f = null;
        try {
            f = Float.valueOf(this.g.c());
        } catch (Throwable e) {
            Logger.d(Logger.DEVICE_TAG, "error getting volume info", e);
        }
        return f;
    }

    public final boolean o() {
        boolean equals = "mounted".equals(Environment.getExternalStorageState());
        boolean a = ji.a(this.i, this);
        if (a && equals) {
            Logger.v(Logger.DEVICE_TAG, "external storage writable");
        } else {
            Logger.w(Logger.DEVICE_TAG, "external storage not writable");
        }
        return a && equals;
    }

    final boolean b(String str) {
        boolean z;
        Throwable e;
        Throwable th;
        try {
            int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.i);
            z = isGooglePlayServicesAvailable == 0;
            if (!z) {
                try {
                    Logger.i(str, "Google Play Services not available: " + GooglePlayServicesUtil.getErrorString(isGooglePlayServicesAvailable));
                } catch (IllegalStateException e2) {
                    e = e2;
                    Logger.w(Logger.CONFIG_TAG, jh.a(e));
                    return z;
                } catch (NoClassDefFoundError e3) {
                    e = e3;
                    Logger.d(str, e.getClass().getSimpleName() + ": " + e.getMessage());
                    Logger.v(str, e);
                    return z;
                }
            }
        } catch (Throwable e4) {
            th = e4;
            z = false;
            e = th;
            Logger.w(Logger.CONFIG_TAG, jh.a(e));
            return z;
        } catch (Throwable e42) {
            th = e42;
            z = false;
            e = th;
            Logger.d(str, e.getClass().getSimpleName() + ": " + e.getMessage());
            Logger.v(str, e);
            return z;
        }
        return z;
    }

    public final String p() {
        if (b(Logger.DEVICE_TAG)) {
            return Integer.toString(4030500);
        }
        return null;
    }

    public final void q() {
        this.l.c(this);
    }

    public final String r() {
        return this.k.getString("defaultUserAgent", null);
    }

    public final void a(WebView webView) {
        this.k.edit().putString("defaultUserAgent", ViewUtils.a(webView)).commit();
    }

    public final boolean a(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }
}
