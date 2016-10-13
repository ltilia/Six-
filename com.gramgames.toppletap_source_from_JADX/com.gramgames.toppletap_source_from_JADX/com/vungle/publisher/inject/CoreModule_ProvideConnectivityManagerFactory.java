package com.vungle.publisher.inject;

import android.content.Context;
import android.net.ConnectivityManager;
import com.vungle.log.Logger;
import com.vungle.publisher.fi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CoreModule_ProvideConnectivityManagerFactory implements Factory<ConnectivityManager> {
    static final /* synthetic */ boolean a;
    private final fi b;
    private final Provider<Context> c;

    static {
        a = !CoreModule_ProvideConnectivityManagerFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideConnectivityManagerFactory(fi module, Provider<Context> contextProvider) {
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

    public final ConnectivityManager get() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ((Context) this.c.get()).getSystemService("connectivity");
        if (connectivityManager == null) {
            Logger.d(Logger.DEVICE_TAG, "ConnectivityManager not available");
        }
        return (ConnectivityManager) Preconditions.checkNotNull(connectivityManager, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ConnectivityManager> create(fi module, Provider<Context> contextProvider) {
        return new CoreModule_ProvideConnectivityManagerFactory(module, contextProvider);
    }
}
