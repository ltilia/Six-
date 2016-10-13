package com.vungle.publisher.inject;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: vungle */
public final class EndpointModule_ProvideIngestBaseUrlFactory implements Factory<String> {
    static final /* synthetic */ boolean a;
    private final EndpointModule b;

    static {
        a = !EndpointModule_ProvideIngestBaseUrlFactory.class.desiredAssertionStatus();
    }

    public EndpointModule_ProvideIngestBaseUrlFactory(EndpointModule module) {
        if (a || module != null) {
            this.b = module;
            return;
        }
        throw new AssertionError();
    }

    public final String get() {
        return (String) Preconditions.checkNotNull(this.b.b, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<String> create(EndpointModule module) {
        return new EndpointModule_ProvideIngestBaseUrlFactory(module);
    }
}
