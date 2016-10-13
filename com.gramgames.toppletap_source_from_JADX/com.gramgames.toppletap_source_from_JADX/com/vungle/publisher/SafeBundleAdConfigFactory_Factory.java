package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class SafeBundleAdConfigFactory_Factory implements Factory<SafeBundleAdConfigFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<SafeBundleAdConfigFactory> b;

    static {
        a = !SafeBundleAdConfigFactory_Factory.class.desiredAssertionStatus();
    }

    public SafeBundleAdConfigFactory_Factory(MembersInjector<SafeBundleAdConfigFactory> safeBundleAdConfigFactoryMembersInjector) {
        if (a || safeBundleAdConfigFactoryMembersInjector != null) {
            this.b = safeBundleAdConfigFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final SafeBundleAdConfigFactory get() {
        return (SafeBundleAdConfigFactory) MembersInjectors.injectMembers(this.b, new SafeBundleAdConfigFactory());
    }

    public static Factory<SafeBundleAdConfigFactory> create(MembersInjector<SafeBundleAdConfigFactory> safeBundleAdConfigFactoryMembersInjector) {
        return new SafeBundleAdConfigFactory_Factory(safeBundleAdConfigFactoryMembersInjector);
    }
}
