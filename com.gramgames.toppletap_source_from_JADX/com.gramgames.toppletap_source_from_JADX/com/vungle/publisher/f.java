package com.vungle.publisher;

import com.vungle.publisher.ad.AdPreparer;
import com.vungle.publisher.ad.AdPreparer.ViewablePreparationListener;
import com.vungle.publisher.ad.prepare.PrepareViewableRunnable.Factory;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheckLazy;
import javax.inject.Provider;

public final class f implements MembersInjector<ViewablePreparationListener> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;
    private final Provider<ScheduledPriorityExecutor> c;
    private final Provider<Factory> d;
    private final Provider<AdPreparer> e;

    static {
        a = !f.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ViewablePreparationListener viewablePreparationListener = (ViewablePreparationListener) obj;
        if (viewablePreparationListener == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eu.a(viewablePreparationListener, this.b);
        viewablePreparationListener.a = (ScheduledPriorityExecutor) this.c.get();
        viewablePreparationListener.b = (Factory) this.d.get();
        viewablePreparationListener.c = DoubleCheckLazy.create(this.e);
    }

    private f(Provider<EventBus> provider, Provider<ScheduledPriorityExecutor> provider2, Provider<Factory> provider3, Provider<AdPreparer> provider4) {
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

    public static MembersInjector<ViewablePreparationListener> a(Provider<EventBus> provider, Provider<ScheduledPriorityExecutor> provider2, Provider<Factory> provider3, Provider<AdPreparer> provider4) {
        return new f(provider, provider2, provider3, provider4);
    }
}
