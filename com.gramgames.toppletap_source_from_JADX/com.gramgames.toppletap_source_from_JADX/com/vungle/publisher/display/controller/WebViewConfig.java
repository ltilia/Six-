package com.vungle.publisher.display.controller;

import com.vungle.publisher.protocol.message.BaseJsonObject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class WebViewConfig extends BaseJsonObject {
    public final /* synthetic */ Object b() throws JSONException {
        return a();
    }

    public final JSONObject a() throws JSONException {
        JSONObject a = super.a();
        a.put("privacyPolicyEnabled", true);
        return a;
    }
}
