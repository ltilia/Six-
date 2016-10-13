package com.vungle.publisher.async;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ScheduledPriorityExecutor_Factory implements Factory<ScheduledPriorityExecutor> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ScheduledPriorityExecutor> b;

    static {
        a = !ScheduledPriorityExecutor_Factory.class.desiredAssertionStatus();
    }

    public ScheduledPriorityExecutor_Factory(MembersInjector<ScheduledPriorityExecutor> scheduledPriorityExecutorMembersInjector) {
        if (a || scheduledPriorityExecutorMembersInjector != null) {
            this.b = scheduledPriorityExecutorMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ScheduledPriorityExecutor get() {
        return (ScheduledPriorityExecutor) MembersInjectors.injectMembers(this.b, new ScheduledPriorityExecutor());
    }

    public static Factory<ScheduledPriorityExecutor> create(MembersInjector<ScheduledPriorityExecutor> scheduledPriorityExecutorMembersInjector) {
        return new ScheduledPriorityExecutor_Factory(scheduledPriorityExecutorMembersInjector);
    }
}
