package com.vungle.publisher.protocol.message;

import com.vungle.log.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public abstract class JsonDeserializationFactory<T> extends MessageFactory<T> {
    protected abstract T a(JSONObject jSONObject) throws JSONException;

    JsonDeserializationFactory() {
    }

    public final T a(String str) throws JSONException {
        return str == null ? null : a(new JSONObject(str));
    }

    protected final T[] a(JSONArray jSONArray) throws JSONException {
        T[] tArr = null;
        if (jSONArray != null) {
            int length = jSONArray.length();
            tArr = a(length);
            for (int i = 0; i < length; i++) {
                tArr[i] = a(jSONArray.optJSONObject(i));
            }
        }
        return tArr;
    }

    protected static void a(JSONObject jSONObject, String str, Object obj) {
        if (obj == null) {
            Object opt = jSONObject.opt(str);
            if (opt == null) {
                Logger.d(Logger.PROTOCOL_TAG, "null " + str + " is required input");
            } else {
                Logger.d(Logger.PROTOCOL_TAG, "invalid " + str + ": " + opt);
            }
        } else if ((obj instanceof String) && ((String) obj).length() == 0) {
            Logger.d(Logger.PROTOCOL_TAG, "empty " + str + " is required input");
        } else if ((obj instanceof JSONArray) && ((JSONArray) obj).length() == 0) {
            Logger.d(Logger.PROTOCOL_TAG, "empty array " + str + " is required input");
        }
    }
}
