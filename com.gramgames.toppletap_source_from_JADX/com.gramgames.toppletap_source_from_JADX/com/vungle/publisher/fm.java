package com.vungle.publisher;

import com.vungle.publisher.protocol.message.BaseJsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class fm {
    public static Boolean a(JSONObject jSONObject, String str) {
        boolean optBoolean = jSONObject.optBoolean(str, false);
        if (optBoolean) {
            return Boolean.valueOf(optBoolean);
        }
        optBoolean = jSONObject.optBoolean(str, true);
        if (!optBoolean) {
            return Boolean.valueOf(optBoolean);
        }
        return null;
    }

    public static Float b(JSONObject jSONObject, String str) {
        Double valueOf;
        double optDouble = jSONObject.optDouble(str);
        if (optDouble == Double.NaN) {
            optDouble = jSONObject.optDouble(str, -1.0d);
            valueOf = optDouble != -1.0d ? Double.valueOf(optDouble) : null;
        } else {
            valueOf = Double.valueOf(optDouble);
        }
        if (valueOf == null) {
            return null;
        }
        return Float.valueOf(valueOf.floatValue());
    }

    public static Integer c(JSONObject jSONObject, String str) {
        int optInt = jSONObject.optInt(str, -1);
        if (optInt != -1) {
            return Integer.valueOf(optInt);
        }
        optInt = jSONObject.optInt(str, -2);
        if (optInt != -2) {
            return Integer.valueOf(optInt);
        }
        return null;
    }

    public static Long d(JSONObject jSONObject, String str) {
        long optLong = jSONObject.optLong(str, -1);
        if (optLong != -1) {
            return Long.valueOf(optLong);
        }
        optLong = jSONObject.optLong(str, -2);
        if (optLong != -2) {
            return Long.valueOf(optLong);
        }
        return null;
    }

    public static String e(JSONObject jSONObject, String str) {
        return jSONObject.isNull(str) ? null : jSONObject.optString(str, null);
    }

    public static String[] f(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        int length = optJSONArray.length();
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = optJSONArray.optString(i, null);
        }
        return strArr;
    }

    public static <T extends BaseJsonObject> JSONArray a(T... tArr) throws JSONException {
        JSONArray jSONArray = null;
        if (tArr != null) {
            jSONArray = new JSONArray();
            for (BaseJsonObject a : tArr) {
                jSONArray.put(a(a));
            }
        }
        return jSONArray;
    }

    public static JSONObject a(BaseJsonObject baseJsonObject) throws JSONException {
        if (baseJsonObject != null) {
            return baseJsonObject.a();
        }
        return null;
    }
}
