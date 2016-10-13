package com.vungle.publisher.protocol.message;

import com.vungle.log.Logger;
import org.json.JSONException;

/* compiled from: vungle */
public abstract class BaseJsonSerializable<T> {
    public abstract T b() throws JSONException;

    public final String c() throws JSONException {
        Object b = b();
        return b == null ? null : b.toString();
    }

    protected static void a(String str, Object obj) {
        if (obj == null) {
            Logger.d(Logger.PROTOCOL_TAG, "null " + str + " is required output");
        }
    }
}
