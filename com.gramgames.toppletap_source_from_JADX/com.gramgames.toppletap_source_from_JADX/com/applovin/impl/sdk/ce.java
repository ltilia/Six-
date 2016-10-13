package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkSettings;
import com.google.android.exoplayer.hls.HlsMediaPlaylist;
import gs.gram.mopub.BuildConfig;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONObject;

class ce {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final Context c;
    private final Object[] d;

    ce(AppLovinSdkImpl appLovinSdkImpl) {
        this.d = new Object[cb.b()];
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getLogger();
        this.c = appLovinSdkImpl.getApplicationContext();
    }

    private static cd a(String str) {
        for (cd cdVar : cb.a()) {
            if (cdVar.b().equals(str)) {
                return cdVar;
            }
        }
        return null;
    }

    private static Object a(String str, JSONObject jSONObject, Object obj) {
        if (obj instanceof Boolean) {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        }
        if (obj instanceof Float) {
            return Float.valueOf((float) jSONObject.getDouble(str));
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(jSONObject.getInt(str));
        }
        if (obj instanceof Long) {
            return Long.valueOf(jSONObject.getLong(str));
        }
        if (obj instanceof String) {
            return jSONObject.getString(str);
        }
        throw new RuntimeException("SDK Error: unknown value type: " + obj.getClass());
    }

    private String e() {
        return "com.applovin.sdk." + dm.a(this.a.getSdkKey()) + ".";
    }

    public SharedPreferences a() {
        if (this.c != null) {
            return this.c.getSharedPreferences("com.applovin.sdk.1", 0);
        }
        throw new IllegalArgumentException("No context specified");
    }

    public Object a(cd cdVar) {
        if (cdVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        }
        Object obj;
        synchronized (this.d) {
            try {
                obj = this.d[cdVar.a()];
                if (obj != null) {
                    obj = cdVar.a(obj);
                } else {
                    obj = cdVar.c();
                }
            } catch (Throwable th) {
                this.a.getLogger().e("SettingsManager", "Unable to retrieve value for setting " + cdVar.b() + "; using default...");
                obj = cdVar.c();
            }
        }
        return obj;
    }

    public void a(cd cdVar, Object obj) {
        if (cdVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        } else if (obj == null) {
            throw new IllegalArgumentException("No new value specified");
        } else {
            synchronized (this.d) {
                this.d[cdVar.a()] = obj;
            }
            this.b.d("SettingsManager", "Setting update: " + cdVar.b() + " set to \"" + obj + "\"");
        }
    }

    void a(AppLovinSdkSettings appLovinSdkSettings) {
        long j = 0;
        boolean z = false;
        this.b.i("SettingsManager", "Loading user-defined settings...");
        if (appLovinSdkSettings != null) {
            synchronized (this.d) {
                boolean z2;
                boolean z3;
                this.d[cb.i.a()] = Boolean.valueOf(appLovinSdkSettings.isVerboseLoggingEnabled());
                long bannerAdRefreshSeconds = appLovinSdkSettings.getBannerAdRefreshSeconds();
                if (bannerAdRefreshSeconds >= 0) {
                    if (bannerAdRefreshSeconds > 0) {
                        j = Math.max(30, bannerAdRefreshSeconds);
                    }
                    this.d[cb.u.a()] = Long.valueOf(j);
                    this.d[cb.t.a()] = Boolean.valueOf(true);
                } else if (bannerAdRefreshSeconds == -1) {
                    this.d[cb.t.a()] = Boolean.valueOf(false);
                }
                String autoPreloadSizes = appLovinSdkSettings.getAutoPreloadSizes();
                if (autoPreloadSizes == null) {
                    autoPreloadSizes = HlsMediaPlaylist.ENCRYPTION_METHOD_NONE;
                }
                Object[] objArr = this.d;
                int a = cb.D.a();
                if (autoPreloadSizes.equals(HlsMediaPlaylist.ENCRYPTION_METHOD_NONE)) {
                    autoPreloadSizes = BuildConfig.FLAVOR;
                }
                objArr[a] = autoPreloadSizes;
                autoPreloadSizes = appLovinSdkSettings.getAutoPreloadTypes();
                if (autoPreloadSizes == null) {
                    autoPreloadSizes = HlsMediaPlaylist.ENCRYPTION_METHOD_NONE;
                }
                if (autoPreloadSizes.equals(HlsMediaPlaylist.ENCRYPTION_METHOD_NONE)) {
                    z2 = false;
                    z3 = false;
                } else {
                    z2 = false;
                    z3 = false;
                    for (String str : autoPreloadSizes.split(",")) {
                        if (str.equals(AppLovinAdType.REGULAR.getLabel())) {
                            z3 = true;
                        } else if (str.equals(AppLovinAdType.INCENTIVIZED.getLabel()) || str.contains("INCENT") || str.contains("REWARD")) {
                            z2 = true;
                        } else if (str.equals(NativeAdImpl.TYPE_NATIVE.getLabel())) {
                            z = true;
                        }
                    }
                }
                if (!z3) {
                    this.d[cb.D.a()] = BuildConfig.FLAVOR;
                }
                this.d[cb.E.a()] = Boolean.valueOf(z2);
                this.d[cb.aE.a()] = Boolean.valueOf(z);
                if (appLovinSdkSettings instanceof bb) {
                    for (Entry entry : ((bb) appLovinSdkSettings).b().entrySet()) {
                        this.d[((cd) entry.getKey()).a()] = entry.getValue();
                    }
                }
            }
        }
    }

