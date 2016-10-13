package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.file.CacheManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class bu implements MembersInjector<DatabaseHelper> {
    static final /* synthetic */ boolean a;
    private final Provider<CacheManager> b;
    private final Provider<EventBus> c;
    private final Provider<em> d;
    private final Provider<ScheduledPriorityExecutor> e;

    static {
        a = !bu.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        DatabaseHelper databaseHelper = (DatabaseHelper) obj;
        if (databaseHelper == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        databaseHelper.a = (CacheManager) this.b.get();
        databaseHelper.b = (EventBus) this.c.get();
        databaseHelper.c = (em) this.d.get();
        databaseHelper.d = (ScheduledPriorityExecutor) this.e.get();
    }

    private bu(Provider<CacheManager> provider, Provider<EventBus> provider2, Provider<em> provider3, Provider<ScheduledPriorityExecutor> provider4) {
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

    public static MembersInjector<DatabaseHelper> a(Provider<CacheManager> provider, Provider<EventBus> provider2, Provider<em> provider3, Provider<ScheduledPriorityExecutor> provider4) {
        return new bu(provider, provider2, provider3, provider4);
    }
}
