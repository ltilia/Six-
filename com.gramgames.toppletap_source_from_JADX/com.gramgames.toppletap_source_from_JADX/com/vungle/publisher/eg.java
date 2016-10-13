package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.env.AdvertisingDeviceIdStrategy;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class eg implements MembersInjector<AdvertisingDeviceIdStrategy> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;
    private final Provider<EventBus> c;
    private final Provider<ScheduledPriorityExecutor> d;

    static {
        a = !eg.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AdvertisingDeviceIdStrategy advertisingDeviceIdStrategy = (AdvertisingDeviceIdStrategy) obj;
        if (advertisingDeviceIdStrategy == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        advertisingDeviceIdStrategy.c = (Context) this.b.get();
        advertisingDeviceIdStrategy.d = (EventBus) this.c.get();
        advertisingDeviceIdStrategy.e = (ScheduledPriorityExecutor) this.d.get();
    }

    private eg(Provider<Context> provider, Provider<EventBus> provider2, Provider<ScheduledPriorityExecutor> provider3) {
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

    public static MembersInjector<AdvertisingDeviceIdStrategy> a(Provider<Context> provider, Provider<EventBus> provider2, Provider<ScheduledPriorityExecutor> provider3) {
        return new eg(provider, provider2, provider3);
    }
}
