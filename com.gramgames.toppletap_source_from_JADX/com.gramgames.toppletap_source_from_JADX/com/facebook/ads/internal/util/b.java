package com.facebook.ads.internal.util;

import com.facebook.internal.NativeProtocol;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    public static String a;
    private String b;
    private Map<String, Object> c;
    private int d;
    private String e;

    public enum a {
        OPEN_STORE(0),
        OPEN_LINK(1),
        XOUT(2),
        OPEN_URL(3),
        SHOW_INTERSTITIAL(4);
        
        int f;

        private a(int i) {
            this.f = i;
        }
    }

    static {
        a = null;
    }

    public b(String str, Map<String, Object> map, int i, String str2) {
        this.b = str;
        this.c = map;
        this.d = i;
        this.e = str2;
    }

    public static b a(long j, a aVar, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        Map hashMap = new HashMap();
        hashMap.put("Time", String.valueOf(currentTimeMillis - j));
        hashMap.put("AdAction", String.valueOf(aVar.f));
        return new b("bounceback", hashMap, (int) (currentTimeMillis / 1000), str);
    }

    public static b a(Throwable th, String str) {
        Map hashMap = new HashMap();
        hashMap.put("ex", th.getClass().getSimpleName());
        hashMap.put("ex_msg", th.getMessage());
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        String str2 = NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE;
        if (str == null) {
            str = a;
        }
        return new b(str2, hashMap, currentTimeMillis, str);
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY, this.b);
            jSONObject.put(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY, new JSONObject(this.c));
            jSONObject.put("time", this.d);
            jSONObject.put("request_id", this.e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
