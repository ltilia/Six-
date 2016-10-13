package com.vungle.publisher.net.http;

import com.vungle.publisher.net.http.HttpRequest.a;
import com.vungle.publisher.net.http.HttpRequest.b;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class DownloadHttpRequest extends HttpRequest {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.net.http.HttpRequest.Factory<DownloadHttpRequest> {
        protected final /* synthetic */ HttpRequest b() {
            return new DownloadHttpRequest();
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

    DownloadHttpRequest() {
    }

    protected final a b() {
        return a.GET;
    }

    protected final b a() {
        return b.download;
    }

    public final String toString() {
        return "{" + b.download + ": " + this.b + "}";
    }
}
