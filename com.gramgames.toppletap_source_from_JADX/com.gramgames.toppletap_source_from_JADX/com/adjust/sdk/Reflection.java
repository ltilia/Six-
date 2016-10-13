package com.adjust.sdk;

import android.content.Context;
import com.adjust.sdk.plugin.Plugin;
import com.mopub.common.GpsHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Reflection {
    public static String getPlayAdId(Context context) {
        try {
            return (String) invokeInstanceMethod(getAdvertisingInfoObject(context), "getId", null, new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Boolean isPlayTrackingEnabled(Context context) {
        Boolean bool = null;
        boolean z = false;
        try {
            if (!((Boolean) invokeInstanceMethod(getAdvertisingInfoObject(context), GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, null, new Object[0])).booleanValue()) {
                z = true;
            }
            bool = Boolean.valueOf(z);
        } catch (Throwable th) {
        }
        return bool;
    }

    public static String getMacAddress(Context context) {
        try {
            return (String) invokeStaticMethod("com.adjust.sdk.plugin.MacAddressUtil", "getMacAddress", new Class[]{Context.class}, context);
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getAndroidId(Context context) {
        try {
            return (String) invokeStaticMethod("com.adjust.sdk.plugin.AndroidIdUtil", "getAndroidId", new Class[]{Context.class}, context);
        } catch (Throwable th) {
            return null;
        }
    }

    private static Object getAdvertisingInfoObject(Context context) throws Exception {
        return invokeStaticMethod("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", new Class[]{Context.class}, context);
    }

    private static boolean isConnectionResultSuccess(Integer statusCode) {
        if (statusCode == null) {
            return false;
        }
        try {
            if (Class.forName("com.google.android.gms.common.ConnectionResult").getField("SUCCESS").getInt(null) == statusCode.intValue()) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public static Class forName(String className) {
        try {
            return Class.forName(className);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Object createDefaultInstance(String className) {
        return createDefaultInstance(forName(className));
    }

    public static Object createDefaultInstance(Class classObject) {
        try {
            return classObject.newInstance();
        } catch (Throwable th) {
            return null;
        }
    }

    public static Object createInstance(String className, Class[] cArgs, Object... args) {
        try {
            return Class.forName(className).getConstructor(cArgs).newInstance(args);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Object invokeStaticMethod(String className, String methodName, Class[] cArgs, Object... args) throws Exception {
        return invokeMethod(Class.forName(className), methodName, null, cArgs, args);
    }

    public static Object invokeInstanceMethod(Object instance, String methodName, Class[] cArgs, Object... args) throws Exception {
        return invokeMethod(instance.getClass(), methodName, instance, cArgs, args);
    }

    public static Object invokeMethod(Class classObject, String methodName, Object instance, Class[] cArgs, Object... args) throws Exception {
        return classObject.getMethod(methodName, cArgs).invoke(instance, args);
    }

    public static Map<String, String> getPluginKeys(Context context) {
        Map<String, String> pluginKeys = new HashMap();
        for (Plugin plugin : getPlugins()) {
            Entry<String, String> pluginEntry = plugin.getParameter(context);
            if (pluginEntry != null) {
                pluginKeys.put(pluginEntry.getKey(), pluginEntry.getValue());
            }
        }
        if (pluginKeys.size() == 0) {
            return null;
        }
        return pluginKeys;
    }

    private static List<Plugin> getPlugins() {
        List<Plugin> plugins = new ArrayList(Constants.PLUGINS.size());
        for (String pluginName : Constants.PLUGINS) {
            Object pluginObject = createDefaultInstance(pluginName);
            if (pluginObject != null && (pluginObject instanceof Plugin)) {
                plugins.add((Plugin) pluginObject);
            }
        }
        return plugins;
    }
}
