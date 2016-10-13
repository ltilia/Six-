package com.applovin.impl.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.util.Log;
import com.applovin.nativeAds.AppLovinNativeAdService;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.applovin.sdk.AppLovinTargetingData;
import gs.gram.mopub.BuildConfig;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

public class AppLovinSdkImpl extends AppLovinSdk {
    private String a;
    private AppLovinSdkSettings b;
    private Context c;
    private AppLovinLogger d;
    private cv e;
    private ce f;
    private o g;
    private cg h;
    private y i;
    private b j;
    private bg k;
    private r l;
    private m m;
    private AppLovinAdServiceImpl n;
    private bi o;
    private PostbackServiceImpl p;
    private EventServiceImpl q;
    private br r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;

    public AppLovinSdkImpl() {
        this.s = true;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = false;
        this.x = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.content.Context r7) {
        /*
        r6 = this;
        r5 = 630; // 0x276 float:8.83E-43 double:3.113E-321;
        r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r7);
        r0 = "com.applovin.sdk.impl.lastKnownVersionCode";
        r2 = 0;
        r0 = r1.getInt(r0, r2);	 Catch:{ Exception -> 0x003a }
        if (r0 >= r5) goto L_0x0032;
    L_0x000f:
        r0 = "AppLovinSdkImpl";
        r2 = "SDK has been updated since last run. Continuing...";
        android.util.Log.i(r0, r2);	 Catch:{ Exception -> 0x003a }
        r0 = r6.getSettingsManager();	 Catch:{ Exception -> 0x003a }
        r0.d();	 Catch:{ Exception -> 0x003a }
        r0 = r6.getSettingsManager();	 Catch:{ Exception -> 0x003a }
        r0.b();	 Catch:{ Exception -> 0x003a }
    L_0x0024:
        r0 = r1.edit();
        r1 = "com.applovin.sdk.impl.lastKnownVersionCode";
        r0 = r0.putInt(r1, r5);
        r0.apply();
    L_0x0031:
        return;
    L_0x0032:
        r0 = "AppLovinSdkImpl";
        r2 = "SDK has not been updated since last run. Continuing...";
        android.util.Log.d(r0, r2);	 Catch:{ Exception -> 0x003a }
        goto L_0x0024;
    L_0x003a:
        r0 = move-exception;
        r2 = r6.getLogger();	 Catch:{ all -> 0x0054 }
        r3 = "AppLovinSdkImpl";
        r4 = "Unable to check for SDK update";
        r2.e(r3, r4, r0);	 Catch:{ all -> 0x0054 }
        r0 = r1.edit();
        r1 = "com.applovin.sdk.impl.lastKnownVersionCode";
        r0 = r0.putInt(r1, r5);
        r0.apply();
        goto L_0x0031;
    L_0x0054:
        r0 = move-exception;
        r1 = r1.edit();
        r2 = "com.applovin.sdk.impl.lastKnownVersionCode";
        r1 = r1.putInt(r2, r5);
        r1.apply();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.AppLovinSdkImpl.a(android.content.Context):void");
    }

    private static boolean i() {
        return (VERSION.RELEASE.startsWith("1.") || VERSION.RELEASE.startsWith("2.0") || VERSION.RELEASE.startsWith("2.1")) ? false : true;
    }

    cv a() {
        return this.e;
    }

    Object a(cd cdVar) {
        return this.f.a(cdVar);
    }

    void a(boolean z) {
        this.s = false;
        this.t = z;
        this.u = true;
    }

    cg b() {
        return this.h;
    }

    b c() {
        return this.j;
    }

    public boolean checkCorrectInitialization(Context context) {
        try {
            getLogger().d(AppLovinLogger.SDK_TAG, "Checking if sdk is initialized in main activity...");
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(context.getPackageName());
            String stackTraceString = Log.getStackTraceString(new Throwable());
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (queryIntentActivities != null) {
                getLogger().d(AppLovinLogger.SDK_TAG, "Found " + queryIntentActivities.size() + " main activities for this application");
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    if (stackTraceString.contains(resolveInfo.activityInfo.name)) {
                        return true;
                    }
                }
            }
            getLogger().w(AppLovinLogger.SDK_TAG, "AppLovin SDK was initialized too late in session; SDK should always be initialized within main activity and/or any relevant entry points");
            getLogger().w(AppLovinLogger.SDK_TAG, "Initialization instead happened from: " + stackTraceString);
        } catch (Throwable th) {
            getLogger().e(AppLovinLogger.SDK_TAG, "Error checking if sdk is initialized in main activity...", th);
        }
        return false;
    }

    bg d() {
        return this.k;
    }

    boolean e() {
        return this.s;
    }

    boolean f() {
        return this.u;
    }

    void g() {
        this.s = true;
        this.e.a(new cu(this), 0);
    }

    public AppLovinAdService getAdService() {
        return this.n;
    }

    public Context getApplicationContext() {
        return this.c;
    }

    public o getConnectionManager() {
        return this.g;
    }

    public r getDataCollector() {
        return this.l;
    }

    public AppLovinEventService getEventService() {
        return this.q;
    }

    public y getFileManager() {
        return this.i;
    }

    public AppLovinLogger getLogger() {
        return this.d;
    }

    public AppLovinNativeAdService getNativeAdService() {
        return this.o;
    }

    public br getPersistentPostbackManager() {
        return this.r;
    }

    public PostbackServiceImpl getPostbackService() {
        return this.p;
    }

    public String getSdkKey() {
        return this.a;
    }

    public AppLovinSdkSettings getSettings() {
        return this.b;
    }

    public ce getSettingsManager() {
        return this.f;
    }

    public AppLovinTargetingData getTargetingData() {
        return this.m;
    }

    void h() {
        this.f.d();
        this.f.b();
        this.h.a();
    }

    public boolean hasCriticalErrors() {
        return this.v || this.w;
    }

    public void initialize(String str, AppLovinSdkSettings appLovinSdkSettings, Context context) {
        this.a = str;
        this.b = appLovinSdkSettings;
        this.c = context;
        try {
            k kVar = new k();
            this.d = kVar;
            this.f = new ce(this);
            this.e = new cv(this);
            this.g = new o(this);
            this.h = new cg(this);
            this.i = new y(this);
            this.l = new r(this);
            this.n = new AppLovinAdServiceImpl(this);
            this.o = new bi(this);
            this.p = new PostbackServiceImpl(this);
            this.q = new EventServiceImpl(this);
            this.r = new br(this);
            this.j = new b(this);
            this.k = new bg(this);
            this.m = new m(this);
            if (!i()) {
                this.v = true;
                Log.e(AppLovinLogger.SDK_TAG, "Unable to initalize AppLovin SDK: Android SDK version has to be at least level 8");
            }
            if (str == null || str.length() < 1) {
                this.w = true;
                Log.e(AppLovinLogger.SDK_TAG, "Unable to find AppLovin SDK key. Please add     meta-data android:name=\"applovin.sdk.key\" android:value=\"YOUR_SDK_KEY_HERE\" into AndroidManifest.xml.");
                Writer stringWriter = new StringWriter();
                new Throwable(BuildConfig.FLAVOR).printStackTrace(new PrintWriter(stringWriter));
                Log.e(AppLovinLogger.SDK_TAG, "Called with an invalid SDK key from: " + stringWriter.toString());
            }
            if (hasCriticalErrors()) {
                a(false);
                return;
            }
            kVar.a(this.f);
            if (appLovinSdkSettings instanceof bb) {
                kVar.a(((bb) appLovinSdkSettings).a());
            }
            a(context);
            this.f.c();
            if (((Boolean) this.f.a(cb.b)).booleanValue()) {
                this.f.a(appLovinSdkSettings);
                this.f.b();
            }
            g();
        } catch (Throwable th) {
            Log.e(AppLovinLogger.SDK_TAG, "Failed to load AppLovin SDK, ad serving will be disabled", th);
            a(false);
        }
    }

    public void initializeSdk() {
    }

    public boolean isEnabled() {
        return this.t;
    }

    public boolean isInitializedInMainActivity() {
        return this.x;
    }

    public void setInitializedInMainActivity(boolean z) {
        this.x = z;
    }

    public void setPluginVersion(String str) {
        if (str == null) {
            throw new IllegalArgumentException("No version specified");
        }
        this.f.a(cb.z, str);
        this.f.b();
    }
}
