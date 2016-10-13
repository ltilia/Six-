package com.vungle.publisher.protocol.message;

import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class SessionEnd extends BaseJsonObject {
    public Long a;
    public SessionStart b;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public com.vungle.publisher.protocol.message.SessionStart.Factory a;

        @Inject
        Factory() {
        }

        public static SessionEnd a() {
            return new SessionEnd();
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

    @Inject
    SessionEnd() {
    }

    public final JSONObject a() throws JSONException {
        JSONObject a = this.b == null ? super.a() : this.b.a();
        BaseJsonSerializable.a(TtmlNode.END, this.a);
        a.put(TtmlNode.END, this.a);
        return a;
    }
}
