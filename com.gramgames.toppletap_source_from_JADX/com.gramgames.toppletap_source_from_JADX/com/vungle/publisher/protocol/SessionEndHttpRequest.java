package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import com.vungle.publisher.protocol.message.SessionEnd;
import com.vungle.publisher.protocol.message.SessionStart;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

/* compiled from: vungle */
public final class SessionEndHttpRequest extends ProtocolHttpRequest {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<SessionEndHttpRequest> {
        @Inject
        public com.vungle.publisher.protocol.message.SessionEnd.Factory g;

        protected final /* synthetic */ HttpRequest b() {
            return new SessionEndHttpRequest();
        }

        @Inject
        Factory() {
        }

        protected final SessionEndHttpRequest a(long j, long j2) throws JSONException {
            SessionEndHttpRequest sessionEndHttpRequest = (SessionEndHttpRequest) a();
            sessionEndHttpRequest.c.putString("Content-Type", WebRequest.CONTENT_TYPE_JSON);
            sessionEndHttpRequest.b = this.d + "sessionEnd";
            SessionStart a = this.g.a.a(j);
            SessionEnd a2 = com.vungle.publisher.protocol.message.SessionEnd.Factory.a();
            a2.b = a;
            a2.a = Long.valueOf(j2);
            sessionEndHttpRequest.d = a2.c();
            return sessionEndHttpRequest;
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

    protected SessionEndHttpRequest() {
    }

    protected final b a() {
        return b.sessionEnd;
    }

    protected final HttpRequest.a b() {
        return HttpRequest.a.POST;
    }
}
