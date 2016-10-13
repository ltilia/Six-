package com.vungle.publisher;

import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.exception.ExceptionManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ez implements MembersInjector<ExceptionManager> {
    static final /* synthetic */ boolean a;
    private final Provider<gu> b;
    private final Provider<SdkConfig> c;
    private final Provider<Factory> d;

    static {
        a = !ez.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ExceptionManager exceptionManager = (ExceptionManager) obj;
        if (exceptionManager == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        exceptionManager.a = (gu) this.b.get();
        exceptionManager.b = (SdkConfig) this.c.get();
        exceptionManager.c = (Factory) this.d.get();
    }

    private ez(Provider<gu> provider, Provider<SdkConfig> provider2, Provider<Factory> provider3) {
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

    public static MembersInjector<ExceptionManager> a(Provider<gu> provider, Provider<SdkConfig> provider2, Provider<Factory> provider3) {
        return new ez(provider, provider2, provider3);
    }
}
