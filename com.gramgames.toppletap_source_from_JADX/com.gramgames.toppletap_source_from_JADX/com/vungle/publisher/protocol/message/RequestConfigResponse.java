package com.vungle.publisher.protocol.message;

import com.vungle.publisher.fm;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class RequestConfigResponse extends BaseJsonObject {
    public Boolean a;
    public Integer b;
    public Integer c;
    public a d;
    public Boolean e;
    public Long f;
    public Long g;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends JsonDeserializationFactory<RequestConfigResponse> {
        protected final /* synthetic */ Object a() {
            return new RequestConfigResponse();
        }

        protected final /* synthetic */ Object a(JSONObject jSONObject) throws JSONException {
            Object obj = null;
            if (jSONObject == null) {
                return null;
            }
            RequestConfigResponse requestConfigResponse = new RequestConfigResponse();
            requestConfigResponse.a = fm.a(jSONObject, "optIn");
            requestConfigResponse.b = fm.c(jSONObject, "updateDelay");
            requestConfigResponse.c = fm.c(jSONObject, "threshold");
            Class cls = a.class;
            String e = fm.e(jSONObject, "connection");
            if (e != null) {
                obj = Enum.valueOf(cls, e);
            }
            requestConfigResponse.d = (a) obj;
            requestConfigResponse.e = fm.a(jSONObject, "exceptionReportingEnabled");
            requestConfigResponse.f = fm.d(jSONObject, "last_app_fingerprint_timestamp");
            requestConfigResponse.g = fm.d(jSONObject, "app_fingerprint_frequency");
            return requestConfigResponse;
        }

        @Inject
        Factory() {
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

    public enum a {
        all,
        wifi
    }
}
