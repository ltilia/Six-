package com.vungle.publisher.protocol.message;

import com.vungle.publisher.db.model.AdReportExtra;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class ExtraInfo extends BaseJsonObject {
    protected Map<String, String> a;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends MessageFactory<ExtraInfo> {
        protected final /* synthetic */ Object a() {
            return new ExtraInfo();
        }

        @Inject
        Factory() {
        }

        protected static ExtraInfo a(Map<String, String> map) {
            if (map == null) {
                return null;
            }
            ExtraInfo extraInfo = new ExtraInfo();
            extraInfo.a = new HashMap(map);
            return extraInfo;
        }

        protected static ExtraInfo b(Map<String, AdReportExtra> map) {
            if (map == null) {
                return null;
            }
            ExtraInfo extraInfo = new ExtraInfo();
            Map hashMap = new HashMap();
            extraInfo.a = hashMap;
            for (AdReportExtra adReportExtra : map.values()) {
                hashMap.put(adReportExtra.b, adReportExtra.c);
            }
            return extraInfo;
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

    public final /* synthetic */ Object b() throws JSONException {
        return a();
    }

    ExtraInfo() {
    }

    public final JSONObject a() throws JSONException {
        return (this.a == null || this.a.isEmpty()) ? null : new JSONObject(this.a);
    }
}
