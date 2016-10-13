package com.facebook.ads.internal.server;

import com.facebook.ads.internal.dto.a;
import com.facebook.ads.internal.dto.c;
import com.facebook.ads.internal.dto.d;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.Yytoken;

public class b {
    private static b a;

    static {
        a = new b();
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            bVar = a;
        }
        return bVar;
    }

    private d a(JSONObject jSONObject) {
        int i = 0;
        JSONObject jSONObject2 = jSONObject.getJSONArray("placements").getJSONObject(0);
        d a = d.a(jSONObject2.getJSONObject("definition"));
        c cVar = new c(a);
        AdPlacementType a2 = a.a();
        if (jSONObject2.has("ads")) {
            JSONArray jSONArray = jSONObject2.getJSONArray("ads");
            while (i < jSONArray.length()) {
                a aVar = new a(a2);
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                aVar.a(jSONObject3.getString("adapter"));
                JSONObject jSONObject4 = jSONObject3.getJSONObject(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY);
                JSONArray optJSONArray = jSONObject3.optJSONArray("trackers");
                if (optJSONArray != null) {
                    jSONObject4.put("trackers", optJSONArray);
                }
                aVar.a(jSONObject4);
                cVar.a(aVar);
                i++;
            }
        }
        return new d(cVar);
    }

    private e b(JSONObject jSONObject) {
        try {
            return new e(jSONObject.optString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, BuildConfig.FLAVOR), new c(d.a(jSONObject.getJSONArray("placements").getJSONObject(0).getJSONObject("definition"))));
        } catch (JSONException e) {
            return c(jSONObject);
        }
    }

    private e c(JSONObject jSONObject) {
        return new e(jSONObject.optString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, BuildConfig.FLAVOR), null);
    }

    public c a(String str) {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
        Object obj = -1;
        switch (optString.hashCode()) {
            case 96432:
                if (optString.equals("ads")) {
                    obj = null;
                    break;
                }
                break;
            case 96784904:
                if (optString.equals(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE)) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case Yylex.YYINITIAL /*0*/:
                return a(jSONObject);
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return b(jSONObject);
            default:
                JSONObject optJSONObject = jSONObject.optJSONObject(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE);
                return optJSONObject != null ? c(optJSONObject) : new c(c.a.UNKNOWN, null);
        }
    }
}
