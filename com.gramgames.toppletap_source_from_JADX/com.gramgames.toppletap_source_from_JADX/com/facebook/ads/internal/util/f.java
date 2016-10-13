package com.facebook.ads.internal.util;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.facebook.ads.internal.adapters.a;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;

public class f {
    public static Collection<String> a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        Set hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.optString(i));
        }
        return hashSet;
    }

    public static boolean a(Context context, a aVar) {
        e a = aVar.a();
        if (a == null || a == e.NONE) {
            return false;
        }
        Collection<String> c = aVar.c();
        if (c == null || c.isEmpty()) {
            return false;
        }
        for (String a2 : c) {
            if (a(context, a2)) {
                int i = 1;
                break;
            }
        }
        boolean z = false;
        if (a == e.INSTALLED) {
            int i2 = 1;
        } else {
            boolean z2 = false;
        }
        if (i != i2) {
            return false;
        }
        if (s.a(aVar.b())) {
            return true;
        }
        new o().execute(new String[]{a2});
        return false;
    }

    public static boolean a(Context context, String str) {
        if (s.a(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
