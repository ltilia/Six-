package com.vungle.publisher.protocol;

import com.vungle.publisher.fl;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import com.vungle.publisher.protocol.message.RequestLocalAd;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class RequestLocalAdHttpRequest extends ProtocolHttpRequest {
    RequestLocalAd e;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<RequestLocalAdHttpRequest> {
        @Inject
        public com.vungle.publisher.protocol.message.RequestLocalAd.Factory g;

        public final /* synthetic */ ProtocolHttpRequest a() {
            return d();
        }

        protected final /* synthetic */ HttpRequest b() {
            return new RequestLocalAdHttpRequest();
        }

        public final /* synthetic */ HttpRequest c() {
            return d();
        }

        @Inject
        Factory() {
        }

        public final RequestLocalAdHttpRequest d() throws fl {
            try {
                RequestLocalAdHttpRequest requestLocalAdHttpRequest = (RequestLocalAdHttpRequest) super.a();
                requestLocalAdHttpRequest.b = this.d + "requestAd";
                requestLocalAdHttpRequest.c.putString("Content-Type", WebRequest.CONTENT_TYPE_JSON);
                RequestLocalAd c = this.g.c();
                requestLocalAdHttpRequest.e = c;
                requestLocalAdHttpRequest.d = c.c();
                return requestLocalAdHttpRequest;
            } catch (Throwable e) {
                throw new fl(e);
            }
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

    @Inject
    RequestLocalAdHttpRequest() {
    }

    protected final b a() {
        return b.requestLocalAd;
    }

    protected final HttpRequest.a b() {
        return HttpRequest.a.GET;
    }
}
