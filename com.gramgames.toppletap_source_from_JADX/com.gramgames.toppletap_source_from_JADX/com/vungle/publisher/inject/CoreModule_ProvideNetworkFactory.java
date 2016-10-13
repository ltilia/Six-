package com.vungle.publisher.inject;

import com.vungle.publisher.fi;
import com.vungle.publisher.fw;
import com.vungle.publisher.net.AndroidNetwork;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CoreModule_ProvideNetworkFactory implements Factory<fw> {
    static final /* synthetic */ boolean a;
    private final fi b;
    private final Provider<AndroidNetwork> c;

    static {
        a = !CoreModule_ProvideNetworkFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideNetworkFactory(fi module, Provider<AndroidNetwork> androidNetworkProvider) {
        if (a || module != null) {
            this.b = module;
            if (a || androidNetworkProvider != null) {
                this.c = androidNetworkProvider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public final fw get() {
        return (fw) Preconditions.checkNotNull((AndroidNetwork) this.c.get(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<fw> create(fi module, Provider<AndroidNetwork> androidNetworkProvider) {
        return new CoreModule_ProvideNetworkFactory(module, androidNetworkProvider);
    }
}
