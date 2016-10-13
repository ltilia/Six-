package com.vungle.publisher.inject;

import com.vungle.publisher.fi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import gs.gram.mopub.BuildConfig;

/* compiled from: vungle */
public final class CoreModule_ProvideWrapperFrameworkVersionFactory implements Factory<String> {
    static final /* synthetic */ boolean a;
    private final fi b;

    static {
        a = !CoreModule_ProvideWrapperFrameworkVersionFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideWrapperFrameworkVersionFactory(fi module) {
        if (a || module != null) {
            this.b = module;
            return;
        }
        throw new AssertionError();
    }

    public final String get() {
        fi fiVar = this.b;
        return (String) Preconditions.checkNotNull(fiVar.f == null ? BuildConfig.FLAVOR : fiVar.f, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<String> create(fi module) {
        return new CoreModule_ProvideWrapperFrameworkVersionFactory(module);
    }
}
