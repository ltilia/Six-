package com.facebook.ads.internal.util;

import gs.gram.mopub.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class c {
    private static final List<b> a;

    static {
        a = new ArrayList();
    }

    public static String a() {
        synchronized (a) {
            if (a.isEmpty()) {
                String str = BuildConfig.FLAVOR;
                return str;
            }
            List<b> arrayList = new ArrayList(a);
            a.clear();
            JSONArray jSONArray = new JSONArray();
            for (b a : arrayList) {
                jSONArray.put(a.a());
            }
            return jSONArray.toString();
        }
    }

    public static void a(b bVar) {
        synchronized (a) {
            a.add(bVar);
        }
    }
}
