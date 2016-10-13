package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class bo {
    @Inject
    protected ScheduledPriorityExecutor a;

    public abstract Runnable a();

    public b b() {
        return b.otherTask;
    }

    public final void a(long j) {
        this.a.a(a(), b(), j);
    }
}
