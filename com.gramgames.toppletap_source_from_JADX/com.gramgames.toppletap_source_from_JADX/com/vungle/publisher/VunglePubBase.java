package com.vungle.publisher;

import android.content.Context;
import android.os.Build.VERSION;
import com.vungle.log.Logger;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.DatabaseHelper.1;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.env.SdkState.EndAdEventListener;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.file.CacheManager;
import com.vungle.publisher.fj.a;
import com.vungle.publisher.inject.EndpointModule;
import com.vungle.publisher.inject.Injector;
import dagger.internal.Preconditions;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class VunglePubBase {
    public static final String VERSION = "VungleDroid/3.3.5";
    @Inject
    AdManager a;
    @Inject
    InitializationEventListener b;
    @Inject
    CacheManager c;
    @Inject
    DatabaseHelper d;
    @Inject
    Demographic e;
    @Inject
    protected ek f;
    @Inject
    EventBus g;
    @Inject
    AdConfig h;
    @Inject
    SafeBundleAdConfigFactory i;
    @Inject
    SdkConfig j;
    @Inject
    SdkState k;
    @Inject
    Context l;
    private boolean m;
    private boolean n;

    protected VunglePubBase() {
    }

    public boolean init(Context context, String vungleAppId) {
        Object obj = null;
        boolean z = this.n;
        if (z) {
            try {
                Logger.d(Logger.VUNGLE_TAG, "already initialized");
                return z;
            } catch (Throwable e) {
                Logger.e(Logger.VUNGLE_TAG, "VunglePub initialization failed", e);
                return z;
            }
        }
        int i = VERSION.SDK_INT;
        Object obj2 = i >= 9 ? 1 : null;
        if (obj2 != null) {
            Logger.d(Logger.DEVICE_TAG, "Device Android API level " + i);
        } else {
            Logger.w(Logger.DEVICE_TAG, "Device Android API level " + i + " does not meet required minimum 9");
        }
        if (obj2 != null) {
            a(context, vungleAppId);
            if (ji.b(this.l, this.f).length == 0) {
                obj = 1;
            }
            if (obj != null) {
                Logger.i(Logger.VUNGLE_TAG, "VungleDroid/3.3.5 init(" + vungleAppId + ")");
                CacheManager cacheManager = this.c;
                Logger.d(Logger.FILE_TAG, "deleting old ad temp directory");
                CacheManager.a((String) cacheManager.b.get());
                this.b.register();
                DatabaseHelper databaseHelper = this.d;
                databaseHelper.d.a(new 1(databaseHelper), b.databaseWrite);
                this.f.q();
                Logger.v(Logger.VUNGLE_TAG, "initialization successful");
                this.n = true;
                return true;
            }
            Logger.w(Logger.VUNGLE_TAG, "initialization failed");
        }
        return z;
    }

    public String[] getMissingPermissions() {
        String[] strArr = null;
        try {
            if (a()) {
                strArr = ji.b(this.l, this.f);
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error getting missing permissions", e);
        }
        return strArr;
    }

    private boolean a(boolean z, String str) {
        boolean z2 = this.n;
        if (z2) {
            Logger.v(Logger.VUNGLE_TAG, "VunglePub was initialized");
        } else if (z) {
            Logger.w(Logger.VUNGLE_TAG, "Please call VunglePub.init() before " + str);
        }
        return z2;
    }

    private boolean a() {
        boolean z = this.m;
        if (!z) {
            Logger.d(Logger.VUNGLE_TAG, "VunglePub not injected");
        }
        return z;
    }

    protected void a(Context context, String str) {
        if (this.m) {
            Logger.d(Logger.VUNGLE_TAG, "already injected");
            return;
        }
        Injector instance = Injector.getInstance();
        try {
            if (instance.a) {
                Logger.d(Logger.INJECT_TAG, "already initialized");
            } else {
                Logger.d(Logger.INJECT_TAG, "initializing");
                fi a = instance.a();
                if (a.g) {
                    Logger.d(Logger.INJECT_TAG, "publisher module already initialized");
                } else {
                    Logger.d(Logger.INJECT_TAG, "initializing publisher module");
                    a.a = context.getApplicationContext();
                    a.b = str;
                    a.g = true;
                }
                a a2 = fj.a();
                a2.a = (fi) Preconditions.checkNotNull(a);
                if (instance.b == null) {
                    instance.b = new EndpointModule();
                }
                a2.c = (EndpointModule) Preconditions.checkNotNull(instance.b);
                if (instance.c == null) {
                    instance.c = new fk();
                }
                a2.b = (fk) Preconditions.checkNotNull(instance.c);
                if (a2.a == null) {
                    a2.a = new fi();
                }
                if (a2.b == null) {
                    a2.b = new fk();
                }
                if (a2.c == null) {
                    a2.c = new EndpointModule();
                }
                instance.d = new fj((byte) 0);
                instance.a = true;
            }
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, "error initializing injector", e);
        }
        Injector.b().a(this);
        Logger.d(Logger.VUNGLE_TAG, "injection successful");
        this.m = true;
    }

    public Demographic getDemographic() {
        try {
            a();
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error getting demographic info", e);
        }
        return this.e;
    }

    public void addEventListeners(EventListener... eventListeners) {
        try {
            if (a()) {
                this.j.a(eventListeners);
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error adding event listeners", e);
        }
    }

    public void setEventListeners(EventListener... eventListeners) {
        try {
            if (a()) {
                SdkConfig sdkConfig = this.j;
                sdkConfig.a();
                sdkConfig.a(eventListeners);
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error setting event listeners", e);
        }
    }

    public void clearEventListeners() {
        try {
            if (a()) {
                this.j.a();
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error clearing event listeners", e);
        }
    }

    public void removeEventListeners(EventListener... eventListeners) {
        try {
            if (a()) {
                SdkConfig sdkConfig = this.j;
                if (eventListeners != null) {
                    for (Object obj : eventListeners) {
                        ey eyVar = (ey) sdkConfig.a.remove(obj);
                        if ((eyVar != null ? 1 : null) != null) {
                            Logger.d(Logger.CONFIG_TAG, "removing event listener " + obj);
                            eyVar.unregister();
                        } else {
                            Logger.d(Logger.CONFIG_TAG, "event listener not found for remove " + obj);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error removing event listeners", e);
        }
    }

    public AdConfig getGlobalAdConfig() {
        try {
            a();
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error getting globalAdConfig", e);
        }
        return this.h;
    }

    public void onResume() {
        try {
            if (a(false, "onResume()")) {
                SdkState sdkState = this.k;
                Logger.d(Logger.AD_TAG, "onDeveloperActivityResume()");
                sdkState.a(true);
                sdkState.b(false);
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error onResume()", e);
        }
    }

    public void onPause() {
        try {
            if (a(false, "onPause()")) {
                SdkState sdkState = this.k;
                Logger.d(Logger.AD_TAG, "onDeveloperActivityPause()");
                sdkState.f();
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error onPause()", e);
        }
    }

    public boolean isAdPlayable() {
        boolean z = false;
        try {
            if (a(true, "isAdPlayable()")) {
                z = this.a.a();
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error returning ad playable", e);
        }
        return z;
    }

    public void playAd() {
        playAd(null);
    }

    public void playAd(AdConfig adConfig) {
        Object obj = 1;
        Object obj2 = null;
        try {
            Logger.d(Logger.AD_TAG, "VunglePub.playAd()");
            if (a(true, "playAd()")) {
                SdkState sdkState = this.k;
                if (!sdkState.b()) {
                    sdkState.d.a(new bd((int) ((SdkState.d() - sdkState.c()) / 1000), sdkState.e()));
                    obj = null;
                } else if (!sdkState.l.compareAndSet(false, true)) {
                    Logger.d(Logger.AD_TAG, "ad already playing");
                    sdkState.d.a(new az());
                    obj = null;
                }
                if (obj != null) {
                    ((EndAdEventListener) sdkState.k.get()).register();
                }
                obj2 = obj;
            } else if (this.m) {
                this.g.a(new bc());
            }
            if (obj2 != null) {
                AdManager adManager = this.a;
                adManager.f.a(new AdManager.1(adManager, this.i.merge(this.h, adConfig)), b.otherTask);
            }
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error playing ad", e);
            if (this.m) {
                this.g.a(new bb());
            }
        }
    }
}
