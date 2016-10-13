package com.vungle.publisher.inject;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.vungle.publisher.fk;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class IdStrategyModule_ProvideWifiManagerFactory implements Factory<WifiManager> {
    static final /* synthetic */ boolean a;
    private final fk b;
    private final Provider<Context> c;

    static {
        a = !IdStrategyModule_ProvideWifiManagerFactory.class.desiredAssertionStatus();
    }

    public IdStrategyModule_ProvideWifiManagerFactory(fk module, Provider<Context> contextProvider) {
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

    public final WifiManager get() {
        return (WifiManager) Preconditions.checkNotNull(this.b.a((Context) this.c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<WifiManager> create(fk module, Provider<Context> contextProvider) {
        return new IdStrategyModule_ProvideWifiManagerFactory(module, contextProvider);
    }
}
