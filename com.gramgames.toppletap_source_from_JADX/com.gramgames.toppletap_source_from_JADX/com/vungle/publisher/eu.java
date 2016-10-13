package com.vungle.publisher;

import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class eu implements MembersInjector<et> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;

    static {
        a = !eu.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        et etVar = (et) obj;
        if (etVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        etVar.h = (EventBus) this.b.get();
    }

    public static void a(et etVar, Provider<EventBus> provider) {
        etVar.h = (EventBus) provider.get();
    }
}
