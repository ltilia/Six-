package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class SafeBundleAdConfigFactory_MembersInjector implements MembersInjector<SafeBundleAdConfigFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<AdConfig> b;

    static {
        a = !SafeBundleAdConfigFactory_MembersInjector.class.desiredAssertionStatus();
    }

    public SafeBundleAdConfigFactory_MembersInjector(Provider<AdConfig> globalAdConfigProvider) {
        if (a || globalAdConfigProvider != null) {
            this.b = globalAdConfigProvider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<SafeBundleAdConfigFactory> create(Provider<AdConfig> globalAdConfigProvider) {
        return new SafeBundleAdConfigFactory_MembersInjector(globalAdConfigProvider);
    }

    public final void injectMembers(SafeBundleAdConfigFactory instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.a = (AdConfig) this.b.get();
    }

    public static void injectGlobalAdConfig(SafeBundleAdConfigFactory instance, Provider<AdConfig> globalAdConfigProvider) {
        instance.a = (AdConfig) globalAdConfigProvider.get();
    }
}
