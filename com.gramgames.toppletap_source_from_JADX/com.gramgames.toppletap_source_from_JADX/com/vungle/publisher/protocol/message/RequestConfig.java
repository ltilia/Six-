package com.vungle.publisher.protocol.message;

import com.vungle.publisher.ek;
import com.vungle.publisher.em;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class RequestConfig extends BaseJsonObject {
    @Inject
    public ek a;
    @Inject
    public em b;

    public final /* synthetic */ Object b() throws JSONException {
        return a();
    }

    @Inject
    RequestConfig() {
    }

    public final JSONObject a() throws JSONException {
        JSONObject a = super.a();
        a.putOpt("pubAppId", this.b.b());
        a.putOpt("ifa", this.a.a());
        a.putOpt("isu", this.a.c());
        return a;
    }
}
