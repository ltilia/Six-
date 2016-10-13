package com.vungle.publisher.protocol;

import com.vungle.publisher.ad.SafeBundleAdConfig;
import com.vungle.publisher.fl;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class RequestStreamingAdHttpRequest extends ProtocolHttpRequest {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<RequestStreamingAdHttpRequest> {
        @Inject
        public com.vungle.publisher.protocol.message.RequestStreamingAd.Factory g;

        protected final /* synthetic */ HttpRequest b() {
            return new RequestStreamingAdHttpRequest();
        }

        @Inject
        Factory() {
        }

        public final RequestStreamingAdHttpRequest a(String str, SafeBundleAdConfig safeBundleAdConfig) throws fl {
            try {
                RequestStreamingAdHttpRequest requestStreamingAdHttpRequest = (RequestStreamingAdHttpRequest) super.a();
                requestStreamingAdHttpRequest.b = this.d + "requestStreamingAd";
                requestStreamingAdHttpRequest.c.putString("Content-Type", WebRequest.CONTENT_TYPE_JSON);
                requestStreamingAdHttpRequest.d = this.g.a(str, safeBundleAdConfig).c();
                return requestStreamingAdHttpRequest;
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
    RequestStreamingAdHttpRequest() {
    }

    protected final b a() {
        return b.requestStreamingAd;
    }

    protected final HttpRequest.a b() {
        return HttpRequest.a.GET;
    }
}
