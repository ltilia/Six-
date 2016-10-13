package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class bp implements MembersInjector<bo> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;

    static {
        a = !bp.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        bo boVar = (bo) obj;
        if (boVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        boVar.a = (ScheduledPriorityExecutor) this.b.get();
    }

    public static void a(bo boVar, Provider<ScheduledPriorityExecutor> provider) {
        boVar.a = (ScheduledPriorityExecutor) provider.get();
    }
}
