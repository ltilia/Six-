package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinTargetingData;
import gs.gram.mopub.BuildConfig;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

class m implements AppLovinTargetingData {
    private final AppLovinSdkImpl a;
    private final Context b;

    m(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getApplicationContext();
    }

    private static String a(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strArr) {
            if (AppLovinSdkUtils.isValidString(str)) {
                stringBuilder.append(dm.c(str));
                stringBuilder.append(",");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    private void a(String str, String str2) {
        if (AppLovinSdkUtils.isValidString(str)) {
            Editor edit = this.b.getSharedPreferences("applovin.sdk.targeting", 0).edit();
            edit.putString(str, dm.c(str2));
            edit.commit();
        }
    }

    Map a() {
        Map hashMap = new HashMap();
        Map all = this.b.getSharedPreferences("applovin.sdk.targeting", 0).getAll();
        if (all != null && all.size() > 0) {
            for (Entry entry : all.entrySet()) {
                hashMap.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return hashMap;
    }

    public void clearData() {
        Editor edit = this.b.getSharedPreferences("applovin.sdk.targeting", 0).edit();
        edit.clear();
        edit.commit();
    }

    public void putExtra(String str, String str2) {
        if (AppLovinSdkUtils.isValidString(str) && AppLovinSdkUtils.isValidString(str2)) {
            a("ex_" + str, str2);
        }
    }

    public void setBirthYear(int i) {
        if (i < 9999 && i > 1900) {
            a("yob", Integer.toString(i));
        }
    }

    public void setCarrier(String str) {
        this.a.getLogger().userError("AppLovinTargetingDataImpl", "Explicitly setting `carrier` targeting data is deprecated.");
    }

    public void setCountry(String str) {
        this.a.getLogger().userError("AppLovinTargetingDataImpl", "Explicitly setting `country code` targeting data is deprecated.");
    }

    public void setGender(char c) {
        String str = c == AppLovinTargetingData.GENDER_MALE ? "m" : c == AppLovinTargetingData.GENDER_FEMALE ? "f" : "u";
        a("gender", str);
    }

    public void setInterests(String... strArr) {
        if (strArr != null && strArr.length > 0) {
            a("interests", a(strArr));
        }
    }

    public void setKeywords(String... strArr) {
        if (strArr != null && strArr.length > 0) {
            a("keywords", a(strArr));
        }
    }

    public void setLanguage(String str) {
        if (AppLovinSdkUtils.isValidString(str)) {
            a("language", str.toLowerCase(Locale.ENGLISH));
        }
    }

    public void setLocation(Location location) {
        this.a.getLogger().userError("AppLovinTargetingDataImpl", "Explicitly setting `location` targeting data is deprecated.");
    }
}
