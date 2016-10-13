package com.vungle.publisher;

import com.vungle.publisher.env.SdkState.AdThrottleEndRunnable;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class eo implements MembersInjector<AdThrottleEndRunnable> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;

    static {
        a = !eo.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AdThrottleEndRunnable adThrottleEndRunnable = (AdThrottleEndRunnable) obj;
        if (adThrottleEndRunnable == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        adThrottleEndRunnable.a = (EventBus) this.b.get();
    }

    private eo(Provider<EventBus> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<AdThrottleEndRunnable> a(Provider<EventBus> provider) {
        return new eo(provider);
    }
}
