package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import dagger.MembersInjector;
import dagger.internal.DoubleCheckLazy;
import javax.inject.Provider;

/* compiled from: vungle */
public final class bq implements MembersInjector<ScheduledPriorityExecutor> {
    static final /* synthetic */ boolean a;
    private final Provider<Factory> b;

    static {
        a = !bq.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ScheduledPriorityExecutor scheduledPriorityExecutor = (ScheduledPriorityExecutor) obj;
        if (scheduledPriorityExecutor == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        scheduledPriorityExecutor.a = DoubleCheckLazy.create(this.b);
    }

    private bq(Provider<Factory> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<ScheduledPriorityExecutor> a(Provider<Factory> provider) {
        return new bq(provider);
    }
}
