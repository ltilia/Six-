package com.vungle.publisher;

import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.protocol.RequestConfigAsync.RequestConfigRunnable;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ho implements MembersInjector<RequestConfigRunnable> {
    static final /* synthetic */ boolean a;
    private final Provider<ProtocolHttpGateway> b;

    static {
        a = !ho.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        RequestConfigRunnable requestConfigRunnable = (RequestConfigRunnable) obj;
        if (requestConfigRunnable == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        requestConfigRunnable.a = (ProtocolHttpGateway) this.b.get();
    }

    private ho(Provider<ProtocolHttpGateway> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<RequestConfigRunnable> a(Provider<ProtocolHttpGateway> provider) {
        return new ho(provider);
    }
}
