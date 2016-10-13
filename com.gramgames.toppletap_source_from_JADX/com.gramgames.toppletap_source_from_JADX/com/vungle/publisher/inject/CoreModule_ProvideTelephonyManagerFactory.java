package com.vungle.publisher.inject;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.vungle.log.Logger;
import com.vungle.publisher.fi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CoreModule_ProvideTelephonyManagerFactory implements Factory<TelephonyManager> {
    static final /* synthetic */ boolean a;
    private final fi b;
    private final Provider<Context> c;

    static {
        a = !CoreModule_ProvideTelephonyManagerFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideTelephonyManagerFactory(fi module, Provider<Context> contextProvider) {
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

    public final TelephonyManager get() {
        TelephonyManager telephonyManager = (TelephonyManager) ((Context) this.c.get()).getSystemService("phone");
        if (telephonyManager == null) {
            Logger.d(Logger.DEVICE_TAG, "TelephonyManager not avaialble");
        }
        return (TelephonyManager) Preconditions.checkNotNull(telephonyManager, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<TelephonyManager> create(fi module, Provider<Context> contextProvider) {
        return new CoreModule_ProvideTelephonyManagerFactory(module, contextProvider);
    }
}
