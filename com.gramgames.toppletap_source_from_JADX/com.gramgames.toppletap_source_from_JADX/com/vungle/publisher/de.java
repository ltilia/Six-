package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.StreamingAdReportEvent;
import com.vungle.publisher.db.model.StreamingAdReportEvent.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class de implements MembersInjector<StreamingAdReportEvent> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;

    static {
        a = !de.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        StreamingAdReportEvent streamingAdReportEvent = (StreamingAdReportEvent) obj;
        if (streamingAdReportEvent == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        streamingAdReportEvent.t = (DatabaseHelper) this.b.get();
        streamingAdReportEvent.e = (Factory) this.c.get();
    }

    private de(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
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

    public static MembersInjector<StreamingAdReportEvent> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
        return new de(provider, provider2);
    }
}
