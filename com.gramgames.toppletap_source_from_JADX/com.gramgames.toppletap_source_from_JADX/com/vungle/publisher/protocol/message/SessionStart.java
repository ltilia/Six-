package com.vungle.publisher.protocol.message;

import com.vungle.publisher.ek;
import com.vungle.publisher.em;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class SessionStart extends BaseJsonObject {
    String a;
    String b;
    String c;
    String d;
    Long e;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public ek a;
        @Inject
        public em b;

        @Inject
        Factory() {
        }

        public final SessionStart a(long j) {
            SessionStart sessionStart = new SessionStart();
            sessionStart.a = this.a.a();
            sessionStart.b = this.a.c();
            sessionStart.c = this.a.j();
            sessionStart.d = this.b.b();
            sessionStart.e = Long.valueOf(j);
            return sessionStart;
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

    SessionStart() {
    }

    public final JSONObject a() throws JSONException {
        JSONObject a = super.a();
        a.putOpt("ifa", this.a);
        a.putOpt("isu", this.b);
        a.putOpt("mac", this.c);
        BaseJsonSerializable.a("pubAppId", this.d);
        a.put("pubAppId", this.d);
        BaseJsonSerializable.a(TtmlNode.START, this.e);
        a.put(TtmlNode.START, this.e);
        return a;
    }
}
