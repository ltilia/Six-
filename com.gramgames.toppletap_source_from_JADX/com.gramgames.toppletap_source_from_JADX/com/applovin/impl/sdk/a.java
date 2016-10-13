package com.applovin.impl.sdk;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import gs.gram.mopub.BuildConfig;
import java.util.HashMap;
import java.util.Map;

class a {
    private static final Object a;
    private static final Map b;

    static {
        a = new Object();
        b = new HashMap();
    }

    static Map a(AppLovinSdkImpl appLovinSdkImpl) {
        Map c;
        synchronized (a) {
            appLovinSdkImpl.getLogger().d("AdDataCache", "Reading cached device data...");
            c = c(appLovinSdkImpl);
        }
        return c;
    }

    private static void a(String str, Map map) {
        String[] split = str.split("=");
        if (split.length == 2) {
            map.put(split[0], split[1]);
        }
    }

    static void a(Map map, AppLovinSdkImpl appLovinSdkImpl) {
        b(map, appLovinSdkImpl);
    }

    static void b(AppLovinSdkImpl appLovinSdkImpl) {
        synchronized (a) {
            appLovinSdkImpl.getLogger().d("AdDataCache", "Clearing old device data cache...");
            a(new HashMap(0), appLovinSdkImpl);
        }
    }

    private static void b(Map map, AppLovinSdkImpl appLovinSdkImpl) {
        if (map == null) {
            throw new IllegalArgumentException("No ad aata specified");
        } else if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            try {
                synchronized (b) {
                    Map map2 = (Map) b.get("ad_data_cache");
                    if (map2 == null) {
                        map2 = new HashMap();
                    }
                    map2.clear();
                    map2.putAll(map);
                    b.put("ad_data_cache", map2);
                }
                Editor edit = appLovinSdkImpl.getSettingsManager().a().edit();
                edit.putString("ad_data_cache", dm.a(map));
                edit.commit();
                appLovinSdkImpl.getLogger().d("AdDataCache", map.size() + " " + "ad_data_cache" + " entries saved in cache");
            } catch (Throwable e) {
                appLovinSdkImpl.getLogger().e("AdDataCache", "Unable to save ad data entries", e);
            }
        }
    }

    private static Map c(AppLovinSdkImpl appLovinSdkImpl) {
        Map map;
        Map hashMap;
        Throwable e;
        synchronized (b) {
            map = (Map) b.get("ad_data_cache");
        }
        if (map == null) {
            SharedPreferences a = appLovinSdkImpl.getSettingsManager().a();
            String string = a.getString("ad_data_cache", BuildConfig.FLAVOR);
            if (string != null && string.length() > 0) {
                try {
                    hashMap = new HashMap();
                    try {
                        for (String a2 : string.split("&")) {
                            a(a2, hashMap);
                        }
                        synchronized (b) {
                            b.put("ad_data_cache", hashMap);
                        }
                        appLovinSdkImpl.getLogger().d("AdDataCache", hashMap.size() + " " + "ad_data_cache" + " entries loaded from cache");
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Throwable e3) {
                    Throwable th = e3;
                    hashMap = map;
                    e = th;
                    appLovinSdkImpl.getLogger().e("AdDataCache", "Unable to load ad data", e);
                    Editor edit = a.edit();
                    edit.putString("ad_data_cache", BuildConfig.FLAVOR);
                    edit.commit();
                    return hashMap != null ? new HashMap(hashMap) : new HashMap();
                }
                if (hashMap != null) {
                }
            }
        }
        hashMap = map;
        if (hashMap != null) {
        }
    }
}
