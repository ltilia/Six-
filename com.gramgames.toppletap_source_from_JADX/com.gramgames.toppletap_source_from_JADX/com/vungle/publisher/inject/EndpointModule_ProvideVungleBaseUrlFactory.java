package com.vungle.publisher.inject;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: vungle */
public final class EndpointModule_ProvideVungleBaseUrlFactory implements Factory<String> {
    static final /* synthetic */ boolean a;
    private final EndpointModule b;

    static {
        a = !EndpointModule_ProvideVungleBaseUrlFactory.class.desiredAssertionStatus();
    }

    public EndpointModule_ProvideVungleBaseUrlFactory(EndpointModule module) {
        if (a || module != null) {
            this.b = module;
            return;
        }
        throw new AssertionError();
    }

    public final String get() {
        return (String) Preconditions.checkNotNull(this.b.a, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<String> create(EndpointModule module) {
        return new EndpointModule_ProvideVungleBaseUrlFactory(module);
    }
}
