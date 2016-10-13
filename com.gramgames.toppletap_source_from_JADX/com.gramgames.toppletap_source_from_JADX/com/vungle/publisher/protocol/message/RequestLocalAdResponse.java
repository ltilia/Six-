package com.vungle.publisher.protocol.message;

import com.vungle.publisher.fm;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class RequestLocalAdResponse extends RequestAdResponse {
    public Integer r;
    public Long s;
    public String t;
    public String u;
    public Integer v;
    public String w;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.protocol.message.RequestAdResponse.Factory<RequestLocalAdResponse> {
        protected final /* synthetic */ Object a() {
            return new RequestLocalAdResponse();
        }

        public final /* synthetic */ Object a(JSONObject jSONObject) throws JSONException {
            return c(jSONObject);
        }

        public final /* synthetic */ RequestAdResponse b(JSONObject jSONObject) throws JSONException {
            return c(jSONObject);
        }

        @Inject
        Factory() {
        }

        private RequestLocalAdResponse c(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            if (jSONObject.isNull("sleep")) {
                RequestLocalAdResponse requestLocalAdResponse = (RequestLocalAdResponse) super.b(jSONObject);
                Long d = fm.d(jSONObject, "expiry");
                requestLocalAdResponse.s = d;
                JsonDeserializationFactory.a(jSONObject, "expiry", d);
                requestLocalAdResponse.t = fm.e(jSONObject, "postBundle");
                requestLocalAdResponse.u = fm.e(jSONObject, "preBundle");
                requestLocalAdResponse.v = fm.c(jSONObject, "size");
                requestLocalAdResponse.w = jSONObject.optString("md5");
                JsonDeserializationFactory.a(jSONObject, "md5", requestLocalAdResponse.w);
                return requestLocalAdResponse;
            }
            requestLocalAdResponse = new RequestLocalAdResponse();
            requestLocalAdResponse.r = Integer.valueOf(jSONObject.getInt("sleep"));
            return requestLocalAdResponse;
        }
    }

    /* compiled from: vungle */
    public final class Factory_Factory implements dagger.internal.Factory<Factory> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<Factory> b;

        static {
            a = !Factory_Factory.class.desiredAssertionStatus();
        }

        public Factory_Factory(MembersInjector<Factory> factoryMembersInjector) {
            if (a || factoryMembersInjector != null) {
                this.b = factoryMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final Factory get() {
            return (Factory) MembersInjectors.injectMembers(this.b, new Factory());
        }

        public static dagger.internal.Factory<Factory> create(MembersInjector<Factory> factoryMembersInjector) {
            return new Factory_Factory(factoryMembersInjector);
        }
    }

    RequestLocalAdResponse() {
    }
}
