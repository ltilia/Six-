package com.vungle.publisher.net.http;

import com.vungle.publisher.device.data.AppFingerprint;
import com.vungle.publisher.net.http.HttpRequest.b;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONException;

/* compiled from: vungle */
public final class AppFingerprintHttpRequest extends IngestHttpRequest {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.net.http.IngestHttpRequest.Factory<AppFingerprintHttpRequest> {
        @Inject
        public Provider<AppFingerprintHttpRequest> a;

        protected final /* synthetic */ HttpRequest b() {
            return (AppFingerprintHttpRequest) this.a.get();
        }

        @Inject
        Factory() {
        }

        public final AppFingerprintHttpRequest a(AppFingerprint appFingerprint) throws JSONException {
            AppFingerprintHttpRequest appFingerprintHttpRequest = (AppFingerprintHttpRequest) d();
            appFingerprintHttpRequest.d = appFingerprint.c();
            return appFingerprintHttpRequest;
        }

        protected final String a() {
            return "installedApps";
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
    AppFingerprintHttpRequest() {
    }

    protected final b a() {
        return b.appFingerprint;
    }
}
