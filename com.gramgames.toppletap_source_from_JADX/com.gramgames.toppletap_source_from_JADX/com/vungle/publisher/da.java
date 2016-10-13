package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.StreamingAdPlay;
import com.vungle.publisher.db.model.StreamingAdPlay.Factory;
import com.vungle.publisher.db.model.StreamingAdReportEvent;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class da implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<StreamingAdPlay> c;
    private final Provider<StreamingAdReportEvent.Factory> d;

    static {
        a = !da.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.c = (DatabaseHelper) this.b.get();
        factory.a = this.c;
        factory.b = (StreamingAdReportEvent.Factory) this.d.get();
    }

    private da(Provider<DatabaseHelper> provider, Provider<StreamingAdPlay> provider2, Provider<StreamingAdReportEvent.Factory> provider3) {
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

    public static MembersInjector<Factory> a(Provider<DatabaseHelper> provider, Provider<StreamingAdPlay> provider2, Provider<StreamingAdReportEvent.Factory> provider3) {
        return new da(provider, provider2, provider3);
    }
}
