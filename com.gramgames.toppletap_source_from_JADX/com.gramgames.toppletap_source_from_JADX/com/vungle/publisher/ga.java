package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.net.http.AppFingerprintHttpResponseHandler;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ga implements MembersInjector<AppFingerprintHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;
    private final Provider<Factory> c;
    private final Provider<SdkConfig> d;

    static {
        a = !ga.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AppFingerprintHttpResponseHandler appFingerprintHttpResponseHandler = (AppFingerprintHttpResponseHandler) obj;
        if (appFingerprintHttpResponseHandler == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        appFingerprintHttpResponseHandler.f = (ScheduledPriorityExecutor) this.b.get();
        appFingerprintHttpResponseHandler.g = (Factory) this.c.get();
        appFingerprintHttpResponseHandler.a = (SdkConfig) this.d.get();
    }

    private ga(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<SdkConfig> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<AppFingerprintHttpResponseHandler> a(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<SdkConfig> provider3) {
        return new ga(provider, provider2, provider3);
    }
}
