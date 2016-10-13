package com.vungle.publisher;

import com.vungle.log.Logger;

/* compiled from: vungle */
public final class je {
    public static String a(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class}).invoke(null, new Object[]{str});
        } catch (Throwable e) {
            Logger.w(Logger.CONFIG_TAG, "error getting Android system property " + str, e);
            return null;
        }
    }
}
