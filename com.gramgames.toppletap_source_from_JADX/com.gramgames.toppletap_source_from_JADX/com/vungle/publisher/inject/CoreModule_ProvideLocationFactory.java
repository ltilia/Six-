package com.vungle.publisher.inject;

import com.vungle.publisher.fi;
import com.vungle.publisher.ft;
import com.vungle.publisher.location.AndroidLocation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CoreModule_ProvideLocationFactory implements Factory<ft> {
    static final /* synthetic */ boolean a;
    private final fi b;
    private final Provider<AndroidLocation> c;

    static {
        a = !CoreModule_ProvideLocationFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideLocationFactory(fi module, Provider<AndroidLocation> androidLocationProvider) {
        if (a || module != null) {
            this.b = module;
            if (a || androidLocationProvider != null) {
                this.c = androidLocationProvider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public final ft get() {
        return (ft) Preconditions.checkNotNull((AndroidLocation) this.c.get(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ft> create(fi module, Provider<AndroidLocation> androidLocationProvider) {
        return new CoreModule_ProvideLocationFactory(module, androidLocationProvider);
    }
}
