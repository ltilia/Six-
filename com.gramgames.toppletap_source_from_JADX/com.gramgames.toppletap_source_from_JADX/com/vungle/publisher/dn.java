package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.device.data.AppFingerprint.Factory;
import com.vungle.publisher.device.data.AppFingerprintManager;
import com.vungle.publisher.env.SdkConfig;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dn implements MembersInjector<AppFingerprintManager> {
    static final /* synthetic */ boolean a;
    private final Provider<SdkConfig> b;
    private final Provider<Factory> c;
    private final Provider<gu> d;
    private final Provider<ScheduledPriorityExecutor> e;
    private final Provider<LoggedException.Factory> f;

    static {
        a = !dn.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AppFingerprintManager appFingerprintManager = (AppFingerprintManager) obj;
        if (appFingerprintManager == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        appFingerprintManager.a = (SdkConfig) this.b.get();
        appFingerprintManager.b = (Factory) this.c.get();
        appFingerprintManager.c = (gu) this.d.get();
        appFingerprintManager.d = (ScheduledPriorityExecutor) this.e.get();
        appFingerprintManager.e = (LoggedException.Factory) this.f.get();
    }

    private dn(Provider<SdkConfig> provider, Provider<Factory> provider2, Provider<gu> provider3, Provider<ScheduledPriorityExecutor> provider4, Provider<LoggedException.Factory> provider5) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        if (a || provider5 != null) {
                            this.f = provider5;
                            return;
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<AppFingerprintManager> a(Provider<SdkConfig> provider, Provider<Factory> provider2, Provider<gu> provider3, Provider<ScheduledPriorityExecutor> provider4, Provider<LoggedException.Factory> provider5) {
        return new dn(provider, provider2, provider3, provider4, provider5);
    }
}
