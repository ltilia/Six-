package com.vungle.publisher;

import com.vungle.publisher.ad.AdPreparer;
import com.vungle.publisher.ad.AdPreparer.ViewablePreparationListener;
import com.vungle.publisher.ad.prepare.PrepareAdRunnable.Factory;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class g implements MembersInjector<AdPreparer> {
    static final /* synthetic */ boolean a;
    private final Provider<Factory> b;
    private final Provider<ViewablePreparationListener> c;
    private final Provider<ScheduledPriorityExecutor> d;

    static {
        a = !g.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AdPreparer adPreparer = (AdPreparer) obj;
        if (adPreparer == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        adPreparer.a = (Factory) this.b.get();
        adPreparer.b = (ViewablePreparationListener) this.c.get();
        adPreparer.c = (ScheduledPriorityExecutor) this.d.get();
    }

    private g(Provider<Factory> provider, Provider<ViewablePreparationListener> provider2, Provider<ScheduledPriorityExecutor> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<AdPreparer> a(Provider<Factory> provider, Provider<ViewablePreparationListener> provider2, Provider<ScheduledPriorityExecutor> provider3) {
        return new g(provider, provider2, provider3);
    }
}
