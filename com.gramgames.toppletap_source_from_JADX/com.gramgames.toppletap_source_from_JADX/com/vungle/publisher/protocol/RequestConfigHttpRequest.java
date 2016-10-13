package com.vungle.publisher.protocol;

import com.vungle.publisher.fl;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import com.vungle.publisher.protocol.message.RequestConfig;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class RequestConfigHttpRequest extends ProtocolHttpRequest {
    boolean e;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<RequestConfigHttpRequest> {
        @Inject
        public RequestConfigHttpRequest g;
        @Inject
        public RequestConfig h;

        public final /* synthetic */ ProtocolHttpRequest a() {
            return d();
        }

        protected final /* bridge */ /* synthetic */ HttpRequest b() {
            return this.g;
        }

        public final /* synthetic */ HttpRequest c() {
            return d();
        }

        @Inject
        Factory() {
        }

        public final RequestConfigHttpRequest d() throws fl {
            try {
                if (this.g.e) {
                    return this.g;
                }
                RequestConfigHttpRequest requestConfigHttpRequest = (RequestConfigHttpRequest) super.a();
                requestConfigHttpRequest.b = this.d + "config";
                requestConfigHttpRequest.c.putString("Content-Type", WebRequest.CONTENT_TYPE_JSON);
                requestConfigHttpRequest.d = this.h.c();
                return requestConfigHttpRequest;
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
    RequestConfigHttpRequest() {
    }

    protected final HttpRequest.a b() {
        return HttpRequest.a.GET;
    }

    protected final b a() {
        return b.requestConfig;
    }
}
