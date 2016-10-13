package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

/* compiled from: vungle */
public final class SessionStartHttpRequest extends ProtocolHttpRequest {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<SessionStartHttpRequest> {
        @Inject
        public com.vungle.publisher.protocol.message.SessionStart.Factory g;

        protected final /* synthetic */ HttpRequest b() {
            return new SessionStartHttpRequest();
        }

        @Inject
        Factory() {
        }

        protected final SessionStartHttpRequest a(long j) throws JSONException {
            SessionStartHttpRequest sessionStartHttpRequest = (SessionStartHttpRequest) a();
            sessionStartHttpRequest.c.putString("Content-Type", WebRequest.CONTENT_TYPE_JSON);
            sessionStartHttpRequest.b = this.d + "sessionStart";
            sessionStartHttpRequest.d = this.g.a(j).c();
            return sessionStartHttpRequest;
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

    protected SessionStartHttpRequest() {
    }

    protected final b a() {
        return b.sessionStart;
    }

    protected final HttpRequest.a b() {
        return HttpRequest.a.POST;
    }
}
