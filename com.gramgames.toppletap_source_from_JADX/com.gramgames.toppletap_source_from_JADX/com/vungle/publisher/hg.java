package com.vungle.publisher;

import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.protocol.ProtocolHttpGateway.PrepareLocalAdEventListener;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hg implements MembersInjector<PrepareLocalAdEventListener> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;
    private final Provider<ProtocolHttpGateway> c;

    static {
        a = !hg.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        PrepareLocalAdEventListener prepareLocalAdEventListener = (PrepareLocalAdEventListener) obj;
        if (prepareLocalAdEventListener == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eu.a(prepareLocalAdEventListener, this.b);
        prepareLocalAdEventListener.a = this.c;
    }

    private hg(Provider<EventBus> provider, Provider<ProtocolHttpGateway> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<PrepareLocalAdEventListener> a(Provider<EventBus> provider, Provider<ProtocolHttpGateway> provider2) {
        return new hg(provider, provider2);
    }
}
