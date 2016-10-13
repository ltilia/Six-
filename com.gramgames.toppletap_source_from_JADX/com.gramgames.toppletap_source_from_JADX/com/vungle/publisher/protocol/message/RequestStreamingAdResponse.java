package com.vungle.publisher.protocol.message;

import com.vungle.publisher.fm;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class RequestStreamingAdResponse extends RequestAdResponse {
    public Boolean r;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.protocol.message.RequestAdResponse.Factory<RequestStreamingAdResponse> {
        protected final /* synthetic */ Object a() {
            return new RequestStreamingAdResponse();
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

        private RequestStreamingAdResponse c(JSONObject jSONObject) throws JSONException {
            RequestStreamingAdResponse requestStreamingAdResponse = null;
            if (jSONObject != null) {
                Boolean a = fm.a(jSONObject, "shouldStream");
                if (Boolean.TRUE.equals(a)) {
                    requestStreamingAdResponse = (RequestStreamingAdResponse) super.b(jSONObject);
                } else {
                    requestStreamingAdResponse = new RequestStreamingAdResponse();
                }
                requestStreamingAdResponse.r = a;
            }
            return requestStreamingAdResponse;
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
}
