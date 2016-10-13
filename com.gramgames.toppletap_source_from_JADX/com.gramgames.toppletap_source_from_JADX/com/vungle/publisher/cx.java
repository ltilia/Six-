package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.db.model.LoggedException.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cx implements MembersInjector<LoggedException> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;

    static {
        a = !cx.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        LoggedException loggedException = (LoggedException) obj;
        if (loggedException == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        loggedException.t = (DatabaseHelper) this.b.get();
        loggedException.j = (Factory) this.c.get();
    }

    private cx(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<LoggedException> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
        return new cx(provider, provider2);
    }
}