    void a(JSONObject jSONObject) {
        this.b.d("SettingsManager", "Loading settings from JSON array...");
        synchronized (this.d) {
            String str = BuildConfig.FLAVOR;
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                if (str != null && str.length() > 0) {
                    try {
                        cd a = a(str);
                        if (a != null) {
                            Object a2 = a(str, jSONObject, a.c());
                            this.d[a.a()] = a2;
                            this.b.d("SettingsManager", "Setting update: " + a.b() + " set to \"" + a2 + "\"");
                        } else {
                            this.b.w("SettingsManager", "Unknown setting recieved: " + str);
                        }
                    } catch (Throwable e) {
                        this.b.e("SettingsManager", "Unable to parse JSON settings array", e);
                    } catch (Throwable e2) {
                        this.b.e("SettingsManager", "Unable to convert setting object ", e2);
                    }
                }
            }
        }
    }

    void b() {
        if (this.c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.b.i("SettingsManager", "Saving settings with the application...");
        String e = e();
        Editor edit = a().edit();
        synchronized (this.d) {
            for (cd cdVar : cb.a()) {
                Object obj = this.d[cdVar.a()];
                if (obj != null) {
                    String str = e + cdVar.b();
                    if (obj instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof Float) {
                        edit.putFloat(str, ((Float) obj).floatValue());
                    } else if (obj instanceof Integer) {
                        edit.putInt(str, ((Integer) obj).intValue());
                    } else if (obj instanceof Long) {
                        edit.putLong(str, ((Long) obj).longValue());
                    } else if (obj instanceof String) {
                        edit.putString(str, (String) obj);
                    } else {
                        throw new RuntimeException("SDK Error: unknown value: " + obj.getClass());
                    }
                }
            }
        }
        edit.commit();
        this.b.d("SettingsManager", "Settings saved with the application.");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void c() {
        /*
        r10 = this;
        r0 = r10.c;
        if (r0 != 0) goto L_0x000c;
    L_0x0004:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "No context specified";
        r0.<init>(r1);
        throw r0;
    L_0x000c:
        r0 = r10.b;
        r1 = "SettingsManager";
        r2 = "Loading settings saved with the application...";
        r0.i(r1, r2);
        r2 = r10.e();
        r3 = r10.a();
        r4 = r10.d;
        monitor-enter(r4);
        r0 = com.applovin.impl.sdk.cb.a();	 Catch:{ all -> 0x008e }
        r5 = r0.iterator();	 Catch:{ all -> 0x008e }
    L_0x0028:
        r0 = r5.hasNext();	 Catch:{ all -> 0x008e }
        if (r0 == 0) goto L_0x00f2;
    L_0x002e:
        r0 = r5.next();	 Catch:{ all -> 0x008e }
        r0 = (com.applovin.impl.sdk.cd) r0;	 Catch:{ all -> 0x008e }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0068 }
        r1.<init>();	 Catch:{ Exception -> 0x0068 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0068 }
        r6 = r0.b();	 Catch:{ Exception -> 0x0068 }
        r1 = r1.append(r6);	 Catch:{ Exception -> 0x0068 }
        r6 = r1.toString();	 Catch:{ Exception -> 0x0068 }
        r1 = r0.c();	 Catch:{ Exception -> 0x0068 }
        r7 = r1 instanceof java.lang.Boolean;	 Catch:{ Exception -> 0x0068 }
        if (r7 == 0) goto L_0x0091;
    L_0x0051:
        r1 = (java.lang.Boolean) r1;	 Catch:{ Exception -> 0x0068 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x0068 }
        r1 = r3.getBoolean(r6, r1);	 Catch:{ Exception -> 0x0068 }
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x0068 }
    L_0x005f:
        r6 = r10.d;	 Catch:{ Exception -> 0x0068 }
        r7 = r0.a();	 Catch:{ Exception -> 0x0068 }
        r6[r7] = r1;	 Catch:{ Exception -> 0x0068 }
        goto L_0x0028;
    L_0x0068:
        r1 = move-exception;
        r6 = r10.b;	 Catch:{ all -> 0x008e }
        r7 = "SettingsManager";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x008e }
        r8.<init>();	 Catch:{ all -> 0x008e }
        r9 = "Unable to load \"";
        r8 = r8.append(r9);	 Catch:{ all -> 0x008e }
        r0 = r0.b();	 Catch:{ all -> 0x008e }
        r0 = r8.append(r0);	 Catch:{ all -> 0x008e }
        r8 = "\"";
        r0 = r0.append(r8);	 Catch:{ all -> 0x008e }
        r0 = r0.toString();	 Catch:{ all -> 0x008e }
        r6.e(r7, r0, r1);	 Catch:{ all -> 0x008e }
        goto L_0x0028;
    L_0x008e:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x008e }
        throw r0;
    L_0x0091:
        r7 = r1 instanceof java.lang.Float;	 Catch:{ Exception -> 0x0068 }
        if (r7 == 0) goto L_0x00a4;
    L_0x0095:
        r1 = (java.lang.Float) r1;	 Catch:{ Exception -> 0x0068 }
        r1 = r1.floatValue();	 Catch:{ Exception -> 0x0068 }
        r1 = r3.getFloat(r6, r1);	 Catch:{ Exception -> 0x0068 }
        r1 = java.lang.Float.valueOf(r1);	 Catch:{ Exception -> 0x0068 }
        goto L_0x005f;
    L_0x00a4:
        r7 = r1 instanceof java.lang.Integer;	 Catch:{ Exception -> 0x0068 }
        if (r7 == 0) goto L_0x00b7;
    L_0x00a8:
        r1 = (java.lang.Integer) r1;	 Catch:{ Exception -> 0x0068 }
        r1 = r1.intValue();	 Catch:{ Exception -> 0x0068 }
        r1 = r3.getInt(r6, r1);	 Catch:{ Exception -> 0x0068 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x0068 }
        goto L_0x005f;
    L_0x00b7:
        r7 = r1 instanceof java.lang.Long;	 Catch:{ Exception -> 0x0068 }
        if (r7 == 0) goto L_0x00ca;
    L_0x00bb:
        r1 = (java.lang.Long) r1;	 Catch:{ Exception -> 0x0068 }
        r8 = r1.longValue();	 Catch:{ Exception -> 0x0068 }
        r6 = r3.getLong(r6, r8);	 Catch:{ Exception -> 0x0068 }
        r1 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x0068 }
        goto L_0x005f;
    L_0x00ca:
        r7 = r1 instanceof java.lang.String;	 Catch:{ Exception -> 0x0068 }
        if (r7 == 0) goto L_0x00d5;
    L_0x00ce:
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x0068 }
        r1 = r3.getString(r6, r1);	 Catch:{ Exception -> 0x0068 }
        goto L_0x005f;
    L_0x00d5:
        r6 = new java.lang.RuntimeException;	 Catch:{ Exception -> 0x0068 }
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0068 }
        r7.<init>();	 Catch:{ Exception -> 0x0068 }
        r8 = "SDK Error: unknown value: ";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x0068 }
        r1 = r1.getClass();	 Catch:{ Exception -> 0x0068 }
        r1 = r7.append(r1);	 Catch:{ Exception -> 0x0068 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0068 }
        r6.<init>(r1);	 Catch:{ Exception -> 0x0068 }
        throw r6;	 Catch:{ Exception -> 0x0068 }
    L_0x00f2:
        monitor-exit(r4);	 Catch:{ all -> 0x008e }
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ce.c():void");
    }

    void d() {
        synchronized (this.d) {
            Arrays.fill(this.d, null);
        }
        Editor edit = a().edit();
        edit.clear();
        edit.commit();
    }
}
