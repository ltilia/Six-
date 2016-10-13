package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gq implements MembersInjector<gp> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;
    private final Provider<Factory> c;

    static {
        a = !gq.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        gp gpVar = (gp) obj;
        if (gpVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gpVar.f = (ScheduledPriorityExecutor) this.b.get();
        gpVar.g = (Factory) this.c.get();
    }

    public static void a(gp gpVar, Provider<ScheduledPriorityExecutor> provider) {
        gpVar.f = (ScheduledPriorityExecutor) provider.get();
    }

    public static void b(gp gpVar, Provider<Factory> provider) {
        gpVar.g = (Factory) provider.get();
    }
}
