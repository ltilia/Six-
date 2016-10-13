package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.StreamingAdPlay;
import com.vungle.publisher.db.model.StreamingAdPlay.Factory;
import com.vungle.publisher.db.model.StreamingAdReportEvent;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class db implements MembersInjector<StreamingAdPlay> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;
    private final Provider<StreamingAdReportEvent.Factory> d;

    static {
        a = !db.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        StreamingAdPlay streamingAdPlay = (StreamingAdPlay) obj;
        if (streamingAdPlay == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        streamingAdPlay.t = (DatabaseHelper) this.b.get();
        streamingAdPlay.e = (Factory) this.c.get();
        streamingAdPlay.f = (StreamingAdReportEvent.Factory) this.d.get();
    }

    private db(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<StreamingAdReportEvent.Factory> provider3) {
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

    public static MembersInjector<StreamingAdPlay> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<StreamingAdReportEvent.Factory> provider3) {
        return new db(provider, provider2, provider3);
    }
}
