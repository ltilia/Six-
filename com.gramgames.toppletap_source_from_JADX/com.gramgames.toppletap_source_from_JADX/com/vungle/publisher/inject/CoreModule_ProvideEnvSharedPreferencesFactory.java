package com.vungle.publisher.inject;

import android.content.Context;
import android.content.SharedPreferences;
import com.vungle.publisher.fi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CoreModule_ProvideEnvSharedPreferencesFactory implements Factory<SharedPreferences> {
    static final /* synthetic */ boolean a;
    private final fi b;
    private final Provider<Context> c;

    static {
        a = !CoreModule_ProvideEnvSharedPreferencesFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideEnvSharedPreferencesFactory(fi module, Provider<Context> contextProvider) {
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

    public final SharedPreferences get() {
        return (SharedPreferences) Preconditions.checkNotNull(((Context) this.c.get()).getSharedPreferences("VUNGLE_PUB_APP_INFO", 0), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SharedPreferences> create(fi module, Provider<Context> contextProvider) {
        return new CoreModule_ProvideEnvSharedPreferencesFactory(module, contextProvider);
    }
}
