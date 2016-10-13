package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.ArchiveEntry;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalArchive;
import com.vungle.publisher.db.model.LocalArchive.Factory;
import com.vungle.publisher.db.model.LocalViewableDelegate;
import com.vungle.publisher.db.model.LoggedException;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cq implements MembersInjector<LocalArchive> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;
    private final Provider<LoggedException.Factory> d;
    private final Provider<ArchiveEntry.Factory> e;
    private final Provider<LocalAd.Factory> f;
    private final Provider<LocalViewableDelegate> g;

    static {
        a = !cq.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        LocalArchive localArchive = (LocalArchive) obj;
        if (localArchive == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        localArchive.t = (DatabaseHelper) this.b.get();
        localArchive.e = (Factory) this.c.get();
        localArchive.f = (LoggedException.Factory) this.d.get();
        localArchive.g = (ArchiveEntry.Factory) this.e.get();
        localArchive.h = (LocalAd.Factory) this.f.get();
        localArchive.i = (LocalViewableDelegate) this.g.get();
    }

    private cq(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<LoggedException.Factory> provider3, Provider<ArchiveEntry.Factory> provider4, Provider<LocalAd.Factory> provider5, Provider<LocalViewableDelegate> provider6) {
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
                            if (a || provider6 != null) {
                                this.g = provider6;
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
        throw new AssertionError();
    }

    public static MembersInjector<LocalArchive> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<LoggedException.Factory> provider3, Provider<ArchiveEntry.Factory> provider4, Provider<LocalAd.Factory> provider5, Provider<LocalViewableDelegate> provider6) {
        return new cq(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
