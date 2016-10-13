package com.vungle.publisher.net.http;

import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.EventTracking.a;
import com.vungle.publisher.net.http.HttpRequest.b;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class TrackEventHttpRequest extends HttpRequest {
    Ad<?, ?, ?> e;
    a f;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.net.http.HttpRequest.Factory<TrackEventHttpRequest> {
        protected final /* synthetic */ HttpRequest b() {
            return new TrackEventHttpRequest();
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

    @Inject
    TrackEventHttpRequest() {
    }

    protected final b a() {
        return b.trackEvent;
    }

    protected final HttpRequest.a b() {
        return HttpRequest.a.GET;
    }

    public final String toString() {
        return "{" + b.trackEvent + ": " + this.b + "}";
    }
}
