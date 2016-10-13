package com.vungle.publisher.inject;

import com.vungle.publisher.fi;
import com.vungle.publisher.fo;
import com.vungle.publisher.fp;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CoreModule_ProvideGoogleAggregateDetailedLocationProviderFactory implements Factory<fo> {
    static final /* synthetic */ boolean a;
    private final fi b;
    private final Provider<fp> c;

    static {
        a = !CoreModule_ProvideGoogleAggregateDetailedLocationProviderFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideGoogleAggregateDetailedLocationProviderFactory(fi module, Provider<fp> googleAggregateDetailedLocationProvider) {
        if (a || module != null) {
            this.b = module;
            if (a || googleAggregateDetailedLocationProvider != null) {
                this.c = googleAggregateDetailedLocationProvider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public final fo get() {
        return (fo) Preconditions.checkNotNull((fp) this.c.get(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<fo> create(fi module, Provider<fp> googleAggregateDetailedLocationProvider) {
        return new CoreModule_ProvideGoogleAggregateDetailedLocationProviderFactory(module, googleAggregateDetailedLocationProvider);
    }
}
