package com.vungle.publisher.inject;

import android.content.Context;
import com.vungle.publisher.fa;
import com.vungle.publisher.fc;
import com.vungle.publisher.fi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CoreModule_ProvideOldAdTempDirectoryFactory implements Factory<String> {
    static final /* synthetic */ boolean a;
    private final fi b;
    private final Provider<Context> c;

    static {
        a = !CoreModule_ProvideOldAdTempDirectoryFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideOldAdTempDirectoryFactory(fi module, Provider<Context> contextProvider) {
        if (a || module != null) {
            this.b = module;
            if (a || contextProvider != null) {
                this.c = contextProvider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public final String get() {
        if (((Context) this.c.get()).getExternalCacheDir() == null) {
            throw new fa();
        }
        return (String) Preconditions.checkNotNull(fc.a(((Context) this.c.get()).getExternalCacheDir(), ".VungleCacheDir"), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<String> create(fi module, Provider<Context> contextProvider) {
        return new CoreModule_ProvideOldAdTempDirectoryFactory(module, contextProvider);
    }
}
