package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gl implements MembersInjector<gk> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;
    private final Provider<Factory> c;
    private final Provider<ScheduledPriorityExecutor> d;

    static {
        a = !gl.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        gk gkVar = (gk) obj;
        if (gkVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gkVar.c = (Context) this.b.get();
        gkVar.d = (Factory) this.c.get();
        gkVar.e = (ScheduledPriorityExecutor) this.d.get();
    }

    public static void a(gk gkVar, Provider<Context> provider) {
        gkVar.c = (Context) provider.get();
    }

    public static void b(gk gkVar, Provider<Factory> provider) {
        gkVar.d = (Factory) provider.get();
    }

    public static void c(gk gkVar, Provider<ScheduledPriorityExecutor> provider) {
        gkVar.e = (ScheduledPriorityExecutor) provider.get();
    }
}
