package com.facebook.ads.internal.util;

import gs.gram.mopub.BuildConfig;
import java.util.Set;

public class t {
    public static String a(Set<String> set, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : set) {
            stringBuilder.append(append);
            stringBuilder.append(str);
        }
        return stringBuilder.length() > 0 ? stringBuilder.substring(0, stringBuilder.length() - 1) : BuildConfig.FLAVOR;
    }
}
