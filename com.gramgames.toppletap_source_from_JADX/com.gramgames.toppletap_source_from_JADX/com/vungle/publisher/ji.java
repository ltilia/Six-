package com.vungle.publisher;

import android.content.Context;
import com.vungle.log.Logger;
import java.util.ArrayList;

/* compiled from: vungle */
public final class ji {
    public static boolean a(Context context, ek ekVar) {
        if (ekVar.a(eh.KITKAT) || context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        return false;
    }

    public static String[] b(Context context, ek ekVar) {
        ArrayList arrayList = new ArrayList();
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
            arrayList.add("android.permission.INTERNET");
        }
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            arrayList.add("android.permission.ACCESS_NETWORK_STATE");
        }
        if (!a(context, ekVar)) {
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (arrayList.size() > 0) {
            Logger.w(Logger.VUNGLE_TAG, "Make sure to add <uses-permission> for \"" + jj.a(", ", arrayList) + "\" in your AndroidManifest.xml? AND request if revoked in the runtime, which is possible on Android Marshmallow (API 23) and above.");
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
