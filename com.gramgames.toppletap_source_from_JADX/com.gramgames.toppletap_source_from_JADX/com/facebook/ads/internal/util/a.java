package com.facebook.ads.internal.util;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class a {
    private static SensorManager a;
    private static Sensor b;
    private static Sensor c;
    private static volatile float[] d;
    private static volatile float[] e;
    private static Map<String, Object> f;
    private static String[] g;

    static {
        a = null;
        b = null;
        c = null;
        f = new ConcurrentHashMap();
        g = new String[]{"x", "y", "z"};
    }

    public static Map<String, Object> a() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.putAll(f);
        a(hashMap);
        return hashMap;
    }

    private static void a(Map<String, Object> map) {
        int i;
        int i2 = 0;
        float[] fArr = d;
        float[] fArr2 = e;
        if (fArr != null) {
            int min = Math.min(g.length, fArr.length);
            for (i = 0; i < min; i++) {
                map.put("accelerometer_" + g[i], Float.valueOf(fArr[i]));
            }
        }
        if (fArr2 != null) {
            i = Math.min(g.length, fArr2.length);
            while (i2 < i) {
                map.put("rotation_" + g[i2], Float.valueOf(fArr2[i2]));
                i2++;
            }
        }
    }
}
