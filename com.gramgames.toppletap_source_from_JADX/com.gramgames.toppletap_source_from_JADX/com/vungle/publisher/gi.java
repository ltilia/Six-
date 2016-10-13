package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.net.http.TrackEventHttpTransactionFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gi implements MembersInjector<gh> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;
    private final Provider<Factory> c;
    private final Provider<ScheduledPriorityExecutor> d;
    private final Provider<TrackEventHttpTransactionFactory> e;

    static {
        a = !gi.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        gh ghVar = (gh) obj;
        if (ghVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ghVar.c = (Context) this.b.get();
        ghVar.d = (Factory) this.c.get();
        ghVar.e = (ScheduledPriorityExecutor) this.d.get();
        ghVar.a = (TrackEventHttpTransactionFactory) this.e.get();
        ghVar.b = (ScheduledPriorityExecutor) this.d.get();
    }

    private gi(Provider<Context> provider, Provider<Factory> provider2, Provider<ScheduledPriorityExecutor> provider3, Provider<TrackEventHttpTransactionFactory> provider4) {
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

    public static MembersInjector<gh> a(Provider<Context> provider, Provider<Factory> provider2, Provider<ScheduledPriorityExecutor> provider3, Provider<TrackEventHttpTransactionFactory> provider4) {
        return new gi(provider, provider2, provider3, provider4);
    }
}
