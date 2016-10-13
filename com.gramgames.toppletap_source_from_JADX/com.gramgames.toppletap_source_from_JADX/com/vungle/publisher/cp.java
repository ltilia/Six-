package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.LocalArchive;
import com.vungle.publisher.db.model.LocalArchive.Factory;
import com.vungle.publisher.db.model.LocalViewableDelegate;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cp implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<LocalArchive> c;
    private final Provider<LocalViewableDelegate.Factory> d;

    static {
        a = !cp.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.c = (DatabaseHelper) this.b.get();
        factory.a = this.c;
        factory.b = (LocalViewableDelegate.Factory) this.d.get();
    }

    private cp(Provider<DatabaseHelper> provider, Provider<LocalArchive> provider2, Provider<LocalViewableDelegate.Factory> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<DatabaseHelper> provider, Provider<LocalArchive> provider2, Provider<LocalViewableDelegate.Factory> provider3) {
        return new cp(provider, provider2, provider3);
    }
}
