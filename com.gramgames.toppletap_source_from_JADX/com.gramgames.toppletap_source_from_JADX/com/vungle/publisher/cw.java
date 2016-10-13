package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.env.SdkConfig;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cw implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<ek> c;
    private final Provider<SdkConfig> d;
    private final Provider<LoggedException> e;

    static {
        a = !cw.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.c = (DatabaseHelper) this.b.get();
        factory.a = (ek) this.c.get();
        factory.b = (SdkConfig) this.d.get();
        factory.d = this.e;
    }

    private cw(Provider<DatabaseHelper> provider, Provider<ek> provider2, Provider<SdkConfig> provider3, Provider<LoggedException> provider4) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
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

    public static MembersInjector<Factory> a(Provider<DatabaseHelper> provider, Provider<ek> provider2, Provider<SdkConfig> provider3, Provider<LoggedException> provider4) {
        return new cw(provider, provider2, provider3, provider4);
    }
}
